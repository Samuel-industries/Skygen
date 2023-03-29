package api.cache.chat;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.chat.ChatEnablePacket;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatEnablePacketCache implements PacketListener<ChatEnablePacket> {

    @Override
    public void handle(ChatEnablePacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        Config.ENABLE_CHAT = true;
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}