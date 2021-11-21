package sample.JPA.copper_price;




public class ObservableCopperStockLog {
    private int id;
    private String creationDate;
    private double copperStockPrice;
    private double usdToEurRate;

    public ObservableCopperStockLog(int id, String creationDate, double copperStockPrice, double usdToEurRate) {
        this.id = id;
        this.creationDate = creationDate;
        this.copperStockPrice = copperStockPrice;
        this.usdToEurRate = usdToEurRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public double getCopperStockPrice() {
        return copperStockPrice;
    }

    public void setCopperStockPrice(double copperStockPrice) {
        this.copperStockPrice = copperStockPrice;
    }

    public double getUsdToEurRate() {
        return usdToEurRate;
    }

    public void setUsdToEurRate(double usdToEurRate) {
        this.usdToEurRate = usdToEurRate;
    }
}
