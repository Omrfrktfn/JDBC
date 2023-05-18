import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JunitTest01 {

    @Test //test yaparken main ile bir isimiz yok @Test yaparak islemlere devam ediyoruz.
    //M.pom olan yerden sirket tarafinda verilen repositori eklenip refres islemi yaptiktan sonra devam edilir.Birkere olmsai yeterli.

    public void test1() {

        assertEquals(2, 2); //bu methodun parentez icindeki parametreleri esitse pass olur degilse fail olur.
        assertTrue("helle".contains("e"));//bu methodun parentez ici true ise pass olur degilse fail olur.
//Yukaridaki iki kod test yapmada kullanmamiz gereken olan kod.
        //Eger bir yerde hata alirsa devam etmez direk hata verir.
    }


}
