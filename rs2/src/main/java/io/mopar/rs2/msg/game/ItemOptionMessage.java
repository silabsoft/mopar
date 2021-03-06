package io.mopar.rs2.msg.game;

import io.mopar.core.msg.Message;

/**
 * @author Hadyn Fitzgerald
 */
public class ItemOptionMessage extends Message {
    private int widgetId;
    private int componentId;
    private int itemId;
    private int slot;
    private int option;

    public ItemOptionMessage(int widgetId, int componentId, int itemId, int slot, int option) {
        this.widgetId = widgetId;
        this.componentId = componentId;
        this.itemId = itemId;
        this.slot = slot;
        this.option = option;
    }

    public int getWidgetId() {
        return widgetId;
    }

    public int getComponentId() {
        return componentId;
    }

    public int getItemId() {
        return itemId;
    }

    public int getSlot() {
        return slot;
    }

    public int getOption() {
        return option;

    }
}
