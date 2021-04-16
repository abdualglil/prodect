import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;


public class BillsBuing {// كلاس فاتوره بيع
    private int No_bill;
    private String name;
    private String date;
    private double sum;
    private Product product = new Product();
    private BoxAwomwnt bo=new BoxAwomwnt();

    public BillsBuing() {
    }

    public double getSum(int no) {
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement = c.createStatement();
            ResultSet re = statement.executeQuery("SELECT total FROM bills WHERE id_bil=" + no);
            while (re.next()) {
                sum = re.getDouble("total");
                c.close();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sum;
    }


    public int getNo_bill(String name) {
        int a = 0;
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement = c.createStatement();
            ResultSet re = statement.executeQuery("SELECT * FROM bills");

            while (re.next()) {
                if (re.getString("name").equals(name)) {
                    a = re.getInt("id_bil");
                    c.close();
                }
                c.close();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return a;

    }

    public BillsBuing(String name,String date) {

        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            PreparedStatement st = c.prepareStatement("INSERT INTO bills (name,total,date) VALUES (?,?,?)");
            st.setString(1, name);
            st.setDouble(2, 0);
            st.setString(3, date);
            int row=st.executeUpdate();
            c.close();
        }

     catch(
    SQLException throwables)

    {
        throwables.printStackTrace();
    }

}

public void addprpdect(int id_bil,String name_product,int p_quantity,double s_price,double b_pris){


    product.updutmyplasbill(id_bil,name_product,p_quantity,s_price,b_pris);
    Connection c= null;
    try {
        sum=0;
        c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
        Statement statement=c.createStatement();
        ResultSet re=statement.executeQuery("SELECT Total_item FROM tapl_bli WHERE id_bil="+ id_bil);
        while (re.next()){
            sum=sum+re.getDouble("Total_item");
        }
        statement.executeUpdate("UPDATE bills SET total=" + sum + " WHERE id_bil=" + id_bil);
        bo.adddaen(id_bil,sum);
        c.close();



    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }


}
    public DefaultTableModel model(int bill){
        DefaultTableModel model =new DefaultTableModel(0,4);
        String aaa[]={"الإجمالي", "سعر الوحدة", "الكمية", "اسم الصنف"};
        model.setColumnIdentifiers(aaa);
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement = c.createStatement();
            ResultSet re = statement.executeQuery("SELECT * FROM tapl_bli WHERE id_bil=" + bill);
            while (re.next()) {
                Object[] raw={ re.getDouble("Total_item"),re.getDouble("s_price"),re.getInt("p_quantity"),re.getString("product")};
                model.addRow(raw);



            }
            c.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return model;
    }
    public ArrayList Searchname(int idbill){
        int se=0;
        ArrayList<String> sear=new ArrayList<>();
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
        Statement statement=c.createStatement();
        ResultSet re=statement.executeQuery("SELECT * FROM bills ");
        while (re.next()){
            if(re.getInt("id_bil")==idbill){
            sear.add(re.getString("name"));
            sear.add(re.getString("total"));
            sear.add(re.getString("date"));
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


return sear;

    }


}
