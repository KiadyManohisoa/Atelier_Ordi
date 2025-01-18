package src.servlets.facture;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import src.models.materiel.TypeComposant;
import src.models.processus.Reparation;
import src.models.materiel.Categorie;
import src.services.UtilDB;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/facture/recherche")
public class Recherche extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = new String("/web/pages/facture/recherche.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            TypeComposant [] tps = new TypeComposant().lister(co);
            Categorie [] cats = new Categorie().lister(co);
            request.setAttribute("tps", tps);
            request.setAttribute("cats", cats);
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
        } finally {
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
        String idTypeComposant = request.getParameter("idTypeComposant");
        String idCategorie = request.getParameter("idCategorie");
        String url = new String("/web/pages/facture/recherche.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Reparation [] reparations = new Reparation().getByCriteria(co, idCategorie, idTypeComposant);
            request.setAttribute("reparations", reparations);

            TypeComposant [] tps = new TypeComposant().lister(co);
            Categorie [] cats = new Categorie().lister(co);
            request.setAttribute("tps", tps);
            request.setAttribute("cats", cats);
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
        } finally {
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
