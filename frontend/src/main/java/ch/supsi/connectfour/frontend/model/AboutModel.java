package ch.supsi.connectfour.frontend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AboutModel implements AboutModelInterface {
    private static AboutModel myself;
    private Properties properties;

    private AboutModel() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static AboutModel getInstance() {
        if (myself == null) {
            myself = new AboutModel();
        }
        return myself;
    }

    @Override
    public String getApplicationName() {
        return properties.getProperty("application.name");
    }

    @Override
    public String getVersion() {
        return properties.getProperty("application.version");
    }

    @Override
    public String getBuildTimestamp() {
        return properties.getProperty("build.timestamp");
    }

    @Override
    public String getDevelopers() {
        return properties.getProperty("developers");
    }
}
