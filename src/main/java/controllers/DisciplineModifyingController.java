package controllers;

import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "DisciplineModifyingController",urlPatterns = "/discipline-modifying")
public class DisciplineModifyingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("modifyingDiscHidden");
        Discipline disc= DBManager.getDisciplineById(id);
        req.setAttribute("modifDiscipline",disc);

        req.getRequestDispatcher("WEB-INF/jsp/discipline-creating.jsp").forward(req,resp);
          
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String disc=req.getParameter("newDisc");
        String modifId=req.getParameter("idDisc");

        DBManager.modifyDiscipline(modifId,disc);
        resp.sendRedirect("/disciplines");
    }
}
