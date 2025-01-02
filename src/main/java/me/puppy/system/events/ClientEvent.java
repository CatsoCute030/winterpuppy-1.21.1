package me.puppy.system.events;

import me.puppy.client.modules.Feature;
import me.puppy.client.settings.Setting;
import meteordevelopment.orbit.ICancellable;

public class ClientEvent implements ICancellable {

    private boolean cancelled = false;

    private Feature feature;
    private Setting<?> setting;
    private int stage;

    public ClientEvent(int stage, Feature feature) {
        this.stage = stage;
        this.feature = feature;
    }

    public ClientEvent(Setting<?> setting) {
        this.stage = 2;
        this.setting = setting;
    }

    public Feature getFeature() {
        return this.feature;
    }

    public Setting<?> getSetting() {
        return this.setting;
    }

    public int getStage() {
        return stage;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }
}
