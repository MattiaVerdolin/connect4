package ch.supsi.connectfour.backend.domain;
import ch.supsi.connectfour.backend.dataAccess.PreferenceDataAccess;
import ch.supsi.connectfour.backend.dataAccess.PreferencesDataAccessInterface;

import java.util.Map;
import java.util.Properties;

public class PreferenceModel implements PreferenceModelInterface {
    private static PreferenceModel myself;

    private final Properties userPreferences;
    private final PreferencesDataAccessInterface preferencesInterface = PreferenceDataAccess.getInstance();

    public PreferenceModel() {
        userPreferences = preferencesInterface.getPreferences();
    }

    public static PreferenceModel getInstance() {
        if (myself == null) {
            myself = new PreferenceModel();
        }

        return myself;
    }

    @Override
    public String getCurrentLanguage() {
        return userPreferences.getProperty("language-tag");
    }

    @Override
    public Object getPreference(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }

        if (userPreferences == null) {
            return null;
        }

        return userPreferences.get(key);
    }

    @Override
    //carica le preferenze presenti nel file preferences nelle istanze di giocatori
    public void loadPreferences(GameModelInterface gameModel) {

        String player1Name = getPreference("player1.name").toString();
        String player1Symbol = getPreference("player1.symbol").toString();
        String player1Color = getPreference("player1.color").toString();

        String player2Name = getPreference("player2.name").toString();
        String player2Symbol = getPreference("player2.symbol").toString();
        String player2Color = getPreference("player2.color").toString();


        // Aggiornare i giocatori con le preferenze caricate
        gameModel.getPlayer1().setPreferences(player1Name, player1Symbol, player1Color);
        gameModel.getPlayer2().setPreferences(player2Name, player2Symbol, player2Color);
    }

    @Override
    public void updatePreference(Map<String, String> preferencesMap) {
        for (Map.Entry<String, String> entry : preferencesMap.entrySet()) {
            preferencesInterface.updatePreference(entry.getKey(), entry.getValue());
        }
    }
}
