package api.cache.broadcast;

import org.bukkit.Bukkit;

import api.messages.Config;
import api.packet.broadcast.BroadcastEventyPacket;
import api.redis.BroadcastType;
import api.redis.PacketListener;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;

public class BroadcastTurboCoinsCache implements PacketListener<BroadcastEventyPacket> {

    public void handle(BroadcastEventyPacket packet) {
    	
        BroadcastType broadcastType = packet.getType();       
        if (broadcastType == BroadcastType.TURBOCOINS_CHAT) {
            Bukkit.broadcastMessage("");
            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Na serwerze aktywowano &C&lTurbo Coins"));
            Bukkit.broadcastMessage(ChatUtil.fixColor(" &8 &7Czas trwania: &a" + DataUtil.secondsToString(packet.getContent())));
            Bukkit.broadcastMessage("");
            Config.OTHER_TURBOCOINS2 = packet.getContent();
            Config.saveConfig();
        }
    }
    }





