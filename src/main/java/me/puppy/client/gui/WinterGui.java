package me.puppy.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class WinterGui extends Screen {

    private static WinterGui winterGui;
    private static WinterGui INSTANCE;

    static {
        INSTANCE = new WinterGui();
    }

    public WinterGui() {
        super(Text.literal("Winterpuppy"));
        setInstance();
    }

    public static WinterGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new WinterGui();
        }
        return INSTANCE;
    }

    public static WinterGui getClickGui() {
        return WinterGui.getInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int clickedButton) {
        return super.mouseClicked(mouseX, mouseY, clickedButton);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int releaseButton) {
        return super.mouseReleased(mouseX, mouseY, releaseButton);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return super.keyPressed(keyCode, scanCode, modifiers);
    }


    @Override
    public boolean shouldPause() {
        return false;
    }
}
