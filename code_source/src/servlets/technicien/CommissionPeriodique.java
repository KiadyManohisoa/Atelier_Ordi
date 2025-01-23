package src.servlets.technicien;


import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import src.models.processus.CommissionTechnicien;
import jakarta.servlet.annotation.WebServlet;
import src.models.util.Periode;
import src.services.UtilDB;

@WebServlet("/technicien/commissions")
public class CommissionPeriodique extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/web/pages/technicien/commission.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String periode = request.getParameter("periode");
        String url = new String("/web/pages/technicien/commission.jsp");
        String message = new String();
        Connection co = null;
        try {
            co = new UtilDB().getConnection();
            CommissionTechnicien com = new CommissionTechnicien();
            List<CommissionTechnicien> ct = com.listerCommissionTechParPeriode(co, new Periode(periode));
            double [] coms =com.toutauxDesHommesEtFemmes(ct);
            request.setAttribute("ct", ct);
            request.setAttribute("periode",periode);
            request.setAttribute("coms", coms);
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
