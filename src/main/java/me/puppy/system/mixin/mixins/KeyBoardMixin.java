package me.puppy.system.mixin.mixins;

import me.puppy.Winterpuppy;
import me.puppy.system.events.KeyEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyBoardMixin {

    @Inject(method = "onKey", at = @At("TAIL"), cancellable = true)
    private void onKey(long windowPointer, int key, int scanCode, int action, int modifiers, CallbackInfo ci) {
        KeyEvent event = new KeyEvent(key);
        Winterpuppy.Events.post(event);
        if (event.isCancelled()) ci.cancel();
    }
}
