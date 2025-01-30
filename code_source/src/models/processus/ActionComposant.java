package src.models.processus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import src.models.materiel.Composant;
import src.models.materiel.TypeComposant;

public class ActionComposant {
    TypeComposant typeComposant;
    Composant aUtiliser;

    public Composant getaUtiliser() {
        return aUtiliser;
    }

    public void setaUtiliser(Composant aUtiliser) {
        this.aUtiliser = aUtiliser;
    }

    public ActionComposant(String idTypeComposant, String idComposant) throws Exception{
        this.typeComposant = new TypeComposant(idTypeComposant);
        this.aUtiliser = new Composant(idComposant);
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public void enregistrer(Connection co, String idReparationOrdi) throws Exception {
        PreparedStatement st = null;
        String query = "insert into ActionComposant (idReparationOrdi, idTypeComposant, idComposant) values (?, ?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, idReparationOrdi);
            st.setString(2, this.getTypeComposant().getId());
            st.setString(3, this.getaUtiliser().getId());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
    
}
