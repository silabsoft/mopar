package io.mopar.game.action;

import io.mopar.game.model.Player;

/**
 * @author Hadyn Fitzgerald
 */
public interface ButtonMenuActionHandler<T> {
    void handle(Player player, int widgetId, int componentId, int childId, int option);
}
