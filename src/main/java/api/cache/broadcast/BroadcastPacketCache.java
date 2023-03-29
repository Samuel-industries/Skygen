package api.cache.broadcast;

import org.bukkit.Bukkit;

import api.packet.broadcast.BroadcastPacket;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.samuel.skygen.utils.ChatUtil;


public class BroadcastPacketCache implements PacketListener<BroadcastPacket> {

    public void handle(BroadcastPacket packet) {
    	
        BroadcastType broadcastType = packet.getType();       
        if (broadcastType == BroadcastType.CHAT) {
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8[&cI&8] &7Wiadomosc Globalna:&c" + packet.getContent()));
            return;
        }
        if (broadcastType == BroadcastType.TITLE) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> ChatUtil.sendTitle(p, packet.getContent(), " ", 0, 0, 0));
            return;
        }
        if (broadcastType == BroadcastType.SUBTITLE) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> ChatUtil.sendTitle(p, " ", packet.getContent(), 0, 0, 0));
            return;
        }
        if (broadcastType == BroadcastType.ACTIONBAR) {
            Bukkit.getServer().getOnlinePlayers().forEach(p -> p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(packet.getContent())));
        }
    }
    }





