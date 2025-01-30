package src.servlets.composants;

import java.io.IOException;
import java.sql.Connection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import src.models.materiel.Composant;
import src.models.util.Utilitaire;
import src.services.UtilDB;

@WebServlet("/composant/mettreAjour")
public class MiseAjour extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idComposant = request.getParameter("idComposant");
        String nouveauPrixVente = request.getParameter("nouveauPrixVente");

        String url = new String("/composants/insertion");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Composant composant = new Composant(idComposant);
            composant.setPrixVente(nouveauPrixVente);
            composant.mettreAjour(co);
            message = "Mise-à-jour effectuée avec succès";
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
