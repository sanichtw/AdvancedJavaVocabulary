package com.example.advancedjavavocabulary;

import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class HelloController {
    public TextField textField;
    public ListView collectionListView;
    public ListView translationListView;
    public HashMap<String, String> collection = new HashMap<>();
    public Button closeButton;


    public void addToCollection() {
        String[] splittedText = textField.getText().split(" ");
        collection.put(splittedText[0], splittedText[1]);

        for (Map.Entry<String, String> entry: collection.entrySet())
            if (entry.getKey().equals(splittedText[0])) {
                collectionListView.getItems().add(entry.getKey());
                translationListView.getItems().add(entry.getValue());
            }

        textField.clear();
    }

    public void onEdit() {
        String selectedIndicesFromCollection = (String) collectionListView.getSelectionModel().getSelectedItem();
        String selectedIndicesFromTranslation = (String) translationListView.getSelectionModel().getSelectedItem();
        String changeableWord;


        if (selectedIndicesFromTranslation != null && textField.getText().isEmpty()) {
            textField.setText(selectedIndicesFromTranslation);
        } else if (!textField.getText().isEmpty() && selectedIndicesFromTranslation != null) {
            Set<Map.Entry<String, String>> entrySet = collection.entrySet();
            changeableWord = selectedIndicesFromTranslation;
            for (Map.Entry<String, String> entry : entrySet) {
                if (entry.getValue().equals(changeableWord)) {
                    collection.replace(entry.getKey(), textField.getText());

                    collectionListView.getItems().clear();
                    translationListView.getItems().clear();

                    collectionListView.getItems().addAll(collection.keySet());
                    Collection<String> translationValues = collection.values();
                    translationListView.getItems().addAll(translationValues);

                    textField.clear();
                    break;
                }
            }
        }


        if (selectedIndicesFromTranslation == null && selectedIndicesFromCollection != null
                && textField.getText().isEmpty()) {
            textField.setText(selectedIndicesFromCollection);
        } else if (!textField.getText().isEmpty() && selectedIndicesFromCollection != null) {
            changeableWord = selectedIndicesFromCollection;

            Set<Map.Entry<String, String>> entrySet = collection.entrySet();
            for (Map.Entry<String, String> entry : entrySet) {
                if (entry.getKey().equals(changeableWord)) {
                    String valueOfKey = entry.getValue();
                    collection.remove(entry.getKey(), entry.getValue());
                    collection.put(textField.getText(), valueOfKey);

                    collectionListView.getItems().clear();
                    translationListView.getItems().clear();

                    collectionListView.getItems().addAll(collection.keySet());
                    Collection<String> translationValues = collection.values();
                    translationListView.getItems().addAll(translationValues);

                    textField.clear();
                    break;
                }
            }
        }
    }


    public void deleteFromCollection() {
        String selectedIndicesFromCollection = (String) collectionListView.getSelectionModel().getSelectedItem();
        String selectedIndicesFromTranslation = (String) translationListView.getSelectionModel().getSelectedItem();


        if (selectedIndicesFromCollection != null) {
            collection.remove(selectedIndicesFromCollection);

            collectionListView.getItems().clear();
            translationListView.getItems().clear();

            collectionListView.getItems().addAll(collection.keySet());
            Collection<String> translationValues = collection.values();
            translationListView.getItems().addAll(translationValues);
        } else if (selectedIndicesFromTranslation != null) {
            Set<Map.Entry<String,String>> entrySet = collection.entrySet();

            for (Map.Entry<String,String> entry : entrySet) {
                if (entry.getValue().equals(selectedIndicesFromTranslation)) {
                    collection.remove(entry.getKey());
                    break;
                }
            }
        }
        collectionListView.getItems().clear();
        translationListView.getItems().clear();
        collectionListView.getItems().addAll(collection.keySet());
        Collection<String> translationValues = collection.values();
        translationListView.getItems().addAll(translationValues);

        translationListView.getSelectionModel().clearSelection();
        collectionListView.getSelectionModel().clearSelection();
    }

    public void closeApplication() {
        try(FileWriter writer = new FileWriter("data.txt", false)) {
            writer.write(String.valueOf(collection));
            writer.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}