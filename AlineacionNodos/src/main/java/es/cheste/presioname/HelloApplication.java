package es.cheste.presioname;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // crear y configurar un campo de texto para entrada
        TextField presionaTextField = new TextField();
        presionaTextField.setMaxWidth(250);

        // crear y configurar una etiqueta para mostrar la salida
        Label presionaLabel= new Label();
        presionaLabel.setTextFill(Color.RED);
        presionaLabel.setFont(Font.font("Arial", 20));

        // crear y configurar un botón para cambiar etiqueta
        Button presionaButton = new Button();
        presionaButton.setText(
                "Ingresa algo en la caja y luego presióname");
        presionaButton.setOnAction(
                e -> presionaLabel.setText(
                        "Ingresó: " + presionaTextField.getText()));

        // crear y configurar un VBox para guardar los componentes
        VBox raiz = new VBox();
        raiz.setSpacing(10);
        raiz.setAlignment(Pos.CENTER);

        // agregar los componentes al VBox
        raiz.getChildren().addAll(
                presionaTextField, presionaButton, presionaLabel);

        // crear una escena nueva
        Scene scene = new Scene(raiz, 350, 150);

        // agregar escena; configurar y hacerlo visible escenario
        stage.setScene(scene);
        stage.setTitle("Presióname");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}