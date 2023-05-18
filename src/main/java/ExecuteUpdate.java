import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExecuteUpdate {
    public static void main(String[] args) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        Statement statement = connection.createStatement();


        String query = "Insert into countries (country_id, country_name,region_id) values ('SE','SWEDEN','6')";
        int sonuc = statement.executeUpdate(query);
        System.out.println(sonuc);
        statement.close();




/*
       // Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
       // Statement statement = connection.createStatement();

        Scanner input = new Scanner(System.in);
        System.out.println("Ulke kisaltma ismi: ");
        String cName = input.nextLine().toLowerCase();

        System.out.println("Ulke ismi: ");
        String cName2 = input.nextLine().toLowerCase();

        System.out.println("Bolge id: ");
        int cId = input.nextInt();

       String query = "Insert into countries (country_id, country_name,region_id) values (" + cName + "," + cName2 + "," + cId + ")";
      //  String query = "Insert into countries  values (" + cName + "," + cName2 + "," + cId+")";
      //  int sonuc = statement.executeUpdate(query);
        System.out.println(statement.executeUpdate(query));
        connection.close();
        statement.close();

 */
        connection.close();
        statement.close();


    }
}
