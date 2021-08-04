package sample.JPA;


import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import javafx.stage.Popup;
import org.apache.xmlbeans.impl.jam.JParameter;
import sample.controller.LoginController;
import sample.utils.Constants;
import sun.security.util.DisabledAlgorithmConstraints;
import javafx.scene.text.Text;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.lang.management.PlatformManagedObject;
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
                    showPopupWindow("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Tel. nr.: " + Constants.CONTACT_PHONE_NUMBER + "\n- El. paštu: " + Constants.CONTACT_EMAIL +  "\n- Programos versija: " + Constants.PROGRAM_VERSION + "\n- Klaidos kodas: " + e, "#b02a37", "#FFFFFF", getScene(), 500, 200);
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


    public static void showPopupWindow(String title, String information, String titleBackroundColor, String titleTextColor, Scene scene, int width, int height) {


        //   Window parent = scene.getWindow();
        //  javafx.stage.Popup popup = new Popup();


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
/*
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-effect: dropshadow(two-pass-box, #000000, 10, 0.0, 1.0, 1.0);");


        HBox hBox1 = new HBox();
        hBox1.setStyle("-fx-background-color: " + titleBackroundColor + ";");
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
        okButton.setText("Atnaujinti ryšį" + "\n");
        okButton.setStyle("-fx-font-size: 14; -fx-background-radius: 0; -fx-background-color: #0078D7, linear-gradient(#E1e1e1, #E1E1E1);");
        okButton.setMinSize(82, 28);
        okButton.setAlignment(Pos.CENTER);
        okButton.setOnAction(event -> {
            popup.hide();
            Platform.exit();
        });

        hBox31.getChildren().add(okButton);
        hBox3.getChildren().add(hBox31);

        root.getChildren().add(hBox1);
        root.getChildren().add(hBox2);
        root.getChildren().add(hBox3);

        popup.getContent().addAll(root);
        popup.show(parent);


 */
    }

    public static void exit() {
        Platform.exit();
        System.exit(0);
    }

    public static void showPopupWindowAndClose(String title, String information, String titleBackroundColor, String titleTextColor, Scene scene) {

        Window parent = scene.getWindow();
        javafx.stage.Popup popup = new Popup();

        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-border-width: 1; -fx-border-color: #000000; -fx-effect: dropshadow(two-pass-box, #000000, 10, 0.0, 1.0, 1.0);");


        HBox hBox1 = new HBox();
        hBox1.setStyle("-fx-background-color: " + titleBackroundColor + ";");
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
            Platform.exit();
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

