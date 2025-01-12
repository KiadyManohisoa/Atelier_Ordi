package src.servlets.clients;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.clients.Client;
import src.services.UtilDB;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/clients")
public class ClientsServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = new String("/web/pages/clients/insertion.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomClt = request.getParameter("nomClt");
        String prenomClt = request.getParameter("prenomClt");
        String dtnClt = request.getParameter("dtnClt");
        String mailClt = request.getParameter("mailClt");
        String contactClt = request.getParameter("contactClt");
        String adrClt = request.getParameter("adrClt");

        String message = new String();
        String url = new String("/web/pages/clients/insertion.jsp");
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Client clt = new Client(nomClt, prenomClt, dtnClt, mailClt, contactClt, adrClt);
            clt.enregistrer(co);
            message = "Opération effectuée avec succès";
        } catch (Exception e) {
            message = e.getMessage();
        }
        finally {
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
