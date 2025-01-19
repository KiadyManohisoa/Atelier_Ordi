package src.servlets.composants;


import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.models.materiel.Composant;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;
import src.models.materiel.MarqueComposant;
import src.models.materiel.TypeComposant;

@WebServlet("/composants/etatstock")
public class EtatStock extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = new String("/web/pages/composants/liste.jsp");
        String message = new String();
        if(request.getParameter("message")!=null && !request.getParameter("message").isEmpty()) {
            message = request.getParameter("message");
        }

        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            TypeComposant [] tps = new TypeComposant().lister(co);
            MarqueComposant [] marques = new MarqueComposant().lister(co);
            Composant[] cps = new Composant().lister(co);

            request.setAttribute("tps", tps);
            request.setAttribute("marques", marques);
            request.setAttribute("cps", cps);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idTypeComposant = request.getParameter("idTypeComposant");
        String idMarqueComposant = request.getParameter("idMarqueComposant");

        String url = new String("/web/pages/composants/liste.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant [] cps = new Composant().listerParCritere(co, idMarqueComposant, idTypeComposant);
            TypeComposant [] tps = new TypeComposant().lister(co);
            MarqueComposant [] marques = new MarqueComposant().lister(co);

            request.setAttribute("tps", tps);
            request.setAttribute("marques", marques);
            request.setAttribute("cps", cps);
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
