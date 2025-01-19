package src.servlets.composants;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.composants.StockComposant;
import src.models.materiel.Composant;
import src.models.util.Utilitaire;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/composants/stock/approvisionner")
public class Approvisionnement extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idComposant = request.getParameter("idComposant");
        String dateApprovisionnement = request.getParameter("dateAprv");
        String quantite = request.getParameter("qtt");
        String prixUnitaire = request.getParameter("pu");
        System.out.println("dateApprivisonnement valeur "+dateApprovisionnement);

        String url = new String("/composants/etatstock");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant cmp = new Composant(idComposant, new StockComposant(dateApprovisionnement, quantite, prixUnitaire));
            cmp.approvisionnerEnStock(co);
            message = "Approvisionnement réussi ";
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
        try {
            response.sendRedirect(request.getContextPath() + url + "?message=" + Utilitaire.encodeMessage(message));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
