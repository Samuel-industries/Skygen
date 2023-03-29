package api.cache.chat;

import org.bukkit.Bukkit;

import api.packet.chat.ChatPacket;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatPacketCache implements PacketListener<ChatPacket> {

    @Override
    public void handle(ChatPacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));
    }
}