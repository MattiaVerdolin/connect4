package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class MenuBarDispatcher {

    private Stage primaryStage;

    private final GameController gameController = GameController.getInstance();
    private final AboutController aboutController = AboutController.getInstance();
    private final HelpController helpController = HelpController.getInstance();
    private final PreferenceController preferencesController = PreferenceController.getInstance();
    private final PersistenceController persistenceController = PersistenceController.getInstance();

    public void newGame(ActionEvent actionEvent) {
            gameController.newGame();
    }

    public void openGame(ActionEvent actionEvent) {
        persistenceController.open(primaryStage);
    }

    public void saveGame(ActionEvent actionEvent) {
        persistenceController.save();
    }

    public void saveGameAs(ActionEvent actionEvent) {
        persistenceController.saveGameAs(primaryStage);
    }

    public void quit(ActionEvent actionEvent) {
        gameController.quit(primaryStage);
    }

    public void showAbout(ActionEvent actionEvent) {
        aboutController.about();
    }

    public void showHelp(ActionEvent actionEvent) {
        helpController.help();
    }

    public void showPreferences(ActionEvent actionEvent) {
        preferencesController.changePreferences();
    }

//    public void setGameController(GameController gameController) {
//        this.gameController = gameController;
//    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
