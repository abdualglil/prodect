import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;


public class Product { // كلاس الاصناف
  private   String p_name;
  private int p_quantity;
    private double p_price;
    private double s_price;
    private  TepalBaill pro=new TepalBaill();


    public Product() {
    }

    public Product(String p_name, int p_quantity,double p_price, double s_price)
/**
 * داله اضافة صنف جديد
  */
    {
        if (p_name.equals("")){
            JOptionPane.showMessageDialog(null,"يرجى ادخال اسم الصنف","خطاء",0);

        }
        else {


        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
           PreparedStatement  statement= c.prepareStatement ("INSERT INTO productTB (p_name,p_quantity,p_price,s_price,storage_price) VALUES (?,?,?,?,?)");
            statement.setString(1,p_name);
            statement.setInt(2,p_quantity);
            statement.setDouble(3,p_price);
            statement.setDouble(4,s_price);
            statement.setDouble(5,(p_quantity*p_price));
            int row=statement.executeUpdate();
            if (row>0)
            {
               JOptionPane.showMessageDialog(null,"تم اظافة الصنف بنجاح","تم",3);
            }

            c.close();

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        } }

    }
    private void updutplas(String name ,int p_quantity){
        try {
            int no;

            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
        Statement statement=c.createStatement();
        ResultSet re=statement.executeQuery("SELECT * FROM productTB");
        int a,t=0;
        double x,x1;
        int w;
        while (re.next()){
           if (re.getString("p_name").equals(name)) {
               a = re.getInt("p_quantity");
               w = a + p_quantity;
               x = re.getDouble("p_price");
               x1 = w * x;
               no=re.getInt("p_no");
               statement.executeUpdate("UPDATE productTB SET p_quantity=" + w + " WHERE p_no=" + no);
               statement.executeUpdate("UPDATE productTB SET storage_price=" + x1 + " WHERE p_no=" + no);
               t++;
               c.close();
           }

        }
        if (t==0){

               JOptionPane.showMessageDialog(null,"لا يوجد صنف بهاذا الاسم يرجى اظافة الصنف اولا ");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
   private void updutmynus(String name ,int p_quantity){
        try {
            int no;

            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");
            int a,t=0;
            double x,x1;
            int w;
            while (re.next()){
                if (re.getString("p_name").equals(name)) {
                    a = re.getInt("p_quantity");
                    w = a - p_quantity;
                    x = re.getDouble("p_price");
                    x1 = w * x;
                    no=re.getInt("p_no");
                    statement.executeUpdate("UPDATE productTB SET p_quantity=" + w + " WHERE p_no=" + no);
                    statement.executeUpdate("UPDATE productTB SET storage_price=" + x1 + " WHERE p_no=" + no);
                    
                    t++;
                    c.close();
                }

            }
            if (t==0){

                JOptionPane.showMessageDialog(null,"لا يوجد صنف بهاذا الاسم يرجى التحقق من اسم الصنف ");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    protected void updutmynusbill(int id_bil,String name ,int p_quantity,double s_price)
    /**
     *  داله انقاص من الخزون تستخدم في فواتير الشراء
     */
    {
        try {

            int no;
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");
            int a,t=0;
            double x,x1;
            int w;
            while (re.next()){

                if (re.getString("p_name").equals(name)) {
                  a = re.getInt("p_quantity");
                    w = a - p_quantity;
                    x = re.getDouble("p_price");
                    x1 = w * x;
                    no=re.getInt("p_no");
                    statement.executeUpdate("UPDATE productTB SET p_quantity=" + w + " WHERE p_no=" + no);
                    statement.executeUpdate("UPDATE productTB SET storage_price=" + x1 + " WHERE p_no=" + no);
                    t++;
                    c.close();

                    pro.addtepsal(id_bil,name,p_quantity,s_price);

                }
            }
            if (t==0){

                JOptionPane.showMessageDialog(null,"لا يوجد صنف بهاذا الاسم يرجى التحقق من اسم الصنف ");



            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    protected void updutmyplasbill(int id_bil,String name ,int p_quantity,double s_price,double b_pris)
    /**
     * داله اضافه للمخزون في حاله الشراء
     */
    {
        try {

            int no;
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");
            int a,t=0;
            double x,x1;
            int w;
            while (re.next()){

                if (re.getString("p_name").equals(name)) {
                    a = re.getInt("p_quantity");
                    w = a + p_quantity;
                    x = re.getDouble("p_price");
                    x1 = w * x;
                    no=re.getInt("p_no");
                    statement.executeUpdate("UPDATE productTB SET p_quantity=" + w + " WHERE p_no=" + no);
                    statement.executeUpdate("UPDATE productTB SET storage_price=" + x1 + " WHERE p_no=" + no);
                    statement.executeUpdate("UPDATE productTB SET p_price=" + s_price + " WHERE p_no=" + no);
                    statement.executeUpdate("UPDATE productTB SET s_price=" + b_pris + " WHERE p_no=" + no);
                    t++;
                    c.close();
                    TepalBaill pro=new TepalBaill();
                    pro.addtepbuin(id_bil,name,p_quantity,s_price);

                }
            }
            if (t==0){

                JOptionPane.showMessageDialog(null,"لا يوجد صنف بهاذا الاسم يرجى التحقق من اسم الصنف ");



            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

public DefaultTableModel Stock_review()
/**
 * داله عرض المخزون كاملل علي هيئه جدول للواجهه
 */
{
    DefaultTableModel model =new DefaultTableModel(0,6);
    String aaa[]={"اجمالي الكمية" ,"سعر البيع", "سعر الشراء", "الكمية", "اسم الصنف" ,"رقم الصنف"};
    model.setColumnIdentifiers(aaa);


    try {

        Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
        Statement statement=c.createStatement();
        ResultSet re=statement.executeQuery("SELECT * FROM productTB");

        while (re.next()){
            if (re.getInt("p_quantity")>0){
            Object[] rew={re.getDouble("storage_price"),re.getDouble("s_price"),re.getDouble("p_price"),re.getInt("p_quantity")
            ,re.getString("p_name"),re.getInt("p_no")};
            model.addRow(rew);}

        }
        c.close();

    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
    return model;
}
    public double Inventory_value()
    /**
     * داله تحسب قيمه المخزون كامل
     */
    {double sum=0;
        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");

            while (re.next()){
                sum=sum+re.getDouble("storage_price");

            }

            c.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

return sum;
}


    public DefaultTableModel Stoureg(String name)
    /**
     * داله البحث عن عنصر في المخزون وعرض مخزونه
     */
    {
        DefaultTableModel model =new DefaultTableModel(0,6);
        String aaa[]={"اجمالي الكمية" ,"سعر البيع", "سعر الشراء", "الكمية", "اسم الصنف" ,"رقم الصنف"};
        model.setColumnIdentifiers(aaa);
        try {


            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");
            int t=0;

            while (re.next()){
                if (re.getString("p_name").equals(name)) {
                    Object[] rew={re.getDouble("storage_price"),re.getDouble("s_price"),re.getDouble("p_price"),re.getInt("p_quantity")
                            ,re.getString("p_name"),re.getInt("p_no")};
                    model.addRow(rew);
                    t++;
                }
                c.close();
            }
            if (t==0){

                JOptionPane.showMessageDialog(null,"لا يوجد صنف بهاذا الاسم يرجى اظافة الصنف اولا ");

            }

        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return model;
    }

    public double getP_price(String name)
    /**
     * داله ترجع سعر صنف بنائن علي اسمه
     */
    {

        try {


            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");

            while (re.next()){

                if (re.getString("p_name").equals(name)) {
                    p_price=re.getDouble("p_price");

                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return p_price;
    }


    public int getp_quantity(String name)
    /**
     * داله ترجع كميه مخزون صنف بنائنا علي اسمه
     */
    {



        try {


            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM productTB");

            while (re.next()){

                if (re.getString("p_name").equals(name)) {
                    p_quantity=re.getInt("p_quantity");

                }
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }





        return p_quantity;
    }
}
