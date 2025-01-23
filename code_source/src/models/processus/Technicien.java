package src.models.processus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Technicien {

    String id;
    String nom;
    String prenom;
    
    public Technicien(String id) throws Exception {
        this.setId(id);
    }

    public Technicien[] lister(Connection co) throws Exception {
        Technicien[] techniciens;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from Technicien";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            techniciens = new Technicien[rowCount];
            int i = 0;
            while (res.next()) {
                techniciens[i] = new Technicien();
                techniciens[i].setId(res.getString("id"));
                techniciens[i].setNom(res.getString("nom"));
                techniciens[i].setPrenom(res.getString("prenom"));
                i++;
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
        return techniciens;
    }
   
    public String getId() {
        return id;
    }
   
    public void setId(String id) throws Exception {
        if(id==null || id.isEmpty()) {
            throw new Exception("L\\'identifiant de la catégorie de l\\'ordinateur ne peut pas être null ou vide");
        }
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public Technicien() {
    }
}