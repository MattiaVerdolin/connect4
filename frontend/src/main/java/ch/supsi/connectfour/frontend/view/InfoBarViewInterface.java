package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.domain.PlayerModel;

public interface InfoBarViewInterface {
    void welcomeTextWIthTurn(String playerName);
    void displayTurnText(PlayerModel player);
    void winText(String playerName);
    void fullColumn();
    void tieText();
    void endGameText();
    void saveCancelled();
    void loadCancelled();
    void loadError();
    void saveError();
    void saveFirst();
}
