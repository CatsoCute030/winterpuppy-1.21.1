package me.puppy.client.gui;

import me.puppy.Winterpuppy;
import me.puppy.client.gui.items.Item;
import me.puppy.client.gui.items.buttons.ModuleButton;
import me.puppy.client.modules.Category;
import me.puppy.client.modules.Feature;
import me.puppy.client.modules.Module;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

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

    private void setInstance() {
        INSTANCE = this;
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

    private final ArrayList<Component> components = new ArrayList();

    private void load() {
        int x = -84;
        for (final Category category : Winterpuppy.moduleManager.getCategories()) {
            this.components.add(new Component(category.getName(), x += 90, 4,  true) {

                @Override
                public void setupItems() {
                    counter1 = new int[]{1};
                    Winterpuppy.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton(new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort(Comparator.comparing(Feature::getName)));
    }

    public void updateModule(Module module) {
        for (Component component : this.components) {
            for (Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) continue;
                ModuleButton button = (ModuleButton) item;
                Module mod = button.getModule();
                if (module == null || !module.equals(mod)) continue;
                button.initSettings();
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        Item.context = context;
        context.fill(0, 0, context.getScaledWindowWidth(), context.getScaledWindowHeight(), new Color(0, 0, 0, 120).hashCode());
        this.components.forEach(components -> components.drawScreen(context, mouseX, mouseY, delta));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int clickedButton) {
        this.components.forEach(components -> components.mouseClicked((int) mouseX, (int) mouseY, clickedButton));
        return super.mouseClicked(mouseX, mouseY, clickedButton);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int releaseButton) {
        this.components.forEach(components -> components.mouseReleased((int) mouseX, (int) mouseY, releaseButton));
        return super.mouseReleased(mouseX, mouseY, releaseButton);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (verticalAmount < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        } else if (verticalAmount > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
        return super.mouseScrolled(mouseX, mouseY, horizontalAmount, verticalAmount);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        this.components.forEach(component -> component.onKeyPressed(keyCode));
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        this.components.forEach(component -> component.onKeyTyped(chr, modifiers));
        return super.charTyped(chr, modifiers);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public final ArrayList<Component> getComponents() {
        return this.components;
    }

    public int getTextOffset() {
        return -6;
    }

    public Component getComponentByName(String name) {
        for (Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) continue;
            return component;
        }
        return null;
    }
}