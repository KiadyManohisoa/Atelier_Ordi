package src.models.clients;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Client {

    String id;
    String nom;
    String prenom;
    Date dateNaissance;
    String adresseMail;
    String contact;
    String adressePostale;

    public Client(String id)throws  Exception  {
        this.setId(id);
    }

    public Client[] lister(Connection co) throws Exception {
        Client [] clts;
        PreparedStatement st = null;
        ResultSet res = null;
        String query = "select * from client order by id desc";
        try {
            st = co.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            res = st.executeQuery();

            res.last();
            int rowCount = res.getRow();
            res.beforeFirst();

            clts = new Client[rowCount];
            int i = 0;
            while (res.next()) {
                clts[i] = new Client();
                clts[i].setId(res.getString("id"));
                clts[i].setNom(res.getString("nom"));
                clts[i].setPrenom(res.getString("prenom"));
                clts[i].setContact(res.getString("contact"));
                clts[i].setAdresseMail(res.getString("adresseMail"));
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
        return clts;
    }

    public void enregistrer(Connection co) throws Exception {
        PreparedStatement st = null;
        String query = "insert into client (nom, prenom, dateNaissance, adresseMail, contact, adressePostale)"
        +" values (?, ?, ?, ?, ?, ?)";
        try {
            co.setAutoCommit(false);
            st = co.prepareStatement(query);

            st.setString(1, this.getNom());
            st.setString(2, this.getPrenom());
            st.setDate(3, this.getDateNaissance());
            st.setString(4, this.getAdresseMail());
            st.setString(5, this.getContact());
            st.setString(6, this.getAdressePostale());

            st.executeUpdate();
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
            if (st != null) {
                st.close();
            }
        }
    }

    public Client(String nom, String prenom, String dateNaissance, String adresseMail, String contact, String adressePostale) throws Exception {
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setDateNaissance(dateNaissance);
        this.setAdresseMail(adresseMail);
        this.setContact(contact);
        this.setAdressePostale(adressePostale);
    }

    public Client() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws  Exception {
        if(id==null || id.isEmpty()) {
            throw new Exception("L\\'identifiant client ne peut pas être null ou vide");
        }
        this.id = id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dtn) throws Exception {
        try {
            Date dateDeNaissance = Date.valueOf(dtn);
            this.setDateNaissance(dateDeNaissance);
        }
        catch(Exception e) {
            throw new Exception("Format de la date de naissance invalide, avec la valeur "+dtn);
        }
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) throws Exception {
        if(!adresseMail.contains("@")) {
            throw new Exception("Format de l\\'adresse mail invalide, avec la valeur "+adresseMail);
        }
        this.adresseMail = adresseMail;
    }

    public String getContact() {
        return contact;
    }    
    
    public void setContact(String ctc) {
        String contactTrm = ctc.replaceAll("\\s+", "");
        this.contact = contactTrm;
    }
    
    
    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }


}
