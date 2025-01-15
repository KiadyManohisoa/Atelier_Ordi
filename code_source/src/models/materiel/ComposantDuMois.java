package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ComposantDuMois extends Composant{

    String periode;

    public ComposantDuMois(String id, String periode) {
        super(id);
        this.setPeriode(periode);
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public ComposantDuMois(){
        
    }
    public void ajouterAuxRecommandations(Connection co) throws Exception{
        PreparedStatement st = null;
        String query = "insert into ComposantsDuMois (idComposant,periode) values (?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, this.getId());
            st.setString(2, this.getPeriode());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

}
