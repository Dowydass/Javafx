package sample.controller;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.ScheduledService;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;
import org.apache.log4j.BasicConfigurator;
import sample.API.Dto.StocksDto;
import sample.API.YahooStockAPI;


import sample.Main;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class StocksRateController extends Main implements Initializable {



    public Label stock_currency;
    public Label stock_closed;
    public Label stock_date;
    public Label stock_dividend;
    public static Timeline clock;

    public static ScheduledService<Integer> service;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BasicConfigurator.configure();
        refreshStocks();
        refreshDate();
    }

    static void onClose() {
        BasicConfigurator.resetConfiguration();

        clock.stop();

    }

    public void refreshStocks() {


        YahooStockAPI yahooStockAPI = new YahooStockAPI();

        StocksDto rateInfo = null;
        try {
            rateInfo = yahooStockAPI.getRealTimeRate();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        stock_currency.setText(rateInfo.getCurrency());


        DecimalFormat df = new DecimalFormat("#.###");
        BigDecimal x = rateInfo.getPrice();
        df.setRoundingMode(RoundingMode.CEILING);

        double sum = 3 * x.doubleValue() / (3 * 0.453592);

        stock_closed.setText(String.valueOf(df.format(sum)));
        stock_dividend.setText(String.valueOf(rateInfo.getChange()));

        double stockPriceChange = rateInfo.getChange().doubleValue();
        if (stockPriceChange < 0) {
            stock_dividend.setStyle("-fx-text-fill: red;");
        } else if (stockPriceChange > 0) {
            stock_dividend.setStyle("-fx-text-fill: green;");
        }


    }

    public void refreshDate() {

        clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            stock_date.setText(formatter.format(date));
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();

    }
}