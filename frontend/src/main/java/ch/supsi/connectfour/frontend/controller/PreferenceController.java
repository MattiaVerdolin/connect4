package ch.supsi.connectfour.frontend.controller;

import ch.supsi.connectfour.frontend.view.PreferenceView;
import ch.supsi.connectfour.frontend.view.PreferenceViewInterface;

public class PreferenceController {
    private static PreferenceController myself;

    private final PreferenceViewInterface preferenceView = PreferenceView.getInstance();

    public PreferenceController() {
    }

    public static PreferenceController getInstance() {
        if (myself == null) {
            myself = new PreferenceController();
        }

        return myself;
    }

    public void changePreferences() {
        preferenceView.preferenceWindow();
    }
}
