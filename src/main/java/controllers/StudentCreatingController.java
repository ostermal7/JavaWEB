package controllers;

import database.DBManager;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet (name = "StudentCreatingController",urlPatterns = "/student-creating")
public class StudentCreatingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isCreated","1");
        req.getRequestDispatcher("WEB-INF/jsp/student-creating.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newSername=req.getParameter("newSername");
        String newName=req.getParameter("newName");
        String newGroup=req.getParameter("newGroup");
        String newDate= req.getParameter("newDate");
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = format.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString= formatter.format(date);
        if (newSername==null||newSername.equals("")||newName==null||newName.equals("")
                ||newGroup==null||newGroup.equals("")||newDate==null||newDate.equals("")){
            req.setAttribute("message","1");
            req.setAttribute("isCreated","1");
            req.getRequestDispatcher("WEB-INF/jsp/student-creating.jsp").forward(req,resp);
            return;
        }
        DBManager.createNewStudent(newSername,newName,newGroup,dateString);
        resp.sendRedirect("/students");
    }
}
