package api.cache.chat;

import org.bukkit.Bukkit;

import api.packet.chat.ChatClearPacket;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatClearPacketCache implements PacketListener<ChatClearPacket> {

    @Override
    public void handle(ChatClearPacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));
        for (int i = 0; i < 100; ++i) {
            Bukkit.getServer().broadcastMessage("");
      }
    }
}