package src.servlets.reparation;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import src.models.clients.Client;
import src.models.processus.Reparation;
import src.services.UtilDB;

@WebServlet("/reparation/encours")
public class EnCours extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = new String("/web/pages/reparation/encours.jsp");
        String message = new String();
        if(request.getParameter("message")!=null && !request.getParameter("message").isEmpty()) {
            message = request.getParameter("message");   
        }
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Reparation [] reparations = new Reparation().listerOrdinateursEnCours(co);
            request.setAttribute("reparations", reparations);
        } catch (Exception e) {
            message = e.getMessage();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {
                    message = e.getMessage();
                }
            }
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

}
