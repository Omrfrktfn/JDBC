import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        Statement statement = connection.createStatement();

        //1.ornek: region_id'si 1 olan bilgileri cagir
        /*
        execute(); data cagirirken kullanmak cok uygun degil.
        satirlari gormek icin executeQuery methodunu kullanmaliyiz.
        */
        String sql1 = "select country_name from countries where region_id=1;";
        boolean r1 = statement.execute(sql1);
        System.out.println(r1);//DQL ile data dondugu icin true verir.

        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
            //System.out.println(resultSet.getString("Country_name")); //ikinci yazim sekli
        }

        // 2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.
        // SELECT * FROM countries
        System.out.println("***************************************************");
        String sql2 = "select country_id,country_name from countries where region_id>2;";

        ResultSet resultSet1 = statement.executeQuery(sql2);

        while (resultSet1.next()) { //resultSet1 son satira gelip false verdikten sonra kapanir. Kapali resultset1 uzerinde islem yapilirsa exception atar.
            System.out.println(resultSet1.getString(1) + " -- " + resultSet1.getString(2));
        }
        //resultSet1.next() --> while icindeki anlami satirlar oldugu surece devam et manasinda



        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.
        System.out.println("***************************************************");
        String sql3 = "select  * from companies where number_of_employees=(select min(number_of_employees) from companies);";

        ResultSet resultSet2 = statement.executeQuery(sql3);

        while (resultSet2.next()) {
          //  System.out.println(resultSet2.getInt(1) + " -- " + resultSet2.getString(2) + " -- " + resultSet2.getInt(3) );
            System.out.println(resultSet2.getInt("company_id")+"--"+resultSet2.getString("company")+"--"+resultSet2.getInt("number_of_employees"));
        //yukaridaki gibi isim seklinde de cagrilabilir
        }
        System.out.println("***************************************************");

        connection.close();
        statement.close();

    }

}
