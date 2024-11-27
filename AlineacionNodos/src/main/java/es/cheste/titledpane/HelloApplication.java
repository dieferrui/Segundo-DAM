package es.cheste.titledpane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Titled Panes");

        HBox raiz = new HBox(10);

        VBox box1 = new VBox();
        box1.setMinSize(100, 75);
        TitledPane tp1 = new TitledPane("Pane 1", box1);
        tp1.setCollapsible(false);

        VBox box2 = new VBox();
        box2.setMinSize(100, 75);
        TitledPane tp2 = new TitledPane("Pane 2", box2);
        tp2.setCollapsible(true);

        raiz.getChildren().addAll(tp1, tp2);
        raiz.setAlignment(Pos.CENTER);
        raiz.setPadding(new Insets(10));

        Scene scene = new Scene(raiz, 300, 120);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}