package sample.controller;


import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import sample.API.Dto.StocksDto;
import sample.API.YahooStockAPI;
import sample.JPA.*;
import sample.JPA.copper_price.CopperStockHolder;
import sample.JPA.copper_price.CopperStock;
import sample.JPA.copper_price.CopperStockDAO;
import sample.JPA.user.User;
import sample.JPA.user.UserDAO;
import sample.JPA.user.UserHolder;
import sample.Main;
import sample.utils.Constants;
import sample.utils.Validation;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;
import java.util.prefs.Preferences;
import java.io.FileWriter;


public class DashboardController extends Main implements Initializable {

    public Button close_button;
    @FXML
    public TableView<ProductCatalog> table;
    public TextField listViewSearchField;
    public Label countAll;
    public TextField tableViewSearchField;
    public TitledPane leftTitledPane;
    public Label current_session_user_email;
    public Label current_session_user_status;
    public ListView<Categories> listView;

    public Button allCategoriesButton;


    // Dešinės panelės label
    @FXML
    public AnchorPane right_panel_anchor_pane;
    @FXML
    public ProgressIndicator loadProgress;
    @FXML
    public MenuItem user_list;
    @FXML
    public MenuItem user_about;
    @FXML
    private Menu file_menu_bar;
    @FXML
    private Menu add_menu_bar;
    @FXML
    private Menu more_menu_bar;

    public double rightPanelLabelY;

    public static long loggedTimeStart;
    public static long loggedTimeEnd;
    public static long loggedTimeSpent;
    public static int spentTimeInSeconds;
    public static long loggedTimePriceUpdateStart;
    public static long loggedTimePriceUpdateEnd;
    long today;
    Timestamp userLastLogin;
    List<Categories> categoryNamesForListView;
    ObservableList<Categories> observableCategoryList;
    ObservableList<ProductCatalog> observableProducts;
    List<ProductCatalog> fullProductList;
    List<Categories> fullCategoryList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserHolder userHolder = UserHolder.getInstance();

        try {
            catchCopperStockPrice();
        } catch (IOException e) {
            e.printStackTrace();
        }

        today = System.currentTimeMillis();
        userLastLogin = UserDAO.getLastLogin(userHolder.getUser());
        loggedTimePriceUpdateStart = System.currentTimeMillis();
        loadColumnToTable();
        loadCategoriesToListView();
        currentSessionUserData();
        reloadCategoryListView();
        reloadProductTableView();
        firstFillDescriptionPanel();


        UserDAO.setLastLoginTime(userHolder.getUser());
        loggedTimeStart = System.currentTimeMillis();// Fiksuoja prisijungimo laiko pradžią

        setImageOnAllCategoriesButton();


