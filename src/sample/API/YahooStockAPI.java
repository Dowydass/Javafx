package sample.API;


import sample.API.Dto.StocksDto;
import sample.JPA.copper_price.CopperStockHolder;
import sample.JPA.copper_price.CopperStock;
import sample.JPA.copper_price.CopperStockDAO;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;

public class YahooStockAPI {


    public StocksDto getRealTimeRate() throws IOException {

        // Gaunamas vario kursas per svarą JAV rinkoje
        Stock cooperStock = YahooFinance.get("HG=F");
        // Gaunamas euro valiutos kursas JAV rinkoje
        Stock usdToEuroCurrency = YahooFinance.get("EUR=X");

        String currency = "EUR";
        String name = cooperStock.getName();
        String symbol = cooperStock.getSymbol();
        double pricePoundPerUsd = cooperStock.getQuote().getPrice().doubleValue();
        double usdToEurRatio = usdToEuroCurrency.getQuote().getPrice().doubleValue();

        BigDecimal change = cooperStock.getQuote().getChangeInPercent();

        double copperKgPerEur = pricePoundPerUsd * usdToEurRatio * 2.20462;
        BigDecimal price = BigDecimal.valueOf(copperKgPerEur);



        StocksDto stocksDto = new StocksDto(symbol,currency, price, change, name);
        getYeahooStockData();
        return stocksDto;
    }

    public static void getYeahooStockData() throws IOException {

        // Gaunamas vario kursas per svarą JAV rinkoje
        Stock cooperStock = YahooFinance.get("HG=F");
        // Gaunamas euro valiutos kursas JAV rinkoje
        Stock usdToEuroStock = YahooFinance.get("EUR=X");

        double pricePoundPerUsd = cooperStock.getQuote().getPrice().doubleValue();
        double usdToEurRatio = usdToEuroStock.getQuote().getPrice().doubleValue();

        //Konvertuojama kaina doleriais į eurus ir svarai į kilogramus
        double copperKgPerEur = pricePoundPerUsd * usdToEurRatio * 2.20462;


        //Sukuriamas log duomenų bazėje
        CopperStock copperStock = new CopperStock(copperKgPerEur, usdToEurRatio);
        CopperStockDAO.insert(copperStock);

        //Naujausia kaina išsaugoma singleton klasėje
        CopperStockHolder cph = CopperStockHolder.getInstance();
        cph.setCopperStock(copperStock);


    }

}
