package pl.samuel.skygen.commands.Admin;

import org.bukkit.command.CommandSender;

import api.messages.Config;
import api.packet.broadcast.BroadcastEventyPacket;
import api.redis.BroadcastType;
import pl.samuel.skygen.core;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;
public class TurboCoinsCommand extends Command
{
   
    

    
    public TurboCoinsCommand() {
        super("turbo", "Turbocoinsy na serwerze", "/turbo [Czas trwania turbomonet]", "core.cmd.turbomoneta");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }   
        final long time = DataUtil.parseDateDiff(args[0], true);
        BroadcastEventyPacket BroadcastEventyPacket;
        BroadcastEventyPacket = new BroadcastEventyPacket(BroadcastType.TURBOCOINS_CHAT, time);
        core.getPlugin().getRedisService().publishAsync("spigot", BroadcastEventyPacket);      
        return true;
    }
}