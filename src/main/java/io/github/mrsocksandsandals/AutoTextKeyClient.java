package io.github.mrsocksandsandals;

// Imports:
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoTextKeyClient implements ClientModInitializer {
	// Define our logger.
	public static final Logger LOGGER = LoggerFactory.getLogger("autotxt");

	@Override
	public void onInitializeClient() {
		// Print an init message.
		LOGGER.info("Hello Fabric world!");
	}
}
