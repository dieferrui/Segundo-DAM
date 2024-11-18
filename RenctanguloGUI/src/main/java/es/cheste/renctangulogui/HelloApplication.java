package es.cheste.renctangulogui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class HelloApplication extends Application {
    // crear un objeto de la clase Rectangulo como un atributo
    private Rectangle probarRect = new Rectangle(0, 0);

    @Override
    public void start(Stage stage) throws IOException {
        // crear y configurar campos de texto para entrada
        TextField campoBase = new TextField();
        campoBase.setMaxWidth(50);

        TextField campoAltura = new TextField();
        campoAltura.setMaxWidth(50);

        // crear y configurar área de texto, no editable,
        // para mostrar resultados.
        TextArea pantalla = new TextArea();
        pantalla.setEditable(false);
        pantalla.setMinSize(210,50);
        pantalla.setMaxSize(210,50);

        // crear y configurar Labels para los campos de texto
        Label etiquetaBase = new Label("Base");
        etiquetaBase.setTextFill(Color.RED);
        etiquetaBase.setFont(Font.font("Arial", 20));

        Label etiquetaAltura= new Label("Altura");
        etiquetaAltura.setTextFill(Color.RED);
        etiquetaAltura.setFont(Font.font("Arial", 20));

        // crear y configurar Button para realizar cálculos
        Button botonCalcular = new Button();
        botonCalcular.setText("Calcular");
        botonCalcular.setOnAction( e -> {
            // revisar que los campos no estén vacíos
            if(campoBase.getText().isEmpty() || campoAltura.getText().isEmpty()) {
                pantalla.setText("Se debe ingresar base y altura");
            } else {
                // convertir entradas a double y poner
                // poner base y altura Rectangulo
                probarRect.setWidth(Double.parseDouble(campoBase.getText()));
                probarRect.setHeight(Double.parseDouble(campoAltura.getText()));
                // usar métodos de Rectangulo para área y perímetro
                pantalla.setText("El área es: " + calcularArea(probarRect)
                        + "\n" + "El perímetro es: "
                        + calcularPerimetro(probarRect));
            }
        });

        // crear y configurar un HBox para etiquetas y entradas
        HBox componentesEntrada = new HBox(10);
        componentesEntrada.setAlignment(Pos.CENTER);
        componentesEntrada.getChildren().addAll(etiquetaBase, campoBase, etiquetaAltura, campoAltura);

        // crear y configurar contenedor vertical para componentes
        VBox raiz = new VBox(25);
        raiz.setAlignment(Pos.CENTER);
        raiz.getChildren().addAll(componentesEntrada, botonCalcular, pantalla);

        // crear una escena nueva y agregarla al escenario
        Scene escena = new Scene(raiz, 350, 250);
        stage.setScene(escena);
        stage.setTitle("Rectangulo GUI");
        stage.show();
    }

    // Función para calcular el área
    private double calcularArea(Rectangle rect) {
        return rect.getWidth() * rect.getHeight();
    }

    // Función para calcular el perímetro
    private double calcularPerimetro(Rectangle rect) {
        return 2 * (rect.getWidth() + rect.getHeight());
    }

    public static void main(String[] args) {
        launch();
    }
}