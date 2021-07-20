package sample.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static final String FIRST_NAME_PATTERN = "^[a-zA-ZĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";

    public static boolean isValidFirstName(String username) {
        Pattern pattern = Pattern.compile(FIRST_NAME_PATTERN); //Pagal jūsu Stringę aprašytas taisyklęs sukurią šabloną
        Matcher matcher = pattern.matcher(username); //Pagal ankš// čiau sukurtą šablona yra palyginima duomenys
        return matcher.find(); //Grazina true jei atitinka validacija, false jei neatitinka
    }

    public static final String LAST_NAME_PATTERN = "^[a-zA-ZĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";

    public static boolean isValidLastName(String username) {
        Pattern pattern = Pattern.compile(LAST_NAME_PATTERN); //Pagal jūsu Stringę aprašytas taisyklęs sukurią šabloną
        Matcher matcher = pattern.matcher(username); //Pagal ankš// čiau sukurtą šablona yra palyginima duomenys
        return matcher.find(); //Grazina true jei atitinka validacija, false jei neatitinka
    }

    public static final String COMPANY_NAME_PATTERN = "^((?![\\^!@#$*~ <>?]).)((?![\\^!@#$*~<>?]).){0,255}((?![\\^!@#$*~ <>?]).)$";

    public static boolean isValidCompanyName(String username) {
        Pattern pattern = Pattern.compile(COMPANY_NAME_PATTERN); //Pagal jūsu Stringę aprašytas taisyklęs sukurią šabloną
        Matcher matcher = pattern.matcher(username); //Pagal ankš// čiau sukurtą šablona yra palyginima duomenys
        return matcher.find(); //Grazina true jei atitinka validacija, false jei neatitinka
    }

    public static final String REGISTER_PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zĄąČčĘęĖėĮįŠšŲųŪūŽž])(?=.*[A-ZĄąČčĘęĖėĮįŠšŲųŪūŽž])(?=.*[a-zA-Z]).\\S{7,255}$";

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(REGISTER_PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public static final String PASSWORD_CONTAIN_ONE_UPPERCASE = "(.*[A-Z].*)";

    public static boolean isOneUpperCaseExist(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_CONTAIN_ONE_UPPERCASE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public static final String REGISTER_EMAIL_PATTERN = "^[A-Za-z0-9._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]+@[A-Za-z0-9.-]+.[A-Za-z]{2,255}$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(REGISTER_EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static final String PASSWORD_CONTAIN_ONE_SYMBOL = "(?=.*\\d)";

    public static boolean isOneDigitAre(String email) {
        Pattern pattern = Pattern.compile(PASSWORD_CONTAIN_ONE_SYMBOL);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
    public static final String PRODUCT_FORM_CATALOG_NO_PATTERN = "^[A-Za-z0-9]{1,255}$";
    public static boolean isValidCatalogNo(String catalogNo){
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CATALOG_NO_PATTERN);
        Matcher matcher = pattern.matcher(catalogNo);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SYMBOL_PATTERN = "^((?![\\^!@#$*~ <>?]).)((?![\\^!@#$*~<>?]).){1,1024}((?![\\^!@#$*~ <>?]).)$";
    public static boolean isValidSymbol(String symbol){
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SYMBOL_PATTERN);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_PRICE_PATTERN = "^[0-9]{1,253}(\\.?[0-9]{1,2})$";
    public static boolean isValidPrice(String price){
        Pattern pattern = Pattern.compile(PRODUCT_FORM_PRICE_PATTERN);
        Matcher matcher = pattern.matcher(price);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_STOCK_PATTERN = "^[0-9]{1,11}$";
    public static boolean isValidStock(String stock){
        Pattern pattern = Pattern.compile(PRODUCT_FORM_STOCK_PATTERN);
        Matcher matcher = pattern.matcher(stock);
        return matcher.find();
    }

    public static final String CATEGORY_FORM_NAME_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,512}$";
    public static boolean isValidCategoryName(String name){
        Pattern pattern = Pattern.compile(CATEGORY_FORM_NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    /* Naujo produkto kūrimo lango Validacija : Start */

    public static final String PRODUCT_FORM_AUKSTIS_PATTERN = "^[0-9]{1,11}$";
    public static boolean isValidAukstis(String aukstis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_AUKSTIS_PATTERN);
        Matcher matcher = pattern.matcher(aukstis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_PLOTIS_PATTERN = "^[0-9]{1,11}$";
    public static boolean isValidPlotis(String plotis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_PLOTIS_PATTERN);
        Matcher matcher = pattern.matcher(plotis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GYLIS_PATTERN = "^[0-9]{1,11}$";
    public static boolean isValidGylis(String gylis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GYLIS_PATTERN);
        Matcher matcher = pattern.matcher(gylis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SKERSMUO_PATTERN = "^[0-9]{1,10}$";
    public static boolean isValidSkersmuo(String skersmuo) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SKERSMUO_PATTERN);
        Matcher matcher = pattern.matcher(skersmuo);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ILGIS_PATTERN = "^[0-9]{1,10}$";
    public static boolean isValidIlgis(String ilgis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ILGIS_PATTERN);
        Matcher matcher = pattern.matcher(ilgis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APSAUGOS_LAIPSNIS_PATTERN = "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidApsaugosLaipsnis(String apsaugosLaipsnis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APSAUGOS_LAIPSNIS_PATTERN);
        Matcher matcher = pattern.matcher(apsaugosLaipsnis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MODULIU_SKAICIUS_PATTERN = "^[0-9]{1,11}$";
    public static boolean isValidModuliuSkaicius(String moduliuSkaicius) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MODULIU_SKAICIUS_PATTERN);
        Matcher matcher = pattern.matcher(moduliuSkaicius);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VARDINE_SROVE_PATTERN = "^[0-9]{1,11}(?=.*A).*$";
    public static boolean isValidVardineSrove(String vardineSrove) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VARDINE_SROVE_PATTERN);
        Matcher matcher = pattern.matcher(vardineSrove);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VARDINE_ITAMPA_PATTERN = "^[0-9]{1,11}(?=.*V AC).*$";
    public static boolean isValidVardineItampa(String vardineItampa) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VARDINE_ITAMPA_PATTERN);
        Matcher matcher = pattern.matcher(vardineItampa);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MECHANINIS_ATSPARUMAS_IK_PATTERN = "^[0-9A-Za-z]{1,10}$";
    public static boolean isValidMechaninisAtsparumasIK(String mechaninisAtsparumasIK) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MECHANINIS_ATSPARUMAS_IK_PATTERN);
        Matcher matcher = pattern.matcher(mechaninisAtsparumasIK);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SPALVA_PATTERN = "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";
    public static boolean isValidSpalva(String spalva) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SPALVA_PATTERN);
        Matcher matcher = pattern.matcher(spalva);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_KORPUSO_MEDZIAGA_PATTERN = "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidKorpusoMedziaga(String korpusoMedziaga) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_KORPUSO_MEDZIAGA_PATTERN);
        Matcher matcher = pattern.matcher(korpusoMedziaga);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_IZOLIACIJA_PATTERN = "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidIzoliacija(String izoliacija) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_IZOLIACIJA_PATTERN);
        Matcher matcher = pattern.matcher(izoliacija);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SVORIS_PATTERN = "^[0-9]{1,10}$";
    public static boolean isValidSvoris(String svoris) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SVORIS_PATTERN);
        Matcher matcher = pattern.matcher(svoris);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GALIA_PATTERN = "^[0-9 /]{1,10}(?=.*W).*$";
    public static boolean isValidGalia(String galia) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GALIA_PATTERN);
        Matcher matcher = pattern.matcher(galia);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SVIESOS_SRAUTAS_PATTERN = "^[0-9]{1,10}$";
    public static boolean isValidSviesosSrautas(String sviesosSrautas){
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SVIESOS_SRAUTAS_PATTERN);
        Matcher matcher = pattern.matcher(sviesosSrautas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SVIESOS_SPALVOS_TEMPERATURA_PATTERN= "[0-9]{1,10}(?=.*K).*$";
    public static boolean isValidSviesosSpalvosTemperatura(String sviesosSpalvosTemperatura) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SVIESOS_SPALVOS_TEMPERATURA_PATTERN);
        Matcher matcher = pattern.matcher(sviesosSpalvosTemperatura);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_LAIDININKAS_PATTERN = "^[0-9]{1,10}$";
    public static boolean isValidLaidininkas(String laidininkas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_LAIDININKAS_PATTERN);
        Matcher matcher = pattern.matcher(laidininkas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_IZOLIACIJA2_PATTERN = "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidIzoliacija2(String izoliacija2) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_IZOLIACIJA2_PATTERN);
        Matcher matcher = pattern.matcher(izoliacija2);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_DARBINE_TEMPERATURA_PATTERN = "^[0-9a-zA-Z -°]{1,11}(?=.*C).*$";
    public static boolean isValidDarbineTemperatura(String darbineTemperatura) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_DARBINE_TEMPERATURA_PATTERN);
        Matcher matcher = pattern.matcher(darbineTemperatura);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MAX_DARBINE_TEMPERATURA_PATTERN= "^[0-9]{1,10}$";
    public static boolean isValidMaxDarbineTemperatura(String maxDarbineTemperatura) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MAX_DARBINE_TEMPERATURA_PATTERN);
        Matcher matcher = pattern.matcher(maxDarbineTemperatura);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APVALKALAS_PATTERN = "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidApvalkalas(String apvalkalas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APVALKALAS_PATTERN);
        Matcher matcher = pattern.matcher(apvalkalas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_CPR_KLASE_PATTERN= "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidCprKlase(String cprKlase) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CPR_KLASE_PATTERN);
        Matcher matcher = pattern.matcher(cprKlase);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ISJUNGIMO_GEBA_PATTERN= "^[0-9]{1,10}(?=.*kA).*$";
    public static boolean isValidIsjungimoGeba(String isjungimoGeba) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ISJUNGIMO_GEBA_PATTERN);
        Matcher matcher = pattern.matcher(isjungimoGeba);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ISJUNGIMO_CHARAKTERISTIKA_PATTERN= "^[a-zA-Z ĄąČčĘęĖėĮįŠšŲųŪūŽž]{3,255}$";
    public static boolean isValidIsjungimoCharakteristika(String isjungimoCharakteristika) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ISJUNGIMO_CHARAKTERISTIKA_PATTERN);
        Matcher matcher = pattern.matcher(isjungimoCharakteristika);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MECHANINIS_ATSPARUMAS_PATTERN= "^[0-9 /]{1,10}(?=.*N).*$";
    public static boolean isValidMechaninisAtsparumas(String mechaninisAtsparumas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MECHANINIS_ATSPARUMAS_PATTERN);
        Matcher matcher = pattern.matcher(mechaninisAtsparumas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SKERSPJUVIS_PATTERN= "^[0-9a-zA-Z -:,]{1,10}$";
    public static boolean isValidSkerspjuvis(String skerspjuvis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SKERSPJUVIS_PATTERN);
        Matcher matcher = pattern.matcher(skerspjuvis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SKERSPJUVIS2_PATTERN= "^[0-9a-zA-Z -:,]{1,10}$";
    public static boolean isValidSkerspjuvis2(String skerspjuvis2) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SKERSPJUVIS2_PATTERN);
        Matcher matcher = pattern.matcher(skerspjuvis2);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_NUOTEKU_SROVE_PATTERN= "^[0-9]{1,10}(?=.*mA).*$";
    public static boolean isValidNuotekuSrove(String nuotekuSrove) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_NUOTEKU_SROVE_PATTERN);
        Matcher matcher = pattern.matcher(nuotekuSrove);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_DYDIS_PATTERN= "^[0-9]{1,10}$";
    public static boolean isValidDydis(String dydis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_DYDIS_PATTERN);
        Matcher matcher = pattern.matcher(dydis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_PLOTAS_PATTERN= "^[0-9 -,]{1,10}$";
    public static boolean isValidPlotas(String plotas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_PLOTAS_PATTERN);
        Matcher matcher = pattern.matcher(plotas);
        return matcher.find();
    }
    /* Naujo produkto kūrimo lango Validacija : End */

}
