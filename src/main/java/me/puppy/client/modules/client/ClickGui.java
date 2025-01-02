package me.puppy.client.modules.client;

import com.google.common.eventbus.Subscribe;
import me.puppy.Winterpuppy;
import me.puppy.client.gui.WinterGui;
import me.puppy.client.modules.Category;
import me.puppy.client.modules.Module;
import me.puppy.client.settings.Setting;
import me.puppy.system.commands.Command;
import me.puppy.system.events.ClientEvent;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;

public class ClickGui extends Module {

    private static ClickGui INSTANCE = new ClickGui();
    public Setting<String> prefix = this.register(new Setting<>("Prefix", "."));
    public Setting<Boolean> outline = this.register(new Setting<>("Outline", true));
    public Setting<Boolean> customFov = this.register(new Setting<>("CustomFov", false));
    public Setting<Float> fov = this.register(new Setting<>("Fov", 150f, -180f, 180f));
    public Setting<Integer> red = this.register(new Setting<>("Red", 0, 0, 255));
    public Setting<Integer> green = this.register(new Setting<>("Green", 0, 0, 255));
    public Setting<Integer> blue = this.register(new Setting<>("Blue", 255, 0, 255));
    public Setting<Integer> hoverAlpha = this.register(new Setting<>("Alpha", 180, 0, 255));
    public Setting<Integer> topRed = this.register(new Setting<>("TopRed", 0, 0, 255));
    public Setting<Integer> topGreen = this.register(new Setting<>("TopGreen", 0, 0, 255));
    public Setting<Integer> topBlue = this.register(new Setting<>("TopBlue", 150, 0, 255));
    public Setting<Integer> topAlpha = this.register(new Setting<>("TopAlpha", 255, 0, 255));
    public Setting<Integer> alpha = this.register(new Setting<>("HoverAlpha", 240, 0, 255));

    //public Setting<Integer> width = this.register(new Setting<>("Width", 2, 1, 10));
    public Setting<Boolean> rainbow = this.register(new Setting<>("Rainbow", false));
    public Setting<rainbowMode> rainbowModeHud = this.register(new Setting<>("HRainbowMode", rainbowMode.Static, v -> this.rainbow.getValue()));
    public Setting<rainbowModeArray> rainbowModeA = this.register(new Setting<>("ARainbowMode", rainbowModeArray.Static, v -> this.rainbow.getValue()));
    public Setting<Integer> rainbowHue = this.register(new Setting<>("Delay", 240, 0, 600, v -> this.rainbow.getValue()));
    public Setting<Float> rainbowBrightness = this.register(new Setting<>("Brightness ", 150.0f, 1.0f, 255.0f, v -> this.rainbow.getValue()));
    public Setting<Float> rainbowSaturation = this.register(new Setting<>("Saturation", 150.0f, 1.0f, 255.0f, v -> this.rainbow.getValue()));
    public Setting<Boolean> particle = this.register(new Setting<>("Particle", true));
    public Setting<Boolean> pMode2 = this.register(new Setting<>("SecondParticle", false));
    //public Setting<Integer> pAmount = this.register(new Setting<>("Amount", 32, 1, 100, v -> particle.getValue()));
    public Setting<Boolean> syncColor = this.register(new Setting<>("SyncColor", false));
    private WinterGui click;

    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Category.CLIENT, true, false, false);
        setBind(GLFW.GLFW_KEY_Y);
        this.setInstance();
    }

    public static ClickGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClickGui();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void onUpdate() {
        if (this.customFov.getValue().booleanValue()) {
            mc.options.getFov().setValue(this.fov.getValue().intValue());
        }
    }

    @Subscribe
    public void onSettingChange(ClientEvent event) {
        if (event.getStage() == 2 && event.getSetting().getFeature().equals(this)) {
            if (event.getSetting().equals(this.prefix)) {
                Winterpuppy.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage("Prefix set to " + Formatting.DARK_GRAY + Winterpuppy.commandManager.getPrefix());
            }
            Winterpuppy.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }

    @Override
    public void onEnable() {
        mc.setScreen(WinterGui.getClickGui());
    }

    @Override
    public void onLoad() {
        Winterpuppy.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        Winterpuppy.commandManager.setPrefix(this.prefix.getValue());
    }

    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof WinterGui)) {
            disable();
        }
    }

    public enum rainbowModeArray {
        Static,
        Up

    }

    public enum rainbowMode {
        Static,
        Sideway

    }
}