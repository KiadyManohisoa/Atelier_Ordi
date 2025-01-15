package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Composant {

    String id;
    MarqueComposant marqueComposant;
    TypeComposant typeComposant;
    String nomModele;
    String description;

    public Composant(String id) {
        this.setId(id);
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public MarqueComposant getMarqueComposant() {
        return marqueComposant;
    }
    public void setMarqueComposant(MarqueComposant marqueComposant) {
        this.marqueComposant = marqueComposant;
    }
    public TypeComposant getTypeComposant() {
        return typeComposant;
    }
    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }
    public String getNomModele() {
        return nomModele;
    }
    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Composant(){

    }

    public Composant[] lister(Connection co) throws Exception{
        Composant[] composants;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_Composant";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            composants = new Composant[rowCount];
            int i = 0;
            while (res.next()) {
                composants[i] = new Composant();
                composants[i].setId(res.getString("id"));

                MarqueComposant marque=new MarqueComposant();
                marque.setId(res.getString("idmarquecomposant"));
                marque.setLibelle(res.getString("marquecomposant"));
                composants[i].setMarqueComposant(marque);

                TypeComposant typeC=new TypeComposant();
                typeC.setId(res.getString("idmarquecomposant"));
                typeC.setLibelle(res.getString("typecomposant"));
                composants[i].setTypeComposant(typeC);

                composants[i].setDescription(res.getString("description"));

                composants[i].setNomModele(res.getString("nommodele"));

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
}
