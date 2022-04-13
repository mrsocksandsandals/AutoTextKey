// Contains the code for getting the configuration.
package io.github.mrsocksandsandals;

// Imports:
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import net.fabricmc.loader.api.FabricLoader;

public class AutoTextKeyConfig {
    public void loadConfig() {
        // Gets the config file, and loads it.
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("autotxt.properties");
        if (!configPath.toFile().exists()) {
            // File does not exist, set the auto text key string to the default.
            autoTextString = DEFAULT_TEXT;            
        } else {
            // File exists, read in the properties.
            Properties properties = new Properties();
            FileInputStream inStream;
            try {
                inStream = new FileInputStream(configPath.toString());
                properties.load(inStream);
                inStream.close();
            } catch (FileNotFoundException err) {
                // Fall back to the default (even though we shouldn't be here)
                AutoTextKeyClient.LOGGER.warn("Failed to find config file: " + err);
                AutoTextKeyClient.LOGGER.warn("Falling back to default (\"" + DEFAULT_TEXT + "\")");
                autoTextString = DEFAULT_TEXT;
                return;
            } catch (IOException err) {
                AutoTextKeyClient.LOGGER.warn("An exception occurred: " + err);
                AutoTextKeyClient.LOGGER.warn("Ignoring...");
            }

            // Actually configure the string.
            if (properties.containsKey("autoTextString")) {
                // The key exists, continue...
                autoTextString = properties.getProperty("autoTextString", DEFAULT_TEXT);
            } else {
                AutoTextKeyClient.LOGGER.warn("The config file exists, but the \"autoTextString\" key does not.");
                AutoTextKeyClient.LOGGER.warn("Defaulting to \"" + DEFAULT_TEXT + "\".");
                autoTextString = DEFAULT_TEXT;
            }
        }
    }

    public String getText() {
        return autoTextString;
    }

    private static String autoTextString;
    private final String DEFAULT_TEXT = "gg";
}
