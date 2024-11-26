package es.cheste.ejemploborderpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Ejemplo BorderPane");

        Group raiz = new Group();
        raiz.setAutoSizeChildren(true);

        BorderPane pane = new BorderPane();

        Button botonTop = new Button("Top");
        pane.setTop(botonTop);
        Button botonLeft = new Button("Left");
        pane.setLeft(botonLeft);
        Button botonCenter = new Button("Center");
        pane.setCenter(botonCenter);
        Button botonRight = new Button("Right");
        pane.setRight(botonRight);
        Button botonBottom = new Button("Bottom");
        pane.setBottom(botonBottom);

        raiz.getChildren().add(pane);

        Scene scene = new Scene(raiz, 320, 240);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}