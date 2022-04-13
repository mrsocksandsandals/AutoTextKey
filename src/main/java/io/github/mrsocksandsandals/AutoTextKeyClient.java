package io.github.mrsocksandsandals;

// Imports:
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AutoTextKeyClient implements ClientModInitializer {
	// Define our logger.
	public static final Logger LOGGER = LoggerFactory.getLogger("autotxt");

	// Get the message we're sending to chat.
	private static AutoTextKeyConfig cfg = new AutoTextKeyConfig();

	@Override
	public void onInitializeClient() {
		// Print an init message.
		LOGGER.info("Initializing AutoTextKey...");

		// Set the message.
		cfg.loadConfig();
		String autoTextMessage = cfg.getText();

		// Register our keybinding.
		LOGGER.info("Registering keybinding...");
		ClientTickEvents.END_CLIENT_TICK.register(c -> {
			while (autoTextKey.wasPressed()) {
				MinecraftClient.getInstance().player.sendChatMessage(autoTextMessage);
			}
		});
	}

	private KeyBinding autoTextKey = new KeyBinding(
		"key.autotxt.autotextkey",
		GLFW.GLFW_KEY_DELETE,
		"category.autotxt"
	);
}
