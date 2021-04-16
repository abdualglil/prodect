import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestBox {
    public static void main(String[] args) {
       Date date=new Date();boolean b=true;
        SimpleDateFormat de=new SimpleDateFormat("yyyy/MM/dd");

        System.out.println(de.format(date));
        AcshenUser acshenUser=new AcshenUser();
        char a[]={7,7,6,3,2,0,1,4,1};
         b=acshenUser.Achsen("عبدالجليل بخاش","776320141");
        System.out.println(b);
    }
}
