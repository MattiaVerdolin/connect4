package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.application.TranslationController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpView implements HelpViewInterface {

    private final TranslationController translationController = TranslationController.getInstance();

    private static HelpView myself;

    public static HelpView getInstance() {
        if (myself == null) {
            myself = new HelpView();
        }

        return myself;
    }

    @Override
    public void helpWindow(String rules) {
        Stage helpStage = new Stage();
        helpStage.initModality(Modality.APPLICATION_MODAL);
        helpStage.setTitle(translationController.translate("windowTitle"));

        Label titleLabel = new Label(translationController.translate("helpTitle"));
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label rulesLabel = new Label(rules);
        rulesLabel.setWrapText(true); // Enable text wrapping

        ScrollPane scrollPane = new ScrollPane(rulesLabel);
        scrollPane.setFitToWidth(true); // Ensure the text wraps to fit the width of the window

        Button closeButton = new Button(translationController.translate("ruleClose"));
        closeButton.setOnAction(e -> helpStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(titleLabel, scrollPane, closeButton);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Scene scene = new Scene(layout, 500, 300);
        helpStage.setScene(scene);
        helpStage.showAndWait();
    }
}
