package es.cheste.ejemplogridpane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Ejemplo GridPane");

        GridPane panel = new GridPane();
        panel.setAlignment(Pos.CENTER);
        panel.setHgap(5);
        panel.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // Crear un nuevo botón para cada celda
                Button miBoton = new Button("Botón " + (i * 3 + j + 1));
                panel.add(miBoton, j, i);
            }
        }

        Scene scene = new Scene(panel, 320, 240);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}