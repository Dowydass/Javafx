package sample.utils;

public class Constants {

    // langu dydziu matmenys
    public static final int LOGIN_WINDOW_WIDTH = 500;
    public static final int LOGIN_WINDOW_HEIGHT = 303;
    public static final int REGISTER_WINDOW_WIDTH = 550;
    public static final int REGISTER_WINDOW_HEIGHT = 552;
    public static final int CATEGORY_FORM_WINDOW_WIDTH = 600;
    public static final int CATEGORY_FORM_WINDOW_HEIGHT = 350;

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
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_CATALOG_NUMBER = "Produkto katalogo numeris neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SYMBOL = "Produkto pavadinimas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_PRICE = "Produkto kaina neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_STOCK = "Produkto pasirinktas kiekis neatitinka reikalavimų.";

    /* Naujo produkto kūrimo lango Validacija : Start */

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
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SPALVA = "Spalvos parametras neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_KORPUSO_MEDZIAGA = "Korpuso medžiaga neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_IZOLIACIJA = "Izoliacija neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVORIS = "Svoris neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_GALIA = "Galios parametras neatitinka reikalavimų. \n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1.23W";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVIESOS_SRAUTAS = "Šviesos srautas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_SVIESOS_SPALVOS_TEMPERATURA = "Šviesos spalvos temperatūra neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1000K";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_LAIDININKAS = "Laidininkas neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_IZOLIACIJA2 = "Izoliacija2 neatitinka reikalavimų.";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_DARBINE_TEMPERATURA = "Darbinė temperatūra neatitinka reikalavimų.\n(Bent vienas skaitmuo)(panaudoti parametro matavimo vienetą pvz. 1°C";
    public static final String CREDENTIALS_IS_NOT_CORRECT_PRODUCT_MAX_DARBINE_TEMPERATURA = "Maksimali darbinė temperatūra neatitinka reikalavimų.";
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
}
