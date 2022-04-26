package io.github.mrsocksandsandals;

// Imports:
import java.lang.ArrayIndexOutOfBoundsException;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;

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
			// We have to get the UUID every time, because the player isn't initialized before this point.
			// Otherwise, I would keep it in a constant.

			// Print the first message.
			while (autoTextKey0.wasPressed()) {
				try {
					CLIENT.player.sendChatMessage(messages[0]);
				} catch (ArrayIndexOutOfBoundsException e) {
					LOGGER.error("messages[0] is unset!");
					LOGGER.error("not sending...");
					// Print an error message to the player only.
					CLIENT.inGameHud.addChatMessage(
						MessageType.SYSTEM,
						new LiteralText("You haven't defined the first message!"),
						CLIENT.player.getUuid()
					);
				}
			}

			// Print the second message.
			while (autoTextKey1.wasPressed()) {
				try {
					CLIENT.player.sendChatMessage(messages[1]);
				} catch (ArrayIndexOutOfBoundsException e) {
					LOGGER.error("messages[1] is unset!");
					LOGGER.error("not sending...");
					CLIENT.inGameHud.addChatMessage(
						MessageType.SYSTEM,
						new LiteralText("You haven't defined the second message!"),
						CLIENT.player.getUuid()
					);
				}
			}

			// Print the third message.
			while (autoTextKey2.wasPressed()) {
				try {
					CLIENT.player.sendChatMessage(messages[2]);
				} catch (ArrayIndexOutOfBoundsException e) {
					LOGGER.error("messages[2] is unset!");
					LOGGER.error("not sending...");
					CLIENT.inGameHud.addChatMessage(
						MessageType.SYSTEM,
						new LiteralText("You haven't defined the third message!"),
						CLIENT.player.getUuid()
					);
				}
			}
		});
	}

	// Create keybindings (translation key, keycode, category)
	private KeyBinding autoTextKey0 = new KeyBinding(
		"key.autotxt.key0",
		GLFW.GLFW_KEY_DELETE,
		"key.categories.autotxt"
	);

	private KeyBinding autoTextKey1 = new KeyBinding(
		"key.autotxt.key1",
		GLFW.GLFW_KEY_PAGE_UP,
		"key.categories.autotxt"
	);

	private KeyBinding autoTextKey2 = new KeyBinding(
		"key.autotxt.key2",
		GLFW.GLFW_KEY_PAGE_DOWN,
		"key.categories.autotxt"
	);
}
