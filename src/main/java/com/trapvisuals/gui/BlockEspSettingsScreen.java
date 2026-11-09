package com.trapvisuals.gui;

import com.trapvisuals.TrapVisualsClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class BlockEspSettingsScreen extends Screen {
    private final Screen parent;

    public BlockEspSettingsScreen(Screen parent) {
        super(Text.literal("Block ESP Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Toggle Diamond Ore"), b -> {
            toggleBlock("minecraft:diamond_ore");
            b.setMessage(Text.literal("Diamond Ore: " + (isHighlighted("minecraft:diamond_ore") ? "ON" : "OFF")));
        }).dimensions(centerX - 100, centerY - 40, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Toggle Ancient Debris"), b -> {
            toggleBlock("minecraft:ancient_debris");
            b.setMessage(Text.literal("Ancient Debris: " + (isHighlighted("minecraft:ancient_debris") ? "ON" : "OFF")));
        }).dimensions(centerX - 100, centerY - 10, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Back"), b -> {
            this.client.setScreen(parent);
        }).dimensions(centerX - 100, centerY + 40, 200, 20).build());
    }

    private void toggleBlock(String id) {
        if (TrapVisualsClient.MANAGER.getHighlightedBlocks().contains(id)) {
            TrapVisualsClient.MANAGER.getHighlightedBlocks().remove(id);
        } else {
            TrapVisualsClient.MANAGER.getHighlightedBlocks().add(id);
        }
    }

    private boolean isHighlighted(String id) {
        return TrapVisualsClient.MANAGER.getHighlightedBlocks().contains(id);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 80, 0xFFFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