        if (userHolder.getUser().isAdmin()) {
            file_menu_bar.setVisible(true);
            add_menu_bar.setVisible(true);
            more_menu_bar.setVisible(true);
            user_list.setVisible(true);

        } else {
            file_menu_bar.setVisible(false);
            add_menu_bar.setVisible(false);
            more_menu_bar.setVisible(true);
            user_list.setVisible(false);

        }
        user_about.setVisible(true);

//
//        preferencesPriceRate.put(STOCK_PRICE, String.valueOf(0));
//        preferencesPriceRate.put(IS_NEW_SESSION, String.valueOf(false));

    }

    public void catchCopperStockPrice() throws IOException {
        CopperStockHolder cph = CopperStockHolder.getInstance();
        List<CopperStock> latestPrice = CopperStockDAO.getLatestPrice();
        if (latestPrice.isEmpty()) {
            YahooStockAPI.getYeahooStockDataToSingleton();
            collectObservableCablesAndUpdatePrice();
        } else if ((System.currentTimeMillis() - latestPrice.get(0).getLogTime().getTime()) / 1000 / 3600 >= 2) {
            YahooStockAPI.getYeahooStockDataToSingleton();
            collectObservableCablesAndUpdatePrice();
        } else {
            cph.setCopperStock(latestPrice.get(0));
        }
    }

    public void collectObservableCablesAndUpdatePrice() {
        fullCategoryList = CategoriesDAO.selectCategoryById(40);
        fullProductList = ProductCatalogDAO.displayAllItems();
        observableProducts = FXCollections.observableList(createFilteredProductList(fullCategoryList, fullProductList));
        setProductPrice(observableProducts);
    }

    public void setImageOnAllCategoriesButton() {

        Image all_Categories_Button_picture = new Image("pictures/all_Categories_Button_picture.jpg");
        ImageView imageView = new ImageView(all_Categories_Button_picture);
        imageView.setFitHeight(13);//13
        imageView.setFitWidth(12);//12
        allCategoriesButton.setGraphic(imageView);
    }

    //Nusako table'o stulpelius ir jų matmenys.
    public void loadColumnToTable() {

        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        boolean isAdmin = u.isAdmin();


        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // pakeisti lietuviskai kategorijos numeri, produkto pavadinimas, kaina.
        TableColumn number = new TableColumn("#");
        TableColumn<ProductCatalog, Integer> catalogNo = new TableColumn<>("Katalogo nr.");
        TableColumn<ProductCatalog, String> symbol = new TableColumn<>("Produkto pavadinimas");
        TableColumn<ProductCatalog, Double> priceNet = new TableColumn<>("Kaina");

        table.getColumns().addAll(number, catalogNo, symbol, priceNet);

        number.minWidthProperty().bind(table.widthProperty().multiply(0.05));
        catalogNo.minWidthProperty().bind(table.widthProperty().multiply(0.17));
        symbol.minWidthProperty().bind(table.widthProperty().multiply(0.52));
        priceNet.minWidthProperty().bind(table.widthProperty().multiply(0.09));

        number.setCellValueFactory((Callback<TableColumn.CellDataFeatures<ProductCatalog, ProductCatalog>, ObservableValue<ProductCatalog>>) p -> new ReadOnlyObjectWrapper(p.getValue()));

        number.setCellFactory(new Callback<TableColumn<ProductCatalog, ProductCatalog>, TableCell<ProductCatalog, ProductCatalog>>() {
            @Override
            public TableCell<ProductCatalog, ProductCatalog> call(TableColumn<ProductCatalog, ProductCatalog> param) {
                return new TableCell<ProductCatalog, ProductCatalog>() {
                    @Override
                    protected void updateItem(ProductCatalog item, boolean empty) {
                        super.updateItem(item, empty);

                        if (this.getTableRow() != null && item != null) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });
        number.setSortable(false);
        catalogNo.setCellValueFactory(new PropertyValueFactory<>("catalogNo"));
        symbol.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        if (isAdmin) {
            symbol.setCellFactory(TextFieldTableCell.forTableColumn());
            symbol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductCatalog, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<ProductCatalog, String> event) {
                    ProductCatalog productCatalog;
                    if (event.getNewValue().isEmpty()) {
                        productCatalog = event.getRowValue();
                        productCatalog.setSymbol(event.getOldValue());
                        showErrorPopupWindow("Neįvestas produkto pavadinimas", "Redaguojant produkto pavadinimą\n" +
                                "privalote įvesti reikšmę.", 350, 120);
                        System.out.println("SYMBOL IS EMPTY");
                    } else if (Validation.isValidSymbol(event.getNewValue())) {
                        productCatalog = event.getRowValue();
                        productCatalog.setSymbol(event.getNewValue());
                        ProductCatalogDAO.updateSymbol(event.getNewValue(), productCatalog.getId());
                        table.refresh();
                        System.out.println("REGEX VALIDATION SUCCESS");
                    } else {
                        productCatalog = event.getRowValue();
                        productCatalog.setSymbol(event.getOldValue());
                        table.refresh();
                        showErrorPopupWindow("Blogai įvestas produkto pavadinimas", "Netinkami simboliai: „!@#$*~<>?“\nEilutę privalo sudaryti nuo 2 iki 75 simbolių, \nlietuviški simboliai priimami", 350, 150);
                        System.out.println("REGEX VALIDATION DENIED");
                    }
                }
            });
        }


        priceNet.setCellValueFactory(new PropertyValueFactory<>("priceNet"));
        if (isAdmin) {
            try {
                priceNet.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter() {
                    @Override
                    public Double fromString(String value) {
                        double nulis = 0.00;
                        if (value == null) {
                            return nulis;
                        }

                        value = value.trim();

                        if (value.length() < 1) {
                            return nulis;
                        }
                        // If the specified value is null or zero-length, return null
                        if (Validation.isValidPrice(String.valueOf(value))) {
                            return Double.parseDouble(value);
                        } else {
                            return Double.valueOf(nulis);
                        }
                    }
                }));

                priceNet.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ProductCatalog, Double>>() {


                    @Override
                    public void handle(TableColumn.CellEditEvent<ProductCatalog, Double> event) {

                        ProductCatalog productCatalog;
                        if (event.getNewValue() == 0.00) {
                            productCatalog = event.getRowValue();
                            productCatalog.setPriceNet(event.getOldValue());
                            table.refresh();
                            showErrorPopupWindow("Blogai įvesta produkto kaina", "Skaičius turi būti 1 arba daugiau simbolių, po kablelio turėti vieną,\ndu arba neturėti skaitmenų. Pavyzdžiui:\n „30“, „7.15“, „1500.0“ ir t.t.", 350, 150);
                            System.out.println("REGEX VALIDATION DENIED");
                        } else if (event.getNewValue() == null) {
                            productCatalog = event.getRowValue();
                            productCatalog.setPriceNet(event.getOldValue());
                            showErrorPopupWindow("Neįvesta produkto kaina", "Skaičius turi būti 1 arba daugiau simbolių, po kablelio turėti vieną,\ndu arba neturėti skaitmenų. Pavyzdžiui:\n „30“, „7.15“, „1500.0“ ir t.t.", 350, 150);
                            System.out.println("PRICENET IS EMPTY");
                            table.refresh();
                        } else if (Validation.isValidPrice(String.valueOf(event.getNewValue()))) {
                            productCatalog = event.getRowValue();
                            productCatalog.setPriceNet(event.getNewValue());
                            ProductCatalogDAO.updatePrice(event.getNewValue(), productCatalog.getId());
                            table.refresh();
                            System.out.println("REGEX VALIDATION SUCCESS");
                        } else {
                            productCatalog = event.getRowValue();
                            productCatalog.setPriceNet(event.getOldValue());
                            table.refresh();
                            showErrorPopupWindow("Blogai įvesta produkto kaina", "Skaičius turi būti 1 arba daugiau simbolių, po kablelio turėti vieną,\ndu arba neturėti skaitmenų. Pavyzdžiui:\n „30“, „7.15“, „1500.0“ ir t.t.", 350, 150);
                            System.out.println("REGEX VALIDATION DENIED");
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }

        }


        number.setResizable(true);
        catalogNo.setResizable(true);
        symbol.setResizable(true);
        priceNet.setResizable(true);

        tableViewSearchField.setPromptText("Įveskite produkto pavadinimą filtravimui...");
    }

    public void loadCategoriesToListView() {
        listViewSearchField.setPromptText("Įveskite kategorijos pavadinimą filtravimui...");
        listView.setCellFactory(lv -> new ListCell<Categories>() {
            @Override
            protected void updateItem(Categories c, boolean empty) {
                super.updateItem(c, empty);
                if (empty) {
                    setText("");
                    setStyle("");
                } else {
                    setText(c.getName());
                    if (c.getCountParents() == 3) {
                        setStyle("-fx-font-weight: bold");
                    } else if (c.getCountParents() == 2) {
                        setText(getText().toUpperCase());
                        setStyle("-fx-font-style: italic");
                    } else if (c.getCountParents() == 1) {
                        setStyle("-fx-text-fill: rgb(9, 96, 235)");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

    }

    //Filtruoja listview panelyje esamą informaciją pagal užrašyta teksta searchfielde
    public void createFilteredCategoryList() {
        FilteredList<Categories> filteredList = new FilteredList<>(observableCategoryList, p -> true);
        listViewSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(category -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return category.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });
        listView.setItems(filteredList);
    }

    public void SelectAllCategories(ActionEvent actionEvent) {
        if (!listViewSearchField.getText().isEmpty()) {
            reloadCategoryListView();
            listViewSearchField.clear();
        }
        listView.scrollTo(0);
        listView.getSelectionModel().select(0);
    }

    private Double callAPI() {


        YahooStockAPI yahooStockAPI = new YahooStockAPI();

        StocksDto rateInfo = null;
        try {
            rateInfo = yahooStockAPI.getRealTimeRate();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        double price = rateInfo.getPrice().doubleValue();

        return price;
    }


//    final String STOCK_PRICE = "PRICE";
//    final String CHANGE = "CHANGE";
//    final String IS_NEW_SESSION = "IS_NEW_SESSION";
//    final String IS_SESSION_UPDATED_IN_DB = "IS_SESSION_IN_DB";
//
//    Preferences preferencesPriceRate = Preferences.userNodeForPackage(DashboardController.class);


    public void setProductPrice(List<ProductCatalog> observableProducts) {
        if (observableProducts != null) {

//            boolean s = Boolean.parseBoolean(preferencesPriceRate.get(IS_NEW_SESSION, ""));
//            if (!s) {
//
//                Boolean session = true;
//                Boolean UPDATED_IN_DB = true;
//                double price = CopperStockHolder.getInstance().getCopperStock().getCopperStockPrice();
//
//                //Nekeičiau, bet prasmės šitas neturi.
//                double change = callAPI();
//
//                preferencesPriceRate.put(STOCK_PRICE, String.valueOf(price));
//                preferencesPriceRate.put(CHANGE, String.valueOf(change));
//                preferencesPriceRate.put(IS_NEW_SESSION, String.valueOf(session));
//
//                System.out.println("User button event. Is API called at session, state = " + preferencesPriceRate.get(IS_NEW_SESSION, ""));
//
//            }

//            System.out.println("User button event. Is API called at session, state = " + preferencesPriceRate.get(IS_NEW_SESSION, ""));
//            System.out.println(preferencesPriceRate.get(STOCK_PRICE, ""));


//            double change = Double.parseDouble(preferencesPriceRate.get(CHANGE, ""));

            // Metodas paima vario kainą per kilogramą pagal esamą euro kursą
            double price = CopperStockHolder.getInstance().getCopperStock().getCopperStockPrice();
            price = (price * 100);

            for (ProductCatalog observableProduct : observableProducts) {

                double cuAmount = observableProduct.getCuAmount();
                double cuPrice = observableProduct.getCuPrice();
                int cableType = 0;

                if (cuAmount != 0 && cuPrice != 0) {
                    if (observableProduct.getSymbol().toLowerCase().contains("instaliacinis kabelis nym")) {
                        cableType = Constants.KABELIS150;
                    }
                    if (observableProduct.getSymbol().toLowerCase().contains("jėgos kabelis nyy")) {
                        cableType = Constants.KABELIS150;
                    }
                    if (observableProduct.getSymbol().toLowerCase().contains("behalogeninis kabelis n2xh")) {
                        cableType = Constants.KABELIS300;
                    }
                    if (observableProduct.getSymbol().toLowerCase().contains("lankstus viengyslis laidas")) {
                        cableType = Constants.KABELIS300;
                    }
                    if (observableProduct.getSymbol().toLowerCase().contains("jėgos kabelis u")) {
                        cableType = Constants.KABELIS300;
                    }
                    if (observableProduct.getSymbol().toLowerCase().contains("ekranuotas behalogeninis kabelis")) {
                        cableType = Constants.KABELIS300;
                    }
                    if (observableProduct.getSymbol().toLowerCase().contains("ugniai atsparus kabelis")) {
                        cableType = Constants.KABELIS300;
                    }


                    double priceNet = ((cuPrice + (cuAmount * (price - cableType) / 100)) / 1000) / 0.8;
                    if (observableProduct.getPriceNet() != priceNet) {
//                        observableProduct.setPriceNet(priceNet); // NETURI PRASMĖS -M
                        ProductCatalogDAO.updatePrice(priceNet, observableProduct.getId());

                    }

//
//                    //Pakeisti šitą į metodą.
//                    boolean ss = Boolean.parseBoolean(preferencesPriceRate.get(IS_NEW_SESSION, ""));
//                    if (ss == false) {
//                        Boolean UPDATED_IN_DB = true;
//                        preferencesPriceRate.put(IS_SESSION_UPDATED_IN_DB, String.valueOf(UPDATED_IN_DB));
//                    }
                }
            }
        }
    }

    public void getSelectionModel() {
        Categories item;
        try {
            if (!listView.getSelectionModel().isEmpty()) {
                item = listView.getSelectionModel().getSelectedItem();
                fullCategoryList = CategoriesDAO.selectCategoryById(item.getId());
                fullProductList = ProductCatalogDAO.displayAllItems();
                observableProducts = FXCollections.observableList(createFilteredProductList(fullCategoryList, fullProductList));
                loggedTimePriceUpdateEnd = System.currentTimeMillis();
                if ((loggedTimePriceUpdateEnd - loggedTimePriceUpdateStart) / 1000 / 3600 >= 2) {
                    setProductPrice(observableProducts);
                    loggedTimePriceUpdateStart = System.currentTimeMillis();
                }
                countTableViewObservableProducts(observableProducts);
                table.setItems(observableProducts);

            }
        } catch (IllegalStateException e) {
            System.out.println("mouseEventForListView( " + e + " ) ");
        } catch (NullPointerException e) {
            System.out.println("mouseEventForListView( " + e + " )");
        }
    }

    //Paspaudus ant listview elemento tableview panelyje pavaizduoja visus produktus priklausančius šiam kategorija.
    public void mouseEventForListView(MouseEvent mouseEvent) {
        getSelectionModel();


    }

    //Iš excel failo pasiema produktus.
    public void openExcelFileFromDialog() {
        final FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        // Loading Spinner start.
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            openFile(file);
//            loadProgress.setVisible(false); // Loading Spinner ends.
        }
//        loadProgress.setVisible(false);
        reloadProductTableView();
    }

    //Konfiguriuoja failo pasirinkimus
    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("Užkrauti excel failą");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Excel file", "*.xlsx")
        );
    }

    //Tikriną excelio produktų kainas su duombasėje esamomis produkto kainomis, įkelia naujus produktus, jei jų nėra duombasėje, visai tai skaičiuoja.
    private void openFile(File file) {
        //ProductCatalogDAO.checkIfCatalogExistsIfNotCreateIt();
        loadProgress();

        Thread uploadExcelLogicalThread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<ProductCatalog> excelProducts = null;
                List<CategoryParameters> allCategoryParameters;
                List<String> uniqueProducts = new ArrayList<>();
                List<String> dublicatedProducts = new ArrayList<>();

                try {
                    excelProducts = ReadExcelWithProductCatalog.readFileUsingPOI(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Products found in data sheet: " + excelProducts.size());
                assert excelProducts != null;


                int countEveryProductInExcel = 0;
                int countEveryProductUpdated = 0;
                int countEveryNewProduct = 0;
                int countEveryDublicateProduct = 0;

                try {
                    countEveryProductInExcel = excelProducts.size();

                    for (ProductCatalog excelProduct : excelProducts) {
                        if (!uniqueProducts.contains(excelProduct.getCatalogNo())) {
                            List<ProductCatalog> dbProducts = ProductCatalogDAO.searchByCatalogNo(excelProduct.getCatalogNo());
                            if (!dbProducts.isEmpty()) {
                                ProductCatalog dbProduct = dbProducts.get(0);
                                uniqueProducts.add(excelProduct.getCatalogNo());
                                if (doSameProductsHaveDifferences(dbProduct, excelProduct)) {
                                    excelProduct.setId(dbProduct.getId());
                                    ProductCatalogDAO.replace(excelProduct);
                                    countEveryProductUpdated++;
                                }
                            } else {
                                insertCategoryParameter(createCategoryParameter(excelProduct), CategoryParametersDAO.displayAllCategoryParameters(), excelProduct);
                                ProductCatalogDAO.insert(excelProduct);
                                uniqueProducts.add(excelProduct.getCatalogNo());
                                countEveryNewProduct++;
                            }
                        } else {
                            dublicatedProducts.add(excelProduct.getCatalogNo());
                            countEveryDublicateProduct++;
                        }

                    }
                    dublicateWriter(dublicatedProducts);
                } catch (NullPointerException e) {
                    System.out.println("openFile(" + e + " )");
                } catch (RuntimeException e) {
                    System.out.println("openFile(" + e + " )");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (countEveryProductUpdated == 0 && countEveryNewProduct == 0 && countEveryProductInExcel == 0) {
                    Platform.runLater(() -> {
                        showErrorPopupWindow("Klaida!", "Failas nebuvo nuskaitytas dėl blogo lentelių formato, \npatikrinkite ar dokumente nepalikote klaidų. \n\nFailo pavadinimas: " + file.getName(), 500, 200);
                        loadProgress.setVisible(false);
                    });
                } else if (countEveryDublicateProduct != 0) {
                    String successToPopup = "Faile rasta produktų: " + countEveryProductInExcel + "\nPakeista produktų: " +
                            countEveryProductUpdated + "\nPridėti nauji produktai: " + countEveryNewProduct + "\nRasta dublikatų: " +
                            countEveryDublicateProduct + "\n - Dublikatų katalogo ID išsaugoti faile:\n  \"dublikatai.txt\" ";

                    Platform.runLater(() -> {
                        showInformationPopupWindow("Failas sėkmingai įkeltas", successToPopup, 500, 250);
                        loadProgress.setVisible(false);

                    });

                } else {
                    String successToPopup = "Faile rasta produktų: " + countEveryProductInExcel + "\nPakeista produktų: " + countEveryProductUpdated + "\nPridėti nauji produktai: " + countEveryNewProduct + "\nRasta dublikatų: " + countEveryDublicateProduct;

                    Platform.runLater(() -> {
                        showInformationPopupWindow("Failas sėkmingai įkeltas", successToPopup, 500, 200);
                        loadProgress.setVisible(false);

                    });
                }
                Platform.runLater(() ->

                {
                    loadProgress.setVisible(false);
                    reloadProductTableView();
                });
            }

        });
        uploadExcelLogicalThread.setDaemon(true);
        uploadExcelLogicalThread.start();

    }

    public void dublicateWriter(List<String> arr) throws IOException {
        FileWriter writer = new FileWriter("dublikatai.txt");
        for (String str : arr) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
    }

    public boolean doSameProductsHaveDifferences(ProductCatalog excelProduct, ProductCatalog dbProduct) {
        if (dbProduct.toStringCompare().equals(excelProduct.toStringCompare())) {
            return false;
        }
        return true;
    }

    //Ikelia categoryParameter objektą į duombazę patikrinus ar tokio objekto nėra duombazėje.
    public void insertCategoryParameter(CategoryParameters categoryParameter, List<CategoryParameters> allCategoryParameters, ProductCatalog product) {
        if (allCategoryParameters.size() == 0) {
            CategoryParametersDAO.createNewCategoryParametersField(categoryParameter);
            CategoryParameters parameter = CategoryParametersDAO.selectLastCategoryParameter();
            CategoriesDAO.updateCategoryParameterById(parameter.getId(), product.getGroupId());
        } else {
            boolean newParameter = true;
            for (CategoryParameters allCategoryParameter : allCategoryParameters) {
                if (!compareCategoryParameters(allCategoryParameter, categoryParameter)) {
                    newParameter = false;
                    Categories category = CategoriesDAO.displayParentCategoryById(product.getGroupId());
                    if (allCategoryParameter.getId() != category.getCategory_parameter_id()) {
                        CategoriesDAO.updateCategoryParameterById(allCategoryParameter.getId(), product.getGroupId());
                    }
                }

            }
            if (newParameter) {
                CategoryParametersDAO.createNewCategoryParametersField(categoryParameter);
                CategoryParameters parameter = CategoryParametersDAO.selectLastCategoryParameter();
                CategoriesDAO.updateCategoryParameterById(parameter.getId(), product.getGroupId());
            }
        }
    }

    //Lygina du categoryParameter objektus.
    public boolean compareCategoryParameters(CategoryParameters parameter, CategoryParameters parameter2) {
        if (parameter.toStringCompareCategoryParameters().equals(parameter2.toStringCompareCategoryParameters())) {
            return false;
        }
        return true;
    }

    //Sukuria categoryParameter objektą iš produkto esamų parametrų.
    public CategoryParameters createCategoryParameter(ProductCatalog product) {
        CategoryParameters categoryParameters = new CategoryParameters();

        double cuAmount = product.getCuAmount();
        categoryParameters.setCuAmount(cuAmount != 0);

        double cuPrice = product.getCuPrice();
        categoryParameters.setCuPrice(cuPrice != 0);

        String gamintojas = product.getGamintojas();
        categoryParameters.setGamintojas(gamintojas != null);

        double aukstis = product.getAukstis();
        categoryParameters.setAukstis(aukstis != 0);

        double plotis = product.getPlotis();
        categoryParameters.setPlotis(plotis != 0);

        double gylis = product.getGylis();
        categoryParameters.setGylis(gylis != 0);

        double skersmuo = product.getSkersmuo();
        categoryParameters.setSkersmuo(skersmuo != 0);

        double ilgis = product.getIlgis();
        categoryParameters.setIlgis(ilgis != 0);

        String apsaugos_laipsnis = product.getApsaugos_laipsnis();
        categoryParameters.setApsaugos_laipsnis(apsaugos_laipsnis != null);

        double moduliu_skaicius = product.getModuliu_skaicius();
        categoryParameters.setModuliu_skaicius(moduliu_skaicius != 0);

        String vardine_srove = product.getVardine_srove();
        categoryParameters.setVardine_srove(vardine_srove != null);

        String vardine_itampa = product.getVardine_itampa();
        categoryParameters.setVardine_itampa(vardine_itampa != null);

        String mechaninis_atsparumas_IK = product.getMechaninis_atsparumas_IK();
        categoryParameters.setMechaninis_atsparumas_IK(mechaninis_atsparumas_IK != null);

        double storis = product.getStoris();
        categoryParameters.setStoris(storis != 0);

        String spalva = product.getSpalva();
        categoryParameters.setSpalva(spalva != null);

        String korpuso_medziaga = product.getKorpuso_medziaga();
        categoryParameters.setKorpuso_medziaga(korpuso_medziaga != null);

        String izoliacija = product.getIzoliacija();
        categoryParameters.setIzoliacija(izoliacija != null);

        double svoris = product.getSvoris();
        categoryParameters.setSvoris(svoris != 0);

        String galia = product.getGalia();
        categoryParameters.setGalia(galia != null);

        double sviesos_srautas = product.getSviesos_srautas();
        categoryParameters.setSviesos_srautas(sviesos_srautas != 0);

        String sviesos_spalvos_temperatura = product.getSviesos_spalvos_temperatura();
        categoryParameters.setSviesos_spalvos_temperatura(sviesos_spalvos_temperatura != null);

        String laidininkas = product.getLaidininkas();
        categoryParameters.setLaidininkas(laidininkas != null);

        String laidininkoIzoliacija = product.getLaidininkoIzoliacija();
        categoryParameters.setLaidininkoIzoliacija(laidininkoIzoliacija != null);

        String darbine_temperatura = product.getDarbine_temperatura();
        categoryParameters.setDarbine_temperatura(darbine_temperatura != null);

        String max_darbine_temperatura = product.getMax_darbine_temperatura();
        categoryParameters.setMax_darbine_temperatura(max_darbine_temperatura != null);

        String apsvieta = product.getApsvieta();
        categoryParameters.setApsvieta(apsvieta != null);

        String apvalkalas = product.getApvalkalas();
        categoryParameters.setApvalkalas(apvalkalas != null);

        String cpr_klase = product.getCpr_klase();
        categoryParameters.setCpr_klase(cpr_klase != null);

        String isjungimo_geba = product.getIsjungimo_geba();
        categoryParameters.setIsjungimo_geba(isjungimo_geba != null);

        String isjungimo_charakteristika = product.getIsjungimo_charakteristika();
        categoryParameters.setIsjungimo_charakteristika(isjungimo_charakteristika != null);

        String mechaninis_atsparumas = product.getMechaninis_atsparumas();
        categoryParameters.setMechaninis_atsparumas(mechaninis_atsparumas != null);

        String skerspjuvis_Al = product.getSkerspjuvis_Al();
        categoryParameters.setSkerspjuvis_Al(skerspjuvis_Al != null);

        String skerspjuvis_Cu = product.getSkerspjuvis_Cu();
        categoryParameters.setSkerspjuvis_Cu(skerspjuvis_Cu != null);

        String nuotekio_srove = product.getNuotekio_srove();
        categoryParameters.setNuotekio_srove(nuotekio_srove != null);

        String dydis = product.getDydis();
        categoryParameters.setDydis(dydis != null);

        String plotas = product.getPlotas();
        categoryParameters.setPlotas(plotas != null);

        String aptikimoZona = product.getAptikimoZona();
        categoryParameters.setAptikimoZona(aptikimoZona != null);

        String maksimaliDarbineItampa = product.getMaksimaliDarbineItampa();
        categoryParameters.setMaksimaliDarbineItampa(maksimaliDarbineItampa != null);

        String iskrovimoSrove820 = product.getIskrovimoSrove820();
        categoryParameters.setIskrovimoSrove820(iskrovimoSrove820 != null);

        String iskrovimoSrove10350 = product.getIskrovimoSrove10350();
        categoryParameters.setIskrovimoSrove10350(iskrovimoSrove10350 != null);

        String itamposApsaugosLygis = product.getItamposApsaugosLygis();
        categoryParameters.setItamposApsaugosLygis(itamposApsaugosLygis != null);

        String kategorija = product.getKategorija();
        categoryParameters.setKategorija(kategorija != null);

        int CRI = product.getCRI();
        categoryParameters.setCRI(CRI != 0);

        String garantija = product.getGarantija();
        categoryParameters.setGarantija(garantija != null);

        String sertifikatai = product.getSertifikatai();
        categoryParameters.setSertifikatai(sertifikatai != null);

        String nemaJungtis = product.getNemaJungtis();
        categoryParameters.setNemaJungtis(nemaJungtis != null);

        String virsitampiuApsauga = product.getVirsitampiuApsauga();
        categoryParameters.setVirsitampiuApsauga(virsitampiuApsauga != null);

        String ilgaamziskumas = product.getIlgaamziskumas();
        categoryParameters.setIlgaamziskumas(ilgaamziskumas != null);

        String korpusoAtidarymas = product.getKorpusoAtidarymas();
        categoryParameters.setKorpusoAtidarymas(korpusoAtidarymas != null);

        String optinesIrElektrinesDaliesPertvara = product.getOptinesIrElektrinesDaliesPertvara();
        categoryParameters.setOptinesIrElektrinesDaliesPertvara(optinesIrElektrinesDaliesPertvara != null);

        String valdymas = product.getValdymas();
        categoryParameters.setValdymas(valdymas != null);

        int apatinisDiametras = product.getApatinisDiametras();
        categoryParameters.setApatinisDiametras(apatinisDiametras != 0);

        int virsutinisDiametras = product.getVirsutinisDiametras();
        categoryParameters.setVirsutinisDiametras(virsutinisDiametras != 0);

        int gembesDiametras = product.getGembesDiametras();
        categoryParameters.setGembesDiametras(gembesDiametras != 0);

        return categoryParameters;
    }

    //Surenka visus produktus turinčius pasirinktos kategorijos id
    public ObservableList<ProductCatalog> createFilteredProductList
    (List<Categories> categories, List<ProductCatalog> products) {
        ObservableList<ProductCatalog> filteredProduct = FXCollections.observableArrayList();
        for (Categories category : categories) {
            for (ProductCatalog product : products) {
                if (category.getId() == product.getGroupId()) {
                    filteredProduct.add(product);
                }
            }
        }
        return filteredProduct;
    }

    //Sukelia observablelistą į table'ą su filtravimo funkciją.
    public void tableViewSearchFunction() {
        try {
            FilteredList<ProductCatalog> flProducts = new FilteredList(observableProducts, product -> true);
            tableViewSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
                flProducts.setPredicate(product -> product.getSymbol().toLowerCase().contains(newValue.toLowerCase().trim()));
                countTableViewFilteredProducts(flProducts);
            });
            SortedList<ProductCatalog> slProducts = new SortedList<>(flProducts);
            slProducts.comparatorProperty().bind(table.comparatorProperty());
            countTableViewSortedProducts(slProducts);
            table.setItems(slProducts);

        } catch (Exception e) {
            System.out.println("tableSearch error ( " + e + " )");
        }
    }

    // Tikrinama ar vidurinėje panelėje buvo pasirinktas item, jeigu buvo, kviečiamas dešinės panelės užpildymo metodas
    // metodui perduodamas item'o katalogo numeris.
    public void mouseEventForTableView() {
        ProductCatalog tableItem;
        try {
            if (!table.getSelectionModel().isEmpty()) {
                tableItem = table.getSelectionModel().getSelectedItem();
                System.out.println("Selected Catalog No: " + tableItem.getCatalogNo());
                fillDescriptionPanel(tableItem.getCatalogNo());
                System.out.println("Item was selected.");
            }
        } catch (IllegalStateException e) {
            System.out.println("mouseEventForTreeView( " + e + " )");
        } catch (NullPointerException e) {
            System.out.println("mouseEventForTreeView(" + e + " )");
        }
    }


    private void setRightPanelLabelY(double y) {
        this.rightPanelLabelY = y;
    }

    private double getRightPanelLabelY() {
        return rightPanelLabelY = this.rightPanelLabelY + 20;
    }

    public void countTableViewObservableProducts(ObservableList<ProductCatalog> products) {
        countAll.setText("Išviso įrašų : " + products.size());
    }

    public void countTableViewFilteredProducts(FilteredList<ProductCatalog> products) {
        countAll.setText("Išviso įrašų : " + products.size());
    }

    public void countTableViewSortedProducts(SortedList<ProductCatalog> products) {
        countAll.setText("Išviso įrašų : " + products.size());
    }

    // Suveikia pasirinkus item'ą vidurinėje panelėje.
    // Pirmiausia kreipiamasi į duomenų bazę, patikrinama ar egzistuoja produkto aprašymas.
    // Jei egzistuoja, ištraukiami visi duomenys ir užpildoma dešinė panelė.

    public void firstFillDescriptionPanel() {
        HBox mainHBox = new HBox();
        mainHBox.setAlignment(Pos.CENTER);
        Label infoLabel = new Label();
        infoLabel.setAlignment(Pos.CENTER);
        infoLabel.setLayoutY(140);
        infoLabel.setLayoutX(70);
        infoLabel.setStyle("-fx-font-style: italic; -fx-font-size: 14;");
        infoLabel.setText("Pasirinkite produktą iš sąrašo,\nkad detali informacija būtų atvaizduota...");
        mainHBox.getChildren().add(infoLabel);
        right_panel_anchor_pane.getChildren().add(infoLabel);
    }

    public void fillDescriptionPanel(String catalogNoImported) {

        right_panel_anchor_pane.getChildren().clear();
        right_panel_anchor_pane.autosize();

        HBox joinedInformationPanelWithImageHBox = new HBox();
//        joinedInformationPanelWithImageHBox.setStyle("-fx-border-width: 3; -fx-border-color: #B7B7B7;");
        joinedInformationPanelWithImageHBox.setPadding(new Insets(5, 3, 2, 3));

        VBox right_panel_main_vbox = new VBox();
        right_panel_main_vbox.setMinWidth(410);
        right_panel_main_vbox.prefHeight(397);
        right_panel_main_vbox.prefWidth(410);
        right_panel_main_vbox.setMaxWidth(410);
        right_panel_anchor_pane.getChildren().add(right_panel_main_vbox);


//        VBox desciptionLabelVBox = new VBox();
        VBox propertyLabelVBox = new VBox();
        HBox informationPanelHBox = new HBox();
        VBox imageVBox = new VBox();

        propertyLabelVBox.setPadding(new Insets(5, 15, 5, 15));

//        desciptionLabelVBox.setPadding(new Insets(0, 5, 10, 5));
//        desciptionLabelVBox.setMinWidth(130);
        propertyLabelVBox.setMinWidth(260);
        imageVBox.setPadding(new Insets(5, 5, 5, 0));
        imageVBox.setAlignment(Pos.TOP_CENTER);
        setRightPanelLabelY(40);

        fullProductList.forEach(irasas -> {
            if (irasas.getCatalogNo() == catalogNoImported) {
                Label symbolProperty = new Label();
                symbolProperty.setWrapText(true);
                symbolProperty.setMinWidth(410);
                symbolProperty.prefWidth(412);
                symbolProperty.setMinHeight(24);
                symbolProperty.setMaxHeight(50);
                symbolProperty.setMaxWidth(410);
                symbolProperty.setAlignment(Pos.CENTER_LEFT);
                symbolProperty.setPadding(new Insets(3, 5, 3, 3));
                symbolProperty.setStyle("-fx-font-weight: bold; -fx-background-color: linear-gradient(to top, #D9D9D9, #EDEDED); -fx-border-width: 1; -fx-border-color: #c8c8c8; -fx-border-radius: 1;");
                symbolProperty.setText(irasas.getSymbol());
                right_panel_main_vbox.getChildren().add(symbolProperty);

                Label catalogNoDescription = new Label();
                Label catalogNoProperty = new Label();
                catalogNoDescription.setStyle("-fx-underline: true;");
                catalogNoDescription.setLayoutX(20);
                catalogNoDescription.setLayoutY(getRightPanelLabelY());

                catalogNoProperty.setStyle("-fx-font-weight: bold;");
                catalogNoProperty.setLayoutX(60);
                catalogNoProperty.setLayoutY(getRightPanelLabelY());
                catalogNoDescription.setText("Katalogo kodas:");
                catalogNoProperty.setText(irasas.getCatalogNo());

                propertyLabelVBox.getChildren().add(catalogNoProperty);

//                Label priceNetDescription = new Label();
                Label priceNetProperty = new Label();
//                setDescriptionAndProperty(priceNetDescription, priceNetProperty, "Kaina: ", irasas.getPriceNet() + "€");
//                desciptionLabelVBox.getChildren().add(priceNetDescription);
                propertyLabelVBox.getChildren().add(priceNetProperty);

                if (irasas.getCuAmount() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Vario kiekis: ", String.valueOf(irasas.getCuAmount()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getCuPrice() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Vario kaina: ", String.valueOf(irasas.getCuPrice()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getGamintojas() != null && irasas.getGamintojas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Gamintojas: ", String.valueOf(irasas.getGamintojas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getAukstis() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Aukštis: ", String.valueOf(irasas.getAukstis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getPlotis() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Plotis: ", String.valueOf(irasas.getPlotis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getGylis() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Gylis: ", String.valueOf(irasas.getGylis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSkersmuo() != 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Skersmuo: ", String.valueOf(irasas.getSkersmuo()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIlgis() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Ilgis: ", String.valueOf(irasas.getIlgis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getApsaugos_laipsnis() != null && !irasas.getApsaugos_laipsnis().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Apsaugos laipsnis: ", String.valueOf(irasas.getApsaugos_laipsnis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getModuliu_skaicius() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Modulių skaičius: ", String.valueOf(irasas.getModuliu_skaicius()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getVardine_itampa() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Vardinė įtampa: ", String.valueOf(irasas.getVardine_itampa()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getVardine_srove() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Vardinė srovė: ", String.valueOf(irasas.getVardine_srove()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getMechaninis_atsparumas_IK() != null && !irasas.getMechaninis_atsparumas_IK().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Mechaninis atsparumas IK: ", String.valueOf(irasas.getMechaninis_atsparumas_IK()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getStoris() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Storis: ", String.valueOf(irasas.getStoris()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSpalva() != null && !irasas.getSpalva().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Spalva: ", String.valueOf(irasas.getSpalva()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getKorpuso_medziaga() != null && !irasas.getKorpuso_medziaga().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Korpuso medžiaga: ", String.valueOf(irasas.getKorpuso_medziaga()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIzoliacija() != null && !irasas.getIzoliacija().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Izoliacija: ", String.valueOf(irasas.getIzoliacija()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSvoris() != 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Svoris: ", String.valueOf(irasas.getSvoris()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getGalia() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Galia: ", String.valueOf(irasas.getGalia()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSviesos_srautas() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Šviesos srautas: ", String.valueOf(irasas.getSviesos_srautas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }

                if (irasas.getSviesos_spalvos_temperatura() != null && !irasas.getSviesos_spalvos_temperatura().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Spalvos temperatūra: ", String.valueOf(irasas.getSviesos_spalvos_temperatura()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getLaidininkas() != null && !irasas.getLaidininkas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Laidininkas: ", String.valueOf(irasas.getLaidininkas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }

                if (irasas.getLaidininkoIzoliacija() != null && !irasas.getLaidininkoIzoliacija().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Laidininko izoliacija: ", String.valueOf(irasas.getLaidininkoIzoliacija()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getDarbine_temperatura() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Darbinė temperatūra: ", String.valueOf(irasas.getDarbine_temperatura()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getMax_darbine_temperatura() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Maks. darbinė temperatūra: ", String.valueOf(irasas.getMax_darbine_temperatura()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getApsvieta() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Apšvieta: ", String.valueOf(irasas.getApsvieta()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getApvalkalas() != null && !irasas.getApvalkalas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Apvalkalas: ", String.valueOf(irasas.getCuAmount()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getCpr_klase() != null && !irasas.getCpr_klase().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("CPR klasė: ", String.valueOf(irasas.getCpr_klase()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIsjungimo_geba() != null && !irasas.getIsjungimo_geba().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Išjungimo geba: ", String.valueOf(irasas.getIsjungimo_geba()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIsjungimo_charakteristika() != null && !irasas.getIsjungimo_charakteristika().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Išjungimo charakteristika: ", String.valueOf(irasas.getIsjungimo_charakteristika()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getMechaninis_atsparumas() != null && !irasas.getMechaninis_atsparumas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Mechaninis atsparumas: ", String.valueOf(irasas.getMechaninis_atsparumas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSkerspjuvis_Al() != null && !irasas.getSkerspjuvis_Al().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Skerspjūvis AL: ", String.valueOf(irasas.getSkerspjuvis_Al()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSkerspjuvis_Cu() != null && !irasas.getSkerspjuvis_Cu().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Skerspjūvis CU: ", String.valueOf(irasas.getSkerspjuvis_Cu()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getNuotekio_srove() != null && !irasas.getNuotekio_srove().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Nuotėkio srovė: ", String.valueOf(irasas.getNuotekio_srove()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getDydis() != null) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Dydis: ", String.valueOf(irasas.getDydis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getPlotas() != null && !irasas.getPlotas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Plotas: ", String.valueOf(irasas.getPlotas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getAptikimoZona() != null && !irasas.getAptikimoZona().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Aptikimo zona: ", String.valueOf(irasas.getAptikimoZona()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getMaksimaliDarbineItampa() != null && !irasas.getMaksimaliDarbineItampa().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Maksimali darbinė temp.: ", String.valueOf(irasas.getDarbine_temperatura()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIskrovimoSrove820() != null && !irasas.getIskrovimoSrove820().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Iškrovimo srovė 8.20: ", String.valueOf(irasas.getIskrovimoSrove820()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIskrovimoSrove10350() != null && !irasas.getIskrovimoSrove10350().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Iškrovimo srovė 10.350: ", String.valueOf(irasas.getIskrovimoSrove10350()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getItamposApsaugosLygis() != null && !irasas.getItamposApsaugosLygis().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Įtampos apsaugos lygis: ", String.valueOf(irasas.getItamposApsaugosLygis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getKategorija() != null && !irasas.getKategorija().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Kategorija: ", String.valueOf(irasas.getKategorija()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getCRI() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("CRI: ", String.valueOf(irasas.getCRI()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getGarantija() != null && !irasas.getGarantija().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Garantija: ", String.valueOf(irasas.getGarantija()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getSertifikatai() != null && !irasas.getSertifikatai().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Sertifikatas: ", String.valueOf(irasas.getSertifikatai()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getNemaJungtis() != null && !irasas.getNemaJungtis().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Nema jungtis: ", String.valueOf(irasas.getNemaJungtis()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getVirsitampiuApsauga() != null && !irasas.getVirsitampiuApsauga().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Viršįtampių apsauga: ", String.valueOf(irasas.getVirsitampiuApsauga()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getIlgaamziskumas() != null && !irasas.getIlgaamziskumas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Ilgaamžiškumas: ", String.valueOf(irasas.getIlgaamziskumas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getKorpusoAtidarymas() != null && !irasas.getKorpusoAtidarymas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Korpuso atidarymas: ", String.valueOf(irasas.getKorpusoAtidarymas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getOptinesIrElektrinesDaliesPertvara() != null && !irasas.getOptinesIrElektrinesDaliesPertvara().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Optinės ir elektrinės dalies pertvara: ", String.valueOf(irasas.getOptinesIrElektrinesDaliesPertvara()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getValdymas() != null && !irasas.getValdymas().isEmpty()) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Valdymas: ", String.valueOf(irasas.getValdymas()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getApatinisDiametras() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Apatinis diametras: ", String.valueOf(irasas.getApatinisDiametras()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getVirsutinisDiametras() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Viršutinis diametras: ", String.valueOf(irasas.getVirsutinisDiametras()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }
                if (irasas.getGembesDiametras() > 0) {
                    HBox descriptionAntProperty = setDescriptionAndProperty("Gembės diametras: ", String.valueOf(irasas.getGembesDiametras()));

                    propertyLabelVBox.getChildren().add(descriptionAntProperty);
                }

                if (irasas.getImage_url() != null && !irasas.getImage_url().isEmpty()) {
                    Image imageFromUrl = new Image(irasas.getImage_url());
                    ImageView imageView = new ImageView();
                    imageView.setImage(imageFromUrl);
                    imageView.setFitWidth(125);
                    imageView.setPreserveRatio(true);
//                    imageView.setLayoutX(20);
//                    imageView.setLayoutY(40);
                    VBox boundryImageVBox = new VBox();
                    boundryImageVBox.getChildren().add(imageView);
                    boundryImageVBox.setStyle("-fx-border-width: 3; -fx-border-color: #B7B7B7;");
                    imageVBox.getChildren().add(boundryImageVBox);
                    System.out.println("Image has been downloaded and loaded.");

                } else {
                    Image imageFromUrl = new Image("/pictures/unavailable_product_picture.png");
                    ImageView imageView = new ImageView();
                    imageView.setImage(imageFromUrl);
                    imageView.setFitWidth(125);
                    imageView.setPreserveRatio(true);
//                    imageView.setLayoutX(20);
//                    imageView.setLayoutY(40);
                    VBox boundryImageVBox = new VBox();
                    boundryImageVBox.getChildren().add(imageView);
                    boundryImageVBox.setStyle("-fx-border-width: 3; -fx-border-color: #B7B7B7;");
                    imageVBox.getChildren().add(boundryImageVBox);
                    System.out.println("Default image has been loaded.");
                }
            }
        });

        joinedInformationPanelWithImageHBox.getChildren().add(propertyLabelVBox);
        informationPanelHBox.getChildren().add(joinedInformationPanelWithImageHBox);
        informationPanelHBox.getChildren().add(imageVBox);
        informationPanelHBox.setStyle("-fx-border-width: 3; -fx-border-color: #B7B7B7;");
        right_panel_main_vbox.getChildren().add(informationPanelHBox);
    }

    public HBox setDescriptionAndProperty(String descriptionText, String propertyText) {

        Label descriptionLabel = new Label();
        Label propertyLabel = new Label();

        descriptionLabel.setWrapText(true);
        propertyLabel.setWrapText(true);

        VBox description = new VBox();
        VBox property = new VBox();
        HBox descriptionAntProperty = new HBox();

        propertyLabel.setStyle("-fx-font-weight: bold;");

        descriptionLabel.setText(descriptionText);
        propertyLabel.setText(propertyText);

        description.getChildren().add(descriptionLabel);
        property.getChildren().add(propertyLabel);
        descriptionAntProperty.getChildren().add(description);
        descriptionAntProperty.getChildren().add(property);

        return descriptionAntProperty;
    }

    private void currentSessionUserData() {


        //Getting data from the last scene calling a class UserHolder
        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        String email = u.getEmail();
        boolean isAdmin = u.isAdmin();


        if (isAdmin) {
            current_session_user_status.setText(Constants.CURRENT_SESSION_STATUS_ADMIN);
        } else {
            current_session_user_status.setText(Constants.CURRENT_SESSION_STATUS_USER);
        }

        current_session_user_email.setText(email);
    }


    // Atidaro langą su vartotojų sąrašu
    public void openUserStats() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(Constants.USER_STATS_VIEW_PATH));
            Stage statsStage = new Stage();
            Scene scene = new Scene(root);
            JPAUtil.setScene(scene);
            statsStage.setMinWidth(500);
            statsStage.setMinHeight(400);
            statsStage.setTitle("Registruotų vartotojų sąrašas");
            statsStage.setScene(scene);
            statsStage.setResizable(true);
            statsStage.initModality(Modality.APPLICATION_MODAL);
            statsStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }


    //Grizta i prisi
    public void goBackToLogin(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.LOGIN_VIEW_DIRECTORY_PATH)));
            Stage loginStage = new Stage();
            Scene scene = new Scene(root, Constants.LOGIN_WINDOW_WIDTH, Constants.LOGIN_WINDOW_HEIGHT);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.CSS_DIRECTORY_PATH)).toExternalForm());
            loginStage.setTitle("Prisijungimas");
            loginStage.setResizable(false);
            loginStage.setScene(scene);
            loginStage.show();
            loginStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                System.exit(0);
                Platform.exit();
            });
            windowClose();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createNewCategory(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.CATEGORY_FORM_VIEW_PATH)));
            Stage createCategoryStage = new Stage();
            Scene scene = new Scene(root, Constants.CATEGORY_FORM_WINDOW_WIDTH, Constants.CATEGORY_FORM_WINDOW_HEIGHT);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.CSS_DIRECTORY_PATH)).toExternalForm());
            createCategoryStage.setTitle("Kategorijos anketa");
            createCategoryStage.setScene(scene);
            createCategoryStage.initModality(Modality.APPLICATION_MODAL);
            createCategoryStage.show();
            createCategoryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                reloadCategoryListView();
            });


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void openStocks(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.OPEN_STOCKS_DIRECTORY_PATH)));
            Stage stocksRate = new Stage();
            Scene scene = new Scene(root);
            stocksRate.initModality(Modality.APPLICATION_MODAL);
            stocksRate.setTitle("Vario kursas");
            stocksRate.setResizable(false);
            stocksRate.setOnCloseRequest(e -> StocksRateController.onClose());
            stocksRate.setScene(scene);
            stocksRate.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    //Atidaro produkto sukurimo form'a
    public void createNewProduct(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.PRODUCT_FORM_VIEW_PATH)));
            Stage createNewProductStage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource(Constants.CSS_DIRECTORY_PATH)).toExternalForm());
            createNewProductStage.setTitle("Produkto įvedimas į duomenų bazę");
            createNewProductStage.setScene(scene);
            createNewProductStage.centerOnScreen();
            createNewProductStage.initModality(Modality.APPLICATION_MODAL);
            createNewProductStage.show();
            createNewProductStage.maxWidthProperty().bind(createNewProductStage.widthProperty());
            createNewProductStage.minWidthProperty().bind(createNewProductStage.widthProperty());
            createNewProductStage.setMaxHeight(400);
            createNewProductStage.setMinHeight(300);

            createNewProductStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                reloadProductTableView();
            });


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void aboutInfo() {
        showInformationPopupWindow("Informacija", "UAB „ECO SPRENDIMAI“\nSusisiekti galite:\n- Tel.: " + Constants.CONTACT_PHONE_NUMBER + "\n- El. paštu: " + Constants.CONTACT_EMAIL + "\nProgramos versija: " + Constants.PROGRAM_VERSION, 400, 150);
    }

    public void windowClose() { //Uzdaro prisijungimo langa
        Stage stage = (Stage) close_button.getScene().getWindow();
        calculateSpentTimeLoggedIn();
        stage.close();
    }

    public static void closeDashboard() {
        calculateSpentTimeLoggedIn();
    }

    public static void calculateSpentTimeLoggedIn() {
        loggedTimeEnd = System.currentTimeMillis();
        loggedTimeSpent = loggedTimeEnd - loggedTimeStart;
        spentTimeInSeconds = (int) loggedTimeSpent / 1000;
        System.out.println("Session time: " + spentTimeInSeconds + " seconds");
        UserHolder userHolder = UserHolder.getInstance();
        UserDAO.updateUserTimeSpent(userHolder.getUser(), spentTimeInSeconds);
    }

    private void loadProgress() {
        Task copyWorker = createWorker();
        loadProgress.progressProperty().bind(copyWorker.progressProperty());
        //table.itemsProperty().bind(copyWorker.valueProperty());
        new Thread(copyWorker).start();
        loadProgress.setVisible(true);

    }

    // Loading Spinner move
    public Task createWorker() {
        return new Task() {
            @Override
            protected TabPane call() throws Exception {
                TabPane tabPane = new TabPane();

                return tabPane;
            }
        };
    }// Loading Spinner set-up-ends


    public void reloadCategoryListView() {
        categoryNamesForListView = CategoriesDAO.selectCategoriesForListView();
        observableCategoryList = FXCollections.observableList(categoryNamesForListView);
        listView.setItems(observableCategoryList);
    }

    public void reloadProductTableView() {
        fullProductList = ProductCatalogDAO.displayAllItems();
        observableProducts = FXCollections.observableList(fullProductList);
        if ((today - userLastLogin.getTime()) / 1000 / 3600 >= 24) {
            setProductPrice(observableProducts);
        } else if ((loggedTimePriceUpdateEnd - loggedTimePriceUpdateStart) / 1000 / 3600 >= 2) {
            setProductPrice(observableProducts);
            loggedTimePriceUpdateStart = System.currentTimeMillis();
        }
        countTableViewObservableProducts(observableProducts);
        table.setItems(observableProducts);
    }


    public static void showInformationPopupWindow(String title, String information, int width, int height) {

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();

        ButtonType buttonTypeOne = new ButtonType(Constants.BUTTON_NAME_OK);
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    public static void showErrorPopupWindow(String title, String information, int width, int height) {

        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);
        dialog.getDialogPane().setPrefSize(width, height);

        ButtonType buttonTypeOne = new ButtonType(Constants.BUTTON_NAME_OK);
        dialog.getButtonTypes().setAll(buttonTypeOne);
        Optional<ButtonType> result = dialog.showAndWait();

    }

}
