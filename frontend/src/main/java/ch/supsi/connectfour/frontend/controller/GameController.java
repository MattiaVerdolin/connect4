package ch.supsi.connectfour.frontend.controller;
import ch.supsi.connectfour.backend.domain.*;
import ch.supsi.connectfour.frontend.view.*;
import javafx.stage.Stage;

public class GameController{
    private static GameController myself;

    private GameModelInterface gameModel = GameModel.getInstance();
    private final PreferenceModelInterface preferenceModel = PreferenceModel.getInstance();
    private final ConfirmViewInterface newGameView = ConfirmView.getInstance();

    private BoardViewInterface boardView;
    private InfoBarViewInterface infoBarView;

    public GameController() {
        preferenceModel.loadPreferences(gameModel);
    }

    public void WelcomeText(){
        //messaggio di benvenuto
        infoBarView.welcomeTextWIthTurn(gameModel.checkTurn().getName());
    }


    public void turn(int colonna) {

        if(gameModel.partitaTerminata()) {
            infoBarView.endGameText();

            return;
        }

        /*Il metodo mossa del gameModel ritorna true se la mossa è andata a buon fine. Se la colonna è piena ritorna false*/
        if(gameModel.move(colonna)){

            boardView.updateBoard(gameModel.getTable());

            if(gameModel.youWon()) {
                infoBarView.winText(gameModel.checkTurn().getName());

                return;
            }

            gameModel.changeTurn();

            //testo con nome del giocatore di turno
            infoBarView.displayTurnText(gameModel.checkTurn());

        } else {

            infoBarView.fullColumn();
        }

        if(gameModel.tie()) {
            infoBarView.tieText();
        }
    }

    public void newGame() {

        if(newGameView.confirmNewGame()) {
            gameModel = new GameModel();
            preferenceModel.loadPreferences(gameModel);

            boardView.clearMap(); //pulisce la mappa della board prima di aggiungere i nuovi simboli/colori
            uploadSignsColorsIntoBoardView();
            boardView.updateBoard(gameModel.getTable());
            infoBarView.displayTurnText(gameModel.checkTurn());
        }
    }

    public void quit(Stage primaryStage) {
        if(newGameView.confirmQuit()){
            primaryStage.close();
        }
    }

    public static GameController getInstance() {
        if (myself == null) {
            myself = new GameController();
        }

        return myself;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
        //aggiorno l'interfaccia di gioco alla creazione (risultato atteso: griglia vuota)
        this.boardView.updateBoard(gameModel.getTable());
    }

    //carico nella mappa della board view le corrispondenze simbolo/colore dei giocatori
    public void uploadSignsColorsIntoBoardView(){
        boardView.addSignColor(gameModel.getPlayers());
    }

    public void setInfoBarView(InfoBarView infoBarView) {
        this.infoBarView = infoBarView;
    }

}