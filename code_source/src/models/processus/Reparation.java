package src.models.processus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import src.models.clients.Client;
import src.models.materiel.Marque;
import src.models.materiel.Ordinateur;
import src.models.materiel.Panne;

public class Reparation {

    String id;
    Client client;
    Ordinateur ordinateur;
    Date dateReception;
    int avancement;
    List<Panne> pannes;
    List<ActionComposant> listeActionComposant;

    public Reparation() {
    }

    public Reparation(Client client, Ordinateur ordinateur, String dateReception, String[] idTypeComposantsEnPannes, String [] descriptionsPannes, String [] idComposantsAremplacer) throws Exception {
        this.setClient(client);
        this.setOrdinateur(ordinateur);
        this.setDateReception(dateReception);
        this.setPannes(idTypeComposantsEnPannes, descriptionsPannes);
        this.setListeActionComposant(idComposantsAremplacer);
    }

    public Reparation[] getByCriteria(Connection co, String idCategorie,String idTypeComposant) throws Exception {
        Reparation[] reparations;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_actionOrdi where idTypeComposant=(?) AND idCategorie=(?)";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, idTypeComposant);
            st.setString(2, idCategorie);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            reparations = new Reparation[rowCount];
            int i = 0;
            while (res.next()) {
                reparations[i] = new Reparation();
                reparations[i].setId(res.getString("idreparation"));
                reparations[i].setDateReception(res.getDate("datereception"));
                Ordinateur ordinateur = new Ordinateur();
                ordinateur.setMarque(new Marque(res.getString("marque"))); 
                ordinateur.setModel(res.getString("nomModele")); 
                ordinateur.setNumeroSerie(res.getString("numeroSerie")); 
                reparations[i].setOrdinateur(ordinateur);
                Client client = new Client();
                client.setNom(res.getString("nom"));
                client.setPrenom(res.getString("prenom"));
                reparations[i].setClient(client);
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
        return reparations;
    }
    
    public void mettreAjour(Connection co) throws Exception {
        PreparedStatement st = null;
        String query="update ReparationOrdi set avancement=(?) where id=(?)";
        try {
            st = co.prepareStatement(query);
           
            st.setInt(1, this.getAvancement() + 1);
            st.setString(2, this.getId());
           
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public Reparation obtenirParId(Connection conn, String idReparation) throws Exception {
        Reparation reparation = new Reparation();
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_ordi where idReparation=(?)";
        try {
            st = conn.prepareStatement(query);
            st.setString(1, idReparation);
            res = st.executeQuery();

            if (res.next()) {
                reparation = new Reparation();
                reparation.setId(res.getString("idreparation"));
                reparation.setDateReception(res.getDate("datereception"));
                reparation.setAvancement(res.getInt("avancement"));
                Ordinateur ordi = new Ordinateur();
                ordi.setMarque(new Marque(res.getString("marque"))); 
                ordi.setModel(res.getString("nomModele")); 
                ordi.setNumeroSerie(res.getString("numeroSerie")); 
                reparation.setOrdinateur(ordi);
                Client clt = new Client();
                clt.setNom(res.getString("nom"));
                clt.setPrenom(res.getString("prenom"));
                reparation.setClient(clt);
            }
            else {
                throw new Exception("Reparation introuvable avec l\\'identifiant "+idReparation);
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
        return reparation;
    }
    
    public Reparation[] listerParAvancement(Connection conn,int niveau) throws Exception {
        Reparation[] reparations;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_ordi where avancement=(?)";
        try {
            st = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setInt(1,niveau);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            reparations = new Reparation[rowCount];
            int i = 0;
            while (res.next()) {
                reparations[i] = new Reparation();
                reparations[i].setId(res.getString("idreparation"));
                reparations[i].setDateReception(res.getDate("datereception"));
                Ordinateur ordinateur = new Ordinateur();
                ordinateur.setMarque(new Marque(res.getString("marque"))); 
                ordinateur.setModel(res.getString("nomModele")); 
                ordinateur.setNumeroSerie(res.getString("numeroSerie")); 
                reparations[i].setOrdinateur(ordinateur);
                Client client = new Client();
                client.setNom(res.getString("nom"));
                client.setPrenom(res.getString("prenom"));
                reparations[i].setClient(client);
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
        return reparations;
    }

    void enregistrerActions(Connection co) throws Exception{
        if(!this.getListeActionComposant().isEmpty())
        {
            for (ActionComposant p : this.getListeActionComposant()) {
                p.enregistrer(co , this.getId());
            }
        }
    }


    void enregistrerPannes(Connection co) throws  Exception {
        if(!this.getPannes().isEmpty()) {
            for (Panne p : this.getPannes()) {
                p.enregistrer(co , this.getId());
            }
        }
    }

    void enregistrerReparation(Connection co) throws Exception {
        PreparedStatement st = null;
        String query = "insert into ReparationOrdi (id, nomModele, numeroSerie, anneeSortie, dateReception, idMarqueOrdinateur, idClient, idCategorie) "
                +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            st = co.prepareStatement(query);
            st.setString(1, this.getId());
            st.setString(2, this.getOrdinateur().getModel());
            st.setString(3, this.getOrdinateur().getNumeroSerie());
            st.setInt(4, this.getOrdinateur().getAnneeSortie());
            st.setDate(5, this.getDateReception());
            st.setString(6, this.getOrdinateur().getMarque().getId());
            st.setString(7, this.getClient().getId());
            st.setString(8, this.getOrdinateur().getCategorie().getId());
            st.executeUpdate();
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    public void enregistrer(Connection co) throws Exception {
        try {
            co.setAutoCommit(false);

            this.initPK(co);
            this.enregistrerReparation(co);
            this.enregistrerPannes(co);
            this.enregistrerActions(co);
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

    void initPK(Connection co) throws Exception {
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select ('REP') || LPAD(nextval('s_ReparationOrdi')::TEXT, 6, '0') as seqVal";
        try {
            st = co.prepareStatement(query);
            res = st.executeQuery();
            if (res.next()) {
                this.setId(res.getString("seqVal"));
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
    }

    public Reparation[] getByCriteria(Connection co, String idTypeComposant) throws Exception {
        Reparation[] reparations;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from v_panneOrdi where idTypeComposant=(?)";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setString(1, idTypeComposant);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            reparations = new Reparation[rowCount];
            int i = 0;
            while (res.next()) {
                reparations[i] = new Reparation();
                reparations[i].setId(res.getString("idreparation"));
                reparations[i].setDateReception(res.getDate("datereception"));
                Ordinateur ordinateur = new Ordinateur();
                ordinateur.setMarque(new Marque(res.getString("marque"))); 
                ordinateur.setModel(res.getString("nomModele")); 
                ordinateur.setNumeroSerie(res.getString("numeroSerie")); 
                reparations[i].setOrdinateur(ordinateur);
                Client client = new Client();
                client.setNom(res.getString("nom"));
                client.setPrenom(res.getString("prenom"));
                reparations[i].setClient(client);
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
        return reparations;
    }

    public void setListeActionComposant(String [] idComposantsAremplacer) throws Exception {
        List<ActionComposant> actions = new ArrayList<>();
        if(idComposantsAremplacer!=null && idComposantsAremplacer.length>0) {
            for (int i = 0; i < idComposantsAremplacer.length; i++) {
                actions.add(new ActionComposant(idComposantsAremplacer[i]));
            }
        }
        this.setListeActionComposant(actions);
    }

    public void setPannes(String[] idTypeComposantsEnPannes, String [] descriptionsPannes) throws Exception {
        List<Panne> pannes = new ArrayList<>();
        if(idTypeComposantsEnPannes!=null && idTypeComposantsEnPannes.length > 0) {
            for (int i = 0; i < idTypeComposantsEnPannes.length; i++) {
                Panne p = new Panne(descriptionsPannes[i], idTypeComposantsEnPannes[i]);
                pannes.add(p);
            }
        }
        this.setPannes(pannes);
    }

    public List<ActionComposant> getListeActionComposant() {
        return listeActionComposant;
    }

    public void setListeActionComposant(List<ActionComposant> listeActionComposant) {
        this.listeActionComposant = listeActionComposant;
    }

    public Date getDateReception() {
        return dateReception;
    }

    public void setDateReception(String dateReception) throws Exception {
        try {
            Date daty = Date.valueOf(dateReception);
            this.setDateReception(daty);
        } catch (Exception e) {
            throw new Exception("Format de la date de récéption invalide, avec valeur : "+dateReception);
        }
    }


    public int getAvancement() {
        return avancement;
    }

    public void setAvancement(int avancement) {
        this.avancement = avancement;
    }

    public void setDateReception(Date dateReception) {
        this.dateReception = dateReception;
    }

    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Ordinateur getOrdinateur() {
        return ordinateur;
    }
    
    public void setOrdinateur(Ordinateur ordinateur) {
        this.ordinateur = ordinateur;
    }
    
    public List<Panne> getPannes() {
        return pannes;
    }
    
    public void setPannes(List<Panne> pannes) {
        this.pannes = pannes;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
