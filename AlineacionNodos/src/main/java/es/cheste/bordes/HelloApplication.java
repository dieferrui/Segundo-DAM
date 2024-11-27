package es.cheste.bordes;

import javafx.application.Application;
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
        stage.setTitle("Bordes");

        VBox raiz = new VBox(10);

        FlowPane fp1 = new FlowPane();
        fp1.setHgap(10);
        FlowPane fp2 = new FlowPane();
        fp2.setHgap(10);

        Button boton1 = new Button("Color: BLACK\nBorderStrokeStyle: SOLID\nCornerRadii: 0\nBorderWidths: 6");
        BorderStroke bs1 = new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(6));
        boton1.setBorder(new Border(bs1));
        boton1.setAlignment(Pos.CENTER);

        Button boton2 = new Button("Color: RED\nBorderStrokeStyle: DOTTED\nCornerRadii: 0\nBorderWidths: 2");
        BorderStroke bs2 = new BorderStroke(Color.RED, BorderStrokeStyle.DOTTED, new CornerRadii(0), new BorderWidths(2));
        boton2.setBorder(new Border(bs2));
        boton2.setAlignment(Pos.CENTER);

        Button boton3 = new Button("Color: GREEN\nBorderStrokeStyle: DASHED\nCornerRadii: 20\nBorderWidths: 1");
        BorderStroke bs3 = new BorderStroke(Color.GREEN, BorderStrokeStyle.DASHED, new CornerRadii(20), new BorderWidths(1));
        boton3.setBorder(new Border(bs3));
        boton3.setAlignment(Pos.CENTER);

        Button boton4 = new Button("Color: BLUE\nBorderStrokeStyle: SOLID\nCornerRadii: 10\nBorderWidths: 1");
        BorderStroke bs4 = new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1));
        boton4.setBorder(new Border(bs4));
        boton4.setAlignment(Pos.CENTER);

        Button boton5 = new Button("Color: DARKVIOLET\nBorderStrokeStyle: DOTTED\nCornerRadii: 0\nBorderWidths: 6");
        BorderStroke bs5 = new BorderStroke(Color.DARKVIOLET, BorderStrokeStyle.DOTTED, new CornerRadii(0), new BorderWidths(6));
        boton5.setBorder(new Border(bs5));
        boton5.setAlignment(Pos.CENTER);

        Button boton6 = new Button("Color: ORANGE\nBorderStrokeStyle: DASHED\nCornerRadii: 0\nBorderWidths: 1");
        BorderStroke bs6 = new BorderStroke(Color.ORANGE, BorderStrokeStyle.DASHED, new CornerRadii(0), new BorderWidths(1));
        boton6.setBorder(new Border(bs6));
        boton6.setAlignment(Pos.CENTER);

        fp1.getChildren().addAll(boton1, boton2, boton3);
        fp2.getChildren().addAll(boton4, boton5, boton6);

        raiz.getChildren().addAll(fp1, fp2);

        Scene scene = new Scene(raiz, 600, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}