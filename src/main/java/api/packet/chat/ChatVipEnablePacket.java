package api.packet.chat;
import api.redis.Packet;

public class ChatVipEnablePacket implements Packet {

   private final String content;

    public ChatVipEnablePacket(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}