package es.cheste.fuentes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Text text = new Text("Verdana, Bold, Italic, Size 12");
        Font ft1 = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 12);
        text.setFont(ft1);

        Text text2 = new Text("Times New Roman, Normal, Regular, Size 18");
        Font ft2 = Font.font("Times New Roman", FontWeight.NORMAL, FontPosture.REGULAR, 18);
        text2.setFont(ft2);

        Text text3 = new Text("Courier New, Normal, Regular, Size 16");
        Font ft3 = Font.font("Courier New", FontWeight.NORMAL, FontPosture.REGULAR, 16);
        text3.setFont(ft3);

        Text text4 = new Text("Lucida Bright, Bold, Regular, Size 22");
        Font ft4 = Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 22);
        text4.setFont(ft4);

        Text text5 = new Text("Calibri, Underlined, Size 38");
        Font ft5 = Font.font("Calibri", 38);
        text5.setUnderline(true);
        text5.setFont(ft5);

        VBox raiz = new VBox(10);
        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(text, text2, text3, text4, text5);

        // crear una escena nueva
        Scene scene = new Scene(raiz, 500, 220);

        // agregar escena; configurar y hacerlo visible escenario
        stage.setScene(scene);
        stage.setTitle("Probador de fuentes");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}