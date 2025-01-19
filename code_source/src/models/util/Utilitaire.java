package src.models.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

public class Utilitaire {

    public static String encodeMessage(String message) throws Exception {
        return URLEncoder.encode(message, StandardCharsets.UTF_8.toString());
    }

    public static void verifierSiEntier(String input) throws Exception {
        try {
            Integer.valueOf(input);
        } catch (Exception e) {
            throw new Exception("Valeur invalide car l\\'entrée doit être un entier");
        }
    }

    public static Date formatToDate(String date) throws Exception {
        Date daty = null;
        try {
            daty = Date.valueOf(date);
        } catch (Exception e) {
            throw new Exception("Format de la date invalide, avec valeur : "+date);
        }
        return daty;
    }
    
}
