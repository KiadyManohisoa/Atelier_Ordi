package src.services;

import java.sql.*;

public class UtilDB {

    private static String driverName = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/atelierordi";
    private static String userName = "postgres";
    private static String password = "postgres";

    public UtilDB(){}

    public Connection getConnection() throws Exception {
        Connection co = null;
        try {
            Class.forName(driverName);
            co = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {
            //e.printStackTrace();
            throw e;
        }
        return co;
    }

}
