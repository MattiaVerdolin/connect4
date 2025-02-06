package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.application.TranslationController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AboutView implements AboutModelViewInterface {
    private static AboutView myself;
    private final TranslationController translationController = TranslationController.getInstance();

    public static AboutView getInstance() {
        if (myself == null) {
            myself = new AboutView();
        }

        return myself;
    }

    @Override
    public void aboutWindow(String applicationName, String version, String buildDate, String developers) {
        Stage aboutStage = new Stage();
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setTitle(translationController.translate("about"));

        Label applicationNameLabel = new Label(applicationName);
        Label versionLabel = new Label(translationController.translate("version") + version);
        Label buildDateLabel = new Label(translationController.translate("buildDate") + buildDate);
        Label developersLabel = new Label(translationController.translate("developed") + developers);
        Label descriptionLabel = new Label(translationController.translate("description"));

        Button closeButton = new Button(translationController.translate("closeAbout"));
        closeButton.setOnAction(e -> aboutStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(applicationNameLabel,versionLabel, buildDateLabel, developersLabel, descriptionLabel, closeButton);
        layout.setStyle("-fx-padding: 10; -fx-alignment: center;");

        Scene scene = new Scene(layout, 400, 200);
        aboutStage.setScene(scene);
        aboutStage.showAndWait();
    }
}
