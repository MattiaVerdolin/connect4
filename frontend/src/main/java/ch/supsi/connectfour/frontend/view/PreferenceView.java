package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.application.PreferencesController;
import ch.supsi.connectfour.backend.application.TranslationController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class PreferenceView implements PreferenceViewInterface{
    private static PreferenceView myself;

    private final TranslationController translationController = TranslationController.getInstance();
    private final PreferencesController preferenceService = PreferencesController.getInstance();

    public static PreferenceView getInstance() {
        if (myself == null) {
            myself = new PreferenceView();
        }

        return myself;
    }

    @Override
    public void preferenceWindow() {
        Stage preferencesStage = new Stage();
        preferencesStage.initModality(Modality.APPLICATION_MODAL);
        preferencesStage.setTitle(translationController.translate("preferencesTitle"));

        // Player 1
        Label player1NameLabel = new Label(translationController.translate("name1"));
        TextField player1NameField = new TextField();

        Label player1ColorLabel = new Label(translationController.translate("color1"));
        ComboBox<String> player1ColorComboBox = new ComboBox<>();
        player1ColorComboBox.setItems(FXCollections.observableArrayList(translationController.translate("yellow")
                , translationController.translate("red")));

        Label player1SymbolLabel = new Label(translationController.translate("symbol1"));
        TextField player1SymbolField = new TextField();

        // Player 2
        Label player2NameLabel = new Label(translationController.translate("name2"));
        TextField player2NameField = new TextField();

        Label player2ColorLabel = new Label(translationController.translate("color2"));
        ComboBox<String> player2ColorComboBox = new ComboBox<>();
        player2ColorComboBox.setItems(FXCollections.observableArrayList(translationController.translate("yellow")
                , translationController.translate("red")));

        Label player2SymbolLabel = new Label(translationController.translate("symbol2"));
        TextField player2SymbolField = new TextField();

        // Language Selection
        Label languageLabel = new Label(translationController.translate("language"));
        ComboBox<String> languageComboBox = new ComboBox<>();
        languageComboBox.setItems(FXCollections.observableArrayList("Italiano", "English"));

        // Save Button
        Button saveButton = new Button("ok");

        // Enable and disable the save button based on color conflict
        player1ColorComboBox.setOnAction(e -> {
            saveButton.setDisable(preferenceService.checkConflicts(player1ColorComboBox.getValue(), player2ColorComboBox.getValue(),player1SymbolField.getText(), player2SymbolField.getText()));
        });
        player2ColorComboBox.setOnAction(e -> {
            saveButton.setDisable(preferenceService.checkConflicts(player1ColorComboBox.getValue(), player2ColorComboBox.getValue(),player1SymbolField.getText(), player2SymbolField.getText()));
        });

        player1SymbolField.textProperty().addListener((observable, oldValue, newValue) -> saveButton.setDisable(preferenceService.checkConflicts(player1ColorComboBox.getValue(), player2ColorComboBox.getValue(),player1SymbolField.getText(), player2SymbolField.getText())));
        player2SymbolField.textProperty().addListener((observable, oldValue, newValue) -> saveButton.setDisable(preferenceService.checkConflicts(player1ColorComboBox.getValue(), player2ColorComboBox.getValue(),player1SymbolField.getText(), player2SymbolField.getText())));


        saveButton.setOnAction(e -> {
            Map<String, String> preferencesMap = new HashMap<>();
            // Aggiungi i campi non vuoti/non nulli alla mappa
            if (!player1NameField.getText().isEmpty()) {
                preferencesMap.put("player1.name", player1NameField.getText());
            }
            if (!player1SymbolField.getText().isEmpty()) {
                preferencesMap.put("player1.symbol", player1SymbolField.getText());
            }
            if (player1ColorComboBox.getValue() != null) {
                preferencesMap.put("player1.color", player1ColorComboBox.getValue());
            }
            if (!player2NameField.getText().isEmpty()) {
                preferencesMap.put("player2.name", player2NameField.getText());
            }
            if (!player2SymbolField.getText().isEmpty()) {
                preferencesMap.put("player2.symbol", player2SymbolField.getText());
            }
            if (player2ColorComboBox.getValue() != null) {
                preferencesMap.put("player2.color", player2ColorComboBox.getValue());
            }
            if (languageComboBox.getValue() != null) {
                String tag;
                switch (languageComboBox.getValue().toUpperCase()) {
                    case "ENGLISH" -> tag = "en-US";
                    case "ITALIANO" -> tag = "it-IT";
                    default -> tag = "it-IT";
                }
                preferencesMap.put("language-tag", tag);
            }

            if (preferencesMap.isEmpty()) {
                System.err.println("Nessun dato valido inserito");
            } else {
                preferenceService.dataTransfer(preferencesMap); // Passa i dati inseriti al controller che poi li passerà al model che a sua volta aggiorna il data access
            }

            preferencesStage.close();
        });

        Button cancelButton = new Button(translationController.translate("cancel"));
        cancelButton.setOnAction(e -> preferencesStage.close());

        // Layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add Player 1 components to the grid
        gridPane.add(player1NameLabel, 0, 0);
        gridPane.add(player1NameField, 0, 1);
        gridPane.add(player1SymbolLabel, 0, 2);
        gridPane.add(player1SymbolField, 0, 3);
        gridPane.add(player1ColorLabel, 0, 4);
        gridPane.add(player1ColorComboBox, 0, 5);

        // Add Player 2 components to the grid
        gridPane.add(player2NameLabel, 2, 0);
        gridPane.add(player2NameField, 2, 1);
        gridPane.add(player2SymbolLabel, 2, 2);
        gridPane.add(player2SymbolField, 2, 3);
        gridPane.add(player2ColorLabel, 2, 4);
        gridPane.add(player2ColorComboBox, 2, 5);

        // Add dividing line
        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        gridPane.add(separator, 1, 0, 1, 6);

        // Add language selection
        gridPane.add(languageLabel, 0, 6);
        gridPane.add(languageComboBox, 0, 7, 3, 1);

        // Add save and cancel buttons
        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setAlignment(Pos.CENTER);
        gridPane.add(buttonBox, 0, 8, 3, 1);

        Scene scene = new Scene(gridPane, 350, 300);
        preferencesStage.setScene(scene);
        preferencesStage.showAndWait();
    }
}
