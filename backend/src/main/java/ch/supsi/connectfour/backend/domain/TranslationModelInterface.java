package ch.supsi.connectfour.backend.domain;

import java.util.ResourceBundle;

public interface TranslationModelInterface {
    void changeLanguage(String languageTag);
    String translate(String key);
    ResourceBundle getTranslationBundle();
}
