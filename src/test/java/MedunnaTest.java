import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MedunnaTest {

    /*
Given
 User connects to the database
 (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

When
 User sends the query to get the created room
 (Kullanıcı oluşturulan odayı getirmek için sorgu gönderir)

Then
 Assert that room is created properly
 (Odanın düzgün kaydedildiğini doğrular)

And
 User closes the connection

*/

    @Test
    public void medunnaTest() throws SQLException {

        //User connects to the database
        JDBCUtils.connectToMedunnaDataBase();
        JDBCUtils.createStatement();


        //User sends the query to get the created room
        String sql = "select * from room where room_number = 9995033";
        ResultSet resultSet = JDBCUtils.executQuery(sql);


        //Assert that room is created properly
        resultSet.next();
        //  assertEquals("123.00",resultSet.getObject("price"));
        assertEquals("123.00",resultSet.getString("price"));
        assertEquals("DataBase Test İçin Oluşturuldu",resultSet.getString("description"));
        assertEquals("mark_twain",resultSet.getString("created_by"));


        // User closes the connection
        JDBCUtils.closeConnection();


    }

    @Test
    public void medunnaTest1() throws SQLException {
        JDBCUtils.connectToMedunnaDataBase();
        JDBCUtils.createStatement();

        String sql = "select * from room where room_type= 'TWIN'";
        ResultSet resultSet = JDBCUtils.executQuery(sql);

        resultSet.next();
        assertEquals("t",resultSet.getString("status"));

        JDBCUtils.closeConnection();

    }

    @Test
    public void medunnaTest2() throws SQLException{
        JDBCUtils.connectToMedunnaDataBase();
        JDBCUtils.createStatement();

        String sql = "select * from staff where last_name= 'Koyuncu'";
        ResultSet resultSet = JDBCUtils.executQuery(sql);

        resultSet.next();
        assertEquals("3453453456",resultSet.getString("phone"));

        JDBCUtils.closeConnection();
    }
}
