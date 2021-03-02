package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Semester;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet (name = "SemesterController",urlPatterns = "/semester-selection")
public class SemesterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Semester> allSemesters= DBManager.getAllActiveSemesters();
        req.setAttribute("allSemesters",allSemesters);

        String semesterId=req.getParameter("semestrik");
        if (semesterId==null){
            semesterId=allSemesters.get(0).getSemId()+"";
        }
        req.setAttribute("selected",semesterId);

        ArrayList<Discipline> disciplineOfSem=DBManager.getDisciplinesFromSemester(semesterId);
        req.setAttribute("disciplineOfSem",disciplineOfSem);

        req.getRequestDispatcher("WEB-INF/jsp/semester-selection.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("deleteSemIdHidden");
        DBManager.deleteSemester(id);

        resp.sendRedirect("/semester-selection");
    }
}
