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

@WebServlet("/reparation/recherche")
public class RechercheOrdi  extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = new String("/web/pages/reparation/rechercherOrdi.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            TypeComposant [] tps = new TypeComposant().lister(co);
            request.setAttribute("tps", tps);
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
        String idTypeComposant = request.getParameter("idTypeComposant");
        String url = new String("/web/pages/reparation/rechercherOrdi.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            TypeComposant [] tps = new TypeComposant().lister(co);
            request.setAttribute("tps", tps);
            Reparation [] reparations = new Reparation().getByCriteria(co, idTypeComposant);
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
