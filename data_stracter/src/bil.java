import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class bil {
    private int id;
   private String name;
   private String  total;
    private String date;
    SinglyLinkedList<Prodect> prod=new SinglyLinkedList<>();

    public bil(int id) {
        this.id = id;
    }

    public void Searchname(int idbill){
        // تم استخدام arreylist
        /**
         * داله البحث عن فاتوره باستخدام رقم الفاتوره
         */
        int se=0;
        ArrayList<String> sear=new ArrayList<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM billsal ");
            while (re.next()){
                if(re.getInt("id_bil")==idbill){
                    name=re.getString("name");
                    total=re.getString("total");
                    date=re.getString("date");
                    prod_pill(idbill);
                    se++;
                }

                c.close();

            }
            if(se==0) {
                JOptionPane.showMessageDialog(null,"لا توجد فاتوره بهاذا الرقم","خطاء",3);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }



    }

    public void prod_pill(int bill){
        /**
         *  داله انشاء جدول اصناف الفاتوره للواجه برقم الفاتورهi
         */
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement = c.createStatement();
            ResultSet re = statement.executeQuery("SELECT * FROM tapl_blisal WHERE id_bil=" + bill);
            while (re.next()) {
                Prodect b=new Prodect(re.getString("product"),re.getInt("p_quantity"),re.getDouble("s_price"));
                prod.addFirst(b);
            }
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void pri1() {
        System.out.println("bil{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", total='" + total + '\'' +
                ", date='" + date + '\'' +
                '}');

        System.out.println(prod.print());

    }
}
