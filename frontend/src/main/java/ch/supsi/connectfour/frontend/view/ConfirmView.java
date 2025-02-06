package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.application.TranslationController;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;

public class ConfirmView implements ConfirmViewInterface {

    private final TranslationController translationController = TranslationController.getInstance();

    private static ConfirmView myself;

    public static ConfirmView getInstance() {
        if (myself == null) {
            myself = new ConfirmView();
        }

        return myself;
    }

    @Override
    public boolean confirmNewGame() {
        // Create the confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(translationController.translate("confirm"));
        alert.setHeaderText(null);
        alert.setContentText(translationController.translate("sure"));

        // Customize the buttons
        ButtonType yesButton = new ButtonType(translationController.translate("yes"));
        ButtonType noButton = new ButtonType(translationController.translate("no"));
        alert.getButtonTypes().setAll(yesButton, noButton);

        // Show the dialog and wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }

    @Override
    public boolean confirmQuit() {
        // Create the confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(translationController.translate("confirmQuit"));
        alert.setHeaderText(null);
        alert.setContentText(translationController.translate("sureQuit"));

        // Customize the buttons
        ButtonType yesButton = new ButtonType(translationController.translate("yesQuit"));
        ButtonType noButton = new ButtonType(translationController.translate("noQuit"));
        alert.getButtonTypes().setAll(yesButton, noButton);

        // Show the dialog and wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }
}
