//import jdk.internal.access.JavaIOFileDescriptorAccess;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountryTest {

    /*
 Given
   User connects to the database
   (Kullanıcı veritabanına bağlanır)

 When //yapilacak islemi belirtir
   User sends the query to get the region ids from "countries" table
   (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

 Then //en son dogurlama yapilan yer
   Assert that the number of region ids greater than 1 is 17.
   (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

 And //coklu islemlerin yapildigi yer
   User closes the connection
*/

    @Test

    public void countryTest() throws SQLException {//test methodlarini parametreleri olmaz.

        //User connects to the database
        JDBCUtils.connectToDatabase();
        JDBCUtils.createStatement(); //olusan statmente ile asagidaki kod calistirilir


        // User sends the query to get the region ids from "countries" table
        String sql = "select count (region_id) from countries\n" +
                "\twhere region_id>1;";

        ResultSet resultSet = JDBCUtils.executQuery(sql);

        //Assert that the number of region ids greater than 1 is 20.
        int number=0;
        while(resultSet.next()){
           number =  resultSet.getInt(1);
        }
       // System.out.println(number);
        assertEquals(20,number);

    }

    @Test
    public void countryTest2() throws SQLException {

        JDBCUtils.connectToDatabase();
        JDBCUtils.createStatement(); //olusan statmente ile asagidaki kod calistirilir

        String sql = "select region_id from countries\n" +
                "\twhere region_id>1;";

        ResultSet resultSet = JDBCUtils.executQuery(sql);
        List<Integer> region_idList = new ArrayList<>();
        while(resultSet.next()){
            region_idList.add(resultSet.getInt(1));
           // region_idList.add(resultSet.getInt("region_id")); //bu sekilde de yazilabilir
        }
        System.out.println(region_idList);

        assertEquals(20,region_idList.size());//region_id listinin eleman sayisinin 20 olmasi gerekir

        //User closes the connection
        JDBCUtils.closeConnection();

    }

}
