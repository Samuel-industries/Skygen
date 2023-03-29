package api.cache.chat;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.chat.ChatSlowPacket;
import api.redis.BroadcastType;
import api.redis.PacketListener;

public class ChatSlowPacketCache implements PacketListener<ChatSlowPacket> {

    @Override
    public void handle(ChatSlowPacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent();
        BroadcastType broadcastType = packet.getType();
        Config.saveConfig();

        if (broadcastType == BroadcastType.DISPATCH_COMMAND) {
        	  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), content);
        	  
        }          
    }
}