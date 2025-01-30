package src.models.processus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import src.models.util.AppConfig;
import src.models.util.Periode;
import src.models.util.Utilitaire;

public class Facture {

    String id;
    Date dateFacturation;
    Periode periode;
    double coutMateriels;
    double coutMainDoeuvre;
    double coutTotal;
    double pourcentageCommission;
    double commissionTech;
    Reparation reparation;

    public Facture(Reparation reparation, String dateFacturation, String coutMatos, String pourcentageCommission, Connection co) throws Exception {
        this.setReparation(reparation);
        this.setDateFacturation(dateFacturation);
        this.setCoutMateriels(reparation.getCoutTotalRemplacement(co));
        this.setCoutMainDoeuvre(coutMatos);
        this.setCoutTotal();
        this.setPourcentageCommission(pourcentageCommission);
    }

    public void enregistrer(Connection co) throws Exception {
        PreparedStatement st = null;
        String query = "insert into facture (dateFacturation, d_periodeFacturation, coutMateriels, coutMainDoeuvre, coutTotal, d_commissionTech, id_reparationOrdi)"
        +" values (?, ?, ?, ?, ?, ?, ?)";
        try {
            co.setAutoCommit(false);
            st = co.prepareStatement(query);

            st.setDate(1, this.getDateFacturation());
            st.setString(2, this.getPeriode().getValeur());
            st.setDouble(3, this.getCoutMateriels());
            st.setDouble(4, this.getCoutMainDoeuvre());
            st.setDouble(5, this.getCoutTotal());
            st.setDouble(6, this.getCommissionTech());
            st.setString(7, this.getReparation().getId());
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
            if(this.getCoutTotal()>AppConfig.borneMinCoutTotal) {
                this.setCommissionTech(this.getCoutTotal()*(p/100.00));
            }
            else {
                this.setCommissionTech(0);
            }
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

    public double getCoutMateriels() {
        return coutMateriels;
    }

    public void setCoutMateriels(double coutMateriels) {
        this.coutMateriels = coutMateriels;
    }
    public double getCoutMainDoeuvre() {
        return coutMainDoeuvre;
    }

    public void setCoutMainDoeuvre(String cm) throws Exception {
        try {
            double c = Double.valueOf(cm);
            this.setCoutMainDoeuvre(c);
        } catch (Exception e) {
            throw new Exception("Format du cout de main d\\'oeuvre pour la facturation invalide, avec valeur "+cm);
        }
    }

    public void setCoutMainDoeuvre(double coutMainDoeuvre) {
        this.coutMainDoeuvre = coutMainDoeuvre;
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

    public void setCoutTotal() {
        this.coutTotal = this.getCoutMainDoeuvre()+this.getCoutMateriels();
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
