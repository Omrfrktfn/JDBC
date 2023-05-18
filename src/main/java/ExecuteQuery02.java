import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        Statement statement = connection.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.
        //first way use the offset and limit;
        String sql = "select  company, number_of_employees from companies order by number_of_employees  offset 1 limit 2;";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            // System.out.println(resultSet.getString("company")+"--"+resultSet.getInt("number_of_employees"));
            System.out.println(resultSet.getObject("company") + "--" + resultSet.getObject("number_of_employees"));
            //getObject(" ")--> ile de veri alabilir int mi string mi oldugu hatasi almamak icin
            //hic bir matematiksel ve string yani ozel bir data turunde yazdirmak istemedigim zaman object aliriz
            //yani her seyi getObhect ile alabiliriz

        }

        System.out.println("--------------------------------");
        //second way use the subquery
        String sql1 = "select  company, number_of_employees from companies\n" +
                "where  number_of_employees = (select max(number_of_employees)\n" +
                "from companies\n" +
                "where number_of_employees < 21000);";

        ResultSet resultSet1 = statement.executeQuery(sql1);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getObject("company") + "--" + resultSet1.getObject("number_of_employees"));
        }
        System.out.println("--------------------------------");

        connection.close();
        statement.close();
    }
}
