package sample.utils;

public class Constants {

    // langu dydziu matmenys
    public static final int LOGIN_WINDOW_WIDTH = 500;
    public static final int LOGIN_WINDOW_HEIGHT = 303;
    public static final int REGISTER_WINDOW_WIDTH = 550;
    public static final int REGISTER_WINDOW_HEIGHT = 552;
    public static final int CATEGORY_FORM_WINDOW_WIDTH = 600;
    public static final int CATEGORY_FORM_WINDOW_HEIGHT = 350;

    //Kabelio tipų skaičiai
    public static final int KABELIS100 = 100;
    public static final int KABELIS150 = 150;
    public static final int KABELIS300 = 300;


    // keliai iki vaizdo
    public static final String REGISTER_VIEW_DIRECTORY_PATH = "sample/Resources/view/register.fxml";
    public static final String DASHBOARD_VIEW_DIRECTORY_PATH = "sample/Resources/view/dashboard.fxml";
    public static final String LOGIN_VIEW_DIRECTORY_PATH = "sample/Resources/view/login.fxml";
    public static final String USER_STATS_VIEW_PATH = "sample/Resources/view/user_stats.fxml";
    public static final String PRODUCT_FORM_VIEW_PATH = "sample/Resources/view/productForm.fxml";
    public static final String CATEGORY_FORM_VIEW_PATH = "sample/Resources/view/categoryForm.fxml";
    public static final String CSS_DIRECTORY_PATH = "sample/Resources/view/CSS.css";
    public static final String OPEN_STOCKS_DIRECTORY_PATH = "sample/Resources/view/stockPriceRate.fxml";

    public static final String EMAIL_NOT_EXIST = "Vartotojas su įvestu el. paštu neegzistuoja.";
    public static final String EMAIL_EXISTS = "Vartotojas su įvestu el. paštu egzistuoja.";
    public static final String CREDENTIALS_IS_NOT_CORRECT = "Įvesti duomenys neteisingi.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_FIRST_NAME = "Įvestas vardas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_LAST_NAME = "Įvesta pavardė neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_COMPANY_NAME = "Neteisingai įvestas įmonės pavadinimas.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PASSWORD = "Slaptažodis neatitinka reikalavimų.\n(Bent 8 simboliai)(bent viena didžioji raidė ir mažoji)\n(bent vienas skaicius)";
    public static final String CREDENTIALS_IS_NOT_FILLED_EMAIL = "Vartotojo el. paštas neatitinka reikalavimų.";
    public static final String PASSWORD_IS_NOT_EQUAL = "Įvesti slaptažodžiai nesutampa.";
    public static final String CREDENTIALS_IS_NOT_FILLED = "Visi laukeliai privalo būti užpildyti.";
    public static final String CREDENTIALS_EMAIL_AND_PASSWORD_NOT_CORRECT = "Blogai įvestas el. paštas arba slaptažodis";
    public static final String CREDENTIALS_IS_NOT_CORRECT_CATEGORY_NAME = "Kategorijos pavadinimas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CHOSEN_CATEGORY = "Tėvinė kategorija privalo būti pasirinkta.";

