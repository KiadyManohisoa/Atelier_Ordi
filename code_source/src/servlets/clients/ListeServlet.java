package src.servlets.clients;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import src.services.UtilDB;
import src.models.clients.Client;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/clients/liste")
public class ListeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = new String("/web/pages/clients/liste.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            Client [] clients = new Client().lister(co);
            request.setAttribute("clients", clients);
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

    }

}
