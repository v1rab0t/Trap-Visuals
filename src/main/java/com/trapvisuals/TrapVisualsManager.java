package com.trapvisuals;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.HashSet;
import java.util.Set;

public class TrapVisualsManager {
    private boolean fullbright = false;
    private boolean esp = false;
    private boolean blockEsp = false;
    private boolean freeLook = false;
    private boolean freeCam = false;
    private boolean noParticles = false;

    private final Set<String> highlightedBlocks = new HashSet<>();

    // FreeCam state
    public double freeCamX, freeCamY, freeCamZ;
    public float freeCamYaw, freeCamPitch;
    private boolean freeCamActiveLastTick = false;

    // FreeLook state
    public float freeLookYaw = 0.0f;
    public float freeLookPitch = 0.0f;

    private double prevGamma = 1.0;

    public void onTick(MinecraftClient client) {
        if (client.player == null) return;

        // Fullbright
        if (fullbright) {
            if (client.options.getGamma().getValue() < 500.0) {
                prevGamma = client.options.getGamma().getValue();
                client.options.getGamma().setValue(1000.0);
            }
        } else {
            if (client.options.getGamma().getValue() >= 1000.0) {
                client.options.getGamma().setValue(1.0);
            }
        }

        // FreeCam logic
        if (freeCam) {
            if (!freeCamActiveLastTick) {
                Vec3d pos = client.player.getPos();
                freeCamX = pos.x;
                freeCamY = pos.y + client.player.getEyeHeight(client.player.getPose());
                freeCamZ = pos.z;
                freeCamYaw = client.player.getYaw();
                freeCamPitch = client.player.getPitch();
                freeCamActiveLastTick = true;
            }
        } else {
            freeCamActiveLastTick = false;
        }
    }

    public boolean isFullbright() { return fullbright; }
    public void setFullbright(boolean v) { this.fullbright = v; }

    public boolean isEsp() { return esp; }
    public void setEsp(boolean v) { this.esp = v; }

    public boolean isBlockEsp() { return blockEsp; }
    public void setBlockEsp(boolean v) { this.blockEsp = v; }

    public boolean isFreeLook() { return freeLook; }
    public void setFreeLook(boolean v) { this.freeLook = v; }

    public boolean isFreeCam() { return freeCam; }
    public void setFreeCam(boolean v) { this.freeCam = v; }

    public boolean isNoParticles() { return noParticles; }
    public void setNoParticles(boolean v) { this.noParticles = v; }

    public Set<String> getHighlightedBlocks() { return highlightedBlocks; }
}
