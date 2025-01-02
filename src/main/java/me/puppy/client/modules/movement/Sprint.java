package me.puppy.client.modules.movement;

import me.puppy.client.modules.Category;
import me.puppy.client.modules.Module;

import java.util.Objects;

public class Sprint extends Module {

    public Sprint() {
        super("Sprint", "adada", Category.MOVEMENT, false, false, true);
    }

    @Override
    public void onEnable() {
        if (mc.player != null) {
            mc.player.setSprinting(true);
        }
    }
}
