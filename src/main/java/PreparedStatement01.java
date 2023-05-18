import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        Statement statement = connection.createStatement();//kendimiz olusturdugumuz icin buna gerek yok.data okumak icin lazim

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.
        /*
        PreparedStatement olusturmak icin :
        -PreparedStatement query'sini olustur.
         */
        String sql = "update companies set number_of_employees=?\n" +
                "where company=? "; //? isareti paremetrelendirme demektir .Bunun yerine baska degerlerde alabilir manasinda
        //2.step: PreparedStatement objesi olustur.
       PreparedStatement preparedStatement= connection.prepareStatement(sql);

       //3.step: preparedStatement objesi ile setInt gibi methodlarla soru isaretli olan yerlere koymak istedigmiz degerleri yerlestiriyoruz
        preparedStatement.setInt(1,9999);
        preparedStatement.setString(2,"IBM");

        //4.step: query'i calistir
        int guncelleneneSatirSayisi = preparedStatement.executeUpdate();
        System.out.println("guncelleneneSatirSayisi = " + guncelleneneSatirSayisi);

        //5.step:Guncelleme sonrasi yeni tableyi okuma
        String sql1 = "Select * from companies";
        ResultSet r1 = statement.executeQuery(sql1);
        while (r1.next()){
            System.out.println(r1.getObject(1)+"--"+r1.getObject(2)+"--"+r1.getObject(3));;
        }

       // statement.close();

        System.out.println("*********************************************");
        //2. Örnek: Prepared statement kullanarak company adı CASPER olan number_of_employees değerini 5000 olarak güncelleyin.

        preparedStatement.setInt(1,5000);
        preparedStatement.setString(2,"CASPER");

        int guncelleneneSatirSayisi1 = preparedStatement.executeUpdate();
        System.out.println("guncelleneneSatirSayisi1 = " + guncelleneneSatirSayisi1);

        //String sql3 = "Select * from companies";
        ResultSet r2 = statement.executeQuery(sql1);
        while (r2.next()){
            System.out.println(r2.getObject(1)+"--"+r2.getObject(2)+"--"+r2.getObject(3));;
        }

        System.out.println("****************");
        //2. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 5000 olarak güncelleyin.
        preparedStatement.setInt(1,8888);
        preparedStatement.setString(2,"GOOGLE");

        int guncelleneneSatirSayisi2 = preparedStatement.executeUpdate();
        System.out.println("guncelleneneSatirSayisi1 = " + guncelleneneSatirSayisi2);

        //String sql3 = "Select * from companies";
        ResultSet r3 = statement.executeQuery(sql1);
        while (r3.next()){
            System.out.println(r3.getObject(1)+"--"+r3.getObject(2)+"--"+r3.getObject(3));;
        }
        connection.close();

    }
}
