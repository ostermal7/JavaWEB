package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name ="DisciplineCreatingController",urlPatterns = "/discipline-creating")
public class DisciplineCreatingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("isCreated","1");
        req.getRequestDispatcher("WEB-INF/jsp/discipline-creating.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String someDisc=req.getParameter("newDisc");
        if (someDisc==null || someDisc.equals("")){
            req.setAttribute("message","1");
            req.setAttribute("isCreated","1");
            req.getRequestDispatcher("WEB-INF/jsp/discipline-creating.jsp").forward(req,resp);
            return;
        }

        DBManager.createNewDiscipline(someDisc);
        resp.sendRedirect("/disciplines");
    }
}
