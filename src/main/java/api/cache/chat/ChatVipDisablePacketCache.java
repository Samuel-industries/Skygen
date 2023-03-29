package api.cache.chat;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.chat.ChatVipDisablePacket;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatVipDisablePacketCache implements PacketListener<ChatVipDisablePacket> {

    @Override
    public void handle(ChatVipDisablePacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        Config.ENABLE_VIPCHAT = false;
        Config.saveConfig();
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}