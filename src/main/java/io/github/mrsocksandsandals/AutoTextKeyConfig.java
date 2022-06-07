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
    // Loads the configuration from the file, or loads the default config.
    public void loadConfig() {
        // Gets the config file, and loads it.
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("autotxt.properties");
        if (!configPath.toFile().exists()) {
            // File does not exist, set the auto text key string to the default.
            messages_unparsed = DEFAULT_MESSAGES;            
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
                AutoTextKeyClient.LOGGER.warn("Falling back to default (\"" + DEFAULT_MESSAGES + "\")");
                messages_unparsed = DEFAULT_MESSAGES;
                return;
            } catch (IOException err) {
                AutoTextKeyClient.LOGGER.warn("An exception occurred: " + err);
                AutoTextKeyClient.LOGGER.warn("Ignoring...");
            }

            // Actually configure the string.
            if (properties.containsKey("messages")) {
                // The key exists, continue...
                messages_unparsed = properties.getProperty("messages", DEFAULT_MESSAGES);
            } else {
                AutoTextKeyClient.LOGGER.warn("The config file exists, but the \"messages\" key does not.");
                AutoTextKeyClient.LOGGER.warn("Defaulting to \"" + DEFAULT_MESSAGES + "\".");
                messages_unparsed = DEFAULT_MESSAGES;
            }
        }
    }

    // Parses the messages config string, then returns the messages themselves.
    public String[] getText() {
        // declare variable.
        String[] messages;

        
        // Use the String.split(String) method to get the messages array.
        messages = messages_unparsed.split(";");
        if (messages.length < 3) {
            // Error handling.
            AutoTextKeyClient.LOGGER.warn("Not enough messages were specified.");
            messages_unparsed = DEFAULT_MESSAGES;
            messages = messages_unparsed.split(";");
        }
        return messages;
    }

    // Variables for the entire class.
    private static String messages_unparsed;
    private final String DEFAULT_MESSAGES = "gg;gf;gl"; // good game, good fight, good luck
}
