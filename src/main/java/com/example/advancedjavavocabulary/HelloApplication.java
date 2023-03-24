package com.example.advancedjavavocabulary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advancedVocabulary.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 563, 434);
        stage.setTitle("Vocabulary");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> Platform.exit());


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}