package com.example.wordcounter;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Word Counter");

        TextArea textArea = new TextArea();
        Button countButton = new Button("Count Words");

        countButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String inputText = textArea.getText();
                if (inputText.isEmpty()) {
                    showAlert("Please enter some text.", Alert.AlertType.ERROR);
                    return;
                }

                // Split the input text into words
                String[] words = inputText.split("\\s+|\\p{Punct}");

                // Initialize a word counter
                int wordCount = 0;

                // Create a map to store word frequencies
                Map<String, Integer> wordFrequency = new HashMap<>();

                for (String word : words) {
                    if (!word.isEmpty()) {
                        wordCount++;

                        // Update word frequency
                        wordFrequency.put(word.toLowerCase(), wordFrequency.getOrDefault(word.toLowerCase(), 0) + 1);
                    }
                }

                // Display word count and statistics
                String resultMessage = "Total words: " + wordCount +
                        "\nUnique words: " + wordFrequency.size() +
                        "\nWord frequency: " + wordFrequency;
                showAlert(resultMessage, Alert.AlertType.INFORMATION);
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(textArea);
        root.setBottom(countButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Word Counter");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
