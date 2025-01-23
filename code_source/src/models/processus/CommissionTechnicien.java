package src.models.processus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import src.models.util.Periode;

public class CommissionTechnicien {

    Technicien technicien;
    double valeurCommission;

    public CommissionTechnicien() {
    }
    public Technicien getTechnicien() {
        return technicien;
    }
    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }
    public double getValeurCommission() {
        return valeurCommission;
    }

    public void setValeurCommission(double valeurCommission) {
        this.valeurCommission = valeurCommission;
    }

    public double[] toutauxDesHommesEtFemmes(List<CommissionTechnicien> commissions) {
        double[] valeurGenre=new double[2];
        valeurGenre[0]=0;
        valeurGenre[1]=0;

        for(CommissionTechnicien comm : commissions) {
            if(comm.getTechnicien().getGenre().getLibelle().equals("femme")){
                valeurGenre[1]+=comm.getValeurCommission();
            }
            else if(comm.getTechnicien().getGenre().getLibelle().equals("homme")){
                valeurGenre[0]+=comm.getValeurCommission();
            }
        }
        return valeurGenre;
    }

    public List<CommissionTechnicien> listerCommissionTechParPeriode(Connection co, Periode periode) throws Exception{
        List<CommissionTechnicien> commissionTechnicien=new ArrayList<>();
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_commissionTechnicien where d_periodeFacturation=(?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, periode.getValeur());
            res = st.executeQuery();

            while (res.next()) {
                CommissionTechnicien comm=new CommissionTechnicien();
                Technicien technicien=new Technicien();

                technicien.setId(res.getString("id"));
                technicien.setNom(res.getString("nom"));
                technicien.setPrenom(res.getString("prenom"));

                Genre genre=new Genre();
                genre.setId(res.getString("idGenre"));
                genre.setLibelle(res.getString("libelleGenre"));

                technicien.setGenre(genre);

                comm.setTechnicien(technicien);
                comm.setValeurCommission(res.getDouble("commission"));
                
              commissionTechnicien.add(comm);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (res != null) {
                res.close();
            }
            if (st != null) {
                st.close();
            }
        }
        return commissionTechnicien;
    }
}