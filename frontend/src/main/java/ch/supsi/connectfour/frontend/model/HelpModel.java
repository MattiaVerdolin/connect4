package ch.supsi.connectfour.frontend.model;

import ch.supsi.connectfour.backend.application.TranslationController;

public class HelpModel implements HelpModelInterface {

    private static HelpModel myself;

    private String rules;
    private final TranslationController translationController = TranslationController.getInstance();

    public static HelpModel getInstance() {
        if (myself == null) {
            myself = new HelpModel();
        }

        return myself;
    }

    private void loadHelpContent() {
        StringBuilder content = new StringBuilder();
        content.append(translationController.translate("gameGoalsTitle")).append("\n");
        content.append(translationController.translate("gameGoalsContent")).append("\n");
        content.append("\n");
        content.append(translationController.translate("gameElements")).append("\n");
        content.append(translationController.translate("gameBoard")).append("\n");
        content.append(translationController.translate("gameCoins")).append("\n");
        content.append("\n");
        content.append(translationController.translate("gameRules")).append("\n");
        content.append(translationController.translate("startRule")).append("\n");
        content.append(translationController.translate("turnRule")).append("\n");
        content.append(translationController.translate("victoryRule")).append("\n");
        content.append(translationController.translate("tieRule")).append("\n");

        rules = content.toString();

    }

    @Override
    public String getRules() {
        loadHelpContent();
        return rules;
    }
}
