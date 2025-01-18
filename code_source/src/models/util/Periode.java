package src.models.util;

import java.util.regex.Pattern;

public class Periode {

    String valeur;

    public Periode(String v) throws Exception {
        this.setValeur(v);
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String input) throws Exception {
        String regex = "^(\\d{4})-(0[1-9]|1[0-2])$";
        Pattern pattern = Pattern.compile(regex);

        if (pattern.matcher(input).matches()) {
            try {
                this.valeur = input;
            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new Exception("Format de la periode invalide, avec valeur " + input);
        }
    }
}
