import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TepalBaill {// كلاس جدول الفواتير
    private int id_bil;
    private String name_product;
    private int p_quantity;
    private double s_price;
    private double Total_item;



    public TepalBaill() {
    }
    public void addtep(int id_bil,String name_product,int p_quantity,double s_price){

        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            PreparedStatement statement= c.prepareStatement ("INSERT INTO tapl_bli (id_bil,product,p_quantity,s_price,Total_item) VALUES (?,?,?,?,?)");
            statement.setInt(1,id_bil);
            statement.setString(2,name_product);
            statement.setInt(3,p_quantity);
            statement.setDouble(4,s_price);
            statement.setDouble(5,(p_quantity*s_price));
            int row=statement.executeUpdate();
            if (row>0)
            {

            }

            c.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addtepsal(int id_bil,String name_product,int p_quantity,double s_price){

        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            PreparedStatement statement= c.prepareStatement ("INSERT INTO tapl_blisal (id_bil,product,p_quantity,s_price,Total_item) VALUES (?,?,?,?,?)");
            statement.setInt(1,id_bil);
            statement.setString(2,name_product);
            statement.setInt(3,p_quantity);
            statement.setDouble(4,s_price);
            statement.setDouble(5,(p_quantity*s_price));
            int row=statement.executeUpdate();
            if (row>0)
            {

            }

            c.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addtepbuin(int id_bil,String name_product,int p_quantity,double s_price){

        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            PreparedStatement statement= c.prepareStatement ("INSERT INTO tapl_bli (id_bil,product,p_quantity,s_price,Total_item) VALUES (?,?,?,?,?)");
            statement.setInt(1,id_bil);
            statement.setString(2,name_product);
            statement.setInt(3,p_quantity);
            statement.setDouble(4,s_price);
            statement.setDouble(5,(p_quantity*s_price));
            int row=statement.executeUpdate();
            if (row>0)
            {

            }

            c.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
