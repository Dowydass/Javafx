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


    TextField image_url_field = new TextField();
    TextField cu_kiekis_field = new TextField();
    TextField cu_kaina_field = new TextField();
    TextField gamintojas_field = new TextField();
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
    TextField storis_field = new TextField();
    TextField spalva_field = new TextField();
    TextField korpuso_medziaga_field = new TextField();
    TextField izoliacija_field = new TextField();
    TextField svoris_field = new TextField();
    TextField galia_field = new TextField();
    TextField sviesos_srautas_field = new TextField();
    TextField sviesos_spalvos_temperatura_field = new TextField();
    TextField laidininkas_field = new TextField();
    TextField laidininko_izoliacija_field = new TextField();
    TextField darbine_temperatura_field = new TextField();
    TextField max_darbine_temperatura_field = new TextField();
    TextField apsvieta_field = new TextField();
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
    TextField aptikimo_zona_field = new TextField();
    TextField max_darbine_itampa_field = new TextField();
    TextField iskrovimo_srove_8_20_field = new TextField();
    TextField iskrovimo_srove_10_350_field = new TextField();
    TextField itampos_apsaugos_lygis = new TextField();
    TextField kategorija_field = new TextField();
    TextField cri_field = new TextField();
    TextField garantija_field = new TextField();
    TextField sertifikatai_field = new TextField();
    TextField nema_jungtis_field = new TextField();
    TextField virsitampiu_apsauga_field = new TextField();
    TextField ilgaamziskumas_field = new TextField();
    TextField korpuso_atidarymas_field = new TextField();
    TextField optines_ir_elektrines_dalies_pertvarka_field = new TextField();
    TextField valdymas_field = new TextField();
    TextField apatinis_diametras_field = new TextField();
    TextField virsutinis_diametras_field = new TextField();
    TextField gembes_diametras_field = new TextField();

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
        } else if (categoryParameters.isCuAmount() && !Validation.isValidCuAmount(cu_kiekis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_CU_KIEKIS);
        }  else if (categoryParameters.isCuPrice() && !Validation.isValidCuPrice(cu_kaina_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_CU_KAINA);
        } else if (categoryParameters.isGamintojas() && !Validation.isValidGamintojas(gamintojas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_GAMINTOJAS);
        } else if (categoryParameters.isAukstis() && !Validation.isValidAukstis(aukstis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_AUKSTIS);
        } else if (categoryParameters.isPlotis() && !Validation.isValidPlotis(plotis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PLOTIS);
        } else if (categoryParameters.isGylis() && !Validation.isValidGylis(gylis_field.getText())) {
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
        } else if (categoryParameters.isVardine_srove() && !Validation.isValidVardineSrove(vardine_srove_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VARDINE_SROVE);
        } else if (categoryParameters.isVardine_itampa() && !Validation.isValidVardineItampa(vardine_itampa_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VARDINE_ITAMPA);
        } else if (categoryParameters.isMechaninis_atsparumas_IK() && !Validation.isValidMechaninisAtsparumasIK(mechaninis_atsparumas_IK_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MECHANINIS_ATSPARUMAS_IK);
        } else if (categoryParameters.isStoris() && !Validation.isValidStoris(storis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_STORIS);
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
        } else if (categoryParameters.isDarbine_temperatura() && !Validation.isValidDarbineTemperatura(darbine_temperatura_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DARBINE_TEMPERATURA);
        } else if (categoryParameters.isMax_darbine_temperatura() && !Validation.isValidMaxDarbineTemperatura(max_darbine_temperatura_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MAX_DARBINE_TEMPERATURA);
        } else if (categoryParameters.isApvalkalas() && !Validation.isValidApvalkalas(apvalkalas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APVALKALAS);
        } else if (categoryParameters.isCpr_klase() && !Validation.isValidCprKlase(cpr_klase_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CPR_KLASE);
        } else if (categoryParameters.isIsjungimo_geba() && !Validation.isValidIsjungimoGeba(isjungimo_geba_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISJUNGIMO_GEBA);
        } else if (categoryParameters.isIsjungimo_charakteristika() && !Validation.isValidIsjungimoCharakteristika(isjungimo_geba_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISJUNGIMO_CHARAKTERISTIKA);
        } else if (categoryParameters.isMechaninis_atsparumas() && !Validation.isValidMechaninisAtsparumas(mechaninis_atsparumas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MECHANINIS_ATSPARUMAS);
        } else if (categoryParameters.isSkerspjuvis_Al() && !Validation.isValidSkerspjuvisAl(skerspjuvis_Al_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSPJUVIS);
        } else if (categoryParameters.isSkerspjuvis_Cu() && !Validation.isValidSkerspjuvisCu(skerspjuvis_Cu_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSPJUVIS2);
        } else if (categoryParameters.isNuotekio_srove() && !Validation.isValidNuotekuSrove(nuotekio_srove_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_NUOTEKIO_SROVE);
        } else if (categoryParameters.isDydis() && !Validation.isValidDydis(dydis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DYDIS);
        } else if (categoryParameters.isPlotas() && !Validation.isValidPlotas(plotas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PLOTAS);
        } else if (categoryParameters.isAptikimoZona() && !Validation.isValidAptikimoZona(aptikimo_zona_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APTIKIMO_ZONA);
        } else if (categoryParameters.isMaksimaliDarbineItampa() && !Validation.isValidMaksDarbineItampa(max_darbine_itampa_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MAX_DARBINE_ITAMPA);
        } else if (categoryParameters.isIskrovimoSrove820() && !Validation.isValidiskrovimoSrove820(iskrovimo_srove_8_20_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISKROVIMO_SROVE_8_30);
        } else if (categoryParameters.isIskrovimoSrove10350() && !Validation.isValidiskrovimoSrove10350(iskrovimo_srove_10_350_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISKROVIMO_SROVE_10_350);
        } else if (categoryParameters.isItamposApsaugosLygis() && !Validation.isValidItamposApsaugosLygis(itampos_apsaugos_lygis.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ITAMPOS_APSAUGOS_LYGIS);
        } else if (categoryParameters.isKategorija() && !Validation.isValidKategorija(kategorija_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KATEGORIJA);
        } else if (categoryParameters.isCRI() && !Validation.isValidCri(cri_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CRI);
        } else if (categoryParameters.isGarantija() && !Validation.isValidGarantija(garantija_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GARANTIJA);
        } else if (categoryParameters.isSertifikatai() && !Validation.isValidSertifikatai(sertifikatai_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SERTIFIKATAI);
        } else if (categoryParameters.isNemaJungtis() && !Validation.isValidNema(nema_jungtis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_NEMA_JUNGTIS);
        } else if (categoryParameters.isVirsitampiuApsauga() && !Validation.isValidVirsitampiuApsauga(virsitampiu_apsauga_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VIRSITAMPIU_APSAUGA);
        } else if (categoryParameters.isIlgaamziskumas() && !Validation.isValidIlgaamziskumas(ilgaamziskumas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ILGAAMZISKUMAS);
        } else if (categoryParameters.isKorpusoAtidarymas() && !Validation.isValidKorpusoAtidarymas(korpuso_atidarymas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KORPUSO_ATIDARYMAS);
        } else if (categoryParameters.isOptinesIrElektrinesDaliesPertvara() && !Validation.isValidOptinesElektrinesDaliesPertvara(optines_ir_elektrines_dalies_pertvarka_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_OPTINES_IR_ELEKTRINES_DALIES_PERTVARKA);
        } else if (categoryParameters.isValdymas() && !Validation.isValidValdymas(valdymas_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VALDYMAS);
        } else if (categoryParameters.isVirsutinisDiametras() && !Validation.isValidVirsutinisDiametras(virsutinis_diametras_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VIRSUTINIS_DIAMETRAS);
        } else if (categoryParameters.isApatinisDiametras() && !Validation.isValidApatinisDiametras(apatinis_diametras_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APATINIS_DIAMETRAS);
        } else if (categoryParameters.isNemaJungtis() && !Validation.isValidNema(nema_jungtis_field.getText())) {
            WarnStyle();
            form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PRODUCT_NEMA_JUNGTIS);
        } else {
            registerProduct(item);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<Categories> categoryNames = CategoriesDAO.selectCategoriesForListView();
        List<Categories> categoriesList = new ArrayList<>();
        for (Categories category : categoryNames) {
            if (!category.getName().equals("   Visos kategorijos")) {
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
        ProductCatalog product = new ProductCatalog(catalog_no_textField.getText(),
                symbol_textField.getText(),
                Double.parseDouble(price_textField.getText()),
                nuotraukos_adresas_field.getText(),
                item.getId(),
                Double.parseDouble(nullCheckerForNumbers(cu_kiekis_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(cu_kaina_field.getText())),
                nullCheckerForText(gamintojas_field.getText()),
                Double.parseDouble(nullCheckerForNumbers(aukstis_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(plotis_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(gylis_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(skersmuo_field.getText())),
                Double.parseDouble(nullCheckerForNumbers(ilgis_field.getText())),
                nullCheckerForText(apsaugos_laipsnis_field.getText()),
                Double.parseDouble(nullCheckerForNumbers(moduliu_skaicius_field.getText())),
                nullCheckerForText(vardine_srove_field.getText()),
                nullCheckerForText(vardine_itampa_field.getText()),
                nullCheckerForText(mechaninis_atsparumas_IK_field.getText()),
                Double.parseDouble(nullCheckerForNumbers(storis_field.getText())),
                nullCheckerForText(spalva_field.getText()),
                nullCheckerForText(korpuso_medziaga_field.getText()),
                nullCheckerForText(izoliacija_field.getText()),
                Double.parseDouble(nullCheckerForNumbers(svoris_field.getText())),
                nullCheckerForText(galia_field.getText()),
                Double.parseDouble(nullCheckerForNumbers(sviesos_srautas_field.getText())),
                nullCheckerForText(sviesos_spalvos_temperatura_field.getText()),
                nullCheckerForText(laidininkas_field.getText()),
                nullCheckerForText(laidininko_izoliacija_field.getText()),
                nullCheckerForText(darbine_temperatura_field.getText()),
                nullCheckerForText(max_darbine_temperatura_field.getText()),
                nullCheckerForText(apsvieta_field.getText()),
                nullCheckerForText(apvalkalas_field.getText()),
                nullCheckerForText(cpr_klase_field.getText()),
                nullCheckerForText(isjungimo_geba_field.getText()),
                nullCheckerForText(isjungimo_charakteristika_field.getText()),
                nullCheckerForText(mechaninis_atsparumas_field.getText()),
                nullCheckerForText(skerspjuvis_Al_field.getText()),
                nullCheckerForText(skerspjuvis_Cu_field.getText()),
                nullCheckerForText(nuotekio_srove_field.getText()),
                nullCheckerForText(dydis_field.getText()),
                nullCheckerForText(plotas_field.getText()),
                nullCheckerForText(aptikimo_zona_field.getText()),
                nullCheckerForText(max_darbine_itampa_field.getText()),
                nullCheckerForText(iskrovimo_srove_8_20_field.getText()),
                nullCheckerForText(iskrovimo_srove_10_350_field.getText()),
                nullCheckerForText(itampos_apsaugos_lygis.getText()),
                nullCheckerForText(kategorija_field.getText()),
                Integer.parseInt(nullCheckerForNumbers(cri_field.getText())),
                nullCheckerForText(garantija_field.getText()),
                nullCheckerForText(sertifikatai_field.getText()),
                nullCheckerForText(nema_jungtis_field.getText()),
                nullCheckerForText(virsitampiu_apsauga_field.getText()),
                nullCheckerForText(ilgaamziskumas_field.getText()),
                nullCheckerForText(korpuso_atidarymas_field.getText()),
                nullCheckerForText(optines_ir_elektrines_dalies_pertvarka_field.getText()),
                nullCheckerForText(valdymas_field.getText()),
                Integer.parseInt(nullCheckerForNumbers(apatinis_diametras_field.getText())),
                Integer.parseInt(nullCheckerForNumbers(virsutinis_diametras_field.getText())),
                Integer.parseInt(nullCheckerForNumbers(gembes_diametras_field.getText())),
                null);
        ProductCatalogDAO.insert(product);
        closeWindow();
    }

    public String nullCheckerForNumbers(String value) {
        if (value.equals("")) {
            return "0";
        }
        return value;
    }

    public String nullCheckerForText(String value) {
        if (value.equals("")) {
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


        if (categoryParameters.isGamintojas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gamintojas:");

            vbox.setSpacing(10);
            gamintojas_field.setLayoutX(60);
            gamintojas_field.setPrefWidth(250);
            gamintojas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(gamintojas_field);
        }
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
        if (categoryParameters.isStoris()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Storis:");

            vbox.setSpacing(10);
            storis_field.setLayoutX(60);
            storis_field.setPrefWidth(250);
            storis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(storis_field);
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

        if (categoryParameters.isLaidininkoIzoliacija()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Laidininko izoliacija:");

            vbox.setSpacing(10);
            laidininko_izoliacija_field.setLayoutX(60);
            laidininko_izoliacija_field.setPrefWidth(250);
            laidininko_izoliacija_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(laidininko_izoliacija_field);
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

        if (categoryParameters.isApsvieta()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apšvieta:");

            vbox.setSpacing(10);
            apsvieta_field.setLayoutX(60);
            apsvieta_field.setPrefWidth(250);
            apsvieta_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(apsvieta_field);
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
        if (categoryParameters.isCpr_klase()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("CPR klasė:");

            vbox.setSpacing(10);
            cpr_klase_field.setLayoutX(60);
            cpr_klase_field.setPrefWidth(250);
            cpr_klase_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(cpr_klase_field);
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
        if (categoryParameters.isAptikimoZona()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Aptikimo zona:");

            vbox.setSpacing(10);
            aptikimo_zona_field.setLayoutX(60);
            aptikimo_zona_field.setPrefWidth(250);
            aptikimo_zona_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(aptikimo_zona_field);
        }
        if (categoryParameters.isMaksimaliDarbineItampa()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Maks. darbinė įtampa:");

            vbox.setSpacing(10);
            max_darbine_itampa_field.setLayoutX(60);
            max_darbine_itampa_field.setPrefWidth(250);
            max_darbine_itampa_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(max_darbine_itampa_field);
        }
        if (categoryParameters.isIskrovimoSrove820()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Iškrovimo srovė 8/20:");

            vbox.setSpacing(10);
            iskrovimo_srove_8_20_field.setLayoutX(60);
            iskrovimo_srove_8_20_field.setPrefWidth(250);
            iskrovimo_srove_8_20_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(iskrovimo_srove_8_20_field);
        }
        if (categoryParameters.isIskrovimoSrove10350()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Iškrovimo srovė 10/350:");

            vbox.setSpacing(10);
            iskrovimo_srove_10_350_field.setLayoutX(60);
            iskrovimo_srove_10_350_field.setPrefWidth(250);
            iskrovimo_srove_10_350_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(iskrovimo_srove_10_350_field);
        }
        if (categoryParameters.isItamposApsaugosLygis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Įtampos apsaugos lygis:");

            vbox.setSpacing(10);
            itampos_apsaugos_lygis.setLayoutX(60);
            itampos_apsaugos_lygis.setPrefWidth(250);
            itampos_apsaugos_lygis.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(itampos_apsaugos_lygis);
        }
        if (categoryParameters.isKategorija()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Kategorija:");

            vbox.setSpacing(10);
            kategorija_field.setLayoutX(60);
            kategorija_field.setPrefWidth(250);
            kategorija_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(kategorija_field);
        }
        if (categoryParameters.isCRI()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("CRI:");

            vbox.setSpacing(10);
            cri_field.setLayoutX(60);
            cri_field.setPrefWidth(250);
            cri_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(cri_field);
        }
        if (categoryParameters.isGarantija()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Garantija:");

            vbox.setSpacing(10);
            garantija_field.setLayoutX(60);
            garantija_field.setPrefWidth(250);
            garantija_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(garantija_field);
        }
        if (categoryParameters.isSertifikatai()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Sertifikatai:");

            vbox.setSpacing(10);
            sertifikatai_field.setLayoutX(60);
            sertifikatai_field.setPrefWidth(250);
            sertifikatai_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(sertifikatai_field);
        }
        if (categoryParameters.isNemaJungtis()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Nema jungtis:");

            vbox.setSpacing(10);
            nema_jungtis_field.setLayoutX(60);
            nema_jungtis_field.setPrefWidth(250);
            nema_jungtis_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(nema_jungtis_field);
        }
        if (categoryParameters.isVirsitampiuApsauga()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Virš įtamp. apsauga:");

            vbox.setSpacing(10);
            virsitampiu_apsauga_field.setLayoutX(60);
            virsitampiu_apsauga_field.setPrefWidth(250);
            virsitampiu_apsauga_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(virsitampiu_apsauga_field);
        }
        if (categoryParameters.isIlgaamziskumas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Ilgaamžiškumas:");

            vbox.setSpacing(10);
            ilgaamziskumas_field.setLayoutX(60);
            ilgaamziskumas_field.setPrefWidth(250);
            ilgaamziskumas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(ilgaamziskumas_field);
        }
        if (categoryParameters.isKorpusoAtidarymas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Korpuso atidarymas:");

            vbox.setSpacing(10);
            korpuso_atidarymas_field.setLayoutX(60);
            korpuso_atidarymas_field.setPrefWidth(250);
            korpuso_atidarymas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(korpuso_atidarymas_field);
        }
        if (categoryParameters.isOptinesIrElektrinesDaliesPertvara()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Opt. ir elektr. dalies pertv.:");

            vbox.setSpacing(10);
            optines_ir_elektrines_dalies_pertvarka_field.setLayoutX(60);
            optines_ir_elektrines_dalies_pertvarka_field.setPrefWidth(250);
            optines_ir_elektrines_dalies_pertvarka_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(optines_ir_elektrines_dalies_pertvarka_field);
        }
        if (categoryParameters.isValdymas()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Valdymas:");

            vbox.setSpacing(10);
            valdymas_field.setLayoutX(60);
            valdymas_field.setPrefWidth(250);
            valdymas_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(valdymas_field);
        }
        if (categoryParameters.isApatinisDiametras()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apatinis diam.:");

            vbox.setSpacing(10);
            apatinis_diametras_field.setLayoutX(60);
            apatinis_diametras_field.setPrefWidth(250);
            apatinis_diametras_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(apatinis_diametras_field);
        }
        if (categoryParameters.isVirsutinisDiametras()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Viršutinis diam.:");

            vbox.setSpacing(10);
            virsutinis_diametras_field.setLayoutX(60);
            virsutinis_diametras_field.setPrefWidth(250);
            virsutinis_diametras_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(virsutinis_diametras_field);
        }
        if (categoryParameters.isGembesDiametras()) {
            Label label = new Label();
            label.setLayoutX(20);
            label.setLayoutY(getformBoxY());
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gembės diam.:");

            vbox.setSpacing(10);
            gembes_diametras_field.setLayoutX(60);
            gembes_diametras_field.setPrefWidth(250);
            gembes_diametras_field.setLayoutY(getformBoxY());
            vBox1.getChildren().add(label);
            vBox2.getChildren().add(gembes_diametras_field);
        }

        Label imageLabel = new Label();
        imageLabel.setLayoutX(20);
        imageLabel.setLayoutY(getformBoxY());
        imageLabel.setFont(new Font("Segoe UI Light", SIZE));
        imageLabel.setText("Nuotraukos adresas:");

        vbox.setSpacing(10);
        image_url_field.setLayoutX(60);
        image_url_field.setPrefWidth(250);
        image_url_field.setLayoutY(getformBoxY());
        vBox1.getChildren().add(imageLabel);
        vBox2.getChildren().add(image_url_field);



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


        hBox1.getChildren().add(vBox1);
        hBox1.getChildren().add(vBox2);
        vbox.getChildren().add(hBox1);
    }



}
