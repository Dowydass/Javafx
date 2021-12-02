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

    public static boolean isValidCatalogNo(String catalogNo) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CATALOG_NO_PATTERN);
        Matcher matcher = pattern.matcher(catalogNo);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SYMBOL_PATTERN = "^((?![\\^!@#$*~ <>?]).)((?![\\^!@#$*~<>?]).){1,1024}((?![\\^!@#$*~ <>?]).)$";

    public static boolean isValidSymbol(String symbol) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SYMBOL_PATTERN);
        Matcher matcher = pattern.matcher(symbol);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_PRICE_PATTERN = "^[0-9]{1,253}(\\.?[0-9]{1,2})$";

    public static boolean isValidPrice(String price) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_PRICE_PATTERN);
        Matcher matcher = pattern.matcher(price);
        return matcher.find();
    }

    public static final String IMAGE_URL_PATTERN = "^(http(s?):)([/|.\\w\\s-])*\\.(?:jpg|gif|png)";

    public static boolean isValidImageUrl(String imageUrl) {
        Pattern pattern = Pattern.compile(IMAGE_URL_PATTERN);
        Matcher matcher = pattern.matcher(imageUrl);
        return matcher.find();
    }

    public static final String CATEGORY_FORM_NAME_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,510}$";

    public static boolean isValidCategoryName(String name) {
        Pattern pattern = Pattern.compile(CATEGORY_FORM_NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    /* Naujo produkto kūrimo lango Validacija : Start */

    public static final String PRODUCT_FORM_CU_AMOUNT_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidCuAmount(String cuAmount) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CU_AMOUNT_PATTERN);
        Matcher matcher = pattern.matcher(cuAmount);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_CU_PRICE_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidCuPrice(String cuPrice) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CU_PRICE_PATTERN);
        Matcher matcher = pattern.matcher(cuPrice);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GAMINTOJAS_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,1024}$";

    public static boolean isValidGamintojas(String gamintojas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GAMINTOJAS_PATTERN);
        Matcher matcher = pattern.matcher(gamintojas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_AUKSTIS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidAukstis(String aukstis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_AUKSTIS_PATTERN);
        Matcher matcher = pattern.matcher(aukstis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_PLOTIS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidPlotis(String plotis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_PLOTIS_PATTERN);
        Matcher matcher = pattern.matcher(plotis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GYLIS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidGylis(String gylis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GYLIS_PATTERN);
        Matcher matcher = pattern.matcher(gylis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SKERSMUO_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidSkersmuo(String skersmuo) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SKERSMUO_PATTERN);
        Matcher matcher = pattern.matcher(skersmuo);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ILGIS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidIlgis(String ilgis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ILGIS_PATTERN);
        Matcher matcher = pattern.matcher(ilgis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APSAUGOS_LAIPSNIS_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidApsaugosLaipsnis(String apsaugosLaipsnis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APSAUGOS_LAIPSNIS_PATTERN);
        Matcher matcher = pattern.matcher(apsaugosLaipsnis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MODULIU_SKAICIUS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidModuliuSkaicius(String moduliuSkaicius) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MODULIU_SKAICIUS_PATTERN);
        Matcher matcher = pattern.matcher(moduliuSkaicius);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VARDINE_SROVE_PATTERN = "^[0-9 ]{1,11}(?=.*A).*$";

    public static boolean isValidVardineSrove(String vardineSrove) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VARDINE_SROVE_PATTERN);
        Matcher matcher = pattern.matcher(vardineSrove);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VARDINE_ITAMPA_PATTERN = "^[0-9 ]{1,13}(?=.*V).*$";

    public static boolean isValidVardineItampa(String vardineItampa) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VARDINE_ITAMPA_PATTERN);
        Matcher matcher = pattern.matcher(vardineItampa);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MECHANINIS_ATSPARUMAS_IK_PATTERN = "^[0-9A-Za-z ]{3,255}$";

    public static boolean isValidMechaninisAtsparumasIK(String mechaninisAtsparumasIK) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MECHANINIS_ATSPARUMAS_IK_PATTERN);
        Matcher matcher = pattern.matcher(mechaninisAtsparumasIK);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_STORIS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidStoris(String storis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_STORIS_PATTERN);
        Matcher matcher = pattern.matcher(storis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SPALVA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,510}$";

    public static boolean isValidSpalva(String spalva) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SPALVA_PATTERN);
        Matcher matcher = pattern.matcher(spalva);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_KORPUSO_MEDZIAGA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidKorpusoMedziaga(String korpusoMedziaga) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_KORPUSO_MEDZIAGA_PATTERN);
        Matcher matcher = pattern.matcher(korpusoMedziaga);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_IZOLIACIJA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidIzoliacija(String izoliacija) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_IZOLIACIJA_PATTERN);
        Matcher matcher = pattern.matcher(izoliacija);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SVORIS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidSvoris(String svoris) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SVORIS_PATTERN);
        Matcher matcher = pattern.matcher(svoris);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GALIA_PATTERN = "^[0-9 ]{1,11}(?=.*W).*$";

    public static boolean isValidGalia(String galia) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GALIA_PATTERN);
        Matcher matcher = pattern.matcher(galia);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SVIESOS_SRAUTAS_PATTERN = "^[0-9]+(.|,)?[0-9]?$";

    public static boolean isValidSviesosSrautas(String sviesosSrautas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SVIESOS_SRAUTAS_PATTERN);
        Matcher matcher = pattern.matcher(sviesosSrautas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SVIESOS_SPALVOS_TEMPERATURA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

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

    public static final String PRODUCT_FORM_LAIDININKO_IZOLIACIJA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,1024}$";

    public static boolean isValidLaidininkoIzoliacija(String laidininkoIzoliacija) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_LAIDININKO_IZOLIACIJA_PATTERN);
        Matcher matcher = pattern.matcher(laidininkoIzoliacija);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_DARBINE_TEMPERATURA_PATTERN = "^[0-9a-zA-Z -°]{1,12}(?=.*C).*$";

    public static boolean isValidDarbineTemperatura(String darbineTemperatura) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_DARBINE_TEMPERATURA_PATTERN);
        Matcher matcher = pattern.matcher(darbineTemperatura);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MAX_DARBINE_TEMPERATURA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidMaxDarbineTemperatura(String maxDarbineTemperatura) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MAX_DARBINE_TEMPERATURA_PATTERN);
        Matcher matcher = pattern.matcher(maxDarbineTemperatura);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APSVIETA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,1024}$";

    public static boolean isValidApsvieta(String apsvieta) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APSVIETA_PATTERN);
        Matcher matcher = pattern.matcher(apsvieta);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APVALKALAS_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,1024}$";

    public static boolean isValidApvalkalas(String apvalkalas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APVALKALAS_PATTERN);
        Matcher matcher = pattern.matcher(apvalkalas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_CPR_KLASE_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidCprKlase(String cprKlase) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CPR_KLASE_PATTERN);
        Matcher matcher = pattern.matcher(cprKlase);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ISJUNGIMO_GEBA_PATTERN = "^[0-9]{1,12}(?=.*kA).*$";

    public static boolean isValidIsjungimoGeba(String isjungimoGeba) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ISJUNGIMO_GEBA_PATTERN);
        Matcher matcher = pattern.matcher(isjungimoGeba);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ISJUNGIMO_CHARAKTERISTIKA_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidIsjungimoCharakteristika(String isjungimoCharakteristika) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ISJUNGIMO_CHARAKTERISTIKA_PATTERN);
        Matcher matcher = pattern.matcher(isjungimoCharakteristika);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MECHANINIS_ATSPARUMAS_PATTERN = "^[0-9 ]{1,255}(?=.*N).*$";

    public static boolean isValidMechaninisAtsparumas(String mechaninisAtsparumas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MECHANINIS_ATSPARUMAS_PATTERN);
        Matcher matcher = pattern.matcher(mechaninisAtsparumas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SKERSPJUVIS_AL_PATTERN = "^[0-9a-zA-Z -:,]{3,255}$";

    public static boolean isValidSkerspjuvisAl(String skerspjuvis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SKERSPJUVIS_AL_PATTERN);
        Matcher matcher = pattern.matcher(skerspjuvis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SKERSPJUVIS_CU_PATTERN = "^[0-9a-zA-Z -:,]{3,255}$";

    public static boolean isValidSkerspjuvisCu(String skerspjuvis2) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SKERSPJUVIS_CU_PATTERN);
        Matcher matcher = pattern.matcher(skerspjuvis2);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_NUOTEKIO_SROVE_PATTERN = "^[0-9 ]{1,12}(?=.*mA).*$";

    public static boolean isValidNuotekioSrove(String nuotekuSrove) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_NUOTEKIO_SROVE_PATTERN);
        Matcher matcher = pattern.matcher(nuotekuSrove);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_DYDIS_PATTERN = "^[0-9]{1,10}$";

    public static boolean isValidDydis(String dydis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_DYDIS_PATTERN);
        Matcher matcher = pattern.matcher(dydis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_PLOTAS_PATTERN = "^[A-Za-z0-9 ._%+-a-z]{2,255}$";

    public static boolean isValidPlotas(String plotas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_PLOTAS_PATTERN);
        Matcher matcher = pattern.matcher(plotas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APTIKIMO_ZONA_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidAptikimoZona(String aptikimoZona) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APTIKIMO_ZONA_PATTERN);
        Matcher matcher = pattern.matcher(aptikimoZona);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_MAX_DARBINE_ITAMPA_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidMaksDarbineItampa(String maksDarbineItampa) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_MAX_DARBINE_ITAMPA_PATTERN);
        Matcher matcher = pattern.matcher(maksDarbineItampa);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ISKROVIMO_SROVE_8_20_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidiskrovimoSrove820(String iskrovimoSrove820) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ISKROVIMO_SROVE_8_20_PATTERN);
        Matcher matcher = pattern.matcher(iskrovimoSrove820);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ISKROVIMO_SROVE_10_350_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidiskrovimoSrove10350(String iskrovimoSrove10350) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ISKROVIMO_SROVE_10_350_PATTERN);
        Matcher matcher = pattern.matcher(iskrovimoSrove10350);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ITAMPOS_APSAUGOS_LYGIS_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidItamposApsaugosLygis(String itamposApsaugosLygis) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ITAMPOS_APSAUGOS_LYGIS_PATTERN);
        Matcher matcher = pattern.matcher(itamposApsaugosLygis);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_KATEGORIJA_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidKategorija(String kategorija) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_KATEGORIJA_PATTERN);
        Matcher matcher = pattern.matcher(kategorija);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_CRI_PATTERN = "^[0-9]{1,255}$";

    public static boolean isValidCri(String cri) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_CRI_PATTERN);
        Matcher matcher = pattern.matcher(cri);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GARANTIJA_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidGarantija(String garantija) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GARANTIJA_PATTERN);
        Matcher matcher = pattern.matcher(garantija);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_SERTIFIKATAI_PATTERN = "^[A-Za-z0-9 ._%+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidSertifikatai(String sertifikatai) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_SERTIFIKATAI_PATTERN);
        Matcher matcher = pattern.matcher(sertifikatai);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_NEMA_JUNGTIS_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidNema(String nema) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_NEMA_JUNGTIS_PATTERN);
        Matcher matcher = pattern.matcher(nema);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VIRSITAMPIU_APSAUGA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidVirsitampiuApsauga(String virsitampiuApsauga) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VIRSITAMPIU_APSAUGA_PATTERN);
        Matcher matcher = pattern.matcher(virsitampiuApsauga);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_ILGAAMZISKUMAS_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,1024}$";

    public static boolean isValidIlgaamziskumas(String ilgaamziskumas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_ILGAAMZISKUMAS_PATTERN);
        Matcher matcher = pattern.matcher(ilgaamziskumas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_KORPUSO_ATIDARYMAS_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidKorpusoAtidarymas(String korpusoAtidarymas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_KORPUSO_ATIDARYMAS_PATTERN);
        Matcher matcher = pattern.matcher(korpusoAtidarymas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_OPTINES_ELEKTRINES_DALIES_PERTVARA_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidOptinesElektrinesDaliesPertvara(String optinesElektrinesDaliesPertvara) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_OPTINES_ELEKTRINES_DALIES_PERTVARA_PATTERN);
        Matcher matcher = pattern.matcher(optinesElektrinesDaliesPertvara);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VALDYMAS_PATTERN = "^[A-Za-z0-9 ._%()+-a-zĄąČčĘęĖėĮįŠšŲųŪūŽž]{2,255}$";

    public static boolean isValidValdymas(String valdymas) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VALDYMAS_PATTERN);
        Matcher matcher = pattern.matcher(valdymas);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_APATINIS_DIAMETRAS_PATTERN = "^[0-9]{1,255}$";

    public static boolean isValidApatinisDiametras(String diametras) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_APATINIS_DIAMETRAS_PATTERN);
        Matcher matcher = pattern.matcher(diametras);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_VIRSUTINIS_DIAMETRAS_PATTERN = "^[0-9]{1,255}$";

    public static boolean isValidVirsutinisDiametras(String diametras) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_VIRSUTINIS_DIAMETRAS_PATTERN);
        Matcher matcher = pattern.matcher(diametras);
        return matcher.find();
    }

    public static final String PRODUCT_FORM_GEMBES_DIAMETRAS_PATTERN = "^[0-9]{1,255}$";

    public static boolean isValidGembesDiametras(String diametras) {
        Pattern pattern = Pattern.compile(PRODUCT_FORM_GEMBES_DIAMETRAS_PATTERN);
        Matcher matcher = pattern.matcher(diametras);
        return matcher.find();
    }



    /* Naujo produkto kūrimo lango Validacija : End */

}
