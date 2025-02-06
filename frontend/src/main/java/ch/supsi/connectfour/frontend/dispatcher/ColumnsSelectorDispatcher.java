package ch.supsi.connectfour.frontend.dispatcher;

import ch.supsi.connectfour.frontend.controller.GameController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ColumnsSelectorDispatcher {

    private final GameController gameController = GameController.getInstance();

    //quando si clicca un pulsante della colonna, viene richiamato il metodo del gameController che si occuperà di posizionare la moneta
    public void playerMove(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();
        String columnName = clickedButton.getText();
        String columnNumberString = columnName.replaceAll("\\D+", "");
        int colonna = Integer.parseInt(columnNumberString);

        gameController.turn(colonna);
    }

//    public void setGameController(GameController gameController) {
//        this.gameController = gameController;
//    }
}
