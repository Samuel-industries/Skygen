package api.packet.player;
import api.redis.Packet;

public class UpdatePlayerPacket implements Packet {

   private final String content;

    public UpdatePlayerPacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}