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
                    showWarningPopupWindow("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Tel. nr.: " + Constants.CONTACT_PHONE_NUMBER + "\n- El. paštu: " + Constants.CONTACT_EMAIL +  "\n- Programos versija: " + Constants.PROGRAM_VERSION + "\n- Klaidos kodas: " + e, "#b02a37", "#FFFFFF", getScene(), 500, 200);
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


    public static void showWarningPopupWindow(String title, String information, String titleBackroundColor, String titleTextColor, Scene scene, int width, int height) {

        Alert dialog = new Alert(Alert.AlertType.WARNING);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);
        //FIXME: Remove after release 8u40

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        ButtonType buttonTypeOne = new ButtonType("Atnaujinti ryšį");
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();

    }


    public static void showInfoPopupWindow(String title, String information, String titleBackroundColor, String titleTextColor, Scene scene, int width, int height) {

        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);
        //FIXME: Remove after release 8u40

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        ButtonType buttonTypeOne = new ButtonType("Atnaujinti ryšį");
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();

    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    public static void showErrorPopupWindowAndClose(String title, String information, String titleBackroundColor, String titleTextColor, Scene scene, int width, int height) {

        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setHeaderText(null);
        dialog.setContentText(information);
        dialog.setTitle(title);
        //FIXME: Remove after release 8u40

        dialog.getDialogPane().setPrefSize(width, height);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();



        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
                System.exit(0);
            }
        });
        ButtonType buttonTypeOne = new ButtonType("Atnaujinti ryšį");
        dialog.getButtonTypes().setAll(buttonTypeOne);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == buttonTypeOne) {
            Platform.exit();
        }


    }
}

