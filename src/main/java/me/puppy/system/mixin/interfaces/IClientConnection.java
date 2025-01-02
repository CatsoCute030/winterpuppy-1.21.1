package me.puppy.system.mixin.interfaces;

import net.minecraft.network.packet.Packet;

public interface IClientConnection {
    void winterpuppy$sendImmediately(Packet<?> packet, boolean flush);
    void winterpuppy$sendImmediately(Packet<?> packet);
}
