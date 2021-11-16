package sample.JPA.copper_price;

//Singleton objektas
public class CopperStockHolder {

    private static CopperStock copperStock;
    private final static CopperStockHolder INSTANCE = new CopperStockHolder();

    public CopperStockHolder() {}

    public static CopperStockHolder getInstance() {
        return INSTANCE;
    }

    public void setCopperStock(CopperStock c) {
        this.copperStock = c;
    }

    public CopperStock getCopperStock() {
        return this.copperStock;
    }
}
