import java.text.SimpleDateFormat;
import java.util.Date;

public class TestbillBuing {
    public static void main(String[] args) {
     /*   Date date=new Date();
        SimpleDateFormat de=new SimpleDateFormat("yyyy/MM/dd");
       BillsBuing be=new BillsBuing("abdualgliljlhj",de.format(date)+"");
        int a =be.getNo_bill("abdualgliljlhj");
        be.addprpdect(a,"دقيق",2,5500,600);
        be.addprpdect(a,"ارز",1,600,6500);
        be.addprpdect(a,"سكر",3,3000,3500);

      //  System.out.println(be.getSum(a));*/

        BillsBuing buing=new BillsBuing();
        System.out.println(buing.Searchname(34));
        BillSall billSall=new BillSall();
        System.out.println(billSall.Searchname(125));



    }
}
