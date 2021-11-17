package sample.JPA.copper_price;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "usa_copper_stock_prices_log")
public class CopperStock {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @CreationTimestamp
    @Column(name = "creation_date")
    private Timestamp creationDate;
    @Column(name = "copper_kg_per_eur")
    private double copperStockPrice;
    @Column(name = "usd_to_eur_rate")
    private double usdToEurRate;

    public CopperStock() {
    }

    public CopperStock(double copperStock, double usdToEurRate) {
        this.copperStockPrice = copperStock;
        this.usdToEurRate = usdToEurRate;
    }

    public int getId() {
        return id;
    }

    public Timestamp getLogTime() {
        return creationDate;
    }

    public void setLogTime(Timestamp logTime) {
        this.creationDate = logTime;
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
