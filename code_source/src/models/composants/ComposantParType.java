package src.models.composants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import src.models.materiel.Composant;
import src.models.materiel.MarqueComposant;
import src.models.materiel.TypeComposant;

public class ComposantParType {
    
    TypeComposant typeComposant;
    List<Composant> composants;

    public List<Composant> getComposantsParType(Connection co, TypeComposant type) throws Exception {
        List<Composant> theComposants = null;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_Composant where idTypeComposant = (?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, type.getId());
            res = st.executeQuery();

            theComposants = new ArrayList<>();
            while (res.next()) {
                Composant cmp = new Composant();
                cmp.setId(res.getString("id"));
                cmp.setNomModele(res.getString("nommodele"));

                MarqueComposant marquecomposant = new MarqueComposant();
                marquecomposant.setId(res.getString("idmarquecomposant"));
                marquecomposant.setLibelle(res.getString("marquecomposant"));  
                
                cmp.setMarqueComposant(marquecomposant);   
                theComposants.add(cmp); 
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
        return theComposants;
    }

    public ComposantParType[] lister(Connection co) throws Exception {
        ComposantParType[] cmParTypes;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from TypeComposant";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            cmParTypes = new ComposantParType[rowCount];
            int i = 0;
            while (res.next()) {
                cmParTypes[i] = new ComposantParType();
                TypeComposant typeComposant = new TypeComposant();
                typeComposant.setId(res.getString("id"));
                typeComposant.setLibelle(res.getString("libelle"));
                this.setTypeComposant(typeComposant);
                this.setComposants(this.getComposantsParType(co, typeComposant));
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
        return cmParTypes;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }
    
    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }

    

    

}
