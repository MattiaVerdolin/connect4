package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.application.TranslationController;
import ch.supsi.connectfour.backend.domain.PlayerModel;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoBarView implements InfoBarViewInterface {

    @FXML
    private Text infobar;

    private TranslationController translationController;
    private static InfoBarView myself;

    public static InfoBarView getInstance() {
        if (myself == null) {
            myself = new InfoBarView();
        }

        return myself;
    }

    public InfoBarView() {
        initialize();
    }

    @FXML
    private void initialize() {
        translationController = TranslationController.getInstance();
    }

    @Override
    public void welcomeTextWIthTurn(String playerName) {
        StringBuilder translatedText = new StringBuilder();
        translatedText.append(translationController.translate("welcome"));
        translatedText.append(" ");
        translatedText.append(playerName);
        translatedText.append(" ");
        translatedText.append(translationController.translate("turn"));
        System.out.println(translatedText);
        infobar.setText(translatedText.toString());
    }

    @Override
    public void displayTurnText(PlayerModel player) {

        String name = player.getName();
        int id = player.getId();

        String translatedText = translationController.translate("turn");
        System.out.println(name + " (" + translationController.translate("player") + id + "): " + translatedText);
        infobar.setText(name + " (" + translationController.translate("player") + id + "): " + translatedText);
    }

    @Override
    public void winText(String playerName) {
        String translatedText = translationController.translate("win");
        System.out.println(playerName + ": " + translatedText);
        infobar.setText(playerName + ": " + translatedText);
    }

    @Override
    public void fullColumn() {
        String translatedText = translationController.translate("fullColumn");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void tieText() {
        String translatedText = translationController.translate("tie");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void endGameText() {
        String translatedText = translationController.translate("endGameText");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void saveCancelled(){
        String translatedText = translationController.translate("saveCanc");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void loadCancelled(){
        String translatedText = translationController.translate("loadCanc");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void loadError(){
        String translatedText = translationController.translate("loadErr");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void saveError(){
        String translatedText = translationController.translate("saveErr");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }

    @Override
    public void saveFirst(){
        String translatedText = translationController.translate("saveFirst");
        System.out.println(translatedText);
        infobar.setText(translatedText);
    }





}
