public class Runner {
    public static void main(String[] args) {
        //database'e baglan
        JDBCUtils.connectToDatabase();

        //statement olustur
        JDBCUtils.createStatement();

        //Query calistirma
        String sql = "CREATE TABLE companies(\n" +
                "company_id SMALLINT,\n" +
                "company VARCHAR(20),\n" +
                "number_of_employees SMALLINT\n" +
                ");";
        JDBCUtils.execute(sql);

        //ExecuteQuery calıstırma









    }
}
