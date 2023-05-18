import java.sql.*;

public class CallableStatement01 {
    public static void main(String[] args) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");
        Statement statement = connection.createStatement();

        //selamlama yapan bir function olusturup calistiriniz.
        //callable statement adimlari
        //1.adim : function kodunu yaziniz
        String sql = "create or replace function selamlama(x TEXT) returns TEXT as  $$ begin\n" +
                "return 'Merhaba ' || x || ', nasilsin'; --|| X || birlestirme islemi yapar.\n" +
                "end;\n" +
                "$$\n" +
                "language plpgsql;";

        //2.adim: funtion icin olusturulan kodu yaziniz.
        statement.execute(sql);//function olusturan query'i calistirdik.

        //     String sql1 = "select selamlama('ali')";//burasi callable statement kullanamdan fuction cagirma islemi
        //      ResultSet r1 = statement.executeQuery(sql1);//fuction'i parametresi ile cagirdik. Bize bir table dondu.
        //     r1.next();
        //   System.out.println(r1.getObject(1));

        //1.ornek :selamlama yapan bir function callable statement ile cagiriniz.
        //3.adim : funtion'u cagirma islemi.
       CallableStatement c1= connection.prepareCall("{? = call selamlama(?)}");//select selamlama('ali') bu islemin paremetreli hali
        //ilk soru isareti return type'i degistiri digeri ise paremetrenin degerini gosterir

        //4.adim : return icin registerOutParameter() methodunu parametreler icin
        c1.registerOutParameter(1,Types.VARCHAR);
        c1.setString(2,"Ali");

        //5.adim : execute(); methodu ile callableStatment calistirma islemi yapilir
        c1.execute();

        //6.adim : sonucu gormek icin callableStatment'dan data turunu cagiralim
        System.out.println(c1.getString(1));
      //  System.out.println(c1.getString("selamlama"));

        System.out.println("******************************************************************************************");
        //2.ornek: iki sayiyi toplayan bir funtion yaziniz ve callableStatement ile cagiriniz.

        String sql1 = "create or replace function toplama(x int , y int)\n" +
                "returns NUMERIC --HATA VERMEMESI ICN INT YAPMADIK\n" +
                "as\n" +
                "$$\n" +
                "begin\n" +
                "\n" +
                "RETURN x+y;\n" +
                "\n" +
                "end;\n" +
                "$$\n" +
                "LANGUAGE plpgsql;";

        statement.execute(sql1);


        CallableStatement c2= connection.prepareCall("{? = call toplama(?, ?)}");

        c2.registerOutParameter(1,Types.NUMERIC);
        c2.setInt(2,5);
        c2.setInt(3,4);

        c2.execute();
        System.out.println(c2.getObject(1));




        connection.close();
        statement.close();

    }


}
