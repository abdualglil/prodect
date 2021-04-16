import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class BillSall {    // كلاس فاتوره شراء

        private double sum;
        private Product product = new Product();
        private BoxAwomwnt bo=new BoxAwomwnt();

    public BillSall() {
    }
    public double getSum(int no) {
        /**
         * داله تعيد اجمالي الفاتوره
         */
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
                Statement statement = c.createStatement();
                ResultSet re = statement.executeQuery("SELECT total FROM billSal WHERE id_bil=" + no);
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
                /**
                 * داله تعيد رقم الفاتوره بنائن علي اسم الفاتوره
                 */
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
                Statement statement = c.createStatement();
                ResultSet re = statement.executeQuery("SELECT * FROM billSal");
                while (re.next()) {
                    if (re.getString("name").equals(name)) {
                        a = re.getInt("id_bil");

                    }

                }
                c.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return a;

        }

        public BillSall(String name,String date) {

            try {
                /**
                 * داله انشاء فاتوره جديده
                 */
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
                PreparedStatement st = c.prepareStatement("INSERT INTO billSal (name,total,date) VALUES (?,?,?)");
                st.setString(1, name);
                st.setDouble(2, 0);
                st.setString(3,date);
                int row=st.executeUpdate();
                c.close();
            }

            catch(
                    SQLException throwables)

            {
                throwables.printStackTrace();
            }

        }


        public void addprpdect(int id_bil,String name_product,int p_quantity,double s_price){
            /**
             * داله اضافه صنف للفاتوره
             */

        if(s_price>=product.getP_price(name_product)){
            if(p_quantity<=product.getp_quantity(name_product)){
                product.updutmynusbill(id_bil,name_product,p_quantity,s_price);
                Connection c= null;
                try {
                    sum=0;
                    c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
                    Statement statement=c.createStatement();
                    ResultSet re=statement.executeQuery("SELECT Total_item FROM tapl_blisal WHERE id_bil="+ id_bil);
                    while (re.next()){

                        sum=sum+re.getDouble("Total_item");


                    }
                    statement.executeUpdate("UPDATE billSal SET total=" + sum + " WHERE id_bil=" + id_bil);

                    c.close();




                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                bo.addmaden(id_bil,getSum(id_bil));

            }
            else {
                JOptionPane.showMessageDialog(null,"لا يوجد مخزون كافي \n"+"المخزون المتوفر ="+product.getp_quantity(name_product)+" ","خطاء",0);
            }
        }

        else {
            JOptionPane.showMessageDialog(null,"سعر البيع اقل من سعر التكلفة \n"+"سعر التكلفة ="+product.getP_price(name_product)+" ","خطاء",0);


        }


        }

        public DefaultTableModel model(int bill){
            /**
             * داله انشاء جدول اصناف الفاتوره للواجه برقم الفاتورهه
             */
            DefaultTableModel model =new DefaultTableModel(0,4);
            String aaa[]={"الإجمالي", "سعر الوحدة", "الكمية", "اسم الصنف"};
            model.setColumnIdentifiers(aaa);
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
                Statement statement = c.createStatement();
                ResultSet re = statement.executeQuery("SELECT * FROM tapl_blisal WHERE id_bil=" + bill);
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

