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

@WebServlet (name = "SemesterCreatingController",urlPatterns = "/semester-creating")
public class SemesterCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isCreated","1");
        ArrayList<Discipline> allDisciplines= DBManager.getAllActiveDisciplines();
        req.setAttribute("allDisciplines",allDisciplines);

        req.getRequestDispatcher("WEB-INF/jsp/semester-creating.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] discArrId=req.getParameterValues("all-disciplines[]");
        String semDuration=req.getParameter("sem-duration");
        ArrayList<Semester> newSemId=DBManager.gettingIdForNewSemester();
        int creatingId=(newSemId.get(newSemId.size()-1).getSemId()+1);
        DBManager.createNewSemester(semDuration,creatingId);
        for (int i = 0; i <discArrId.length ; i++) {
            DBManager.putDisciplinesOfNewSemester(creatingId,discArrId[i]);
        }
       //"+(newSemId.get(newSemId.size()-1).getSemId()+1)+"
        resp.sendRedirect("/semester-selection");
    }
}
