package es.cheste.ejemplostackpane;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Roundels");

        // StackPanel para el roundel
        StackPane panel = new StackPane();
        panel.setAlignment(Pos.CENTER);
        panel.setBackground(Background.EMPTY);

        Circle bigCircle = new Circle(90, Color.RED);
        Circle mediumCircle = new Circle(60, Color.WHITE);
        Circle smallCircle = new Circle(30, Color.BLUE);

        panel.getChildren().addAll(bigCircle, mediumCircle, smallCircle);

        // HBox para botones
        HBox buttons = new HBox(10);
        buttons.setAlignment(Pos.CENTER);

        Button botonFR = new Button("France");
        Button botonBL = new Button("Belgium");
        Button botonIT = new Button("Italy");
        Button botonES = new Button("Spain");

        buttons.getChildren().addAll(botonFR, botonBL, botonIT, botonES);

        // VBox raíz
        VBox raiz = new VBox(20);
        raiz.setBackground(Background.EMPTY);
        raiz.setAlignment(Pos.CENTER);

        raiz.getChildren().addAll(panel, buttons);

        // Funcionalidad botones
        botonFR.setOnAction(e -> {
            bigCircle.setFill(Color.RED);
            mediumCircle.setFill(Color.WHITE);
            smallCircle.setFill(Color.BLUE);
        });

        botonBL.setOnAction(e -> {
            bigCircle.setFill(Color.RED);
            mediumCircle.setFill(Color.YELLOW);
            smallCircle.setFill(Color.BLACK);
        });

        botonIT.setOnAction(e -> {
            bigCircle.setFill(Color.RED);
            mediumCircle.setFill(Color.WHITE);
            smallCircle.setFill(Color.GREEN);
        });

        botonES.setOnAction(e -> {
            bigCircle.setFill(Color.RED);
            mediumCircle.setFill(Color.YELLOW);
            smallCircle.setFill(Color.DARKVIOLET);
        });

        // configurar escena
        Scene scene = new Scene(raiz, 500, 320, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}