package src.servlets.composants;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.models.materiel.Categorie;
import src.models.materiel.MarqueOrdi;
import src.models.materiel.Ordinateur;
import src.models.materiel.TypeComposant;
import src.models.processus.Recommandations;
import src.models.processus.Reparation;
import src.models.util.Periode;
import src.models.materiel.ComposantDuMois;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/composants/recommandes/recherche")
public class RechercheRecommandes extends HttpServlet  {
    

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/composants/recherche.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String periode = request.getParameter("periode");
        String url = new String("/web/pages/composants/recherche.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            List<ComposantDuMois> cdm = new Recommandations().getComposantDuMois(co, new Periode(periode));
            request.setAttribute("cdm", cdm);
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
        dispatcher.forward(request, response);    }
}
