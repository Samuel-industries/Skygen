package api.packet.broadcast;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastEventyPacket implements Packet {

    private final BroadcastType type;
    private final long content;

    public BroadcastEventyPacket(BroadcastType type, long content) {
        this.type = type;
        this.content = content;
    }

    public BroadcastType getType() {
        return type;
    }

    public long getContent() {
        return content;
    }

}
