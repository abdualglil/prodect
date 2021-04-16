public class Prodect {

    private String name_p;
   private int p_quantity;
   private double s_price;

    @Override
    public String toString() {
        return "Prodect{" +
                "name_p='" + name_p + '\'' +
                ", p_quantity=" + p_quantity +
                ", s_price=" + s_price +
                '}';
    }

    public Prodect(String name_p, int p_quantity, double s_price) {

        this.name_p = name_p;
        this.p_quantity = p_quantity;
        this.s_price = s_price;
    }


    public String getName_p() {
        return name_p;
    }

    public void setName_p(String name_p) {
        this.name_p = name_p;
    }

    public int getP_quantity() {
        return p_quantity;
    }

    public void setP_quantity(int p_quantity) {
        this.p_quantity = p_quantity;
    }

    public double getS_price() {
        return s_price;
    }

    public void setS_price(int s_price) {
        this.s_price = s_price;
    }

}
