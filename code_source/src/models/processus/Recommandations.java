package src.models.processus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import src.models.materiel.ComposantDuMois;
import src.models.materiel.MarqueComposant;
import src.models.materiel.TypeComposant;

public class Recommandations {
    
    List<ComposantDuMois> composantMois;

    public List<ComposantDuMois> getComposantMois() {
        return composantMois;
    }

    public void setComposantDuMois(List<ComposantDuMois> composantDuMois) {
        this.composantMois = composantDuMois;
    }

    public Recommandations(){

    }

    public Recommandations(String [] idComposantsDuMois, String periode) {
        this.setComposantDuMois(idComposantsDuMois, periode);
    }

    public void setComposantDuMois(String [] idComposantsDuMois, String periode) {
        List<ComposantDuMois> cdm = new ArrayList<>();
        if(idComposantsDuMois.length>0) {
            for (int i = 0; i < idComposantsDuMois.length; i++) {
                cdm.add(new ComposantDuMois(idComposantsDuMois[i], periode));
            }
        }
        this.setComposantDuMois(cdm);
    }

    public List<ComposantDuMois> getComposantDeLannee(Connection co,String annee) throws Exception{
        List<ComposantDuMois> composants=new ArrayList<>();
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_ComposantsDuMois where periode LIKE ? order by periode";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1,String.valueOf(annee)+"-%" );
            res = st.executeQuery();

            while (res.next()) {
                ComposantDuMois composantsMensuel=new ComposantDuMois();
                composantsMensuel.setId(res.getString("id"));
                composantsMensuel.setNomModele(res.getString("nomModele"));
                composantsMensuel.setDescription(res.getString("description"));
                
                TypeComposant t = new TypeComposant();
                t.setLibelle(res.getString("typecomposant"));
                composantsMensuel.setTypeComposant(t);


                MarqueComposant m = new MarqueComposant();
                m.setLibelle(res.getString("marquecomposant"));
                composantsMensuel.setMarqueComposant(m);

                composantsMensuel.setPeriode(res.getString("periode"));
                composants.add(composantsMensuel);
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

    public List<ComposantDuMois> getComposantDuMois(Connection co,String periode) throws Exception{
        List<ComposantDuMois> composants=new ArrayList<>();
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_ComposantsDuMois where periode=(?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, periode);
            res = st.executeQuery();

            while (res.next()) {
                ComposantDuMois composantsMensuel=new ComposantDuMois();
                composantsMensuel.setId(res.getString("id"));
                composantsMensuel.setNomModele(res.getString("nomModele"));
                composantsMensuel.setDescription(res.getString("description"));
                
                TypeComposant t = new TypeComposant();
                t.setLibelle(res.getString("typecomposant"));
                composantsMensuel.setTypeComposant(t);


                MarqueComposant m = new MarqueComposant();
                m.setLibelle(res.getString("marquecomposant"));
                composantsMensuel.setMarqueComposant(m);

                composants.add(composantsMensuel);
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

    public void enregistrer(Connection co) throws Exception{
        try {
            co.setAutoCommit(false);

            for(ComposantDuMois composant : this.getComposantMois()){
                composant.ajouterAuxRecommandations(co);
            }
            co.commit();

        } catch (Exception e) {
            if (co != null) {
                co.rollback();
            }
            throw e;
        } finally {
            if (co != null) {
                co.setAutoCommit(true);
            }
        }
    }

}
