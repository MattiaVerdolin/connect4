package ch.supsi.connectfour.backend.domain;

import java.util.Map;

public interface PreferenceModelInterface {
    Object getPreference(String key);
    String getCurrentLanguage();
    void loadPreferences(GameModelInterface gameModel);
    void updatePreference(Map<String, String> preferencesMap);
}
