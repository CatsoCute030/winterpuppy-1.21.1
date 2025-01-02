package me.puppy.system.events;

import meteordevelopment.orbit.ICancellable;

public class KeyEvent implements ICancellable {
    private boolean cancelled;
    private final int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
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
