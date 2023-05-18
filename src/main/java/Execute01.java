import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1. Adım: Driver'a kaydol ==> JDBC 4 sonrası gerek yoktur.
        Class.forName("org.postgresql.Driver");//eski bir yontem

        //2. Adım: Database'e bağlan
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Tufan.7106");

        //3. Adım: Statement oluştur
        Statement statement = connection.createStatement();

        //4. Adım: Query çalıştır
        /*
        1-Eger execute() methodu DDL create,drop , alter ile kullanirlirsa false doner.
        2-eger execute() methodu DQL select ile kulanirlirsa data donerse true , donmezse bos doner.

        execute() methodu parametre olarak girilen stringsql komutunu batli oldugu database uzerinde uygular.
         */
        boolean sqli1 = statement.execute("create table workers (\tworker_id varchar(20),worker_name varchar(20),\tworker_salary int);");
        System.out.println(sqli1);

        //2.ornek: workes table'ina address ekeleyiniz.
        String sqlQuery1 = "alter table workers add worker_address varchar(80);";
        boolean sqli2 = statement.execute(sqlQuery1);
        System.out.println(sqli2);



        //3. Örnek: worker table'ı siliniz
        String sqlQuery2 = "DROP TABLE workers";
        boolean sql3 = statement.execute(sqlQuery2);
        System.out.println("sql3 = " + sql3);

        //5. Adım: Bağlantıyı kapat
        connection.close();
        statement.close();



    }
}