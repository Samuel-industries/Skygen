package api.cache.chat;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.chat.ChatDisablePacket;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatDisablePacketCache implements PacketListener<ChatDisablePacket> {

    @Override
    public void handle(ChatDisablePacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        Config.ENABLE_CHAT = false;
        Config.saveConfig();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}