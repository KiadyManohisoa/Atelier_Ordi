package src.models.processus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import src.models.util.Periode;
import src.models.util.Utilitaire;

public class Facture {

    String id;
    Date dateFacturation;
    Periode periode;
    double coutTotal;
    double pourcentageCommission;
    double commissionTech;
    Reparation reparation;

    public Facture(Reparation reparation, String dateFacturation, String coutTotal, String pourcentageCommission) throws Exception {
        this.setReparation(reparation);
        this.setDateFacturation(dateFacturation);
        this.setCoutTotal(coutTotal);
        this.setPourcentageCommission(pourcentageCommission);
    }

    public void enregistrer(Connection co) throws Exception {
        PreparedStatement st = null;
        String query = "insert into facture (dateFacturation, d_periodeFacturation, coutTotal, d_commissionTech, id_reparationOrdi)"
        +" values (?, ?, ?, ?, ?)";
        try {
            co.setAutoCommit(false);
            st = co.prepareStatement(query);

            st.setDate(1, this.getDateFacturation());
            st.setString(2, this.getPeriode().getValeur());
            st.setDouble(3, this.getCoutTotal());
            st.setDouble(4, this.getCommissionTech());
            st.setString(5, this.getReparation().getId());
            st.executeUpdate();
            co.commit();
        } catch (Exception e) {
            if (co != null) {
                co.rollback();
            }
            throw e;
        } finally {
            if (co != null) {
                co.setAutoCommit(true);
            }
            if (st != null) {
                st.close();
            }
        }
    }

    public double getPourcentageCommission() {
        return pourcentageCommission;
    }

    public void setPourcentageCommission(String pc) throws Exception {
        try {
            double p = Double.valueOf(pc);
            this.setPourcentageCommission(p);
            this.setCommissionTech(this.getCoutTotal()*(p/100.00));
        } catch (Exception e) {
            throw new Exception("Format du pourcentage de commission pour la facturation invalide, avec valeur "+pc);
        }
    }

    public void setPourcentageCommission(double pourcentageCommission) {
        this.pourcentageCommission = pourcentageCommission;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Date getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(String d) throws Exception {
        try {
            Date daty = Date.valueOf(d);
            this.setDateFacturation(daty);
            this.setPeriode(Utilitaire.getPeriode(daty));
        } catch (Exception e) {
            throw new Exception("Format de la date de facturation invalide, avec valeur "+d);
        }
    }
    
    public void setDateFacturation(Date dateFacturation) {
        this.dateFacturation = dateFacturation;
    }
    
    public Periode getPeriode() {
        return periode;
    }
    
    public void setPeriode(Periode periode) {
        this.periode = periode;
    }
    
    public double getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(String ct) throws Exception {
        try {
            double c = Double.valueOf(ct);
            this.setCoutTotal(c);
        } catch (Exception e) {
            throw new Exception("Format du cout total pour la facturation invalide, avec valeur "+ct);
        }
    }
    
    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }
    
    public double getCommissionTech() {
        return commissionTech;
    }
    
    public void setCommissionTech(double commissionTech) {
        this.commissionTech = commissionTech;
    }
    
    public Reparation getReparation() {
        return reparation;
    }
    
    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }

}
