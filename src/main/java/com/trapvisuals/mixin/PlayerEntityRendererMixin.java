package com.trapvisuals.mixin;

import com.trapvisuals.TrapVisualsClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public class PlayerEntityRendererMixin {
    @Inject(method = "getDisplayName", at = @At("RETURN"), cancellable = true)
    private void onGetDisplayName(AbstractClientPlayerEntity player, CallbackInfoReturnable<Text> cir) {
        if (player != null && !player.getName().getString().startsWith("TV ")) {
            // Check if player has Trap Visuals (simulation/heuristic or custom team/meta data)
            // For demonstration, if player name contains certain criteria or if we append [TV]
            Text original = cir.getReturnValue();
            if (original != null && !original.getString().contains("[TV]")) {
                cir.setReturnValue(Text.literal("[TV] " + original.getString()));
            }
        }
    }
}
