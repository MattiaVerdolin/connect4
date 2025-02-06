package ch.supsi.connectfour.frontend.view;

import ch.supsi.connectfour.backend.domain.PlayerModel;
import ch.supsi.connectfour.backend.domain.TableModel;

import java.util.ArrayList;

public interface BoardViewInterface {
    void updateBoard(TableModel table);
    void addSignColor(ArrayList<PlayerModel> players);
    void clearMap();

}
