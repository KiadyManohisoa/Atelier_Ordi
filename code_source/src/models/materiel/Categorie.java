package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Categorie {

    String libelle;
    String id;
   
    public Categorie() {
    }

    public Categorie(String id) throws Exception {
        this.setId(id);
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    public Categorie[] lister(Connection co) throws Exception {
        Categorie[] categories;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from Categorie";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            categories = new Categorie[rowCount];
            int i = 0;
            while (res.next()) {
                categories[i] = new Categorie();
                categories[i].setId(res.getString("id"));
                categories[i].setLibelle(res.getString("libelle"));
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
        return categories;
    }
   
}
