package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Panne {
    
    String description;
    TypeComposant composant;

    public Panne(String description, String idComposant) throws Exception {
        this.setDescription(description);
        this.setComposant(new TypeComposant(idComposant));
    }

    public void enregistrer(Connection co, String idReparationOrdi) throws Exception {
        PreparedStatement st = null;
        String query = "insert into PanneOrdi (idReparationOrdi, idTypeComposant, description) values (?, ?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, idReparationOrdi);
            st.setString(2, this.getComposant().getId());
            st.setString(3, this.getDescription());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public TypeComposant getComposant() {
        return composant;
    }
    
    public void setComposant(TypeComposant composant) {
        this.composant = composant;
    }

}
