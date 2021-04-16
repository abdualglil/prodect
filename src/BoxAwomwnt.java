import java.sql.*;

public class BoxAwomwnt {// كلاس الصنجوق
    private double sum;
    private double daen;
    private double maden;
    public BoxAwomwnt() {
    }

    public void addmaden(int id_mov1, double maden){
        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM BoxAm");
            int a=0;
            while (re.next())
            {
                if(re.getInt("id_mov")==id_mov1){
                    a++;
                    statement.executeUpdate("UPDATE BoxAm SET maden=" + maden + " WHERE id_mov=" + id_mov1);
                    c.close();
                    break;
                }

            }
            if (a==0){
                PreparedStatement  st= c.prepareStatement ("INSERT INTO BoxAm (id_mov,maden,daen) VALUES (?,?,?)");
                st.setInt(1,id_mov1);
                st.setDouble(2,maden);
                st.setDouble(3,0.0);
                int row=st.executeUpdate();
                System.out.println(maden);
                c.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public void adddaen(int id_mov1, double daen){
        try {
            Connection c= DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM BoxAm");
            int a=0;
            while (re.next())
            {

                if(re.getInt("id_movd")==id_mov1){
                    a++;
                    statement.executeUpdate("UPDATE BoxAm SET daen=" + daen + " WHERE id_movd=" + id_mov1);
                    c.close();
                    break;
                }

            }
            if (a==0){
                PreparedStatement  st= c.prepareStatement ("INSERT INTO BoxAm (id_movd,maden,daen) VALUES (?,?,?)");
                st.setInt(1,id_mov1);
                st.setDouble(2,0.0);
                st.setDouble(3,daen);
                int row=st.executeUpdate();
                c.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    public String Total(){
        double total = 0;
        double sumM=0;
        double sumD=0;
        Connection c= null;
        try {
            c = DriverManager.getConnection("jdbc:ucanaccess://M:\\db.accdb");
            Statement statement=c.createStatement();
            ResultSet re=statement.executeQuery("SELECT * FROM BoxAm");
            while (re.next()){
                sumM=sumM+re.getDouble("maden");
                sumD=sumD+re.getDouble("daen");

            }
            total=sumD-sumM;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        if (total<0){
            return "مدين     "+(-1*total);
        }
        else
        {
            return "دائن      "+(total);
        }
    }



}
