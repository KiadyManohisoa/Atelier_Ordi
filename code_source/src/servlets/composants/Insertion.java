package src.servlets.composants;


import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.materiel.Composant;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/composants/insertion")
public class Insertion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/composants/insertion.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
