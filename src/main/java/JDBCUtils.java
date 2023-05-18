import java.sql.*;

public class JDBCUtils {

    // Bu class'ta tekrarli kullanilacak methodlar olusturacagiz.
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;


    //database'e baglanma methodu --> connection return yapar.
    public static Connection connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    //(Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))
    //Medunna database'e bağlanan method
    public static Connection connectToMedunnaDataBase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2", "select_user", "Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


    //statement olusturma methodu
    public static Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //execute methodu ile query calistiran method
    public static boolean execute(String sql) {
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //executequery methodu ile query calistiran method

    public static ResultSet executQuery(String sql) {

        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    //Baglanitiyi kapatan method.
    public static void closeConnection() {
        try {
            connection.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}


