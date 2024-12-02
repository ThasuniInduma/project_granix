package Entity;

public class stockEntity {


    private String Stock_ID;
    private String Stock_name;
    private int Quantity;
    private Double PPU_kg;

    public stockEntity() {
    }

    public stockEntity(String Stock_ID, String Stock_name, int Quantity, Double PPU_kg) {
        this.Stock_ID = Stock_ID;
        this.Stock_name = Stock_name;
        this.Quantity = Quantity;
        this.PPU_kg = PPU_kg;
    }

    public String getStock_ID() {
        return Stock_ID;
    }

    public void setStock_ID(String stock_ID) {
        Stock_ID = stock_ID;
    }

    public String getStock_name() {
        return Stock_name;
    }

    public void setStock_name(String stock_name) {
        Stock_name = stock_name;
    }

    
    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Double getPPU_kg() {
        return PPU_kg;
    }

    public void setPPU_kg(Double pPU_kg) {
        PPU_kg = pPU_kg;
    }

    @Override
    public String toString() {
        return "stockEntity{" + "Stock_ID=" + Stock_ID + ", Stock_name=" + Stock_name + 
                 ", Quantity=" + Quantity + ", PPU_kg=" + PPU_kg + "}";
    }



}
