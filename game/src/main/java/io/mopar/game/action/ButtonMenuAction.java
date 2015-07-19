package io.mopar.game.action;

import io.mopar.game.model.Player;

/**
 * @author Hadyn Fitzgerald
 */
public interface ButtonMenuAction<T> {
    void handle(Player player, int a, int option);
}