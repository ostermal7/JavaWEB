package controllers;

import database.DBManager;
import entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet (name = "StudentProgressController",urlPatterns = "/student-progress")
public class StudentProgressController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("progressStudentHidden");
        Student studForProg= DBManager.getStudentById(id);
        req.setAttribute("studForProg",studForProg);

        ArrayList<Semester> allSems= DBManager.getAllActiveSemesters();
        req.setAttribute("allSems",allSems);

        String semId=req.getParameter("semesterForId");
        if (semId==null){
            semId=allSems.get(0).getSemId()+"";
        }
        req.setAttribute("selected",semId);

        ArrayList<StudentDiscAndMark> studDiscAndMarks=DBManager.getDisciplinesAndMarksFromSemester(id,semId);
        req.setAttribute("studDiscAndMarks",studDiscAndMarks);
        
        req.getRequestDispatcher("WEB-INF/jsp/student-progress.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("chooseStudIdHidden");

        Student studForProg= DBManager.getStudentById(id);
        req.setAttribute("studForProg",studForProg);

        String semId=req.getParameter("semesterForId");
        req.setAttribute("selected",semId);

        ArrayList<StudentDiscAndMark> studDiscAndMarks=DBManager.getDisciplinesAndMarksFromSemester(id,semId);
        req.setAttribute("studDiscAndMarks",studDiscAndMarks);

        ArrayList<Semester> allSems= DBManager.getAllActiveSemesters();
        req.setAttribute("allSems",allSems);


        req.getRequestDispatcher("WEB-INF/jsp/student-progress.jsp").forward(req,resp);
    }
}
