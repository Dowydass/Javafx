package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.JPA.*;
import sample.Main;
import sample.utils.Constants;
import sample.utils.Validation;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ProductFormController extends Main implements Initializable {

    public Button create_product_button;
    public TextField catalog_no_textField;
    public TextField symbol_textField;
    public TextField price_textField;
    public TextField nuotraukos_adresas_field;

    public Label form_info_label;
    public ComboBox<Categories> categoryComboBox;
    public CategoryParameters categoryParameters;


    TextField aukstis_field = new TextField();
    TextField plotis_field = new TextField();
    TextField gylis_field = new TextField();
    TextField skersmuo_field = new TextField();
    TextField ilgis_field = new TextField();
    TextField apsaugos_laipsnis_field = new TextField();
    TextField moduliu_skaicius_field = new TextField();
    TextField vardine_srove_field = new TextField();
    TextField vardine_itampa_field = new TextField();
    TextField mechaninis_atsparumas_IK_field = new TextField();
    TextField spalva_field = new TextField();
    TextField korpuso_medziaga_field = new TextField();
    TextField izoliacija_field = new TextField();
    TextField svoris_field = new TextField();
    TextField galia_field = new TextField();
    TextField sviesos_srautas_field = new TextField();
    TextField sviesos_spalvos_temperatura_field = new TextField();
    TextField laidininkas_field = new TextField();
    TextField darbine_temperatura_field = new TextField();
    TextField max_darbine_temperatura_field = new TextField();
    TextField apvalkalas_field = new TextField();
    TextField cpr_klase_field = new TextField();
    TextField isjungimo_geba_field = new TextField();
    TextField isjungimo_charakteristika_field = new TextField();
    TextField mechaninis_atsparumas_field = new TextField();
    TextField skerspjuvis_Al_field = new TextField();
    TextField skerspjuvis_Cu_field = new TextField();
    TextField nuotekio_srove_field = new TextField();
    TextField dydis_field = new TextField();
    TextField plotas_field = new TextField();

    @FXML
    public AnchorPane main_anchor_panel;

    public static VBox vbox;
    public static ScrollPane letsScroll;


    public void createProduct(ActionEvent actionEvent) {
        Categories item = categoryComboBox.getSelectionModel().getSelectedItem();
        if (catalog_no_textField.getText().isEmpty() && symbol_textField.getText().isEmpty() &&
                price_textField.getText().isEmpty() && nuotraukos_adresas_field.getText().isEmpty() && (item == null)) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_FILLED);
        } else if (!Validation.isValidCatalogNo(catalog_no_textField.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CATALOG_NUMBER);
        } else if (!Validation.isValidSymbol(symbol_textField.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SYMBOL);
        } else if (!Validation.isValidPrice(price_textField.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PRICE);
        } else if (!Validation.isValidImageUrl(nuotraukos_adresas_field.getText()) && !nuotraukos_adresas_field.getText().isEmpty()) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_IMAGE_URL);
        } else if ((item == null)) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CHOSEN_CATEGORY);
        } else if (categoryParameters.isAukstis() && !Validation.isValidAukstis(aukstis_field.getText())){
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_AUKSTIS);
        } else if (categoryParameters.isPlotis() && !Validation.isValidPlotis(plotis_field.getText())){
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PLOTIS);
        } else if (categoryParameters.isGylis() && !Validation.isValidGylis(gylis_field.getText())){
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GYLIS);
        } else if (categoryParameters.isSkersmuo() && !Validation.isValidSkersmuo(skersmuo_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSMUO);
        } else if (categoryParameters.isIlgis() && !Validation.isValidIlgis(ilgis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ILGIS);
        } else if (categoryParameters.isApsaugos_laipsnis() && !Validation.isValidApsaugosLaipsnis(apsaugos_laipsnis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APSAUGOS_LAIPSNIS);
        } else if (categoryParameters.isModuliu_skaicius() && !Validation.isValidModuliuSkaicius(moduliu_skaicius_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MODULIU_SKAICIUS);
        }else if (categoryParameters.isVardine_srove() && !Validation.isValidVardineSrove(vardine_srove_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VARDINE_SROVE);
        } else if (categoryParameters.isVardine_itampa() && !Validation.isValidVardineItampa(vardine_itampa_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VARDINE_ITAMPA);
        } else if (categoryParameters.isMechaninis_atsparumas_IK() && !Validation.isValidMechaninisAtsparumasIK(mechaninis_atsparumas_IK_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MECHANINIS_ATSPARUMAS_IK);
        } else if (categoryParameters.isSpalva() && !Validation.isValidSpalva(spalva_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SPALVA);
        } else if (categoryParameters.isKorpuso_medziaga() && !Validation.isValidKorpusoMedziaga(korpuso_medziaga_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KORPUSO_MEDZIAGA);
        } else if (categoryParameters.isIzoliacija() && !Validation.isValidIzoliacija(izoliacija_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_IZOLIACIJA);
        } else if (categoryParameters.isSvoris() && !Validation.isValidSvoris(svoris_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVORIS);
        } else if (categoryParameters.isGalia() && !Validation.isValidGalia(galia_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GALIA);
        } else if (categoryParameters.isSviesos_srautas() && !Validation.isValidSviesosSrautas(sviesos_srautas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVIESOS_SRAUTAS);
        } else if (categoryParameters.isSviesos_spalvos_temperatura() && !Validation.isValidSviesosSpalvosTemperatura(sviesos_spalvos_temperatura_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVIESOS_SPALVOS_TEMPERATURA);
        } else if (categoryParameters.isLaidininkas() && !Validation.isValidLaidininkas(laidininkas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_LAIDININKAS);
        }  else if (categoryParameters.isDarbine_temperatura() && !Validation.isValidDarbineTemperatura(darbine_temperatura_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DARBINE_TEMPERATURA);
        } else if (categoryParameters.isMax_darbine_temperatura() && !Validation.isValidMaxDarbineTemperatura(max_darbine_temperatura_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MAX_DARBINE_TEMPERATURA);
        } else if (categoryParameters.isApvalkalas() && !Validation.isValidApvalkalas(apvalkalas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APVALKALAS);
        } else if (categoryParameters.isIsjungimo_geba() && !Validation.isValidIsjungimoGeba(isjungimo_geba_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISJUNGIMO_GEBA);
        } else if (categoryParameters.isIsjungimo_charakteristika() && !Validation.isValidIsjungimoCharakteristika(isjungimo_geba_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISJUNGIMO_CHARAKTERISTIKA);
        }  else if (categoryParameters.isMechaninis_atsparumas() && !Validation.isValidMechaninisAtsparumas(mechaninis_atsparumas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MECHANINIS_ATSPARUMAS);
        } else if (categoryParameters.isSkerspjuvis_Al() && !Validation.isValidSkerspjuvisAl(skerspjuvis_Al_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSPJUVIS);
        } else if (categoryParameters.isSkerspjuvis_Cu() && !Validation.isValidSkerspjuvisCu(skerspjuvis_Cu_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSPJUVIS2);
        }  else if (categoryParameters.isNuotekio_srove() && !Validation.isValidNuotekuSrove(nuotekio_srove_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_NUOTEKIO_SROVE);
        } else if (categoryParameters.isDydis() && !Validation.isValidDydis(dydis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DYDIS);
        } else if (categoryParameters.isPlotas() && !Validation.isValidPlotas(plotas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PLOTAS);
        } else {
            registerProduct(item);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<Categories> categoryNames = CategoriesDAO.selectCategoriesForListView();
        List<Categories> categoriesList = new ArrayList<>();
        for (Categories category : categoryNames){
            if( !category.getName().equals("   Visos kategorijos")){
                categoriesList.add(category);
            }
        }
        categoryComboBox.setCellFactory(lv -> new ListCell<Categories>() {
            public void updateItem(Categories item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        categoryComboBox.getItems().addAll(categoriesList);
        vbox = new VBox();
        letsScroll = new ScrollPane();
        letsScroll.setLayoutX(429);
        letsScroll.setLayoutY(14);
        letsScroll.prefWidth(300);
        letsScroll.prefHeight(300);
        letsScroll.setVisible(false);
        letsScroll.setContent(vbox);
        main_anchor_panel.getChildren().add(letsScroll);

        form_info_label.setWrapText(true);
        form_info_label.setMaxWidth(300);


    }

    public void closeWindow() {
        Stage stage = (Stage) create_product_button.getScene().getWindow();
        stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    void WarnStyle() {
        form_info_label.setText("");
        form_info_label.setStyle("-fx-text-fill: red;");
    }

    public void registerProduct(Categories item) {
        ProductCatalog product = new ProductCatalog(catalog_no_textField.getText(), symbol_textField.getText(),
                Double.parseDouble(price_textField.getText()), nuotraukos_adresas_field.getText(),
                item.getId(), Double.parseDouble(nullCheckerForNumbers(aukstis_field.getText())), Double.parseDouble(nullCheckerForNumbers(plotis_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(gylis_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(skersmuo_field.getText())), Double.parseDouble(nullCheckerForNumbers(ilgis_field.getText())),
                nullCheckerForText(apsaugos_laipsnis_field.getText()), Double.parseDouble(nullCheckerForNumbers(moduliu_skaicius_field.getText())),
                nullCheckerForText(vardine_srove_field.getText()), nullCheckerForText(vardine_itampa_field.getText()),
                nullCheckerForText(mechaninis_atsparumas_IK_field.getText()), nullCheckerForText(spalva_field.getText()), nullCheckerForText(korpuso_medziaga_field.getText()),
                nullCheckerForText(izoliacija_field.getText()), Double.parseDouble(nullCheckerForNumbers(svoris_field.getText())),
                nullCheckerForText(galia_field.getText()), Double.parseDouble(nullCheckerForNumbers(sviesos_srautas_field.getText())),
                nullCheckerForText(sviesos_spalvos_temperatura_field.getText()), nullCheckerForText(laidininkas_field.getText()),
                nullCheckerForText(darbine_temperatura_field.getText()), nullCheckerForText(max_darbine_temperatura_field.getText()),
                nullCheckerForText(apvalkalas_field.getText()), nullCheckerForText(cpr_klase_field.getText()), nullCheckerForText(isjungimo_geba_field.getText()),
                nullCheckerForText(isjungimo_charakteristika_field.getText()), nullCheckerForText(mechaninis_atsparumas_field.getText()), nullCheckerForText(skerspjuvis_Al_field.getText()),
                nullCheckerForText(skerspjuvis_Cu_field.getText()), nullCheckerForText(nuotekio_srove_field.getText()), nullCheckerForText(dydis_field.getText()),
                nullCheckerForText(plotas_field.getText()),
                null);
        ProductCatalogDAO.insert(product);
        closeWindow();
    }

    public String nullCheckerForNumbers(String value){
        if (value.equals("")){
            return "0";
        }
        return value;
    }

    public String nullCheckerForText(String value){
        if (value.equals("")){
            return null;
        }
        return value;
    }


    public void mouseEventForTableView(ActionEvent event) {
        System.out.println("mouseEventForTableView(ActionEvent event)");
        vbox.getChildren().clear();
        System.out.println("vbox.getChildren().clear();");
        Categories tableItem;
        try {
            if (!categoryComboBox.getSelectionModel().isEmpty()) {
                System.out.println("!categoryComboBox.getSelectionModel().isEmpty()");
                tableItem = categoryComboBox.getSelectionModel().getSelectedItem();
                if (tableItem.getCategory_parameter_id() == 0 || tableItem.getCategory_parameter_id() == 8) {
                    Stage productFormStage = (Stage) create_product_button.getScene().getWindow();
                    productFormStage.setWidth(500);
                    productFormStage.setHeight(400);
                    productFormStage.centerOnScreen();
                    productFormStage.maxWidthProperty().bind(productFormStage.widthProperty());
                    productFormStage.minWidthProperty().bind(productFormStage.widthProperty());
                    productFormStage.setMaxHeight(400);
                    productFormStage.setMinHeight(400);
                    letsScroll.setVisible(false);
                } else if (tableItem.getCategory_parameter_id() != 0) {
                    System.out.println("addParametersForProduct(tableItem.getCategory_parameter_id())");
                    addParametersForProduct(tableItem.getCategory_parameter_id());
                }
            }
        } catch (IllegalStateException e) {
            System.out.println("mouseEventForTreeView( " + e + " )");
        } catch (NullPointerException e) {
            System.out.println("mouseEventForTreeView(" + e + " )");
        }
    }


    public double formBoxY;

    private void setformBoxY(double y) {
        this.formBoxY = y;
    }

    private double getformBoxY() {
        return formBoxY = this.formBoxY + 20;
    }


    public void addParametersForProduct(int category_parameter_id) {
        System.out.println("Method addParametersForProduct() initialized");
        form_info_label.setText("");
        categoryParameters = CategoryParametersDAO.getParametersByCategoryParameterId(category_parameter_id);
        System.out.println("getParametersByCategoryParameterId()");

//        CategoryParameters categoryParameters = new CategoryParameters(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true);
        System.out.println("categoryParameters object: " + categoryParameters);

        letsScroll.setVisible(true);
        letsScroll.setPrefSize(450, 300);
        AnchorPane.setTopAnchor(letsScroll, 40.0);
        AnchorPane.setBottomAnchor(letsScroll, 80.0);

        vbox.setLayoutY(14);
        vbox.prefWidth(USE_COMPUTED_SIZE);
        vbox.prefHeight(USE_COMPUTED_SIZE);
        vbox.setVisible(true);

        Stage productFormStage = (Stage) create_product_button.getScene().getWindow();
        productFormStage.setWidth(930);
        productFormStage.setHeight(400);
        productFormStage.centerOnScreen();
        productFormStage.maxWidthProperty().bind(productFormStage.widthProperty());
        productFormStage.minWidthProperty().bind(productFormStage.widthProperty());
        productFormStage.setMaxHeight(900);
        productFormStage.setMinHeight(400);

        double SIZE = 13;


        HBox hBox1 = new HBox();
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();



        vBox1.setPadding(new Insets(5, 5, 10, 9));
        vBox2.setPadding(new Insets(5, 20, 10, 5));
        setformBoxY(40);
        vBox2.setSpacing(6);
        vBox1.setSpacing(12);
        vBox1.setAlignment(Pos.CENTER_RIGHT);

        if (categoryParameters.isAukstis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Aukštis:");

            vbox.setSpacing(10);
            aukstis_field.setLayoutX(60);
            aukstis_field.setPrefWidth(250);
            aukstis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(aukstis_field);
        }
        if (categoryParameters.isPlotis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Plotis:");

            vbox.setSpacing(10);
            plotis_field.setLayoutX(60);
            plotis_field.setPrefWidth(250);
            plotis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(plotis_field);
        }
        if (categoryParameters.isGylis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gylis:");

            vbox.setSpacing(10);
            gylis_field.setLayoutX(60);
            gylis_field.setPrefWidth(250);
            gylis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(gylis_field);
        }
        if (categoryParameters.isSkersmuo()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Skersmuo:");

            vbox.setSpacing(10);
            skersmuo_field.setLayoutX(60);
            skersmuo_field.setPrefWidth(250);
            skersmuo_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(skersmuo_field);
        }
        if (categoryParameters.isIlgis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Ilgis:");

            vbox.setSpacing(10);
            ilgis_field.setLayoutX(60);
            ilgis_field.setPrefWidth(250);
            ilgis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(ilgis_field);
        }
        if (categoryParameters.isApsaugos_laipsnis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apsaugos laipsnis:");

            vbox.setSpacing(10);
            apsaugos_laipsnis_field.setLayoutX(60);
            apsaugos_laipsnis_field.setPrefWidth(250);
            apsaugos_laipsnis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(apsaugos_laipsnis_field);
        }
        if (categoryParameters.isModuliu_skaicius()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Modulių skaičius:");

            vbox.setSpacing(10);
            moduliu_skaicius_field.setLayoutX(60);
            moduliu_skaicius_field.setPrefWidth(250);
            moduliu_skaicius_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(moduliu_skaicius_field);
        }
        if (categoryParameters.isVardine_srove()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Vardinė srovė:");

            vbox.setSpacing(10);
            vardine_srove_field.setLayoutX(60);
            vardine_srove_field.setPrefWidth(250);
            vardine_srove_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(vardine_srove_field);
        }
        if (categoryParameters.isVardine_itampa()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Vardinė įtampa:");

            vbox.setSpacing(10);
            vardine_itampa_field.setLayoutX(60);
            vardine_itampa_field.setPrefWidth(250);
            vardine_itampa_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(vardine_itampa_field);
        }
        if (categoryParameters.isMechaninis_atsparumas_IK()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Mechaninis atsparumas:");

            vbox.setSpacing(10);
            mechaninis_atsparumas_IK_field.setLayoutX(60);
            mechaninis_atsparumas_IK_field.setPrefWidth(250);
            mechaninis_atsparumas_IK_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(mechaninis_atsparumas_IK_field);
        }
        if (categoryParameters.isSpalva()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Spalva:");

            vbox.setSpacing(10);
            spalva_field.setLayoutX(60);
            spalva_field.setPrefWidth(250);
            spalva_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(spalva_field);
        }
        if (categoryParameters.isKorpuso_medziaga()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Korpuso medžiaga:");

            vbox.setSpacing(10);
            korpuso_medziaga_field.setLayoutX(60);
            korpuso_medziaga_field.setPrefWidth(250);
            korpuso_medziaga_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(korpuso_medziaga_field);
        }
        if (categoryParameters.isIzoliacija()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Izoliacija:");

            vbox.setSpacing(10);
            izoliacija_field.setLayoutX(60);
            izoliacija_field.setPrefWidth(250);
            izoliacija_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(izoliacija_field);
        }
        if (categoryParameters.isSvoris()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Svoris:");

            vbox.setSpacing(10);
            svoris_field.setLayoutX(60);
            svoris_field.setPrefWidth(250);
            svoris_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(svoris_field);
        }
        if (categoryParameters.isGalia()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Galia:");

            vbox.setSpacing(10);
            galia_field.setLayoutX(60);
            galia_field.setPrefWidth(250);
            galia_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(galia_field);
        }
        if (categoryParameters.isSviesos_srautas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Šviesos srautas:");

            vbox.setSpacing(10);
            sviesos_srautas_field.setLayoutX(60);
            sviesos_srautas_field.setPrefWidth(250);
            sviesos_srautas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(sviesos_srautas_field);
        }
        if (categoryParameters.isSviesos_spalvos_temperatura()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Spalvos temperatūra:");

            vbox.setSpacing(10);
            sviesos_spalvos_temperatura_field.setLayoutX(60);
            sviesos_spalvos_temperatura_field.setPrefWidth(250);
            sviesos_spalvos_temperatura_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(sviesos_spalvos_temperatura_field);
        }
        if (categoryParameters.isLaidininkas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Laidininkas:");

            vbox.setSpacing(10);
            laidininkas_field.setLayoutX(60);
            laidininkas_field.setPrefWidth(250);
            laidininkas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(laidininkas_field);
        }
        if (categoryParameters.isDarbine_temperatura()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Darbinė temperatūra:");

            vbox.setSpacing(10);
            darbine_temperatura_field.setLayoutX(60);
            darbine_temperatura_field.setPrefWidth(250);
            darbine_temperatura_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(darbine_temperatura_field);
        }
        if (categoryParameters.isMax_darbine_temperatura()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Maks. darbinė temp.:");

            vbox.setSpacing(10);
            max_darbine_temperatura_field.setLayoutX(60);
            max_darbine_temperatura_field.setPrefWidth(250);
            max_darbine_temperatura_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(max_darbine_temperatura_field);
        }
        if (categoryParameters.isApvalkalas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apvalkalas:");

            vbox.setSpacing(10);
            apvalkalas_field.setLayoutX(60);
            apvalkalas_field.setPrefWidth(250);
            apvalkalas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(apvalkalas_field);
        }

        if (categoryParameters.isIsjungimo_geba()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Išjungimo geba:");

            vbox.setSpacing(10);
            isjungimo_geba_field.setLayoutX(60);
            isjungimo_geba_field.setPrefWidth(250);
            isjungimo_geba_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(isjungimo_geba_field);
        }
        if (categoryParameters.isIsjungimo_charakteristika()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Išjungimo charakter.:");

            vbox.setSpacing(10);
            isjungimo_charakteristika_field.setLayoutX(60);
            isjungimo_charakteristika_field.setPrefWidth(250);
            isjungimo_charakteristika_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(isjungimo_charakteristika_field);
        }
        if (categoryParameters.isMechaninis_atsparumas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Mechaninis atsparumas:");

            vbox.setSpacing(10);
            mechaninis_atsparumas_field.setLayoutX(60);
            mechaninis_atsparumas_field.setPrefWidth(250);
            mechaninis_atsparumas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(mechaninis_atsparumas_field);
        }
        if (categoryParameters.isSkerspjuvis_Al()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Skerspjūvis Al:");

            vbox.setSpacing(10);
            skerspjuvis_Al_field.setLayoutX(60);
            skerspjuvis_Al_field.setPrefWidth(250);
            skerspjuvis_Al_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(skerspjuvis_Al_field);
        }
        if (categoryParameters.isSkerspjuvis_Cu()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Skerspjūvis Cu:");

            vbox.setSpacing(10);
            skerspjuvis_Cu_field.setLayoutX(60);
            skerspjuvis_Cu_field.setPrefWidth(250);
            skerspjuvis_Cu_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(skerspjuvis_Cu_field);
        }
        if (categoryParameters.isNuotekio_srove()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Nuotekio srovė:");

            vbox.setSpacing(10);
            nuotekio_srove_field.setLayoutX(60);
            nuotekio_srove_field.setPrefWidth(250);
            nuotekio_srove_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(nuotekio_srove_field);
        }
        if (categoryParameters.isDydis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Dydis:");

            vbox.setSpacing(10);
            dydis_field.setLayoutX(60);
            dydis_field.setPrefWidth(250);
            dydis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(dydis_field);
        }
        if (categoryParameters.isPlotas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Plotis:");

            vbox.setSpacing(10);
            plotas_field.setLayoutX(60);
            plotas_field.setPrefWidth(250);
            plotas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(plotas_field);
        }
        //         FIELDS FOR TESTING
//        for (int i = 0; i < 6; i++) {
//            Label label = new Label();
//            label.setFont(new Font("Segoe UI Light", SIZE));
//            label.setText("Test field:");
//
//            TextField textField = new TextField();
//            textField.setLayoutX(60);
//            textField.setPrefWidth(250);
//            vBox1.getChildren().add(label);
//            vBox2.getChildren().add(textField);
//        }
//         ^ DELETE AFTER TEST




        /**
         * SENAS KODAS

        if (categoryParameters.isAukstis()) { //aukstis
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Aukštis:");

            vbox.setSpacing(10);
            height_field.setLayoutX(60);
            height_field.setPrefWidth(250);
            height_field.setLayoutY(getformBoxY());
            height_field.setId("aukstis");
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(height_field);
        }
        if (categoryParameters.isPlotis()) { //plotis
            Label label = new Label();
            label.setLayoutX(230);
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setLayoutY(getformBoxY());
            label.setText("Plotis:");


            width_field.setLayoutX(60);
            width_field.setPrefWidth(250);
            width_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(width_field);
        }
        if (categoryParameters.isGylis()) { //gylis
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gylis:");

            depth_field.setLayoutX(60);
            depth_field.setPrefWidth(250);
            depth_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(depth_field );
        }
        if (categoryParameters.isIp_klase()) { //ip_klase
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Ip klasė:");

            ip_class_field.setLayoutX(60);
            ip_class_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(ip_class_field);
        }
        if (categoryParameters.isSpalva()) { //spalva
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Spalva:");

            color_field.setLayoutX(60);
            color_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(color_field);
        }
        if (categoryParameters.isKorpusas()) { //korpusas
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Korpusas:");

            body_field.setLayoutX(60);
            body_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(body_field);
        }
        if (categoryParameters.isTipas()) { //tipas
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Tipas:");

            type_field.setLayoutX(60);
            type_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(type_field);
        }
        if (categoryParameters.isVardine_itampa()) { //vardine itampa
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Vardinė įtampa:");

            nominal_voltage_field.setLayoutX(60);
            nominal_voltage_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(nominal_voltage_field);
        }
        if (categoryParameters.isGalia()) { //galia
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Galia:");

            power_field.setLayoutX(60);
            power_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(power_field);
        }
        if (categoryParameters.isAtsparumo_klase()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Atsparumo klasė:");

            resistance_class_field.setLayoutX(60);
            resistance_class_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(resistance_class_field);
        }
        if (categoryParameters.isSviesos_srautas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Šviesos srautas:");

            light_wave_field.setLayoutX(60);
            light_wave_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(light_wave_field);
        }
        if (categoryParameters.isMatmenys()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Matmenys:");
            dimensions_field.setLayoutX(60);
            dimensions_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(dimensions_field);
        }

        if (categoryParameters.isDarbine_temperatura()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Darbinė temperatūra:");

            temperature_field.setLayoutX(60);
            temperature_field.setPrefWidth(250);
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(temperature_field);
        }

         */


        hBox1.getChildren().add(vBox1);
        hBox1.getChildren().add(vBox2);
        vbox.getChildren().add(hBox1);
    }


}
