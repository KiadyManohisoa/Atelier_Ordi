package src.models.processus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Genre{
    String id;
    String libelle;
    public Genre() {
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
     public Genre[] lister(Connection co) throws Exception{
        Genre[] genre;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from genre";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            genre = new Genre[rowCount];
            int i = 0;
            while (res.next()) {
                genre[i] = new Genre();
                genre[i].setId(res.getString("id"));
                genre[i].setLibelle(res.getString("libelle"));
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
        return genre;
    }
        
}