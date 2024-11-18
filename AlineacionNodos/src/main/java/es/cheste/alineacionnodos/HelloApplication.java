package es.cheste.alineacionnodos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    static final Border vboxBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(2)));
    static final Background vboxBackground = new Background(new BackgroundFill(Color.LIGHTYELLOW, new CornerRadii(0), new Insets(0)));
    static final double vboxWidth = 220.0;
    static final double vboxHeight = 100.0;

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Alineación de nodos");

        // configurar el primer renglón
        FlowPane renglon1 = new FlowPane();

            // línea 1
            VBox linea11 = new VBox();
            linea11.setAlignment(Pos.TOP_LEFT);
            linea11.setPrefWidth(vboxWidth);
            linea11.setPrefHeight(vboxHeight);
            linea11.setBorder(vboxBorder);
            linea11.setBackground(vboxBackground);

            Button boton11 = new Button("TOP LEFT");
            linea11.getChildren().add(boton11);

            // línea 2
            VBox linea12 = new VBox();
            linea12.setAlignment(Pos.TOP_CENTER);
            linea12.setPrefWidth(vboxWidth);
            linea12.setPrefHeight(vboxHeight);
            linea12.setBorder(vboxBorder);
            linea12.setBackground(vboxBackground);

            Button boton12 = new Button("TOP CENTER");
            linea12.getChildren().add(boton12);

            // línea 3
            VBox linea13 = new VBox();
            linea13.setAlignment(Pos.TOP_RIGHT);
            linea13.setPrefWidth(vboxWidth);
            linea13.setPrefHeight(vboxHeight);
            linea13.setBorder(vboxBorder);
            linea13.setBackground(vboxBackground);

            Button boton13 = new Button("TOP RIGHT");
            linea13.getChildren().add(boton13);

            // línea 4
            VBox linea14 = new VBox();
            linea14.setAlignment(Pos.BASELINE_LEFT);
            linea14.setPrefWidth(vboxWidth);
            linea14.setPrefHeight(vboxHeight);
            linea14.setBorder(vboxBorder);
            linea14.setBackground(vboxBackground);

            Button boton14 = new Button("BASELINE LEFT (Top Inset 10)");
            VBox.setMargin(boton14, new Insets(10, 0, 0, 0));
            linea14.getChildren().add(boton14);

        // configurar el segundo renglón
        FlowPane renglon2 = new FlowPane();

            // línea 1
            VBox linea21 = new VBox();
            linea21.setAlignment(Pos.CENTER_LEFT);
            linea21.setPrefWidth(vboxWidth);
            linea21.setPrefHeight(vboxHeight);
            linea21.setBorder(vboxBorder);
            linea21.setBackground(vboxBackground);

            Button boton21 = new Button("CENTER LEFT");
            linea21.getChildren().add(boton21);

            // línea 2
            VBox linea22 = new VBox();
            linea22.setAlignment(Pos.CENTER);
            linea22.setPrefWidth(vboxWidth);
            linea22.setPrefHeight(vboxHeight);
            linea22.setBorder(vboxBorder);
            linea22.setBackground(vboxBackground);

            Button boton22 = new Button("CENTER");
            linea22.getChildren().add(boton22);

            // línea 3
            VBox linea23 = new VBox();
            linea23.setAlignment(Pos.CENTER_RIGHT);
            linea23.setPrefWidth(vboxWidth);
            linea23.setPrefHeight(vboxHeight);
            linea23.setBorder(vboxBorder);
            linea23.setBackground(vboxBackground);

            Button boton23 = new Button("CENTER RIGHT");
            linea23.getChildren().add(boton23);

            // línea 4
            VBox linea24 = new VBox();
            linea24.setAlignment(Pos.BASELINE_CENTER);
            linea24.setPrefWidth(vboxWidth);
            linea24.setPrefHeight(vboxHeight);
            linea24.setBorder(vboxBorder);
            linea24.setBackground(vboxBackground);

            Button boton24 = new Button("BASELINE CENTER(Top Inset 20)");
            VBox.setMargin(boton24, new Insets(20, 0, 0, 0));
            linea24.getChildren().add(boton24);

        // configurar el tercer renglón
        FlowPane renglon3 = new FlowPane();

            // línea 1
            VBox linea31 = new VBox();
            linea31.setAlignment(Pos.BOTTOM_LEFT);
            linea31.setPrefWidth(vboxWidth);
            linea31.setPrefHeight(vboxHeight);
            linea31.setBorder(vboxBorder);
            linea31.setBackground(vboxBackground);

            Button boton31 = new Button("BOTTOM LEFT");
            linea31.getChildren().add(boton31);

            // línea 2
            VBox linea32 = new VBox();
            linea32.setAlignment(Pos.BOTTOM_CENTER);
            linea32.setPrefWidth(vboxWidth);
            linea32.setPrefHeight(vboxHeight);
            linea32.setBorder(vboxBorder);
            linea32.setBackground(vboxBackground);

            Button boton32 = new Button("BOTTOM CENTER");
            linea32.getChildren().add(boton32);

            // línea 3
            VBox linea33 = new VBox();
            linea33.setAlignment(Pos.BOTTOM_RIGHT);
            linea33.setPrefWidth(vboxWidth);
            linea33.setPrefHeight(vboxHeight);
            linea33.setBorder(vboxBorder);
            linea33.setBackground(vboxBackground);

            Button boton33 = new Button("BOTTOM RIGHT");
            linea33.getChildren().add(boton33);

            // línea 4
            VBox linea34 = new VBox();
            linea34.setAlignment(Pos.BASELINE_RIGHT);
            linea34.setPrefWidth(vboxWidth);
            linea34.setPrefHeight(vboxHeight);
            linea34.setBorder(vboxBorder);
            linea34.setBackground(vboxBackground);

            Button boton34 = new Button("BASELINE RIGHT(Top Inset 30)");
            VBox.setMargin(boton34, new Insets(30, 0, 0, 0));
            linea34.getChildren().add(boton34);

        // establecer márgenes entre los renglones
        VBox.setMargin(renglon1, new Insets(10, 0, 10, 0));
        VBox.setMargin(renglon2, new Insets(10, 0, 10, 0));

        // configurar escena
        renglon1.getChildren().addAll(linea11, linea12, linea13, linea14);
        renglon2.getChildren().addAll(linea21, linea22, linea23, linea24);
        renglon3.getChildren().addAll(linea31, linea32, linea33, linea34);
        renglon1.setHgap(10);
        renglon2.setHgap(10);
        renglon3.setHgap(10);

        VBox raiz = new VBox(renglon1, renglon2, renglon3);
        VBox.setMargin(renglon1, new Insets(5));
        VBox.setMargin(renglon2, new Insets(5));
        VBox.setMargin(renglon3, new Insets(5));

        Scene scene = new Scene(raiz, 930, 340);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}