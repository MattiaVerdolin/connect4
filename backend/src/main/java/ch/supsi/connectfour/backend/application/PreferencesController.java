package ch.supsi.connectfour.backend.application;

import ch.supsi.connectfour.backend.domain.PreferenceModel;
import ch.supsi.connectfour.backend.domain.PreferenceModelInterface;

import java.util.Map;

public class PreferencesController {
    private static PreferencesController myself;

    private final PreferenceModelInterface preferenceModel = PreferenceModel.getInstance();

    public PreferencesController() {
    }

    public static PreferencesController getInstance() {
        if (myself == null) {
            myself = new PreferencesController();
        }

        return myself;
    }

    public boolean checkConflicts(String color1, String color2, String symbol1, String symbol2) {
        boolean colorConflict = color1 != null && color1.equals(color2);
        boolean symbolConflict = symbol1 != null && symbol1.equals(symbol2);
        return colorConflict || symbolConflict;
    }



    public void dataTransfer(Map<String, String> preferencesMap) {
        preferenceModel.updatePreference(preferencesMap);
    }

}
