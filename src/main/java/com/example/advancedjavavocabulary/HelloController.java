package com.example.advancedjavavocabulary;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.*;

public class HelloController {
    public TextField textField;
    public ListView collectionListView;
    public ListView translationListView;
    public Button addButton;
    public Button editButton;
    public Button deleteButton;
    public Button closeButton;
    public HashMap<String, String> collection = new HashMap<>();

    public void addToCollection(ActionEvent actionEvent) {
        System.out.println("1 " + textField.getText());
        String[] splittedText = textField.getText().split(" ");
        System.out.println("2 " + Arrays.asList(splittedText));
        System.out.println("3 " + collection);

        collection.put(splittedText[0], splittedText[1]); // word слово
        System.out.println("4 " + collection);
        collectionListView.getItems().add(collection);
//        Collection<String> wordValues = collection.values();
//        translationListView.getItems().add();

    }
}