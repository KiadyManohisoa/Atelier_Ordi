package src.servlets.composants;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.composants.HistoriqueComposant;
import src.models.materiel.Composant;
import src.models.materiel.MarqueComposant;
import src.models.materiel.TypeComposant;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/composants/historique/vente")
public class HistoriquePrix extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/composants/historique.jsp";
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant[] cps = new Composant().lister(co);
            request.setAttribute("cps", cps);
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
        String idComposant = request.getParameter("idComposant");
        String message = new String();
        String url = "/web/pages/composants/historique.jsp";
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant cmp = new Composant(idComposant);
            Composant thecmp = cmp.getById(co);
            HistoriqueComposant[] histos = thecmp.getHistoriqueComposants(co);
            request.setAttribute("histos", histos);
            request.setAttribute("thecmp", thecmp);

            Composant[] cps = new Composant().lister(co);
            request.setAttribute("cps", cps);
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
