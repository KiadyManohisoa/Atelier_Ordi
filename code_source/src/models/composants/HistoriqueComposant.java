package src.models.composants;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import src.models.materiel.Composant;

public class HistoriqueComposant {

    String id;
    Date dateMvt;
    double prixVente;
    Composant composant;

    public void enregistrer(Connection co) throws Exception {
        PreparedStatement st = null;
        String query = "insert into HistoriqueComposant (prixVente, idComposant) values (?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setDouble(1, this.getPrixVente());
            st.setString(2, this.getComposant().getId());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
    
    
    public HistoriqueComposant(Composant cmp) {
        this.setDateMvt(new Date(System.currentTimeMillis()));
        this.setPrixVente(cmp.getPrixVente());
        this.setComposant(cmp);
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Date getDateMvt() {
        return dateMvt;
    }
    public void setDateMvt(Date dateMvt) {
        this.dateMvt = dateMvt;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
    public Composant getComposant() {
        return composant;
    }
    public void setComposant(Composant composant) {
        this.composant = composant;
    }
    public HistoriqueComposant(){

    }
}