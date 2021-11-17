package sample.API.Dto;

import java.math.BigDecimal;

public class StocksDto {
    private String symbol;
    private String date;
    private Long volume;

    private BigDecimal close;
    private String currency;
    private BigDecimal adjClose;
    private BigDecimal price;
    private BigDecimal change;
    private BigDecimal dividend;
    private String name;

    public StocksDto(String symbol, String currency, BigDecimal price, BigDecimal change, String name) {
        this.symbol = symbol;
        this.currency = currency;
        this.price = price;
        this.change = change;
        this.name = name;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Stocks{" +
                "symbol='" + symbol + '\'' +
                ", date=" + date +
                ", volume=" + volume +
                ", close=" + close +
                ", currency='" + currency + '\'' +
                '}';
    }
}
