package sample.API;


import sample.API.Dto.StocksDto;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class YahooStockAPI {


    public StocksDto getRealTimeRate() throws IOException {


        Stock stock = YahooFinance.get("HG=F", true);

        String currency = "EUR";
        String name = stock.getName();
        String symbol = stock.getSymbol();
        BigDecimal price = stock.getQuote().getPreviousClose();
        BigDecimal change = stock.getQuote().getChangeInPercent();

        StocksDto stocksDto = new StocksDto(symbol,currency, price,change,name);
        return stocksDto;
    }
}
