package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.JPA.copper_price.CopperStock;
import sample.JPA.copper_price.CopperStockDAO;
import sample.JPA.copper_price.ObservableCopperStockLog;
import sample.Main;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class CurrencyLogController extends Main implements Initializable {

    @FXML
    public TableColumn number;
    @FXML
    public TableColumn creation_date;
    @FXML
    public TableColumn copper_stock_price;
    @FXML
    public TableColumn usd_to_eur_rate;
    @FXML
    public TableView<ObservableCopperStockLog> copper_stock_log;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fillYahooLogData();
    }


    private void fillYahooLogData() {

        copper_stock_log.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        number.setCellValueFactory(new PropertyValueFactory<>("id"));
        number.setMinWidth(50);
        creation_date.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        creation_date.setMinWidth(100);
        copper_stock_price.setCellValueFactory(new PropertyValueFactory<>("copperStockPrice"));
        usd_to_eur_rate.setCellValueFactory(new PropertyValueFactory<>("usdToEurRate"));

        List<CopperStock> copperStockLogs = CopperStockDAO.getAllPrices();
        ObservableList<ObservableCopperStockLog> observableStockPrices = FXCollections.observableArrayList();

        for (CopperStock cs : copperStockLogs) {
            String logTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cs.getLogTime());
            observableStockPrices.add(new ObservableCopperStockLog(cs.getId(), logTimestamp, cs.getCopperStockPrice(),
                    cs.getUsdToEurRate()));
        }
        copper_stock_log.setItems(observableStockPrices);

    }

}

