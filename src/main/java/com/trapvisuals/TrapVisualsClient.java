package com.trapvisuals;

import com.trapvisuals.gui.TrapVisualsScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class TrapVisualsClient implements ClientModInitializer {
    public static KeyBinding menuKeyBinding;
    public static final TrapVisualsManager MANAGER = new TrapVisualsManager();

    @Override
    public void onInitializeClient() {
        menuKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.trap_visuals.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                KeyBinding.Category.create("category.trap_visuals")
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKeyBinding.wasPressed()) {
                client.setScreen(new TrapVisualsScreen());
            }
            MANAGER.onTick(client);
        });
    }
}
