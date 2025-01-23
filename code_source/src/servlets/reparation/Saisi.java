package src.servlets.reparation;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.models.materiel.Categorie;
import src.models.materiel.MarqueOrdi;
import src.models.materiel.Ordinateur;
import src.models.materiel.TypeComposant;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;
import src.models.processus.Reparation;
import src.models.processus.Technicien;

@WebServlet("/reparation/saisie")
public class Saisi extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/reparation/saisie.jsp";
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Client[] clts = new Client().lister(co);
            MarqueOrdi[] marques = new MarqueOrdi().lister(co);
            TypeComposant[] tps = new TypeComposant().lister(co);
            Categorie[] cats = new Categorie().lister(co);
            Technicien [] techs = new Technicien().lister(co);
            request.setAttribute("clts", clts);
            request.setAttribute("marques", marques);
            request.setAttribute("tps", tps);
            request.setAttribute("cats", cats);
            request.setAttribute("techs", techs);
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
        //informations de base
        String dateReception = request.getParameter("dateReception");
        String idClient = request.getParameter("idClient");
        String idMarque = request.getParameter("idMarque");
        String idCategorie = request.getParameter("idCategorie");
        String nomModele = request.getParameter("nomModele");
        String numeroSerie = request.getParameter("numeroSerie");
        String anneeSortie = request.getParameter("anneeSortie");
        String idTechnicien = request.getParameter("idTechnicien");

        //liste des pannes 
        String [] idTypeComposantsEnPannes = request.getParameterValues("typeComposant[]");
        String [] descriptionsPannes = request.getParameterValues("descriptionPanne[]");

        //liste des composants à remplacer 
        String [] idTypeComposantAremplacer = request.getParameterValues("composantsRemplaces[]");

        String message = new String();
        String url = new String("/web/pages/reparation/saisie.jsp");
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Reparation reparation = new Reparation(new Client(idClient), new Ordinateur(idMarque, idCategorie, nomModele, numeroSerie, anneeSortie), dateReception, idTypeComposantsEnPannes, descriptionsPannes, idTypeComposantAremplacer, new Technicien(idTechnicien));
            reparation.enregistrer(co);

            Client[] clts = new Client().lister(co);
            MarqueOrdi[] marques = new MarqueOrdi().lister(co);
            TypeComposant[] tps = new TypeComposant().lister(co);
            Categorie[] cats = new Categorie().lister(co);
            request.setAttribute("clts", clts);
            request.setAttribute("marques", marques);
            request.setAttribute("tps", tps);
            request.setAttribute("cats", cats);
            
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
