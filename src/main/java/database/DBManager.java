package database;

import constants.Constants;
import entity.*;

import java.sql.*;
import java.util.ArrayList;

public class DBManager {
    private static Connection conn;
    private static PreparedStatement createNewDiscipline;
    private static PreparedStatement deleteDiscipline;
    private static PreparedStatement modifyDiscipline;

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(Constants.PATH_TO_DATABASE);

            createNewDiscipline=conn.prepareStatement("INSERT INTO `discipline` (`disciplines`) VALUES (?);");
            deleteDiscipline=conn.prepareStatement("UPDATE `discipline` SET `status` = '0' WHERE (`id` =?);");
            modifyDiscipline=conn.prepareStatement("UPDATE `discipline` SET `disciplines` = ? WHERE (`id` = ?);");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void createNewDiscipline(String disc){
        try{
            createNewDiscipline.setString(1,disc);
            createNewDiscipline.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteDiscipline(String id){
        id=id.replaceAll("'","");
        try{
            deleteDiscipline.setString(1,id);
            deleteDiscipline.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void modifyDiscipline(String id,String discipline){
        id=id.replaceAll("'","");
        try{
            modifyDiscipline.setString(1,discipline);
            modifyDiscipline.setString(2,id);
            modifyDiscipline.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Discipline> getAllActiveDisciplines(){
        ArrayList<Discipline> arr=new ArrayList<Discipline>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM discipline where status='1';");
            while (rs.next()){
                Discipline discipline=new Discipline();
                discipline.setDiscId(rs.getInt("id"));
                discipline.setDiscName(rs.getString("disciplines"));
                arr.add(discipline);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arr;
    }
    public static Discipline getDisciplineById(String id){
        Discipline discipline=new Discipline();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM discipline where status='1' and id="+id+";");
            while (rs.next()){
                discipline.setDiscId(rs.getInt("id"));
                discipline.setDiscName(rs.getString("disciplines"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return discipline;
    }
    public static ArrayList<Student> getAllActiveStudents(){
        ArrayList<Student> studentArr=new ArrayList<Student>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM student where status='1';");
            while (rs.next()){
                Student student=new Student();
                student.setId(rs.getInt("id"));
                student.setSername(rs.getString("sername"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("dateofadmission"));
                studentArr.add(student);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return studentArr;
    }
    public static void createNewStudent(String sername, String name, String group, String date){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO `student` (`sername`, `name`, `group`, `dateofadmission`) VALUES ('"+sername+"', '"+name+"', '"+group+"', '"+date+"');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteStudent(String id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = "+id+");");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static Student getStudentById(String id){
        Student student=new Student();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM student where status='1' and id="+id+";");
            while (rs.next()){
                student.setId(rs.getInt("id"));
                student.setSername(rs.getString("sername"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("group"));
                student.setDate(rs.getDate("dateofadmission"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }
    public static void modifyStudent(String id,String sername,String name,String group,Date date){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("UPDATE `student` SET `sername` = '"+sername+"', `name` = '"+name+"', `group` = '"+group+"', `dateofadmission` = '"+date+"' WHERE (`id` = '"+id+"');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<StudentDiscAndMark> getDisciplinesAndMarksFromSemester(String studentId, String semesterId){
        ArrayList<StudentDiscAndMark> discAndMarks=new ArrayList<StudentDiscAndMark>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT sd.id as sem_dis_id, sd.id_discipline, d.disciplines, d.status as disc_status, sd.id_semestr as semester_id,sem.name as semester_name, sem.status as sem_status, st.id as student_id, st.status as student_status, m.mark FROM mark as m\n" +
                    "left join student as st on m.id_student=st.id\n" +
                    "left join semestr_discipline as sd on m.id_semestr_discipline=sd.id\n" +
                    "left join semestr as sem on sd.id_semestr=sem.id\n" +
                    "left join discipline as d on sd.id_discipline=d.id\n" +
                    "where sd.id_semestr = "+semesterId+" and st.status= '1' and sem.status='1' and d.status='1' and st.id="+studentId+"");
            while (rs.next()){
                StudentDiscAndMark sdm=new StudentDiscAndMark();
                sdm.setDisciplineName(rs.getString("disciplines"));
                sdm.setDisciplineId(rs.getInt("id_discipline"));
                sdm.setMark(rs.getInt("mark"));
                discAndMarks.add(sdm);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return discAndMarks;
    }

    public static ArrayList<Semester> getAllActiveSemesters(){
        ArrayList<Semester> arr=new ArrayList<Semester>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM semestr where status='1';");
            while (rs.next()){
                Semester semester=new Semester();
                semester.setSemId(rs.getInt("id"));
                semester.setSemName(rs.getString("name"));
                arr.add(semester);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return arr;
    }

    public static ArrayList<Discipline> getDisciplinesFromSemester(String semesterId){
        ArrayList<Discipline> discOfSemester= new ArrayList<Discipline>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT disc.id as id_discipline, disc.disciplines as disciplines, disc.status as discipline_status, s.id as semestr_id, s.name as sem_name, s.duration, s.status as sem_status FROM students_15.semestr_discipline as sd\n" +
                    "left join discipline as disc on sd.id_discipline=disc.id\n" +
                    "left join semestr as s on sd.id_semestr=s.id\n" +
                    "where s.id="+semesterId+" and s.status='1'");
            while (rs.next()){
                Discipline discipline=new Discipline();
                discipline.setDiscName(rs.getString("disciplines"));
                discipline.setDiscId(rs.getInt("id_discipline"));
                discOfSemester.add(discipline);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return discOfSemester;
    }
    public static ArrayList<Semester> gettingIdForNewSemester(){
        ArrayList<Semester> gettingId=new ArrayList<Semester>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT id FROM semestr;");
            while (rs.next()){
                Semester semester=new Semester();
                semester.setSemId(rs.getInt("id"));
                gettingId.add(semester);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gettingId;
    }
    public static void createNewSemester(String duration,int creatingId){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO `semestr` (`name`, `duration`, `status`) VALUES ('Семестр "+creatingId+"', '"+duration+"', '1');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteSemester(String id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("UPDATE `semestr` SET `status` = '0' WHERE (`id` = "+id+");");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void putDisciplinesOfNewSemester(int creatingId,String discId){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("INSERT INTO `semestr_discipline` (`id_semestr`, `id_discipline`) VALUES ('"+creatingId+"', '"+discId+"');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Semester getSemesterById(String id){
        Semester semester=new Semester();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM semestr where status='1' and id="+id+";");
            while (rs.next()){
                semester.setDuration(rs.getString("duration"));
                semester.setSemName(rs.getString("name"));
                semester.setSemId(rs.getInt("id"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return semester;
    }
    public static boolean isExistUser(String login,String password,String role){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where u.login = '"+login+"' and u.password = '"+password+"' and ur.id_role ="+role);
            while (rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void deleteOldDisciplines(String id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
                stmt.execute("DELETE FROM `semestr_discipline` WHERE (`id_semestr` = '" + id + "');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void setSemesterDuration(String id,String duration){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection(Constants.PATH_TO_DATABASE);
            Statement stmt=conn.createStatement();
            stmt.execute("UPDATE `semestr` SET `duration` = '"+duration+"' WHERE (`id` = '"+id+"');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}

/*

SELECT disc.id as id_discipline, disc.disciplines as disciplines, disc.status as discipline_status, s.id as semestr_id, s.name as sem_name, s.duration, s.status as sem_status FROM students_15.semestr_discipline as sd
left join discipline as disc on sd.id_discipline=disc.id
left join semestr as s on sd.id_semestr=s.id
where s.id="+semesterId+" and s.status='1'


SELECT sd.id as sem_dis_id, sd.id_discipline, d.disciplines, d.status as disc_status, sd.id_semestr as semester_id,sem.name as semester_name, sem.status as sem_status, st.id as student_id, st.status as student_status, m.mark FROM mark as m
left join student as st on m.id_student=st.id
left join semestr_discipline as sd on m.id_semestr_discipline=sd.id
left join semestr as sem on sd.id_semestr=sem.id
left join discipline as d on sd.id_discipline=d.id
where sd.id_semestr = '1' and st.status= '1' and sem.status='1' and d.status='1' and st.id='2'
 */
