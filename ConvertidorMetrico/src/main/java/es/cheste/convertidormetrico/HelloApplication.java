package es.cheste.convertidormetrico;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class HelloApplication extends Application {
    static final String txtDe = " --=> ";
    static final String txtIz = " <=-- ";

    @Override
    public void start(Stage stage) throws IOException {
        DecimalFormat df = new DecimalFormat("0.0#");

        // componentes para convertir entre pulgadas y centímetros
            // crear campos de entrada y etiquetas de unidades
            TextField textoCm = new TextField();
            Label etiquetaCm = new Label("cm");
            TextField textoPlg = new TextField();
            Label etiquetaPlg = new Label("in");

            // crear botones para realizar cálculos
            Button botonCmPlg = new Button(txtDe);
            Button botonPlgCm = new Button(txtIz);

            // crear un VBox para guardar los botones
            VBox botonesPlgCm = new VBox();
            botonesPlgCm.getChildren().addAll(botonCmPlg, botonPlgCm);

            // crear un HBox para guardar los elementos del 1er renglón
            HBox panelPlgCm = new HBox(10); // contenedor compuesto
            panelPlgCm.getChildren().addAll(textoCm, etiquetaCm, botonesPlgCm, textoPlg, etiquetaPlg);
            panelPlgCm.setAlignment(Pos.CENTER);

        // componentes para convertir entre millas y kilómetros
            // crear campos de entrada y etiquetas de unidades
            TextField textoKm = new TextField();
            Label etiquetaKm = new Label("km");
            TextField textoMi = new TextField();
            Label etiquetaMi = new Label("mi");

            // crear botones para realizar cálculos
            Button botonKmMi = new Button(txtDe);
            Button botonMiKm = new Button(txtIz);

            // crear un VBox para guardar los botones
            VBox botonesMiKm = new VBox();
            botonesMiKm.getChildren().addAll(botonKmMi, botonMiKm);

            // crear un HBox para guardar los elementos del 2do renglón
            HBox panelMiKm = new HBox(10); // contenedor compuesto
            panelMiKm.getChildren().addAll(textoKm, etiquetaKm, botonesMiKm, textoMi, etiquetaMi);
            panelMiKm.setAlignment(Pos.CENTER);

        // componentes para convertir entre libras y kilogramos
            // crear campos de entrada y etiquetas de unidades
            TextField textoKg = new TextField();
            Label etiquetaKg = new Label("kg");
            TextField textoLb = new TextField();
            Label etiquetaLb = new Label("lb");

            // crear botones para realizar cálculos
            Button botonKgLb = new Button(txtDe);
            Button botonLbKg = new Button(txtIz);

            // crear un VBox para guardar los botones
            VBox botonesLbKg = new VBox();
            botonesLbKg.getChildren().addAll(botonKgLb, botonLbKg);

            // crear un HBox para guardar los elementos del 3er renglón
            HBox panelLbKg = new HBox(10); // contenedor compuesto
            panelLbKg.getChildren().addAll(textoKg, etiquetaKg, botonesLbKg, textoLb, etiquetaLb);
            panelLbKg.setAlignment(Pos.CENTER);

        // crear un VBox para guardar los 3 renglones
        VBox raiz = new VBox(10);
        raiz.getChildren().addAll(panelPlgCm, panelMiKm, panelLbKg);
        raiz.setAlignment(Pos.CENTER);

        // escribir el código para los botones
        botonCmPlg.setOnAction(
                e -> {
                    String s = new String(textoCm.getText());
                    double d = Double.parseDouble(s);
                    d = d / 2.54;
                    s = df.format(d);
                    textoPlg.setText(s);
                }
        );

        botonPlgCm.setOnAction(
                e -> {
                    String s = new String(textoPlg.getText());
                    double d = Double.parseDouble(s);
                    d = d * 2.54;
                    s = df.format(d);
                    textoCm.setText(s);
                }
        );

        botonKmMi.setOnAction(
                e -> {
                    String s = new String(textoKm.getText());
                    double d = Double.parseDouble(s);
                    d = d / 1.609;
                    s = df.format(d);
                    textoMi.setText(s);
                }
        );

        botonMiKm.setOnAction(
                e -> {
                    String s = new String(textoMi.getText());
                    double d = Double.parseDouble(s);
                    d = d * 1.609;
                    s = df.format(d);
                    textoKm.setText(s);
                }
        );

        botonKgLb.setOnAction(
                e -> {
                    String s = new String(textoKg.getText());
                    double d = Double.parseDouble(s);
                    d = d * 2.20462;
                    s = df.format(d);
                    textoLb.setText(s);
                }
        );

        botonLbKg.setOnAction(
                e -> {
                    String s = new String(textoLb.getText());
                    double d = Double.parseDouble(s);
                    d = d / 2.20462;
                    s = df.format(d);
                    textoKg.setText(s);
                }
        );

        // crear la escena
        Scene scene = new Scene(raiz);

        // agregar la escena al escenario, configurar y hacer visible
        stage.setScene(scene);
        stage.setTitle("Convertidor Métrico");
        stage.setWidth(550);
        stage.setHeight(250);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}