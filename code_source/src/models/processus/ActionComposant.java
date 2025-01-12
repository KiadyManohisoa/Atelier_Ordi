package src.models.processus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import src.models.materiel.TypeComposant;

public class ActionComposant {
    TypeComposant typeComposant;

    public ActionComposant(String idComposant) throws Exception{
        this.typeComposant = new TypeComposant(idComposant);
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public void enregistrer(Connection co, String idReparationOrdi) throws Exception {
        PreparedStatement st = null;
        String query = "insert into ActionComposant (idReparationOrdi, idTypeComposant) values (?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, idReparationOrdi);
            st.setString(2, this.getTypeComposant().getId());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }
    
}
