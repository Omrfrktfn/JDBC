import java.sql.*;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        Statement statement = connection.createStatement();

        //1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql = "update companies set number_of_employees = 16000\n" +
                "where  number_of_employees < (select avg(number_of_employees) from companies);";
        int updateEdilenSatirSayisi = statement.executeUpdate(sql);//kac tane satir degistini gosterigi icin int kullaniriz.
        System.out.println("updateEdilenSatirSayisi:" + updateEdilenSatirSayisi);

        //udpdate sonrasi datayi okumak icin DQL kullaniryoruz.
        String sql1 ="SELECT * FROM companies;";

        ResultSet r1 = statement.executeQuery(sql1);
        while (r1.next()){
            System.out.println(r1.getObject(1)+"--"+r1.getObject(2)+"--"+r1.getObject(3));
        }

        connection.close();
        statement.close();
    }
}
