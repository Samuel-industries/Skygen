package api.cache.broadcast;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.broadcast.BroadcastEventyPacket;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;

public class BroadcastTurboRangiCache implements PacketListener<BroadcastEventyPacket> {

    public void handle(BroadcastEventyPacket packet) {
    	
        BroadcastType broadcastType = packet.getType();       
        if (broadcastType == BroadcastType.TURBOVOUCHERY_CHAT) {
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Na serwerze aktywowano &C&lDROP RANG"));
            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Czas trwania: &a" + DataUtil.secondsToString(packet.getContent())));
            Bukkit.broadcastMessage("");
            Config.OTHER_VOUCHERY = packet.getContent();
            Config.saveConfig();
        }
    }
    }





