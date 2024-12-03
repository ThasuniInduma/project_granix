package Entity;

public class salesEntity {
    private String Stock_ID;
    private String Buyer_ID;
    private Double Quantity_obtained;

    public salesEntity() {
    }

    public salesEntity(String Stock_ID, String Buyer_ID, Double Quantity_obtained) {
        
        this.Stock_ID = Stock_ID;
        this.Buyer_ID = Buyer_ID;
        this.Quantity_obtained = Quantity_obtained;
        
    }

    public String getStock_ID() {
        return Stock_ID;
    }

    @Override
    public String toString() {
        return "salesEntity{"+"Stock_ID=" + Stock_ID + ", Buyer_ID=" + Buyer_ID + ", Quantity_obtained=" + Quantity_obtained
                + "}";
    }

    public void setStock_ID(String stock_ID) {
        Stock_ID = stock_ID;
    }

    public String getBuyer_ID() {
        return Buyer_ID;
    }

    public void setBuyer_ID(String buyer_ID) {
        Buyer_ID = buyer_ID;
    }

    public Double getQuantity_obtained() {
        return Quantity_obtained;
    }

    public void setQuantity_obtained(Double quantity_obtained) {
        Quantity_obtained = quantity_obtained;
    }
}
