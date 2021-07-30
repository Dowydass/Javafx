package sample.controller;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.mindrot.jbcrypt.BCrypt;
import sample.JPA.user.User;
import sample.JPA.user.UserDAO;
import sample.Main;
import sample.utils.Constants;
import sample.utils.Validation;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController extends Main implements Initializable {

    @FXML
    public Button register_button;
    @FXML
    public Button close;
    @FXML
    public Label form_info_label;
    @FXML
    public TextField company_name_textfield;
    @FXML
    public PasswordField password_passwordfield;
    @FXML
    public TextField email_textfield;
    @FXML
    public TextField first_name_textfield;
    @FXML
    public TextField last_name_textfield;
    @FXML
    public TextField password_confirm_passwordfield;
    @FXML
    public ProgressIndicator load_progress_indicator;


    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        onMouseClick();
    }

    //Checks all register fields are correct
    public void register() {
        register_button.setVisible(false);
        close.setVisible(false);
        first_name_textfield.setDisable(true);
        last_name_textfield.setDisable(true);
        email_textfield.setDisable(true);
        company_name_textfield.setDisable(true);
        password_passwordfield.setDisable(true);
        password_confirm_passwordfield.setDisable(true);
        loadProgress();
        Thread registerLogicalThread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (first_name_textfield.getText().isEmpty()
                        || last_name_textfield.getText().isEmpty()
                        || email_textfield.getText().isEmpty()
                        || company_name_textfield.getText().isEmpty()
                        || password_passwordfield.getText().isEmpty()
                        || password_confirm_passwordfield.getText().isEmpty()
                ) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_FILLED);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                    return;
                } else if (!Validation.isValidFirstName(first_name_textfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_FIRST_NAME);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                    return;
                } else if (!Validation.isValidLastName(last_name_textfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_LAST_NAME);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                    return;
                } else if (!Validation.isValidEmail(email_textfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_FILLED_EMAIL);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                    return;
                } else if (!Validation.isValidCompanyName(company_name_textfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_COMPANY_NAME);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                    return;
                } else if (!Validation.isValidPassword(password_passwordfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PASSWORD);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);

                    });
                    return;
                } else if (!Validation.isValidPassword(password_confirm_passwordfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.CREDENTIALS_IS_NOT_CORRECT_PASSWORD);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                    return;
                } else if (!password_passwordfield.getText().equals(password_confirm_passwordfield.getText())) {
                    Platform.runLater(() -> {
                        WarnStyle();
                        form_info_label.setText(Constants.PASSWORD_IS_NOT_EQUAL);
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    });
                }
                //Sending data with email address and getting answer is it exists in boolean
                boolean credentials = UserDAO.compareEmailForValidation(email_textfield.getText());
                Platform.runLater(() -> {
                    //If theres is no same email
                    if (!credentials) {
                        //Creating new user
                        registerToDataBase();
                    } else {
                        form_info_label.setText("");
                        form_info_label.setStyle("-fx-text-fill: red;");
                        form_info_label.setText(Constants.EMAIL_EXISTS);
                        email_textfield.setStyle("-fx-text-fill: red;");
                        register_button.setVisible(true);
                        close.setVisible(true);
                        load_progress_indicator.setVisible(false);
                        first_name_textfield.setDisable(false);
                        last_name_textfield.setDisable(false);
                        email_textfield.setDisable(false);
                        company_name_textfield.setDisable(false);
                        password_passwordfield.setDisable(false);
                        password_confirm_passwordfield.setDisable(false);
                    }
                });
            }
        });
        registerLogicalThread.setDaemon(true);
        registerLogicalThread.start();
    }


    public void closeRegister() { //Uzdaro prisijungimo langa
        Stage stage = (Stage) register_button.getScene().getWindow();
        stage.close();
    }

    void WarnStyle() {
        form_info_label.setText("");
        form_info_label.setStyle("-fx-text-fill: red;");
    }

    public void goToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(Constants.LOGIN_VIEW_DIRECTORY_PATH));
            Stage loginStage = new Stage();
            Scene scene = new Scene(root, Constants.LOGIN_WINDOW_WIDTH, Constants.LOGIN_WINDOW_HEIGHT);
            scene.getStylesheets().add(getClass().getClassLoader().getResource(Constants.CSS_DIRECTORY_PATH).toExternalForm());
            loginStage.setTitle("Prisijungimas");
            loginStage.setResizable(false);
            loginStage.setScene(scene);
            loginStage.show();
            loginStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
                System.exit(0);
                Platform.exit();
            });
            closeRegister();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void onMouseClick() {
        form_info_label.setStyle("-fx-text-fill: black;");
        // Handle TextField text changes.
        password_passwordfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() <= 7) {
                form_info_label.setStyle("-fx-text-fill: black;");
                form_info_label.setText("Slaptažodyje turi būti :\n" +
                        "✳bent 8 simboliai.\n");
                return;

            } else {
                form_info_label.setText("");
            }
            if (!Validation.isOneUpperCaseExist(newValue)) {
                form_info_label.setStyle("-fx-text-fill: black;");
                form_info_label.setText("Slaptažodyje turi būti :\n" +
                        "✳bent viena didžioji ir mažoji radė.\n");
                return;

            } else {
                form_info_label.setText("");
            }
            if (!Validation.isOneDigitAre(newValue)) {
                form_info_label.setStyle("-fx-text-fill: black;");
                form_info_label.setText("Slaptažodyje turi būti :\n" +
                        "✳bent vienas skaičius.\n");
                return;

            } else {
                form_info_label.setText("");
            }
            if (!password_passwordfield.getText().isEmpty()) {
                if (!password_passwordfield.getText().equals(password_confirm_passwordfield.getText())) {
                          form_info_label.setStyle("-fx-text-fill: black;");
                    form_info_label.setText("Įvesti slaptažodžiai turi sutapti.");
                    return;
                } else if (password_passwordfield.getText().equals(password_confirm_passwordfield.getText())) {
                    form_info_label.setText("");
                }
            }
        });
        password_confirm_passwordfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!password_confirm_passwordfield.getText().isEmpty()) {
                if (!password_passwordfield.getText().equals(password_confirm_passwordfield.getText())) {
                    form_info_label.setStyle("-fx-text-fill: black;");
                    form_info_label.setText("Įvesti slaptažodžiai turi sutapti.");
                    return;
                } else if (password_passwordfield.getText().equals(password_confirm_passwordfield.getText())) {
                    form_info_label.setText("");
                    return;
                }
            }
        });
    }


    public void registerToDataBase() {

        //Create list of user where puts all text form text field
        User user = new User(
                first_name_textfield.getText(),
                last_name_textfield.getText(),
                email_textfield.getText(),
                company_name_textfield.getText(),


                //encrypting user password then calling function hasPassword
                hashPassword(password_passwordfield.getText())
        );
        //Inserting list to database
        UserDAO.insert(user);
        //back to login screen
        goToLogin();
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
}
