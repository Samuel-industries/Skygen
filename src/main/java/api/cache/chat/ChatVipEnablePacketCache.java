package api.cache.chat;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.chat.ChatVipEnablePacket;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatVipEnablePacketCache implements PacketListener<ChatVipEnablePacket> {

    @Override
    public void handle(ChatVipEnablePacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        Config.ENABLE_VIPCHAT = true;
        
        Bukkit.broadcastMessage(ChatUtil.fixColor(content));            
    }
}