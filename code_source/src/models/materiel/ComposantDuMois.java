package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import src.models.util.Periode;

public class ComposantDuMois extends Composant{

    Periode periode;

    public ComposantDuMois(String id, String periode) throws Exception {
        super(id);
        this.setPeriode(new Periode(periode));
    }
    
    public ComposantDuMois(){
        
    }
    public void ajouterAuxRecommandations(Connection co) throws Exception{
        PreparedStatement st = null;
        String query = "insert into ComposantsDuMois (idComposant,periode) values (?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, this.getId());
            st.setString(2, this.getPeriode().getValeur());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public Periode getPeriode() {
        return periode;
    }

    public void setPeriode(Periode periode) {
        this.periode = periode;
    }

}
