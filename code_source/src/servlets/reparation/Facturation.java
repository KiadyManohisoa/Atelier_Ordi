package src.servlets.reparation;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import src.models.composants.StockComposant;
import src.models.materiel.Composant;
import src.models.processus.Facture;
import src.models.processus.Reparation;
import src.models.util.Utilitaire;
import src.services.UtilDB;

@WebServlet("/reparation/facturation")
public class Facturation extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idReparation = request.getParameter("idReparation");
        String dateFacturation = request.getParameter("dateFacturation");
        String coutMainDoeuvre = request.getParameter("coutMainDoeuvre");
        String pourcentageCommission = request.getParameter("pourcentageCommission");

        String url = new String("/reparation/encours");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Facture fact = new Facture(new Reparation(idReparation), dateFacturation, coutMainDoeuvre, pourcentageCommission, co);
            fact.enregistrer(co);
            message = "Réparation facturée avec succès";
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
            e.printStackTrace();
        }
    }

}
