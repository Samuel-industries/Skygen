package pl.samuel.skygen.commands.HeadAdmin;


import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import api.messages.Config;
import api.packet.broadcast.BroadcastEventyPacket;
import api.redis.BroadcastType;
import pl.samuel.skygen.core;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;

public class EventCommand extends Command
{
    public EventCommand() {
        super("event", "Komenda do eventow", "/event vouchery [czas]", "core.cmd.headadmin");
    }
    
    

public static final String TAG = "BP|UpdateBossInfo";




    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        if (!args[0].equalsIgnoreCase("rangi")) {
            return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        final long time = DataUtil.parseDateDiff(args[1], true);
        if (args[0].equalsIgnoreCase("rangi")) {
        Config.OTHER_VOUCHERY = time;
        BroadcastEventyPacket BroadcastEventyPacket;
        BroadcastEventyPacket = new BroadcastEventyPacket(BroadcastType.TURBOVOUCHERY_CHAT, time);
        core.getPlugin().getRedisService().publishAsync("spigot", BroadcastEventyPacket);           
        	}
        return true;
    }


}