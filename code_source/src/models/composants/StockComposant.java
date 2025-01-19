package src.models.composants;

import java.sql.Date;

public class StockComposant {
    
    Date dateEntree;
    int quantite;
    double prixUnitaire;
    int reste;

    public StockComposant(String dateEntree, String quantite, String prixUnitaire) throws Exception {
        this.setDateEntree(dateEntree);
        this.setQuantite(quantite);
        this.setPrixUnitaire(prixUnitaire);
    }

    public StockComposant(int r) {
        this.setReste(r);
    }
    
    public StockComposant() {
    }
    
    public int getReste() {
        return reste;
    }
    public void setReste(int reste) {
        this.reste = reste;
    }
    
    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(String d) throws Exception {
        try {
            Date daty = Date.valueOf(d);
            this.setDateEntree(daty);
        } catch (Exception e) {
            throw new Exception("Format de la date d\\'entrée du composant invalide, avec valeur "+d);
        }
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) throws Exception {
        try {
            int qtt = Integer.valueOf(quantite);
            this.setQuantite(qtt);
            this.setReste(qtt);
        } catch (Exception e) {
            throw new Exception("Format de la quantité invalide, avec valeur "+quantite);
        }
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(String pu) throws Exception {
        try {
            double p = Double.valueOf(pu);
            this.setPrixUnitaire(p);
        } catch (Exception e) {
            throw new Exception("Format du prix unitaire invalide, avec valeur "+pu);
        }
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }  
}