    /* Naujo produkto kūrimo lango Validacija : Start */

    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CATALOG_NUMBER = "Produkto katalogo numeris neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SYMBOL = "Produkto pavadinimas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PRICE  = "Produkto kaina neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_IMAGE_URL = "Palaikomi tik .jpg, .gif, .png paveikslėlių formatai.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_CU_KIEKIS = "Cu kiekis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_CU_KAINA = "Cu kaina neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_GAMINTOJAS = "Gamintojas įvestas ne pagal reikalavimą.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_AUKSTIS = "Aukštis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PLOTIS = "Plotis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GYLIS= "Gylis neatitinka  reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSMUO = "Skersmuo neatitinka  reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ILGIS = "Ilgis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APSAUGOS_LAIPSNIS = "Apsaugos laipsnis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MODULIU_SKAICIUS = "Moduliu skaičius neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VARDINE_SROVE = "Vardinė srovė neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 40.1A)";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VARDINE_ITAMPA = "Vardinė įtampa neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 12.9V AC)";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MECHANINIS_ATSPARUMAS_IK = "Mechaninis atsparumas IK neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_STORIS = "Storis neatitinka reikalaviamų";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SPALVA = "Spalvos parametras neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KORPUSO_MEDZIAGA = "Korpuso medžiaga neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_IZOLIACIJA = "Izoliacija neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVORIS = "Svoris neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GALIA = "Galios parametras neatitinka reikalavimų. \n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1.23W";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVIESOS_SRAUTAS = "Šviesos srautas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVIESOS_SPALVOS_TEMPERATURA = "Šviesos spalvos temperatūra neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1000K";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_LAIDININKAS = "Laidininkas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_LAIDININKO_IZOLIACIJA = "Laidininko izoliacija neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DARBINE_TEMPERATURA = "Darbinė temperatūra neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1°C";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MAX_DARBINE_TEMPERATURA = "Maksimali darbinė temperatūra neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APSVIETA = "Apšvietos parametras neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APVALKALAS = "Apvalkalo parametras neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CPR_KLASE = "CPR klasė neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISJUNGIMO_GEBA = "Išjungimo geba neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1.9kA";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISJUNGIMO_CHARAKTERISTIKA = "Išjungimo charakteristika neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MECHANINIS_ATSPARUMAS = "Mechaninis atsparumas neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 9.1N";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSPJUVIS = "Skerspjuvis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SKERSPJUVIS2 = "Skerspjuvis2 neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_NUOTEKIO_SROVE = "Nuotekio srovės parametras neatitinka reikalavimų. \n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 11.9mA";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DYDIS = "Dydis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PLOTAS = "Plotas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APTIKIMO_ZONA = "Aptikimo zona neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MAX_DARBINE_ITAMPA = "Maks. darbinė įtampa neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISKROVIMO_SROVE_8_30 = "Maks. iškrovimo srovė 8.30 neatitinka reikalavimų";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ISKROVIMO_SROVE_10_350 = "Maks. ikšrovimo srovė 10.350 neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ITAMPOS_APSAUGOS_LYGIS = "Įtampos apsaugos lygis neatitinka reikalavimų";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KATEGORIJA = "Kategorijos laukelis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CRI = "CRI neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GARANTIJA = "Garantija neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SERTIFIKATAI = "Sertifikatai neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_NEMA_JUNGTIS = "Nema jungtis neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VIRSITAMPIU_APSAUGA = "Viršįtampių apsauga neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_ILGAAMZISKUMAS = "Ilgaamžiškumas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KORPUSO_ATIDARYMAS = "Korpuso atidarymas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_OPTINES_IR_ELEKTRINES_DALIES_PERTVARKA = "Optinės ir elektrinės dalies pertvarka neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VALDYMAS = "Valdymas neatitinka reikalavimų";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_APATINIS_DIAMETRAS = "Apatinis diametras netitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_VIRSUTINIS_DIAMETRAS = "Viršutinis diametras neatitinka reikalavimų";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GEMBES_DIAMETRAS = "Gembės diametras neatitinka reikalavimų";




    /* Naujo produkto kūrimo lango Validacija : End */

    public static final String CURRENT_SESSION_STATUS_ADMIN = "Administratorius";
    public static final String CURRENT_SESSION_STATUS_USER = "Vartotojas";

    public static final String DB_URL = "jdbc:mysql://localhost:3306/task";
    public static final String USER = "root";
    public static final String PASS = "";

    public static final String PROGRAM_VERSION = "1.0";
    public static final String EMAIL_CONTACT_LINE = "Susiekite el. paštu su ECO Sprendimai atstovu: ";
    public static final String CONTACT_EMAIL = "julius@ecosprendimai.lt";
    public static final String CONTACT_PHONE_NUMBER = "+370 699 81772";

    public static final String BUTTON_NAME_OK = "Gerai";
    public static final String BUTTON_NAME_RENEW = "Atnaujinti ryšį";
}
