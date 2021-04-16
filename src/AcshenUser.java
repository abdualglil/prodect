import javax.swing.*;
import java.sql.*;

public class AcshenUser {// كلاس المستخمين
    private String user;
    private int baswrd;

    public AcshenUser() {
    }

    public boolean Achsen(String user,String baswrd)

    {
        boolean bool=false;
        try {
            Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM user ");
            while (re.next()){
                if(user.equals(re.getString("name")) && baswrd.equals(re.getString("pasoerd"))){
                    bool= true;
                }

            }c.close();



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bool;
    }

    public AcshenUser(String nema,String baswrd,String baswrd2)
    {
        if (baswrd.equals(baswrd2)){
            try {
                Connection c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
                PreparedStatement  statement= c.prepareStatement ("INSERT INTO user (name,pasoerd) VALUES (?,?)");
                statement.setString(1,nema);
                statement.setString(2,baswrd);
                int row=statement.executeUpdate();
                if (row>0)
                {
                    JOptionPane.showMessageDialog(null,"تم اظافة المستخدم بنجاح","تم",3);
                }
                c.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        else {
            JOptionPane.showMessageDialog(null,"خطاء في كلمة المرورو وتاكيد كلكة المرور","خطاء",0);
        }


    }

    public void updatebaswred(String nema,String oldbaswrd,String newbaswrd){
            int a=0;
            int id;

        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM user");
            while (re.next()){

                if (re.getString("name").equals(nema) && oldbaswrd.equals(re.getString("pasoerd"))) {
                    id=re.getInt("id");
                   statement.executeUpdate("UPDATE user SET pasoerd=" + newbaswrd +" WHERE id=" + id);

                 JOptionPane.showMessageDialog(null,"تم تعديل كلمة المرور بنجاح");

                    a++;


               }


            }
                if(a==0) {
                JOptionPane.showMessageDialog(null,"خطاء في كلمة المرورو ","خطاء",0);
                          }


                c.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        
    }


        }


