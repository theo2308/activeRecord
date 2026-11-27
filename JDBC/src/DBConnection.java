import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection instance = null;
    private static String dbName = "testpersonne";
    private static String userName = "root";
    private static String password = "root";
    private static String serverName = "localhost";
    private static String portNumber = "3306";

    private DBConnection() throws SQLException {

        Properties props = new Properties();
        props.put("user", userName);
        props.put("password", password);

        String urlDB = "jdbc:mysql://" + serverName + ":";
        urlDB += portNumber + "/" + dbName;
        instance = DriverManager.getConnection(urlDB, props);

    }

    public static synchronized Connection getConnection() throws SQLException {
        if (instance == null) new DBConnection();
        return instance;
    }


    public static synchronized void setNomDB(String nomDB){
        if (nomDB != null && nomDB != dbName){
            dbName = nomDB;
            instance = null;
        }
    }
}
