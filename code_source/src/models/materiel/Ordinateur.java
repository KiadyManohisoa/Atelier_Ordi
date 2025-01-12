package src.models.materiel;

public class Ordinateur {
 
    String model;
    Marque marque;
    String numeroSerie;
    int anneeSortie;
    Categorie categorie;

    public Ordinateur(String idMarque, String idCategorie, String nomModele, String numeroSerie, String anneeSortie) throws Exception {
        Marque marque = new Marque();
        marque.setId(idMarque);
        this.setMarque(marque);
        this.setCategorie(new Categorie(idCategorie));
        this.setModel(nomModele);
        this.setNumeroSerie(numeroSerie);
        this.setAnneeSortie(anneeSortie);
    }

    public Ordinateur() {
        
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    
    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }
    
    public String getNumeroSerie() {
        return numeroSerie;
    }
    
    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }
    
    public int getAnneeSortie() {
        return anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) throws Exception {
        try {
            int annee = Integer.valueOf(anneeSortie);
            this.setAnneeSortie(annee);
        } catch (Exception e) {
            throw new Exception("Format de l\\'année de sortie invalide, avec valeur : "+anneeSortie);
        }
    }

    public void setAnneeSortie(int anneeSortie) {
        this.anneeSortie = anneeSortie;
    }
    
}
