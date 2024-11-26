package es.cheste.cara;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.Group;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        // Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // crear y configurar el círculo principal (cara)
        Circle cara = new Circle(125, 125, 80);
        cara.setFill(Color.PINK);
        cara.setStroke(Color.RED);

        // crear y configurar el círculo para ojo derecho
        Circle ojoDerecho = new Circle(86, 100, 10);
        ojoDerecho.setFill(Color.WHITE);
        ojoDerecho.setStroke(Color.RED);

        // crear y configurar el círculo para ojo izquierdo
        Circle ojoIzquierdo = new Circle(162, 100, 10);
        ojoIzquierdo.setFill(Color.WHITE);
        ojoIzquierdo.setStroke(Color.RED);

        // crear y configurar una boca sonriente
        Arc boca = new Arc(125, 145, 45, 35, 0, -180);
        boca.setFill(Color.PINK);
        boca.setStroke(Color.RED);
        boca.setType(ArcType.OPEN);

        // crear y configurar el texto
        Text leyenda = new Text(65, 240, "Cara cambiante");
        leyenda.setFill(Color.BLUE);
        leyenda.setFill(Color.BLUE);
        leyenda.setFont(Font.font("Verdana", 15));

        // crear un grupo que tenga todos los componentes
        Group grupo = new Group(
                cara, ojoDerecho, ojoIzquierdo, boca, leyenda);

        // crear un botón que hará la cara sonriente
        Button botonSonreir = new Button("Sonreir");

        // crear un botón que hará la cara apenada
        Button botonApenar = new Button("Apenar");

        // crear y configurar contenedor horizontal para botones
        HBox cajaBotones = new HBox(10);
        cajaBotones.setAlignment(Pos.CENTER);

        // agregar los botones al contenedor horizontal
        cajaBotones.getChildren().addAll(
                botonSonreir, botonApenar);

        // crear y configurar un contenedor vertical para guardar
        // la caja de botones y el grupo de la cara
        VBox raiz = new VBox(10);
        raiz.setBackground(Background.EMPTY);
        raiz.setAlignment(Pos.CENTER);

        // agregar la caja de botones y el grupo de la cara
        raiz.getChildren().addAll(cajaBotones, grupo);

        // crear y configurar una escena nueva
        Scene escena = new Scene(raiz, 250, 275, Color.YELLOW);

        // proporcionar el código cuando se presiona botón sonreir
        botonSonreir.setOnAction(e -> {
            boca.setLength(-180);
            boca.setCenterY(145);
        });

        // proporcionar el código cuando se presiona botón apenar
        botonApenar.setOnAction(e -> {
            boca.setCenterY(160);
            boca.setLength(180);
        });

        // agregar la escena al escenario y poner título
        stage.setScene(escena);
        stage.setTitle("Cara cambiante");

        // mostrar el escenario
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}