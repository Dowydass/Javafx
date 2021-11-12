package sample.controller;

import com.sun.javafx.css.Size;
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
        } else if (categoryParameters.isNuotekio_srove() && !Validation.isValidNuotekioSrove(nuotekio_srove_field.getText())) {
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
    public double textBoxY;


    private void setFormBoxY(double y) {
        this.formBoxY = y;
    }

    private double getFormBoxY() {
        return formBoxY = this.formBoxY + 20;
    }

    private double getTextBoxY() {
        return textBoxY = this.textBoxY + 20;
    }


    public void addParametersForProduct(int category_parameter_id) {
        form_info_label.setText("");
        categoryParameters = CategoryParametersDAO.getParametersByCategoryParameterId(category_parameter_id);

//        CategoryParameters categoryParameters = new CategoryParameters(true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true,true, true, true, true, true, true, true, true, true, true);

        letsScroll.setVisible(true);
        letsScroll.setPrefSize(470, 300);
        letsScroll.setMaxSize(470, 300);
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
        HBox hBox2 = new HBox();
//        VBox hBox1 = new VBox();


        hBox1.setPadding(new Insets(5, 5, 10, 9));
        hBox2.setPadding(new Insets(5, 20, 10, 5));
//        setformBoxY(40);
        hBox1.setSpacing(6);
        hBox2.setSpacing(6);
        hBox1.setAlignment(Pos.CENTER_RIGHT);
        hBox2.setAlignment(Pos.CENTER_RIGHT);


        if (categoryParameters.isGamintojas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gamintojas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            gamintojas_field.setAlignment(Pos.CENTER_RIGHT);
            gamintojas_field.setPrefWidth(255);

            HBox hboxField = new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(gamintojas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isAukstis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Aukštis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            aukstis_field.setAlignment(Pos.CENTER_RIGHT);
            aukstis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(aukstis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isPlotis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Plotis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            plotis_field.setAlignment(Pos.CENTER_RIGHT);
            plotis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(plotis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isGylis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gylis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            gylis_field.setAlignment(Pos.CENTER_RIGHT);
            gylis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(gylis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSkersmuo()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Skersmuo:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            skersmuo_field.setAlignment(Pos.CENTER_RIGHT);
            skersmuo_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(skersmuo_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIlgis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Ilgis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            ilgis_field.setAlignment(Pos.CENTER_RIGHT);
            ilgis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(aukstis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isApsaugos_laipsnis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apsaugos laipsnis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            apsaugos_laipsnis_field.setAlignment(Pos.CENTER_RIGHT);
            apsaugos_laipsnis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(apsaugos_laipsnis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isModuliu_skaicius()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Modulių skaičius:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            moduliu_skaicius_field.setAlignment(Pos.CENTER_RIGHT);
            moduliu_skaicius_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(moduliu_skaicius_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isVardine_srove()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Vardinė srovė:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            vardine_srove_field.setAlignment(Pos.CENTER_RIGHT);
            vardine_srove_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(vardine_srove_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isVardine_itampa()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Vardinė įtampa:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            vardine_itampa_field.setAlignment(Pos.CENTER_RIGHT);
            vardine_itampa_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(vardine_itampa_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isMechaninis_atsparumas_IK()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Mech. atsparumas IK:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            mechaninis_atsparumas_IK_field.setAlignment(Pos.CENTER_RIGHT);
            mechaninis_atsparumas_IK_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(mechaninis_atsparumas_IK_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isStoris()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Storis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            storis_field.setAlignment(Pos.CENTER_RIGHT);
            storis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(storis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSpalva()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Spalva:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            spalva_field.setAlignment(Pos.CENTER_RIGHT);
            spalva_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(spalva_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isKorpuso_medziaga()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Korpuso medžiaga:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            korpuso_medziaga_field.setAlignment(Pos.CENTER_RIGHT);
            korpuso_medziaga_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(korpuso_medziaga_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIzoliacija()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Izoliacija:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            izoliacija_field.setAlignment(Pos.CENTER_RIGHT);
            izoliacija_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(izoliacija_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSvoris()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Svoris:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            svoris_field.setAlignment(Pos.CENTER_RIGHT);
            svoris_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(svoris_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isGalia()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Galia:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            galia_field.setAlignment(Pos.CENTER_RIGHT);
            galia_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(galia_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSviesos_srautas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Šviesos srautas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            sviesos_srautas_field.setAlignment(Pos.CENTER_RIGHT);
            sviesos_srautas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(sviesos_srautas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSviesos_spalvos_temperatura()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Spalvos temperatūra:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            sviesos_spalvos_temperatura_field.setAlignment(Pos.CENTER_RIGHT);
            sviesos_spalvos_temperatura_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(sviesos_spalvos_temperatura_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isLaidininkas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Laidininkas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            laidininkas_field.setAlignment(Pos.CENTER_RIGHT);
            laidininkas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(laidininkas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }

        if (categoryParameters.isLaidininkoIzoliacija()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Laidininko izoliacija:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            laidininko_izoliacija_field.setAlignment(Pos.CENTER_RIGHT);
            laidininko_izoliacija_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(laidininko_izoliacija_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }

        if (categoryParameters.isDarbine_temperatura()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Darbinė temperatūra:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            darbine_temperatura_field.setAlignment(Pos.CENTER_RIGHT);
            darbine_temperatura_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(darbine_temperatura_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isMax_darbine_temperatura()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Maks. darbinė temp.:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            darbine_temperatura_field.setAlignment(Pos.CENTER_RIGHT);
            darbine_temperatura_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(darbine_temperatura_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }

        if (categoryParameters.isApsvieta()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apšvieta:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            apsvieta_field.setAlignment(Pos.CENTER_RIGHT);
            apsvieta_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(apsvieta_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }

        if (categoryParameters.isApvalkalas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apvalkalas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            apvalkalas_field.setAlignment(Pos.CENTER_RIGHT);
            apvalkalas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(apvalkalas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isCpr_klase()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("CPR klasė:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            cpr_klase_field.setAlignment(Pos.CENTER_RIGHT);
            cpr_klase_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(cpr_klase_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIsjungimo_geba()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Išjungimo geba:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            isjungimo_geba_field.setAlignment(Pos.CENTER_RIGHT);
            isjungimo_geba_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(isjungimo_geba_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIsjungimo_charakteristika()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Išjungimo charakter.:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            isjungimo_charakteristika_field.setAlignment(Pos.CENTER_RIGHT);
            isjungimo_charakteristika_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(isjungimo_charakteristika_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isMechaninis_atsparumas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Mechaninis atsparumas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            mechaninis_atsparumas_field.setAlignment(Pos.CENTER_RIGHT);
            mechaninis_atsparumas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(mechaninis_atsparumas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSkerspjuvis_Al()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Skerspjūvis Al:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            skerspjuvis_Al_field.setAlignment(Pos.CENTER_RIGHT);
            skerspjuvis_Al_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(skerspjuvis_Al_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSkerspjuvis_Cu()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Skerspjūvis Cu:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            skerspjuvis_Cu_field.setAlignment(Pos.CENTER_RIGHT);
            skerspjuvis_Cu_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(skerspjuvis_Cu_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isNuotekio_srove()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Nuotekio srovė:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            nuotekio_srove_field.setAlignment(Pos.CENTER_RIGHT);
            nuotekio_srove_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(nuotekio_srove_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isDydis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Dydis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            dydis_field.setAlignment(Pos.CENTER_RIGHT);
            dydis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(dydis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isPlotas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Plotis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            plotas_field.setAlignment(Pos.CENTER_RIGHT);
            plotas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(plotas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isAptikimoZona()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Aptikimo zona:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            aptikimo_zona_field.setAlignment(Pos.CENTER_RIGHT);
            aptikimo_zona_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(aptikimo_zona_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isMaksimaliDarbineItampa()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Maks. darbinė įtampa:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            max_darbine_itampa_field.setAlignment(Pos.CENTER_RIGHT);
            max_darbine_itampa_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(max_darbine_itampa_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIskrovimoSrove820()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Iškrovimo srovė 8/20:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            iskrovimo_srove_8_20_field.setAlignment(Pos.CENTER_RIGHT);
            iskrovimo_srove_8_20_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(iskrovimo_srove_8_20_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIskrovimoSrove10350()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Iškrovimo srovė 10/350:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            iskrovimo_srove_10_350_field.setAlignment(Pos.CENTER_RIGHT);
            iskrovimo_srove_10_350_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(iskrovimo_srove_10_350_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isItamposApsaugosLygis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Įtampos apsaugos lygis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            itampos_apsaugos_lygis.setAlignment(Pos.CENTER_RIGHT);
            itampos_apsaugos_lygis.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(itampos_apsaugos_lygis);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isKategorija()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Kategorija:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            kategorija_field.setAlignment(Pos.CENTER_RIGHT);
            kategorija_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(kategorija_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isCRI()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("CRI:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            cri_field.setAlignment(Pos.CENTER_RIGHT);
            cri_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(cri_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isGarantija()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Garantija:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            garantija_field.setAlignment(Pos.CENTER_RIGHT);
            garantija_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(garantija_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isSertifikatai()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Sertifikatai:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            sertifikatai_field.setAlignment(Pos.CENTER_RIGHT);
            sertifikatai_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(sertifikatai_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isNemaJungtis()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Nema jungtis:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            nema_jungtis_field.setAlignment(Pos.CENTER_RIGHT);
            nema_jungtis_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(nema_jungtis_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isVirsitampiuApsauga()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Virš įtamp. apsauga:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            virsitampiu_apsauga_field.setAlignment(Pos.CENTER_RIGHT);
            virsitampiu_apsauga_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(virsitampiu_apsauga_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isIlgaamziskumas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Ilgaamžiškumas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            ilgaamziskumas_field.setAlignment(Pos.CENTER_RIGHT);
            ilgaamziskumas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(ilgaamziskumas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isKorpusoAtidarymas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Korpuso atidarymas:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            korpuso_atidarymas_field.setAlignment(Pos.CENTER_RIGHT);
            korpuso_atidarymas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(korpuso_atidarymas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isOptinesIrElektrinesDaliesPertvara()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Opt. ir elektr. dalies pertv.:");
            label.setPrefSize(122,25);
            label.setAlignment(Pos.CENTER_RIGHT);

            optines_ir_elektrines_dalies_pertvarka_field.setAlignment(Pos.CENTER_RIGHT);
            optines_ir_elektrines_dalies_pertvarka_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(optines_ir_elektrines_dalies_pertvarka_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isValdymas()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Valdymas:");

            valdymas_field.setAlignment(Pos.CENTER_RIGHT);
            valdymas_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(valdymas_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isApatinisDiametras()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Apatinis diam.:");

            apatinis_diametras_field.setAlignment(Pos.CENTER_RIGHT);
            apatinis_diametras_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(apatinis_diametras_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isVirsutinisDiametras()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Viršutinis diam.:");

            virsutinis_diametras_field.setAlignment(Pos.CENTER_RIGHT);
            virsutinis_diametras_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(virsutinis_diametras_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(10, 5, 10, 5));
            vbox.getChildren().add(hboxField);
        }
        if (categoryParameters.isGembesDiametras()) {
            Label label = new Label();
            label.setFont(new Font("Segoe UI Light", SIZE));
            label.setText("Gembės diam.:");

            gembes_diametras_field.setAlignment(Pos.CENTER_RIGHT);
            gembes_diametras_field.setPrefWidth(255);

            HBox hboxField =  new HBox();
            hboxField.setMinWidth(440);
            hboxField.getChildren().add(label);
            hboxField.getChildren().add(gembes_diametras_field);
            hboxField.setSpacing(12);
            hboxField.setPadding(new Insets(5, 5, 5, 5));
            vbox.getChildren().add(hboxField);
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


//        hBox1.getChildren().add(hBox1);
//        hBox1.getChildren().add(hBox1);
        vbox.getChildren().add(hBox1);
    }



}
