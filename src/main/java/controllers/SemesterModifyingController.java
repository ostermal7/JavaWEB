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

@WebServlet(name = "SemesterModifyingController",urlPatterns = "/semester-modifying")
public class SemesterModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("modifySemesterIdHidden");
        System.out.println(id+"id of semester in doget");
        Semester modifSemester=DBManager.getSemesterById(id);
        req.setAttribute("modifSemester",modifSemester);
        ArrayList<Discipline> allDisciplines= DBManager.getAllActiveDisciplines();
        req.setAttribute("allDisciplines",allDisciplines);
        req.getRequestDispatcher("WEB-INF/jsp/semester-creating.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] discArrId=req.getParameterValues("all-disciplines[]");
        String semesterId=req.getParameter("modifySemesterIdHidden");
        System.out.println(semesterId+"sem");
        String semDuration=req.getParameter("sem-duration");
        DBManager.deleteOldDisciplines(semesterId);
        for (int i = 0; i <discArrId.length ; i++) {
            DBManager.putDisciplinesOfNewSemester(Integer.parseInt(semesterId),discArrId[i]);
        }
        DBManager.setSemesterDuration(semesterId,semDuration);
        resp.sendRedirect("/semester-selection");
    }
}
