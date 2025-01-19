package src.models.materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import src.models.composants.StockComposant;

public class Composant {

    String id;
    MarqueComposant marqueComposant;
    TypeComposant typeComposant;
    String nomModele;
    String description;
    StockComposant stockComposant;


    public Composant(String id, StockComposant sc) throws Exception {
        this.setId(id);
        this.setStockComposant(sc);
    }

    public Composant(String nomModele, String description, String idMarqueComposant, String idTypeComposant) throws Exception {
        this.setNomModele(nomModele);
        this.setDescription(description);
        this.setMarqueComposant(new MarqueComposant(idMarqueComposant));
        this.setTypeComposant(new TypeComposant(idTypeComposant));
    }

    public Composant(String id) {
        this.setId(id);
    }
    public StockComposant getStockComposant() {
        return stockComposant;
    }
    public void setStockComposant(StockComposant stockComposant) {
        this.stockComposant = stockComposant;
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

    public void enregistrer(Connection co) throws Exception {
        PreparedStatement st = null;
        String query = "insert into Composant (nomModele,description,idMarqueComposant,idTypeComposant) values (?, ?, ?, ?)";
        try {
            co.setAutoCommit(false);

            st = co.prepareStatement(query);
            st.setString(1, this.getNomModele());
            st.setString(2, this.getDescription());
            st.setString(3, this.getMarqueComposant().getId());
            st.setString(4, this.getTypeComposant().getId());
            st.executeUpdate();

            co.commit();
        } catch(Exception e) {
            if(co!=null) {
                co.rollback();
            }
            throw e;
        } 
        finally {
            if (st != null) {
                st.close();
            }
            if(co!=null) {
                co.setAutoCommit(true);
            }
        }
    }

    public void approvisionnerEnStock(Connection co) throws Exception{
        PreparedStatement st = null;
        String query = "insert into EntreeComposant (dateEntree,quantite,prixUnitaire,d_reste,idComposant) values (?,?,?,?,?)";
        try {
            co.setAutoCommit(false);

            st = co.prepareStatement(query);
            st.setDate(1, this.getStockComposant().getDateEntree());
            st.setInt(2, this.getStockComposant().getQuantite());
            st.setDouble(3, this.getStockComposant().getPrixUnitaire());
            st.setInt(4, this.getStockComposant().getReste());
            st.setString(5, this.getId());
            st.executeUpdate();
            co.commit();

        } catch(Exception e) {
            if(co!=null) {
                co.rollback();
            }
            throw e;
        }  
        finally {
            if (st != null) {
                st.close();
            }
            if(co!=null) {
                co.setAutoCommit(true);
            }
        }
    }

    public Composant[] listerParCritere(Connection co,String idMarqueComposant,String idTypeComposant) throws Exception {
        Composant[] composants;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_Composant where 1=1";
        if(idMarqueComposant!=null && !idMarqueComposant.isEmpty()) {
            query+=" and idmarquecomposant=(?)";
        }
        if(idTypeComposant!=null && !idTypeComposant.isEmpty()) {
            query+=" and idtypecomposant=(?)";
        }
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int index = 1;
            if(idMarqueComposant!=null && !idMarqueComposant.isEmpty()) {
                st.setString(index, idMarqueComposant);
                index++;
            }
            if(idTypeComposant!=null && !idTypeComposant.isEmpty()) {
                st.setString(index, idTypeComposant);
            }
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
                composants[i].setStockComposant(new StockComposant(res.getInt("stockcomposant")));
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
                composants[i].setStockComposant(new StockComposant(res.getInt("stockcomposant")));

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
