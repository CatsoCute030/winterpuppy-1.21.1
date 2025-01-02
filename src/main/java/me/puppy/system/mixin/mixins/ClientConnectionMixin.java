package me.puppy.system.mixin.mixins;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import me.puppy.Winterpuppy;
import me.puppy.system.events.PacketEvent;
import me.puppy.system.mixin.interfaces.IClientConnection;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public abstract class ClientConnectionMixin implements IClientConnection {


    @Shadow
    protected abstract void sendImmediately(Packet<?> packet, @Nullable PacketCallbacks callbacks, boolean flush);

    @Override
    public void winterpuppy$sendImmediately(Packet<?> packet, boolean flush) {
        sendImmediately(packet, null,  true);
    }

    @Override
    public void winterpuppy$sendImmediately(Packet<?> packet) {
        winterpuppy$sendImmediately(packet, true);
    }

    @Shadow
    private Channel channel;
    @Shadow @Final
    private NetworkSide side;

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    public void channelRead0(ChannelHandlerContext chc, Packet<?> packet, CallbackInfo ci) {
        if (this.channel.isOpen() && packet != null) {
            try {
                PacketEvent.Receive event = new PacketEvent.Receive(packet);
                Winterpuppy.Events.post(event);
                if (event.isCancelled())
                    ci.cancel();
            } catch (Exception e) {
            }
        }
    }

    @Inject(method = "sendImmediately", at = @At("HEAD"), cancellable = true)
    void sp(Packet<?> packet, @Nullable PacketCallbacks callbacks, boolean flush, CallbackInfo ci) {
        if(Winterpuppy.Events.post(new PacketEvent.Send(packet)).isCancelled()) ci.cancel();
    }
}
