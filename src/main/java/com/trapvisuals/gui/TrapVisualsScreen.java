package com.trapvisuals.gui;

import com.trapvisuals.TrapVisualsClient;
import com.trapvisuals.TrapVisualsManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class TrapVisualsScreen extends Screen {
    public TrapVisualsScreen() {
        super(Text.literal("Trap Visuals (TV)"));
    }

    @Override
    protected void init() {
        super.init();
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int startY = centerY - 80;

        TrapVisualsManager m = TrapVisualsClient.MANAGER;

        // Row 1: Fullbright
        this.addDrawableChild(ButtonWidget.builder(Text.literal("func: Fullbright"), b -> {
            m.setFullbright(!m.isFullbright());
            updateButtonText(b, "Fullbright", m.isFullbright());
        }).dimensions(centerX - 120, startY, 140, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal(m.isFullbright() ? "[ON]" : "[OFF]"), b -> {
            m.setFullbright(!m.isFullbright());
            b.setMessage(Text.literal(m.isFullbright() ? "[ON]" : "[OFF]"));
        }).dimensions(centerX + 25, startY, 60, 20).build());

        startY += 26;

        // Row 2: ESP
        this.addDrawableChild(ButtonWidget.builder(Text.literal("func: ESP"), b -> {
            m.setEsp(!m.isEsp());
        }).dimensions(centerX - 120, startY, 140, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal(m.isEsp() ? "[ON]" : "[OFF]"), b -> {
            m.setEsp(!m.isEsp());
            b.setMessage(Text.literal(m.isEsp() ? "[ON]" : "[OFF]"));
        }).dimensions(centerX + 25, startY, 60, 20).build());

        startY += 26;

        // Row 3: Block ESP & Settings button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("func: Block ESP"), b -> {
            m.setBlockEsp(!m.isBlockEsp());
        }).dimensions(centerX - 120, startY, 100, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal("Blocks..."), b -> {
            this.client.setScreen(new BlockEspSettingsScreen(this));
        }).dimensions(centerX - 18, startY, 40, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal(m.isBlockEsp() ? "[ON]" : "[OFF]"), b -> {
            m.setBlockEsp(!m.isBlockEsp());
            b.setMessage(Text.literal(m.isBlockEsp() ? "[ON]" : "[OFF]"));
        }).dimensions(centerX + 25, startY, 60, 20).build());

        startY += 26;

        // Row 4: Free Look
        this.addDrawableChild(ButtonWidget.builder(Text.literal("func: Free look"), b -> {
            m.setFreeLook(!m.isFreeLook());
        }).dimensions(centerX - 120, startY, 140, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal(m.isFreeLook() ? "[ON]" : "[OFF]"), b -> {
            m.setFreeLook(!m.isFreeLook());
            b.setMessage(Text.literal(m.isFreeLook() ? "[ON]" : "[OFF]"));
        }).dimensions(centerX + 25, startY, 60, 20).build());

        startY += 26;

        // Row 5: Free Camera
        this.addDrawableChild(ButtonWidget.builder(Text.literal("func: Free Camera"), b -> {
            m.setFreeCam(!m.isFreeCam());
        }).dimensions(centerX - 120, startY, 140, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal(m.isFreeCam() ? "[ON]" : "[OFF]"), b -> {
            m.setFreeCam(!m.isFreeCam());
            b.setMessage(Text.literal(m.isFreeCam() ? "[ON]" : "[OFF]"));
        }).dimensions(centerX + 25, startY, 60, 20).build());

        startY += 26;

        // Row 6: No particles
        this.addDrawableChild(ButtonWidget.builder(Text.literal("func: No particles"), b -> {
            m.setNoParticles(!m.isNoParticles());
        }).dimensions(centerX - 120, startY, 140, 20).build());

        this.addDrawableChild(ButtonWidget.builder(Text.literal(m.isNoParticles() ? "[ON]" : "[OFF]"), b -> {
            m.setNoParticles(!m.isNoParticles());
            b.setMessage(Text.literal(m.isNoParticles() ? "[ON]" : "[OFF]"));
        }).dimensions(centerX + 25, startY, 60, 20).build());

        startY += 36;

        // Close button
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Close (Right Shift)"), b -> {
            this.close();
        }).dimensions(centerX - 60, startY, 120, 20).build());
    }

    private void updateButtonText(ButtonWidget button, String name, boolean state) {
        button.setMessage(Text.literal("func: " + name));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, this.height / 2 - 105, 0xFF55FF55);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
