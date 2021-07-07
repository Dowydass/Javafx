package sample.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.mindrot.jbcrypt.BCrypt;
import sample.JPA.JPAUtil;
import sample.JPA.user.User;
import sample.JPA.user.UserDAO;
import sample.JPA.user.UserHolder;
import sample.utils.Constants;
import sample.utils.Validation;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {

    @FXML
    public Button username_button;
    @FXML
    public Label login_info_label;
    @FXML
    public TextField email_textfield;
    @FXML
    public PasswordField password_passwordfield;
    @FXML
    public Hyperlink register_button;
    @FXML
    public CheckBox check_box_remember_me;
    @FXML
    public Label version_label;
    @FXML
    ProgressIndicator load_progress_indicator;

    final String PREF_NAME = "Email";
    final String PREF_PASSWORD = "Password";
    final String PREF_CHECKBOX = "Check";
    Preferences prefs = Preferences.userNodeForPackage(LoginController.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        version_label.setText("Versija: " + Constants.PROGRAM_VERSION);

        String propertyValue = prefs.get(PREF_NAME, "");
        String password = prefs.get(PREF_PASSWORD, "");
        check_box_remember_me.setSelected(prefs.getBoolean(PREF_CHECKBOX, false));

        email_textfield.setText(propertyValue);
        email_textfield.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        username_button.fire();
                    }
                }
        );
        password_passwordfield.setText(password);
        password_passwordfield.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        username_button.fire();
                    }
                }
        );
        username_button.setOnKeyPressed(event -> {
                    if (event.getCode().equals(KeyCode.ENTER)) {
                        username_button.fire();
                    }
                }
        );
    }


    private void loadProgress() {
        Task copyWorker = createWorker();
        load_progress_indicator.progressProperty().bind(copyWorker.progressProperty());
        new Thread(copyWorker).start();
        load_progress_indicator.setVisible(true);
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected TabPane call() throws Exception {
                TabPane tabPane = new TabPane();

                return tabPane;
            }
        };
    }// Loading Spinner set-up-ends

    public void login() {
        username_button.setVisible(false);
        register_button.setVisible(false);
        loadProgress();

        Thread loginLogicThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (email_textfield.getText().isEmpty()
                        || password_passwordfield.getText().isEmpty()) {
                    Platform.runLater(() -> {
                        login_info_label.setStyle("-fx-text-fill: red;");
                        login_info_label.setText(Constants.CREDENTIALS_IS_NOT_FILLED);
                        load_progress_indicator.setVisible(false);
                        username_button.setVisible(true);
                        register_button.setVisible(true);
                        return;
                    });
                } else if (!Validation.isValidEmail(email_textfield.getText()) || !Validation.isValidPassword(password_passwordfield.getText())) {
                    Platform.runLater(() -> {
                        login_info_label.setStyle("-fx-text-fill: red;");
                        login_info_label.setText(Constants.CREDENTIALS_EMAIL_AND_PASSWORD_NOT_CORRECT);
                        load_progress_indicator.setVisible(false);
                        username_button.setVisible(true);
                        register_button.setVisible(true);
                        return;
                    });
                }
                //Comparing two objects (email)
                //If email from text field equals email from database
                //Go to dashboard

                //Getting list from UserDAO class with credentials of password and username
                User credentials = UserDAO.searchUserByEmail(email_textfield.getText());


                //checking password is it match
                try {
                    assert credentials != null;
                    if (checkPass(password_passwordfield.getText(), credentials.getPassword())) {
                        if (!credentials.isBlocked()) {

                            if (check_box_remember_me.isSelected()) {
                                prefs.put(PREF_NAME, email_textfield.getText());
                                prefs.put(PREF_PASSWORD, password_passwordfield.getText());
                                prefs.putBoolean(PREF_CHECKBOX, true);
                            } else {
                                prefs.put(PREF_NAME, "");
                                prefs.put(PREF_PASSWORD, "");
                                prefs.putBoolean(PREF_CHECKBOX, false);
                            }

                            User u = new User(credentials.getEmail(), credentials.isAdmin());
                            UserHolder holder = UserHolder.getInstance();
                            holder.setUser(u);
                            Platform.runLater(() -> {
                                goToDashBoard();
                            });
                        } else {
                            Platform.runLater(() -> {
                                JPAUtil.showPopupWindow("Informacija", "Vartotojo paskyra yra išjungta. Prašome kreiptis į ECOSprendimai administratorių.\n- Telefono nr.: +370 600 00000\n- El.paštas: info@ecosprendimai.lt", "#0a58ca", "#FFFFFF", login_info_label.getScene());
                            });
                        }

                    } else if (!checkPass(password_passwordfield.getText(), credentials.getPassword())) {
                        Platform.runLater(() -> {
                            login_info_label.setStyle("-fx-text-fill: red;");
                            login_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT);
                            System.out.println("LOGIN UNAVAILABLE");
                            load_progress_indicator.setVisible(false);
                            username_button.setVisible(true);
                            register_button.setVisible(true);

                        });
                    }
                } catch (NullPointerException npe) {
                    Platform.runLater(() -> {
                        login_info_label.setStyle("-fx-text-fill: red;");
                        login_info_label.setText(Constants.EMAIL_NOT_EXIST);
                        load_progress_indicator.setVisible(false);
                        username_button.setVisible(true);
                        register_button.setVisible(true);
                    });
                }
            }
        });
        loginLogicThread.setDaemon(true);
        loginLogicThread.start();
    }

    public void windowCloseLoginButton() { //Uzdaro prisijungimo langa
        Stage stage = (Stage) register_button.getScene().getWindow();
        stage.close();
    }

    public void register() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(Constants.REGISTER_VIEW_DIRECTORY_PATH));
            Stage registerStage = new Stage();
            Scene scene = new Scene(root, Constants.REGISTER_WINDOW_WIDTH, Constants.REGISTER_WINDOW_HEIGHT);
            scene.getStylesheets().add(getClass().getClassLoader().getResource(Constants.CSS_DIRECTORY_PATH).toExternalForm());
            registerStage.setTitle("Registracija");
            registerStage.setScene(scene);
            JPAUtil.setScene(scene);
            registerStage.setResizable(false);
            registerStage.show();
            registerStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                System.exit(0);
                Platform.exit();
            });
            windowCloseLoginButton();


        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void goToDashBoard() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(Constants.DASHBOARD_VIEW_DIRECTORY_PATH));
            Stage dashboardStage = new Stage();
            Scene scene = new Scene(root);
            JPAUtil.setScene(scene);
            //scene.getStylesheets().add(getClass().getResource(Constants.CSS_DIRECTORY_PATH).toExternalForm());
            dashboardStage.setTitle("Produktų peržiūros langas");
            dashboardStage.setScene(scene);
            dashboardStage.setMinWidth(1345);
            dashboardStage.show();
            dashboardStage.setOnCloseRequest(e -> DashboardController.closeDashboard());
            dashboardStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                System.exit(0);
                Platform.exit();
            });
            windowCloseLoginButton();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }


    /**
     * @param hashedPassword is encrypted password
     * @param plainPassword  password witch declared in password field
     */
    public static boolean checkPass(String plainPassword, String hashedPassword) {
        if (BCrypt.checkpw(plainPassword, hashedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
