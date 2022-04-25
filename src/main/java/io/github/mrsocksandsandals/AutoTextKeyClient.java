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
		String[] messages = cfg.getText();

		// Get the Minecraft instance.
		final MinecraftClient CLIENT = MinecraftClient.getInstance();

		// Register our keybinding.
		LOGGER.info("Registering keybinding...");
		ClientTickEvents.END_CLIENT_TICK.register(c -> {
			// Print the first message.
			if (autoTextKey0.wasPressed()) {
				CLIENT.player.sendChatMessage(messages[0]);
			}

			// Print the second message.
			if (autoTextKey1.wasPressed()) {
				CLIENT.player.sendChatMessage(messages[1]);
			}

			// Print the third message.
			if (autoTextKey2.wasPressed()) {
				CLIENT.player.sendChatMessage(messages[2]);
			}
		});
	}

	private KeyBinding autoTextKey0 = new KeyBinding(
		"key.autotxt.key0",
		GLFW.GLFW_KEY_DELETE,
		"category.autotxt"
	);

	private KeyBinding autoTextKey1 = new KeyBinding(
		"key.autotxt.key1",
		GLFW.GLFW_KEY_PAGE_UP,
		"category.autotxt"
	);

	private KeyBinding autoTextKey2 = new KeyBinding(
		"key.autotxt.key2",
		GLFW.GLFW_KEY_PAGE_DOWN,
		"category.autotxt"
	);
}
