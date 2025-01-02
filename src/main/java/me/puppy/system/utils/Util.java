package me.puppy.system.utils;

import me.puppy.Winterpuppy;
import meteordevelopment.orbit.IEventBus;
import net.minecraft.client.MinecraftClient;

public interface Util {
    MinecraftClient mc = MinecraftClient.getInstance();
    IEventBus EVENT_BUS = Winterpuppy.Events;
}
