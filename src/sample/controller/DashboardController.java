package sample.controller;


import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.paint.Paint;
import javafx.stage.*;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.FormatStringConverter;
import sample.JPA.*;
import sample.JPA.user.User;
import sample.JPA.user.UserDAO;
import sample.JPA.user.UserHolder;
import sample.Main;
import sample.utils.Constants;
import sample.utils.Validation;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static sample.JPA.JPAUtil.getScene;

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
    List<Categories> categoryNamesForListView;
    ObservableList<Categories> observableCategoryList;
    ObservableList<ProductCatalog> observableProducts;
    List<ProductCatalog> fullProductList;
    List<Categories> fullCategoryList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadColumnToTable();
        loadCategoriesToListView();
        currentSessionUserData();
        reloadCategoryListView();
        reloadProductTableView();
        firstFillDescriptionPanel();
        UserHolder userHolder = UserHolder.getInstance();
        UserDAO.setLastLoginTime(userHolder.getUser());
        loggedTimeStart = System.currentTimeMillis(); // Fiksuoja prisijungimo laiko pradžią

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


    }

    //Nusako table'o stulpelius ir jų matmenys.
    public void loadColumnToTable() {

        UserHolder holder = UserHolder.getInstance();
        User u = holder.getUser();
        boolean isAdmin = u.isAdmin();


        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // pakeisti lietuviskai kategorijos numeri, produkto pavadinimas, kaina, kiekis.
        TableColumn number = new TableColumn("#");
        TableColumn<ProductCatalog, Integer> catalogNo = new TableColumn<>("Katalogo nr.");
        TableColumn<ProductCatalog, String> symbol = new TableColumn<>("Produkto pavadinimas");
        TableColumn<ProductCatalog, Double> priceNet = new TableColumn<>("Kaina");
        TableColumn<ProductCatalog, Integer> stock = new TableColumn<>("Kiekis");

        table.getColumns().addAll(number, catalogNo, symbol, priceNet, stock);

        number.minWidthProperty().bind(table.widthProperty().multiply(0.05));
        catalogNo.minWidthProperty().bind(table.widthProperty().multiply(0.17));
        symbol.minWidthProperty().bind(table.widthProperty().multiply(0.52));
        priceNet.minWidthProperty().bind(table.widthProperty().multiply(0.09));
        stock.minWidthProperty().bind(table.widthProperty().multiply(0.09));

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
                        showPopupWindow("Neįvestas produkto pavadinimas", "Redaguojant produkto pavadinimą\n" +
                                "privalote įvesti reikšmę.", "#b02a37", "#FFFFFF");
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
                        showPopupWindow("Blogai įvestas produkto pavadinimas", "Netinkami simboliai: „!@#$*~<>?“\nEilutę privalo sudaryti nuo 2 iki 75 simbolių, \nlietuviški simboliai priimami", "#b02a37", "#FFFFFF");
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
                            showPopupWindow("Blogai įvesta produkto kaina", "Skaičius turi būti 1 arba daugiau simbolių, po kablelio turėti vieną,\ndu arba neturėti skaitmenų. Pavyzdžiui:\n „30“, „7.15“, „1500.0“ ir t.t.", "#b02a37", "#FFFFFF");
                            System.out.println("REGEX VALIDATION DENIED");
                        } else if (event.getNewValue() == null) {
                            productCatalog = event.getRowValue();
                            productCatalog.setPriceNet(event.getOldValue());
                            showPopupWindow("Neįvesta produkto kaina", "Skaičius turi būti 1 arba daugiau simbolių, po kablelio turėti vieną,\ndu arba neturėti skaitmenų. Pavyzdžiui:\n „30“, „7.15“, „1500.0“ ir t.t.", "#b02a37", "#FFFFFF");
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
                            showPopupWindow("Blogai įvesta produkto kaina", "Skaičius turi būti 1 arba daugiau simbolių, po kablelio turėti vieną,\ndu arba neturėti skaitmenų. Pavyzdžiui:\n „30“, „7.15“, „1500.0“ ir t.t.", "#b02a37", "#FFFFFF");
                            System.out.println("REGEX VALIDATION DENIED");
                        }
                    }
                });
            } catch (Exception e) {
                System.out.println(e);
            }

        }


        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));


        number.setResizable(true);
        catalogNo.setResizable(true);
        symbol.setResizable(true);
        priceNet.setResizable(true);
        stock.setResizable(true);

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

    //Paspaudus ant listview elemento tableview panelyje pavaizduoja visus produktus priklausančius šiam kategorija.
    public void mouseEventForListView(MouseEvent mouseEvent) {
        Categories item;
        try {
            if (!listView.getSelectionModel().isEmpty()) {
                item = listView.getSelectionModel().getSelectedItem();
                fullCategoryList = CategoriesDAO.selectCategoryById(item.getId());
                fullProductList = ProductCatalogDAO.displayAllItems();
                observableProducts = FXCollections.observableList(createFilteredProductList(fullCategoryList, fullProductList));
                countTableViewObservableProducts(observableProducts);
                table.setItems(observableProducts);

            }
        } catch (IllegalStateException e) {
            System.out.println("mouseEventForListView( " + e + " ) ");
        } catch (NullPointerException e) {
            System.out.println("mouseEventForListView( " + e + " )");
        }

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
                List<ProductCatalog> dbProducts = ProductCatalogDAO.displayAllItems();
                List<CategoryParameters> allCategoryParameters;

                try {
                    excelProducts = ReadExcelWithProductCatalog.readFileUsingPOI(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert excelProducts != null;

                boolean isProductNew = false;
                int countEveryProductInExcel = 0;
                int countEveryProductUpdated = 0;
                int countEveryNewProduct = 0;
                boolean isProductChanged;


                try {
                    countEveryProductInExcel = excelProducts.size();

                    for (ProductCatalog excelProduct : excelProducts) {
                        if (!dbProducts.isEmpty()) {
                            for (ProductCatalog dbProduct : dbProducts) {
                                isProductChanged = false;
                                if (!excelProduct.getCatalogNo().equals(dbProduct.getCatalogNo())) {
                                    isProductNew = true;
                                } else {
                                    isProductNew = false;
                                    excelProduct.setId(dbProduct.getId());
                                    if (dbProduct.getCatalogNo() != null && excelProduct.getCatalogNo() != null) {
                                        if (!dbProduct.getCatalogNo().equals(excelProduct.getCatalogNo())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getSymbol() != null && excelProduct.getSymbol() != null) {
                                        if (!dbProduct.getSymbol().equals(excelProduct.getSymbol())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getPriceNet() != excelProduct.getPriceNet()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getStock() != excelProduct.getStock()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getGroupId() != excelProduct.getGroupId()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getAukstis() != excelProduct.getAukstis()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getPlotis() != excelProduct.getPlotis()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getGylis() != excelProduct.getGylis()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getSkersmuo() != excelProduct.getSkersmuo()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getIlgis() != excelProduct.getIlgis()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getApsaugos_laipsnis() != null && excelProduct.getApsaugos_laipsnis() != null) {
                                        if (!dbProduct.getApsaugos_laipsnis().equals(excelProduct.getApsaugos_laipsnis())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getModuliu_skaicius() != excelProduct.getModuliu_skaicius()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getVardine_srove() != null && excelProduct.getVardine_srove() != null) {
                                        if (!dbProduct.getVardine_srove().equals(excelProduct.getVardine_srove())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getVardine_itampa() != null && excelProduct.getVardine_itampa() != null) {
                                        if (!dbProduct.getVardine_itampa().equals(excelProduct.getVardine_itampa())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getMechaninis_atsparumas_IK() != null && excelProduct.getMechaninis_atsparumas_IK() != null) {
                                        if (!dbProduct.getMechaninis_atsparumas_IK().equals(excelProduct.getMechaninis_atsparumas_IK())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getSpalva() != null && excelProduct.getSpalva() != null) {
                                        if (!dbProduct.getSpalva().equals(excelProduct.getSpalva())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getKorpuso_medziaga() != null && excelProduct.getKorpuso_medziaga() != null) {
                                        if (!dbProduct.getKorpuso_medziaga().equals(excelProduct.getKorpuso_medziaga())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getIzoliacija() != null && excelProduct.getIzoliacija() != null) {
                                        if (!dbProduct.getIzoliacija().equals(excelProduct.getKorpuso_medziaga())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getSvoris() != excelProduct.getSvoris()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getGalia() != null && excelProduct.getGalia() != null) {
                                        if (!dbProduct.getIzoliacija().equals(excelProduct.getIzoliacija())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getSviesos_srautas() != excelProduct.getSviesos_srautas()) {
                                        isProductChanged = true;
                                    }
                                    if (dbProduct.getSviesos_spalvos_temperatura() != null && excelProduct.getSviesos_spalvos_temperatura() != null) {
                                        if (!dbProduct.getSviesos_spalvos_temperatura().equals(excelProduct.getSviesos_spalvos_temperatura())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getLaidininkas() != null && excelProduct.getLaidininkas() != null) {
                                        if (!dbProduct.getLaidininkas().equals(excelProduct.getLaidininkas())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getDarbine_temperatura() != null && excelProduct.getDarbine_temperatura() != null) {
                                        if (!dbProduct.getDarbine_temperatura().equals(excelProduct.getDarbine_temperatura())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getMax_darbine_temperatura() != null && excelProduct.getMax_darbine_temperatura() != null) {
                                        if (!dbProduct.getMax_darbine_temperatura().equals(excelProduct.getMax_darbine_temperatura())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getApvalkalas() != null && excelProduct.getApvalkalas() != null) {
                                        if (!dbProduct.getApvalkalas().equals(excelProduct.getApvalkalas())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getCpr_klase() != null && excelProduct.getCpr_klase() != null) {
                                        if (!dbProduct.getCpr_klase().equals(excelProduct.getCpr_klase())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getIsjungimo_geba() != null && excelProduct.getIsjungimo_geba() != null) {
                                        if (!dbProduct.getIsjungimo_geba().equals(excelProduct.getIsjungimo_geba())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getIsjungimo_charakteristika() != null && excelProduct.getIsjungimo_charakteristika() != null) {
                                        if (!dbProduct.getIsjungimo_charakteristika().equals(excelProduct.getIsjungimo_charakteristika())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getMechaninis_atsparumas() != null && excelProduct.getMechaninis_atsparumas() != null) {
                                        if (!dbProduct.getMechaninis_atsparumas().equals(excelProduct.getMechaninis_atsparumas())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getSkerspjuvis_Al() != null && excelProduct.getSkerspjuvis_Al() != null) {
                                        if (!dbProduct.getSkerspjuvis_Al().equals(excelProduct.getSkerspjuvis_Al())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getSkerspjuvis_Cu() != null && excelProduct.getSkerspjuvis_Cu() != null) {
                                        if (!dbProduct.getSkerspjuvis_Cu().equals(excelProduct.getSkerspjuvis_Cu())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getNuotekio_srove() != null && excelProduct.getNuotekio_srove() != null) {
                                        if (!dbProduct.getNuotekio_srove().equals(excelProduct.getNuotekio_srove())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getDydis() != null && excelProduct.getDydis() != null) {
                                        if (!dbProduct.getDydis().equals(excelProduct.getDydis())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getPlotas() != null && excelProduct.getPlotas() != null) {
                                        if (!dbProduct.getPlotas().equals(excelProduct.getPlotas())) {
                                            isProductChanged = true;
                                        }
                                    }
                                    if (dbProduct.getImage_url() != null && excelProduct.getImage_url() != null) {
                                        if (!dbProduct.getImage_url().equals(excelProduct.getImage_url())) {
                                            isProductChanged = true;
                                        }
                                    }

                                      if (isProductChanged)  {
                                        ProductCatalogDAO.replace(excelProduct);
                                        countEveryProductUpdated++;
                                    }
                                    break;

                                }
                            }
                        } else {
                            ProductCatalogDAO.insert(excelProduct);
                            countEveryNewProduct++;
                        }
                        if (isProductNew) {
                            ProductCatalogDAO.insert(excelProduct);
                            countEveryNewProduct++;
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("openFile(" + e + " )");
                } catch (RuntimeException e) {
                    System.out.println("openFile(" + e + " )");
                }

                if (countEveryProductUpdated != 0 || countEveryNewProduct != 0) {
                    String successToPopup = "\nFaile rasta produktų: " + countEveryProductInExcel + "\nPakeista produktų: " + countEveryProductUpdated + "\nPridėti nauji produktai: " + countEveryNewProduct + "\n";

                    Platform.runLater(() -> {
                        showPopupWindow("Failas sėkmingai įkeltas", successToPopup, "#146c43", "#FFFFFF");
                        loadProgress.setVisible(false);

                    });
                } else if (countEveryProductUpdated == 0 && countEveryNewProduct == 0 && countEveryProductInExcel == 0) {
                    Platform.runLater(() -> {
                        JPAUtil.showPopupWindow("Klaida!", "- Nuskaityti nepavyko \n- Pasirinktas failas netinkamas : " + file.getName(), "#b02a37", "#FFFFFF", getScene());
                        loadProgress.setVisible(false);
                    });
                } else {
                    String successToPopup = "\nFaile rasta produktų: " + countEveryProductInExcel + "\nPakeista produktų: " + countEveryProductUpdated + "\nPridėti nauji produktai: " + countEveryNewProduct + "\n";

                    Platform.runLater(() -> {
                        showPopupWindow("Failas sėkmingai įkeltas", successToPopup, "#146c43", "#FFFFFF");
                        loadProgress.setVisible(false);

                    });
                }
                Platform.runLater(() -> {
                    loadProgress.setVisible(false);
                    reloadProductTableView();
                });
            }

        });
        uploadExcelLogicalThread.setDaemon(true);
        uploadExcelLogicalThread.start();

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
                if (compareCategoryParameters(allCategoryParameter, categoryParameter)) {
                    newParameter = false;
                    Categories category = CategoriesDAO.displayParentCategoryById(product.getGroupId());
                    if (allCategoryParameter.getId() != category.getCategory_parameter_id()) {
                        CategoriesDAO.updateCategoryParameterById(allCategoryParameter.getId(), product.getGroupId());
                    }
                } else {

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
        return parameter.isDarbine_temperatura() == parameter2.isDarbine_temperatura() && parameter.isSviesos_srautas() == parameter2.isSviesos_srautas() &&
                parameter.isGalia() == parameter2.isGalia() && parameter.isVardine_itampa() == parameter2.isVardine_itampa() && parameter.isSpalva() == parameter2.isSpalva() &&
                parameter.isGylis() == parameter2.isGylis() && parameter.isPlotis() == parameter2.isPlotis() &&
                parameter.isAukstis() == parameter2.isAukstis() && parameter.isApsaugos_laipsnis() == parameter2.isApsaugos_laipsnis() && parameter.isApvalkalas() == parameter2.isApvalkalas() &&
                parameter.isCPR_klase() == parameter2.isCPR_klase() && parameter.isDydis() == parameter2.isDydis() && parameter.isIlgis() == parameter2.isIlgis() &&
                parameter.isIsjungimo_charakteristika() == parameter2.isIsjungimo_charakteristika() && parameter.isIsjungimo_geba() == parameter2.isIsjungimo_geba() &&
                parameter.isIzoliacija() == parameter2.isIzoliacija() && parameter.isKorpuso_medziaga() == parameter2.isKorpuso_medziaga() &&
                parameter.isLaidininkas() == parameter2.isLaidininkas() && parameter.isMax_darbine_temperatura() == parameter2.isMax_darbine_temperatura() && parameter.isMechaninis_atsparumas() == parameter2.isMechaninis_atsparumas() &&
                parameter.isMechaninis_atsparumas_IK() == parameter2.isMechaninis_atsparumas_IK() && parameter.isModuliu_skaicius() == parameter2.isModuliu_skaicius() && parameter.isNuotekio_srove() == parameter2.isNuotekio_srove() &&
                parameter.isPlotas() == parameter2.isPlotas() && parameter.isSkersmuo() == parameter2.isSkersmuo() && parameter.isSkerspjuvis_Al() == parameter2.isSkerspjuvis_Al() && parameter.isSkerspjuvis_Cu() == parameter2.isSkerspjuvis_Cu() &&
                parameter.isSviesos_spalvos_temperatura() == parameter2.isSviesos_spalvos_temperatura() && parameter.isSvoris() == parameter2.isSvoris() && parameter.isVardine_srove() == parameter2.isVardine_srove();
    }

    //Sukuria categoryParameter objektą iš produkto esamų parametrų.
    public CategoryParameters createCategoryParameter(ProductCatalog product) {
        CategoryParameters categoryParameters = new CategoryParameters();

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


        String darbine_temperatura = product.getDarbine_temperatura();
        categoryParameters.setDarbine_temperatura(darbine_temperatura != null);

        String max_darbine_temperatura = product.getMax_darbine_temperatura();
        categoryParameters.setMax_darbine_temperatura(max_darbine_temperatura != null);

        String apvalkalas = product.getApvalkalas();
        categoryParameters.setApvalkalas(apvalkalas != null);

        String cpr_klase = product.getCpr_klase();
        categoryParameters.setCPR_klase(cpr_klase != null);

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


        VBox desciptionLabelVBox = new VBox();
        VBox propertyLabelVBox = new VBox();
        HBox informationPanelHBox = new HBox();
        VBox imageVBox = new VBox();

        propertyLabelVBox.setPadding(new Insets(0, 5, 2, 5));
        desciptionLabelVBox.setPadding(new Insets(0, 5, 10, 5));
        desciptionLabelVBox.setMinWidth(130);
        propertyLabelVBox.setMinWidth(130);
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
                symbolProperty.setPadding(new Insets(3, 3, 3, 3));
                symbolProperty.setStyle("-fx-font-weight: bold; -fx-background-color: linear-gradient(to top, #D9D9D9, #EDEDED); -fx-border-width: 1; -fx-border-color: #c8c8c8; -fx-border-radius: 1;");
                symbolProperty.setText(irasas.getSymbol());
                right_panel_main_vbox.getChildren().add(symbolProperty);

                Label catalogNoDescription = new Label();
                catalogNoDescription.setStyle("-fx-underline: true;");
                catalogNoDescription.setLayoutX(20);
                catalogNoDescription.setLayoutY(getRightPanelLabelY());
                Label catalogNoProperty = new Label();
                catalogNoProperty.setStyle("-fx-font-weight: bold;");
                catalogNoProperty.setLayoutX(60);
                catalogNoProperty.setLayoutY(getRightPanelLabelY());
                catalogNoDescription.setText("Katalogo kodas:");
                catalogNoProperty.setText(irasas.getCatalogNo());
                desciptionLabelVBox.getChildren().add(catalogNoDescription);
                propertyLabelVBox.getChildren().add(catalogNoProperty);

                Label priceNetDescription = new Label();
                Label priceNetProperty = new Label();
                priceNetDescription.setLayoutX(20);
                priceNetDescription.setLayoutY(getRightPanelLabelY());
                priceNetProperty.setLayoutX(60);
                priceNetProperty.setLayoutY(getRightPanelLabelY());
                priceNetProperty.setStyle("-fx-font-weight: bold;");
                priceNetDescription.setText("Kaina: ");
                priceNetProperty.setText(irasas.getPriceNet() + "€");
                desciptionLabelVBox.getChildren().add(priceNetDescription);
                propertyLabelVBox.getChildren().add(priceNetProperty);

                Label stockDescription = new Label();
                Label stockProperty = new Label();
                stockProperty.setStyle("-fx-font-weight: bold;");
                stockDescription.setLayoutX(20);
                stockDescription.setLayoutY(getRightPanelLabelY());
                stockProperty.setLayoutX(60);
                stockProperty.setLayoutY(getRightPanelLabelY());
                stockDescription.setText("Likutis: ");
                stockProperty.setText(String.valueOf(irasas.getStock()));
                desciptionLabelVBox.getChildren().add(stockDescription);
                propertyLabelVBox.getChildren().add(stockProperty);


                if (irasas.getAukstis() != 0) {
                    Label heightDescription = new Label();
                    Label heightProperty = new Label();
                    heightProperty.setStyle("-fx-font-weight: bold;");
                    heightDescription.setLayoutX(20);
                    heightDescription.setLayoutY(getRightPanelLabelY());
                    heightProperty.setLayoutX(60);
                    heightProperty.setLayoutY(getRightPanelLabelY());
                    heightDescription.setText("Aukštis: ");
                    heightProperty.setText(String.valueOf(irasas.getAukstis()));
                    desciptionLabelVBox.getChildren().add(heightDescription);
                    propertyLabelVBox.getChildren().add(heightProperty);
                }
                if (irasas.getPlotis() != 0) {
                    Label widthDescription = new Label();
                    Label widthProperty = new Label();
                    widthProperty.setStyle("-fx-font-weight: bold;");
                    widthDescription.setLayoutX(20);
                    widthDescription.setLayoutY(getRightPanelLabelY());
                    widthProperty.setLayoutX(60);
                    widthProperty.setLayoutY(getRightPanelLabelY());
                    widthDescription.setText("Plotis: ");
                    widthProperty.setText(String.valueOf(irasas.getPlotis()));
                    desciptionLabelVBox.getChildren().add(widthDescription);
                    propertyLabelVBox.getChildren().add(widthProperty);
                }
                if (irasas.getGylis() != 0) {
                    Label depthDescription = new Label();
                    Label depthProperty = new Label();
                    depthProperty.setStyle("-fx-font-weight: bold;");
                    depthDescription.setLayoutX(20);
                    depthDescription.setLayoutY(getRightPanelLabelY());
                    depthProperty.setLayoutX(60);
                    depthProperty.setLayoutY(getRightPanelLabelY());
                    depthDescription.setText("Gylis: ");
                    depthProperty.setText(String.valueOf(irasas.getGylis()));
                    desciptionLabelVBox.getChildren().add(depthDescription);
                    propertyLabelVBox.getChildren().add(depthProperty);
                }
                if (irasas.getSkersmuo() != 0) {
                    Label radiusDescription = new Label();
                    Label radiusProperty = new Label();
                    radiusProperty.setStyle("-fx-font-weight: bold;");
                    radiusDescription.setLayoutX(20);
                    radiusDescription.setLayoutY(getRightPanelLabelY());
                    radiusProperty.setLayoutX(60);
                    radiusProperty.setLayoutY(getRightPanelLabelY());
                    radiusDescription.setText("Skersmuo: ");
                    radiusProperty.setText(String.valueOf(irasas.getSkersmuo()));
                    desciptionLabelVBox.getChildren().add(radiusDescription);
                    propertyLabelVBox.getChildren().add(radiusProperty);
                }
                if (irasas.getIlgis() != 0) {
                    Label lengthDescription = new Label();
                    Label lengthProperty = new Label();
                    lengthProperty.setStyle("-fx-font-weight: bold;");
                    lengthDescription.setLayoutX(20);
                    lengthDescription.setLayoutY(getRightPanelLabelY());
                    lengthProperty.setLayoutX(60);
                    lengthProperty.setLayoutY(getRightPanelLabelY());
                    lengthDescription.setText("Ilgis: ");
                    lengthProperty.setText(String.valueOf(irasas.getIlgis()));
                    desciptionLabelVBox.getChildren().add(lengthDescription);
                    propertyLabelVBox.getChildren().add(lengthProperty);
                }
                if (irasas.getApsaugos_laipsnis() != null && !irasas.getApsaugos_laipsnis().isEmpty()) {
                    Label securityLevelDescription = new Label();
                    Label securityLevelProperty = new Label();
                    securityLevelProperty.setStyle("-fx-font-weight: bold;");
                    securityLevelDescription.setLayoutX(20);
                    securityLevelDescription.setLayoutY(getRightPanelLabelY());
                    securityLevelProperty.setLayoutX(60);
                    securityLevelProperty.setLayoutY(getRightPanelLabelY());
                    securityLevelDescription.setText("Apsaugos laipsnis: ");
                    securityLevelProperty.setText(irasas.getApsaugos_laipsnis());
                    desciptionLabelVBox.getChildren().add(securityLevelDescription);
                    propertyLabelVBox.getChildren().add(securityLevelProperty);
                }
                if (irasas.getModuliu_skaicius() != 0) {
                    Label modulesNumberDescription = new Label();
                    Label modulesNumberProperties = new Label();
                    modulesNumberProperties.setStyle("-fx-font-weight: bold;");
                    modulesNumberDescription.setLayoutX(20);
                    modulesNumberDescription.setLayoutY(getRightPanelLabelY());
                    modulesNumberProperties.setLayoutX(60);
                    modulesNumberProperties.setLayoutY(getRightPanelLabelY());
                    modulesNumberDescription.setText("Galia: ");
                    modulesNumberProperties.setText(String.valueOf(irasas.getModuliu_skaicius()));
                    desciptionLabelVBox.getChildren().add(modulesNumberDescription);
                    propertyLabelVBox.getChildren().add(modulesNumberProperties);
                }
                if (irasas.getVardine_itampa() != null) {
                    Label voltageDescription = new Label();
                    Label voltageProperty = new Label();
                    voltageProperty.setStyle("-fx-font-weight: bold;");
                    voltageDescription.setLayoutX(20);
                    voltageDescription.setLayoutY(getRightPanelLabelY());
                    voltageProperty.setLayoutX(60);
                    voltageProperty.setLayoutY(getRightPanelLabelY());
                    voltageDescription.setText("Vardinė įtampa: ");
                    voltageProperty.setText(String.valueOf(irasas.getVardine_itampa()));
                    desciptionLabelVBox.getChildren().add(voltageDescription);
                    propertyLabelVBox.getChildren().add(voltageProperty);
                }
                if (irasas.getVardine_srove() != null) {
                    Label voltageFlowDescription = new Label();
                    Label voltageFlowProperty = new Label();
                    voltageFlowProperty.setStyle("-fx-font-weight: bold;");
                    voltageFlowDescription.setLayoutX(20);
                    voltageFlowDescription.setLayoutY(getRightPanelLabelY());
                    voltageFlowProperty.setLayoutX(60);
                    voltageFlowProperty.setLayoutY(getRightPanelLabelY());
                    voltageFlowDescription.setText("Vardinė srovė: ");
                    voltageFlowProperty.setText(String.valueOf(irasas.getVardine_srove()));
                    desciptionLabelVBox.getChildren().add(voltageFlowDescription);
                    propertyLabelVBox.getChildren().add(voltageFlowProperty);
                }
                if (irasas.getMechaninis_atsparumas_IK() != null && !irasas.getMechaninis_atsparumas_IK().isEmpty()) {
                    Label mechanicalResistanceIKDescription = new Label();
                    Label mechanicalResistanceIKProperty = new Label();
                    mechanicalResistanceIKProperty.setStyle("-fx-font-weight: bold;");
                    mechanicalResistanceIKDescription.setLayoutX(20);
                    mechanicalResistanceIKDescription.setLayoutY(getRightPanelLabelY());
                    mechanicalResistanceIKProperty.setLayoutX(60);
                    mechanicalResistanceIKProperty.setLayoutY(getRightPanelLabelY());
                    mechanicalResistanceIKDescription.setText("Mechaninis atsparumas: ");
                    mechanicalResistanceIKProperty.setText(String.valueOf(irasas.getMechaninis_atsparumas_IK()));
                    desciptionLabelVBox.getChildren().add(mechanicalResistanceIKDescription);
                    propertyLabelVBox.getChildren().add(mechanicalResistanceIKProperty);
                }
                if (irasas.getSpalva() != null && !irasas.getSpalva().isEmpty()) {
                    Label colorDescription = new Label();
                    Label colorProperty = new Label();
                    colorProperty.setStyle("-fx-font-weight: bold;");
                    colorDescription.setLayoutX(20);
                    colorDescription.setLayoutY(getRightPanelLabelY());
                    colorProperty.setLayoutX(60);
                    colorProperty.setLayoutY(getRightPanelLabelY());
                    colorDescription.setText("Spalva: ");
                    colorProperty.setText(irasas.getSpalva());
                    desciptionLabelVBox.getChildren().add(colorDescription);
                    propertyLabelVBox.getChildren().add(colorProperty);
                }
                if (irasas.getKorpuso_medziaga() != null && !irasas.getKorpuso_medziaga().isEmpty()) {
                    Label corpusMaterialDescription = new Label();
                    Label corpusMaterialProperty = new Label();
                    corpusMaterialProperty.setStyle("-fx-font-weight: bold;");
                    corpusMaterialDescription.setLayoutX(20);
                    corpusMaterialDescription.setLayoutY(getRightPanelLabelY());
                    corpusMaterialProperty.setLayoutX(60);
                    corpusMaterialProperty.setLayoutY(getRightPanelLabelY());
                    corpusMaterialDescription.setText("Korpuso medžiaga: ");
                    corpusMaterialProperty.setText(String.valueOf(irasas.getKorpuso_medziaga()));
                    desciptionLabelVBox.getChildren().add(corpusMaterialDescription);
                    propertyLabelVBox.getChildren().add(corpusMaterialProperty);
                }
                if (irasas.getIzoliacija() != null && !irasas.getIzoliacija().isEmpty()) {
                    Label isolationDescription = new Label();
                    Label isolationProperty = new Label();
                    isolationProperty.setStyle("-fx-font-weight: bold;");
                    isolationDescription.setLayoutX(20);
                    isolationDescription.setLayoutY(getRightPanelLabelY());
                    isolationProperty.setLayoutX(60);
                    isolationProperty.setLayoutY(getRightPanelLabelY());
                    isolationDescription.setText("Izoliacija: ");
                    isolationProperty.setText(String.valueOf(irasas.getIzoliacija()));
                    desciptionLabelVBox.getChildren().add(isolationDescription);
                    propertyLabelVBox.getChildren().add(isolationProperty);
                }
                if (irasas.getSvoris() != 0) {
                    Label weightDescription = new Label();
                    Label weightProperty = new Label();
                    weightProperty.setStyle("-fx-font-weight: bold;");
                    weightDescription.setLayoutX(20);
                    weightDescription.setLayoutY(getRightPanelLabelY());
                    weightProperty.setLayoutX(60);
                    weightProperty.setLayoutY(getRightPanelLabelY());
                    weightDescription.setText("Svoris: ");
                    weightProperty.setText(String.valueOf(irasas.getSvoris()));
                    desciptionLabelVBox.getChildren().add(weightDescription);
                    propertyLabelVBox.getChildren().add(weightProperty);
                }
                if (irasas.getGalia() != null) {
                    Label powerDescription = new Label();
                    Label powerProperty = new Label();
                    powerProperty.setStyle("-fx-font-weight: bold;");
                    powerDescription.setLayoutX(20);
                    powerDescription.setLayoutY(getRightPanelLabelY());
                    powerProperty.setLayoutX(60);
                    powerProperty.setLayoutY(getRightPanelLabelY());
                    powerDescription.setText("Galia: ");
                    powerProperty.setText(String.valueOf(irasas.getGalia()));
                    desciptionLabelVBox.getChildren().add(powerDescription);
                    propertyLabelVBox.getChildren().add(powerProperty);
                }
                if (irasas.getSviesos_srautas() != 0) {
                    Label lightStreamDescription = new Label();
                    Label lightStreamProperty = new Label();
                    lightStreamProperty.setStyle("-fx-font-weight: bold;");
                    lightStreamDescription.setLayoutX(20);
                    lightStreamDescription.setLayoutY(getRightPanelLabelY());
                    lightStreamProperty.setLayoutX(60);
                    lightStreamProperty.setLayoutY(getRightPanelLabelY());
                    lightStreamDescription.setText("Šviesos srautas: ");
                    lightStreamProperty.setText(String.valueOf(irasas.getSviesos_srautas()));
                    desciptionLabelVBox.getChildren().add(lightStreamDescription);
                    propertyLabelVBox.getChildren().add(lightStreamProperty);
                }

                if (irasas.getSviesos_spalvos_temperatura() != null && !irasas.getSviesos_spalvos_temperatura().isEmpty()) {
                    Label lightColorTemperatureDescription = new Label();
                    Label lightColorTemperatureProperty = new Label();
                    lightColorTemperatureProperty.setStyle("-fx-font-weight: bold;");
                    lightColorTemperatureDescription.setLayoutX(20);
                    lightColorTemperatureDescription.setLayoutY(getRightPanelLabelY());
                    lightColorTemperatureProperty.setLayoutX(60);
                    lightColorTemperatureProperty.setLayoutY(getRightPanelLabelY());
                    lightColorTemperatureDescription.setText("Spalvos temperatūra: ");
                    lightColorTemperatureProperty.setText(String.valueOf(irasas.getSviesos_spalvos_temperatura()));
                    desciptionLabelVBox.getChildren().add(lightColorTemperatureDescription);
                    propertyLabelVBox.getChildren().add(lightColorTemperatureProperty);
                }
                if (irasas.getLaidininkas() != null && !irasas.getLaidininkas().isEmpty()) {
                    Label conductorDescription = new Label();
                    Label conductorProperty = new Label();
                    conductorProperty.setStyle("-fx-font-weight: bold;");
                    conductorDescription.setLayoutX(20);
                    conductorDescription.setLayoutY(getRightPanelLabelY());
                    conductorProperty.setLayoutX(60);
                    conductorProperty.setLayoutY(getRightPanelLabelY());
                    conductorDescription.setText("Laidininkas: ");
                    conductorProperty.setText(String.valueOf(irasas.getLaidininkas()));
                    desciptionLabelVBox.getChildren().add(conductorDescription);
                    propertyLabelVBox.getChildren().add(conductorProperty);
                }
                if (irasas.getIzoliacija() != null && !irasas.getIzoliacija().isEmpty()) {
                    Label isolationDescription = new Label();
                    Label isolationProperty = new Label();
                    isolationProperty.setStyle("-fx-font-weight: bold;");
                    isolationDescription.setLayoutX(20);
                    isolationDescription.setLayoutY(getRightPanelLabelY());
                    isolationProperty.setLayoutX(60);
                    isolationProperty.setLayoutY(getRightPanelLabelY());
                    isolationDescription.setText("Izoliacija: ");
                    isolationProperty.setText(String.valueOf(irasas.getIzoliacija()));
                    desciptionLabelVBox.getChildren().add(isolationDescription);
                    propertyLabelVBox.getChildren().add(isolationProperty);
                }
                if (irasas.getDarbine_temperatura() != null) {
                    Label workingTemperatureDescription = new Label();
                    Label workingTemperatureProperty = new Label();
                    workingTemperatureProperty.setStyle("-fx-font-weight: bold;");
                    workingTemperatureDescription.setLayoutX(20);
                    workingTemperatureDescription.setLayoutY(getRightPanelLabelY());
                    workingTemperatureProperty.setLayoutX(60);
                    workingTemperatureProperty.setLayoutY(getRightPanelLabelY());
                    workingTemperatureDescription.setText("Darbinė temperatūra:  ");
                    workingTemperatureProperty.setText(String.valueOf(irasas.getDarbine_temperatura()));
                    desciptionLabelVBox.getChildren().add(workingTemperatureDescription);
                    propertyLabelVBox.getChildren().add(workingTemperatureProperty);
                }
                if (irasas.getMax_darbine_temperatura() != null) {
                    Label maxWorkTemperatureDescription = new Label();
                    Label maxWorkTemperatureProperty = new Label();
                    maxWorkTemperatureProperty.setStyle("-fx-font-weight: bold;");
                    maxWorkTemperatureDescription.setLayoutX(20);
                    maxWorkTemperatureDescription.setLayoutY(getRightPanelLabelY());
                    maxWorkTemperatureProperty.setLayoutX(60);
                    maxWorkTemperatureProperty.setLayoutY(getRightPanelLabelY());
                    maxWorkTemperatureDescription.setText("Maks. darb. temperatūra: ");
                    maxWorkTemperatureProperty.setText(String.valueOf(irasas.getMax_darbine_temperatura()));
                    desciptionLabelVBox.getChildren().add(maxWorkTemperatureDescription);
                    propertyLabelVBox.getChildren().add(maxWorkTemperatureProperty);
                }
                if (irasas.getApvalkalas() != null && !irasas.getApvalkalas().isEmpty()) {
                    Label membraneDescription = new Label();
                    Label membraneProperty = new Label();
                    membraneProperty.setStyle("-fx-font-weight: bold;");
                    membraneDescription.setLayoutX(20);
                    membraneDescription.setLayoutY(getRightPanelLabelY());
                    membraneProperty.setLayoutX(60);
                    membraneProperty.setLayoutY(getRightPanelLabelY());
                    membraneDescription.setText("Apvalkalas: ");
                    membraneProperty.setText(String.valueOf(irasas.getApvalkalas()));
                    desciptionLabelVBox.getChildren().add(membraneDescription);
                    propertyLabelVBox.getChildren().add(membraneProperty);
                }
                if (irasas.getCpr_klase() != null && !irasas.getCpr_klase().isEmpty()) {
                    Label cprClassDescription = new Label();
                    Label cprClassProperty = new Label();
                    cprClassProperty.setStyle("-fx-font-weight: bold;");
                    cprClassDescription.setLayoutX(20);
                    cprClassDescription.setLayoutY(getRightPanelLabelY());
                    cprClassProperty.setLayoutX(60);
                    cprClassProperty.setLayoutY(getRightPanelLabelY());
                    cprClassDescription.setText("CPR klasė: ");
                    cprClassProperty.setText(String.valueOf(irasas.getCpr_klase()));
                    desciptionLabelVBox.getChildren().add(cprClassDescription);
                    propertyLabelVBox.getChildren().add(cprClassProperty);
                }
                if (irasas.getIsjungimo_geba() != null && !irasas.getIsjungimo_geba().isEmpty()) {
                    Label offCapacityDescription = new Label();
                    Label offCapacityProperty = new Label();
                    offCapacityProperty.setStyle("-fx-font-weight: bold;");
                    offCapacityDescription.setLayoutX(20);
                    offCapacityDescription.setLayoutY(getRightPanelLabelY());
                    offCapacityProperty.setLayoutX(60);
                    offCapacityProperty.setLayoutY(getRightPanelLabelY());
                    offCapacityDescription.setText("Išjungimo geba: ");
                    offCapacityProperty.setText(String.valueOf(irasas.getIsjungimo_geba()));
                    desciptionLabelVBox.getChildren().add(offCapacityDescription);
                    propertyLabelVBox.getChildren().add(offCapacityProperty);
                }
                if (irasas.getIsjungimo_charakteristika() != null && !irasas.getIsjungimo_charakteristika().isEmpty()) {
                    Label offCharDescription = new Label();
                    Label offCharProperty = new Label();
                    offCharProperty.setStyle("-fx-font-weight: bold;");
                    offCharDescription.setLayoutX(20);
                    offCharDescription.setLayoutY(getRightPanelLabelY());
                    offCharProperty.setLayoutX(60);
                    offCharProperty.setLayoutY(getRightPanelLabelY());
                    offCharDescription.setText("Išjungimo charakte.: ");
                    offCharProperty.setText(String.valueOf(irasas.getIsjungimo_charakteristika()));
                    desciptionLabelVBox.getChildren().add(offCharDescription);
                    propertyLabelVBox.getChildren().add(offCharProperty);
                }
                if (irasas.getMechaninis_atsparumas() != null && !irasas.getMechaninis_atsparumas().isEmpty()) {
                    Label mechanicalResistanceDescription = new Label();
                    Label mechanicalResistanceProperty = new Label();
                    mechanicalResistanceProperty.setStyle("-fx-font-weight: bold;");
                    mechanicalResistanceDescription.setLayoutX(20);
                    mechanicalResistanceDescription.setLayoutY(getRightPanelLabelY());
                    mechanicalResistanceProperty.setLayoutX(60);
                    mechanicalResistanceProperty.setLayoutY(getRightPanelLabelY());
                    mechanicalResistanceDescription.setText("Mechaninis atspar.: ");
                    mechanicalResistanceProperty.setText(String.valueOf(irasas.getMechaninis_atsparumas()));
                    desciptionLabelVBox.getChildren().add(mechanicalResistanceDescription);
                    propertyLabelVBox.getChildren().add(mechanicalResistanceProperty);
                }
                if (irasas.getSkerspjuvis_Al() != null && !irasas.getSkerspjuvis_Al().isEmpty()) {
                    Label crosscutDescription = new Label();
                    Label crosscutProperty = new Label();
                    crosscutProperty.setStyle("-fx-font-weight: bold;");
                    crosscutDescription.setLayoutX(20);
                    crosscutDescription.setLayoutY(getRightPanelLabelY());
                    crosscutProperty.setLayoutX(60);
                    crosscutProperty.setLayoutY(getRightPanelLabelY());
                    crosscutDescription.setText("Skerspjūvis Al: ");
                    crosscutProperty.setText(String.valueOf(irasas.getSkerspjuvis_Al()));
                    desciptionLabelVBox.getChildren().add(crosscutDescription);
                    propertyLabelVBox.getChildren().add(crosscutProperty);
                }
                if (irasas.getSkerspjuvis_Cu() != null && !irasas.getSkerspjuvis_Cu().isEmpty()) {
                    Label crosscutDescription2 = new Label();
                    Label crosscutProperty2 = new Label();
                    crosscutProperty2.setStyle("-fx-font-weight: bold;");
                    crosscutDescription2.setLayoutX(20);
                    crosscutDescription2.setLayoutY(getRightPanelLabelY());
                    crosscutProperty2.setLayoutX(60);
                    crosscutProperty2.setLayoutY(getRightPanelLabelY());
                    crosscutDescription2.setText("Skerspjūvis Cu: ");
                    crosscutProperty2.setText(String.valueOf(irasas.getSkerspjuvis_Cu()));
                    desciptionLabelVBox.getChildren().add(crosscutDescription2);
                    propertyLabelVBox.getChildren().add(crosscutProperty2);
                }
                if (irasas.getNuotekio_srove() != null && !irasas.getNuotekio_srove().isEmpty()) {
                    Label currentDescription = new Label();
                    Label currentProperty = new Label();
                    currentProperty.setStyle("-fx-font-weight: bold;");
                    currentDescription.setLayoutX(20);
                    currentDescription.setLayoutY(getRightPanelLabelY());
                    currentProperty.setLayoutX(60);
                    currentProperty.setLayoutY(getRightPanelLabelY());
                    currentDescription.setText("Nuotėkio srovė: ");
                    currentProperty.setText(String.valueOf(irasas.getNuotekio_srove()));
                    desciptionLabelVBox.getChildren().add(currentDescription);
                    propertyLabelVBox.getChildren().add(currentProperty);
                }
                if (irasas.getDydis() != null) {
                    Label sizeDescription = new Label();
                    Label sizeProperty = new Label();
                    sizeProperty.setStyle("-fx-font-weight: bold;");
                    sizeDescription.setLayoutX(20);
                    sizeDescription.setLayoutY(getRightPanelLabelY());
                    sizeProperty.setLayoutX(60);
                    sizeProperty.setLayoutY(getRightPanelLabelY());
                    sizeDescription.setText("Dydis: ");
                    sizeProperty.setText(String.valueOf(irasas.getDydis()));
                    desciptionLabelVBox.getChildren().add(sizeDescription);
                    propertyLabelVBox.getChildren().add(sizeProperty);
                }
                if (irasas.getPlotas() != null && !irasas.getPlotas().isEmpty()) {
                    Label spaceDescription = new Label();
                    Label spaceProperty = new Label();
                    spaceProperty.setStyle("-fx-font-weight: bold;");
                    spaceDescription.setLayoutX(20);
                    spaceDescription.setLayoutY(getRightPanelLabelY());
                    spaceProperty.setLayoutX(60);
                    spaceProperty.setLayoutY(getRightPanelLabelY());
                    spaceDescription.setText("Plotas: ");
                    spaceProperty.setText(String.valueOf(irasas.getPlotas()));
                    desciptionLabelVBox.getChildren().add(spaceDescription);
                    propertyLabelVBox.getChildren().add(spaceProperty);
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

        joinedInformationPanelWithImageHBox.getChildren().add(desciptionLabelVBox);
        joinedInformationPanelWithImageHBox.getChildren().add(propertyLabelVBox);
        informationPanelHBox.getChildren().add(joinedInformationPanelWithImageHBox);
        informationPanelHBox.getChildren().add(imageVBox);
        informationPanelHBox.setStyle("-fx-border-width: 3; -fx-border-color: #B7B7B7;");
        right_panel_main_vbox.getChildren().add(informationPanelHBox);
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
        showPopupWindow("Informacija", "UAB „ECO SPRENDIMAI“\nSusisiekti galite:\n- Tel.: 869981772\n- El. paštu: julius@ecosprendimai.lt\nProgramos versija: " + Constants.PROGRAM_VERSION, "#0a58ca", "#FFFFFF");
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
//                final int count = 1000 - 1;
//                for (int i = 1; i <= count; i++) {
//                    Thread.sleep(100000);
//                }
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
        fullCategoryList = CategoriesDAO.displayAllCategories();
        observableProducts = createFilteredProductList(fullCategoryList, fullProductList);
        countTableViewObservableProducts(observableProducts);
        table.setItems(observableProducts);
    }

    public void showPopupWindow(String title, String information, String titleBackgroundColor, String titleTextColor) {

        Window parent = table.getScene().getWindow();
        Popup popup = new Popup();

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-effect: dropshadow(two-pass-box, #000000, 10, 0.0, 1.0, 1.0);");


        HBox hBox1 = new HBox();
        hBox1.setStyle("-fx-background-color: " + titleBackgroundColor + ";");
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setMinSize(450, 29);

        HBox hBox11 = new HBox();
        hBox11.setAlignment(Pos.CENTER_LEFT);
        hBox11.setMinSize(420, 28);
        hBox11.setPrefSize(420, 28);

        Label labelTitle = new Label();
        labelTitle.setMinSize(100, 29);
        labelTitle.setAlignment(Pos.CENTER_LEFT);
        labelTitle.setText(title);
        labelTitle.setStyle("-fx-font-size: 14;");
        labelTitle.setTextFill(Paint.valueOf(titleTextColor));
        hBox1.getChildren().add(hBox11);
        hBox11.getChildren().add(labelTitle);

        HBox hBox2 = new HBox();
        HBox hBox21 = new HBox();
        hBox21.setMinSize(370, 160);

        hBox2.setStyle("-fx-background-color: #FFFFFF;");
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setMinSize(350, 120);
        Label stringInformation = new Label();
        stringInformation.setMinSize(300, 100);
        stringInformation.setStyle("-fx-font-size: 14;");
        stringInformation.setAlignment(Pos.CENTER_LEFT);
        stringInformation.setText(information);
        hBox2.getChildren().add(stringInformation);

        HBox hBox3 = new HBox();
        hBox3.setStyle("-fx-background-color: #F0F0F0;");
        hBox3.setAlignment(Pos.CENTER_RIGHT);
        hBox3.setMinSize(170, 65);
        HBox hBox31 = new HBox();
        hBox31.setStyle("-fx-background-color: #F0F0F0;");
        hBox31.setAlignment(Pos.CENTER);
        hBox31.setMinSize(140, 28);
        Button okButton = new Button();
        okButton.setText("Gerai" + "\n");
        okButton.setStyle("-fx-font-size: 14; -fx-background-radius: 0; -fx-background-color: #0078D7, linear-gradient(#E1e1e1, #E1E1E1);");
        okButton.setMinSize(82, 28);
        okButton.setAlignment(Pos.CENTER);
        okButton.setOnAction(event -> popup.hide());

        hBox31.getChildren().add(okButton);
        hBox3.getChildren().add(hBox31);

        root.getChildren().add(hBox1);
        root.getChildren().add(hBox2);
        root.getChildren().add(hBox3);

        popup.getContent().addAll(root);
        popup.show(parent);
    }
}

