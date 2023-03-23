module com.example.advancedjavavocabulary {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.advancedjavavocabulary to javafx.fxml;
    exports com.example.advancedjavavocabulary;
}