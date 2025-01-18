package src.servlets.reparation;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.models.materiel.Ordinateur;
import src.models.materiel.TypeComposant;
import src.models.processus.Reparation;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/reparation/recherche/client")
public class RechercheClient  extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = new String("/web/pages/reparation/rechercheClient.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("dateRecherche");
        String url = new String("/web/pages/reparation/rechercheClient.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Reparation [] reparations = new Reparation().getReparationsParDate(co, date);
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
