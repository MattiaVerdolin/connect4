package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.model.HelpModel;
import ch.supsi.connectfour.frontend.model.HelpModelInterface;
import ch.supsi.connectfour.frontend.view.HelpView;
import ch.supsi.connectfour.frontend.view.HelpViewInterface;

public class HelpController {

    private static HelpController myself;

    private final HelpModelInterface helpModel = HelpModel.getInstance();
    private final HelpViewInterface helpView = HelpView.getInstance();

    public static HelpController getInstance() {
        if (myself == null) {
            myself = new HelpController();
        }

        return myself;
    }

    public void help() {
        helpView.helpWindow(helpModel.getRules());
    }

}
