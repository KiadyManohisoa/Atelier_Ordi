package src.servlets.reparation;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.processus.Reparation;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/reparation/retourner")
public class ValidationRetour extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idReparation = request.getParameter("idReparation");

        String url = new String("/web/pages/reparation/encours.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Reparation reparation = new Reparation().obtenirParId(co, idReparation);
            reparation.mettreAjour(co);
            message = "Réparation "+idReparation+" retournée avec succès";   

            Reparation [] reparations = new Reparation().listerParAvancement(co, 0);
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
