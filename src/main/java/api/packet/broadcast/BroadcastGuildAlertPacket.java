package api.packet.broadcast;

import org.bukkit.entity.Player;

import api.redis.BroadcastType;
import api.redis.Packet;

public class BroadcastGuildAlertPacket implements Packet {
    private final String message;
    private final String guildTag;
    private final String playerName;
    private final BroadcastType type;
    
    public BroadcastGuildAlertPacket(BroadcastType type, String message, String guildTag,String playerName) {
        this.message = message;
        this.type = type;
        this.guildTag = guildTag;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
    
    public String getMessage() {
        return message;
    }
    
	public BroadcastType getType() {
		return type;
	}

	public String getGuildTag() {
		 return guildTag;
    
	}
}
