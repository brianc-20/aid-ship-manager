package edu.utsa.cs3443.sfn402_lab3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Launches the Aid Ship Management System and loads its main screen.
 *
 * @author Brian Caloca
 */
public class AidShipApplication extends Application {
    /**
     * Creates and displays the application's main window.
     *
     * @param stage the main application window
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AidShipApplication.class.getResource("layouts/main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 650);
        stage.setTitle("Aid Ship Management System");
        stage.setScene(scene);
        stage.show();
    }
}


