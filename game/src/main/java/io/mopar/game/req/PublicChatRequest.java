package io.mopar.game.req;

import io.mopar.core.Request;
import io.mopar.game.msg.ChatMessage;

/**
 * @author Hadyn Fitzgerald
 */
public class PublicChatRequest extends Request {

    private int playerId;
    private ChatMessage message;

    public PublicChatRequest(int playerId, ChatMessage message) {
        this.playerId = playerId;
        this.message = message;
    }

    public int getPlayerId() {
        return playerId;
    }

    public ChatMessage getMessage() {
        return message;
    }
}
