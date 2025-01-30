package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TypeComposant {
    
    String id;
    String libelle;

    public TypeComposant() {
    }

    public TypeComposant(String id) throws Exception {
        this.setId(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws Exception {
        if(id==null || id.isEmpty()) {
            throw new Exception("L\\'identifiant du composant ne peut pas être null ou vide");
        }
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeComposant[] lister(Connection co) throws Exception {
        TypeComposant[] composants;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from TypeComposant";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            composants = new TypeComposant[rowCount];
            int i = 0;
            while (res.next()) {
                composants[i] = new TypeComposant();
                composants[i].setId(res.getString("id"));
                composants[i].setLibelle(res.getString("libelle"));
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
        return composants;
    }

    @Override
    public String toString() {
        return "TypeComposant [id=" + id + ", libelle=" + libelle + "]";
    }
}
