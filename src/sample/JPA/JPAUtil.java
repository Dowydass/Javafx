package sample.JPA;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;
import sample.utils.Constants;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Optional;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "PERSISTENCE";
    private static EntityManagerFactory factory = null;
    public static Scene loginScene;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            try {
                factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
            } catch (RuntimeException e) {
                System.out.println("JPAUtil RuntimeException ");
                Platform.runLater(() -> {
                    showErrorPopupWindowAndClose("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Tel. nr.: " + Constants.CONTACT_PHONE_NUMBER + "\n- El. paštu: " + Constants.CONTACT_EMAIL +  "\n- Programos versija: " + Constants.PROGRAM_VERSION + "\n- Klaidos kodas: " + e, 500, 200, Constants.BUTTON_NAME_OK);
                });
            }
        }
        return factory;
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
        }
    }

    public static void setScene(Scene scene) {
        loginScene = scene;
    }

    public static Scene getScene() {
        return loginScene;
    }


    public static void showWarningPopupWindow(String title, String information, int width, int height, String buttonName) {

        Alert dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        ButtonType buttonTypeOne = new ButtonType(buttonName);
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();

    }


    public static void showInfoPopupWindow(String title, String information, int width, int height, String buttonName) {

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();


        ButtonType buttonTypeOne = new ButtonType(buttonName);
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    public static void showErrorPopupWindowAndClose(String title, String information, int width, int height, String buttonName) {

        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        ButtonType buttonTypeOne = new ButtonType(buttonName);
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == buttonTypeOne) {
            Platform.exit();
            System.exit(0);
        }


    }
}

