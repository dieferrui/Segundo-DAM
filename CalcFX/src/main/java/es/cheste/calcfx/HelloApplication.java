package es.cheste.calcfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;

public class HelloApplication extends Application {

    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.setOnMousePressed(event -> {
            stage.setUserData(new double[] { event.getScreenX(), event.getScreenY() });
        });

        scene.setOnMouseDragged(event -> {
            double[] offset = (double[]) stage.getUserData();
            double deltaX = event.getScreenX() - offset[0];
            double deltaY = event.getScreenY() - offset[1];
            stage.setX(stage.getX() + deltaX);
            stage.setY(stage.getY() + deltaY);

            stage.setUserData(new double[] { event.getScreenX(), event.getScreenY() });
        });

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);

        stage.setWidth(400);
        stage.setHeight(600);

        stage.show();
    }

    private void handleMousePressed(MouseEvent event) {
        xOffset = event.getScreenX() - event.getSceneX();
        yOffset = event.getScreenY() - event.getSceneY();
    }

    private void handleMouseDragged(MouseEvent event) {
        Stage stage = (Stage) event.getSource();
        stage.setX(event.getScreenX() - xOffset);
        stage.setY(event.getScreenY() - yOffset);
    }

    public static void main(String[] args) {
        launch();
    }
}
