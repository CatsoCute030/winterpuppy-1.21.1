package me.puppy;

import net.fabricmc.api.ClientModInitializer;

public class WinterpuppyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Winterpuppy.moduleManager.init();
        Winterpuppy.configManager.load();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> Winterpuppy.configManager.save()));
    }
}
