package src.servlets.composants;


import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.models.materiel.Categorie;
import src.models.materiel.Composant;
import src.models.materiel.MarqueOrdi;
import src.models.materiel.Ordinateur;
import src.models.materiel.TypeComposant;
import src.models.processus.Recommandations;
import src.models.processus.Reparation;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/composants/recommandes")
public class SaisieRecommandes extends HttpServlet  {
    

 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/composants/saisie.jsp";
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant[] composants = new Composant().lister(co);
            request.setAttribute("composants", composants);
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String periode = request.getParameter("periode");
        String [] idcomposants = request.getParameterValues("composantsRemplaces[]");

        String message = new String();
        String url = new String("/web/pages/composants/saisie.jsp");
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Recommandations recommandation = new Recommandations(idcomposants, periode);
            recommandation.enregistrer(co);
            
            Composant[] composants = new Composant().lister(co);
            request.setAttribute("composants", composants);
            message = "Opération effectuée avec succès";
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
        }
        finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {
                    message = e.getMessage();
                    e.printStackTrace();
                }
            }
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
