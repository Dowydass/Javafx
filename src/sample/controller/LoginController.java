package sample.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
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
        email_textfield.setDisable(true);
        password_passwordfield.setDisable(true);
        check_box_remember_me.setDisable(true);
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
                        email_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        check_box_remember_me.setDisable(false);
                        return;
                    });
                } else if (!Validation.isValidEmail(email_textfield.getText()) || !Validation.isValidPassword(password_passwordfield.getText())) {
                    Platform.runLater(() -> {
                        login_info_label.setStyle("-fx-text-fill: red;");
                        login_info_label.setText(Constants.CREDENTIALS_EMAIL_AND_PASSWORD_NOT_CORRECT);
                        load_progress_indicator.setVisible(false);
                        username_button.setVisible(true);
                        register_button.setVisible(true);
                        email_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        check_box_remember_me.setDisable(false);
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
                                JPAUtil.showPopupWindow("Informacija", "Vartotojo paskyra yra išjungta. \nPrašome kreiptis į ECOSprendimai atstovą.\n- Telefono nr.: " + Constants.CONTACT_PHONE_NUMBER + "\n- El.paštas: " + Constants.CONTACT_EMAIL, "#0a58ca", "#FFFFFF", login_info_label.getScene(), 400, 150);
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
                            email_textfield.setDisable(false);
                            password_passwordfield.setDisable(false);
                            check_box_remember_me.setDisable(false);

                        });
                    }
                } catch (NullPointerException npe) {
                    Platform.runLater(() -> {
                        login_info_label.setStyle("-fx-text-fill: red;");
                        login_info_label.setText(Constants.EMAIL_NOT_EXIST);
                        load_progress_indicator.setVisible(false);
                        username_button.setVisible(true);
                        register_button.setVisible(true);
                        email_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        check_box_remember_me.setDisable(false);
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

    public void showPopupWindow(String title, String information, String titleBackgroundColor, String titleTextColor, Scene scene) {

        Window parent = scene.getWindow();
        javafx.stage.Popup popup = new Popup();



        Stage stage = (Stage) username_button.getScene().getWindow();

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
        okButton.setOnAction(event -> {
            popup.hide();
            System.exit(0);
            Platform.exit();
        });
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                stage.close();
                Platform.exit();
            }
        });


        hBox31.getChildren().add(okButton);
        hBox3.getChildren().add(hBox31);

        root.getChildren().add(hBox1);
        root.getChildren().add(hBox2);
        root.getChildren().add(hBox3);

        popup.getContent().addAll(root);
        popup.show(parent);

    }

}
