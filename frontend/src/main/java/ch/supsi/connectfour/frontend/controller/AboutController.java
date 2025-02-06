package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.model.AboutModel;
import ch.supsi.connectfour.frontend.model.AboutModelInterface;
import ch.supsi.connectfour.frontend.view.AboutModelViewInterface;
import ch.supsi.connectfour.frontend.view.AboutView;

public class AboutController {

    private static AboutController myself;

    private final AboutModelInterface aboutModel = AboutModel.getInstance();
    private final AboutModelViewInterface aboutView = AboutView.getInstance();

    public static AboutController getInstance() {
        if (myself == null) {
            myself = new AboutController();
        }

        return myself;
    }

    public void about(){
        aboutView.aboutWindow(aboutModel.getApplicationName(), aboutModel.getVersion(),
                aboutModel.getBuildTimestamp(), aboutModel.getDevelopers());
    }
}
