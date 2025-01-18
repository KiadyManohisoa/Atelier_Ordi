package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MarqueOrdi {

    String id;
    String libelle;

    public MarqueOrdi() {
    }

    public MarqueOrdi(String libelle) {
        this.setLibelle(libelle);
    }

    public MarqueOrdi[] lister(Connection co) throws Exception {
        MarqueOrdi [] marques;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from MarqueOrdi";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            marques = new MarqueOrdi[rowCount];
            int i = 0;
            while (res.next()) {
                marques[i] = new MarqueOrdi();
                marques[i].setId(res.getString("id"));
                marques[i].setLibelle(res.getString("libelle"));
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
        return marques;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) throws Exception {
        if(id==null || id.isEmpty()) {
            throw new Exception("L\\'identifiant de la marque ne peut pas être null ou vide");
        }
        this.id = id;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    
}
