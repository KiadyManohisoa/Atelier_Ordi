package src.servlets.composants;


import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.models.materiel.Categorie;
import src.models.materiel.Composant;
import src.models.materiel.TypeComposant;
import src.models.processus.Reparation;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;
import src.models.materiel.MarqueComposant;
import src.models.materiel.MarqueOrdi;
import src.models.materiel.Ordinateur;

@WebServlet("/composants/insertion")
public class Insertion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/composants/insertion.jsp";
        String message = new String();
        if(request.getParameter("message")!=null && !request.getParameter("message").isEmpty()) {
            message = request.getParameter("message");
        }
        
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            TypeComposant [] tps = new TypeComposant().lister(co);
            MarqueComposant[] marques = new MarqueComposant().lister(co);
            Composant[] composants = new Composant().lister(co);
            request.setAttribute("tps", tps);
            request.setAttribute("marques", marques);
            request.setAttribute("composants",composants);
        } catch (Exception e) {
            message = e.getMessage();
            // e.printStackTrace();
        } finally {
            if (co != null) {
                try {
                    co.close();
                } catch (Exception e) {
                    message = e.getMessage();
                    // e.printStackTrace();
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
        String idMarqueComposant = request.getParameter("idMarqueComposant");
        String nomModele = request.getParameter("nomModele");
        String description = request.getParameter("description");
        String pv = request.getParameter("prixVente");


        String message = new String();
        String url = "/web/pages/composants/insertion.jsp";
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant cmp = new Composant(nomModele, description, idMarqueComposant, idTypeComposant, pv);
            cmp.enregistrer(co);

            TypeComposant [] tps = new TypeComposant().lister(co);
            MarqueComposant[] marques = new MarqueComposant().lister(co);
            Composant[] composants = new Composant().lister(co);
            request.setAttribute("tps", tps);
            request.setAttribute("marques", marques);
            request.setAttribute("composants",composants);

            
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
