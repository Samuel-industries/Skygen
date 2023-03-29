package api.packet.chat;

import api.redis.BroadcastType;
import api.redis.Packet;

public class ChatSlowPacket implements Packet {

    private final BroadcastType type;
    private final String content;

    public ChatSlowPacket(BroadcastType type, String content) {
        this.type = type;
        this.content = content;
    }

    public BroadcastType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

}
