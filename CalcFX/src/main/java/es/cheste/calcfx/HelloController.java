package es.cheste.calcfx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TextField display;

    private String operator = "";
    private double firstOperand = 0;

    @FXML
    private void handleNumberInput(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        // Si el display contiene el resultado, borrar antes de añadir el número
        if (display.getText().equals("0") || display.getText().equals("Error")) {
            display.clear();
        }
        display.appendText(button.getText());
    }

    @FXML
    private void handleOperatorInput(javafx.event.ActionEvent event) {
        Button button = (Button) event.getSource();
        // Si el operador ya ha sido presionado, calcular el resultado antes de operar
        if (!display.getText().isEmpty()) {
            firstOperand = Double.parseDouble(display.getText());
        }
        operator = button.getText();
        display.clear();  // Limpiar la pantalla para ingresar el segundo número
    }

    @FXML
    private void handleDot() {
        if (!display.getText().contains(".")) {
            display.appendText(".");
        }
    }

    @FXML
    private void handleEquals() {
        double secondOperand = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+" -> result = firstOperand + secondOperand;
            case "-" -> result = firstOperand - secondOperand;
            case "X" -> result = firstOperand * secondOperand;
            case "%" -> result = firstOperand / secondOperand;
            default -> {
                display.setText("0");
                return;
            }
        }

        String resultStr = String.valueOf(result);
        resultStr = resultStr.replaceAll("0*$", "").replaceAll("\\.$", "");

        if (resultStr.length() > 10) {
            resultStr = resultStr.substring(0, 10);
        }

        display.setText(resultStr);
        operator = "";
    }

    @FXML
    private void handleHideApp() {
        Stage stage = (Stage) display.getScene().getWindow();
        stage.setIconified(true); // Minimiza la ventana
    }

    @FXML
    private void handleCloseApp() {
        Stage stage = (Stage) display.getScene().getWindow();
        stage.close(); // Cierra la ventana
    }
}
