package ch.supsi.connectfour.backend.dataAccess;

import java.util.Properties;

public interface PreferencesDataAccessInterface {
    Properties getPreferences();
    void updatePreference(String key, String value);
}
