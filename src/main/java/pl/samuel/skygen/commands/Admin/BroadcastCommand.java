package pl.samuel.skygen.commands.Admin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import api.messages.Config;
import api.packet.broadcast.BroadcastPacket;
import api.redis.BroadcastType;
import pl.samuel.skygen.core;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
public class BroadcastCommand extends Command
{
    public BroadcastCommand() {
        super("broadcast", "ogloszenie do graczy", "/broadcast <title/sb>/actionbar/chat>", "core.cmd.moderator", "bc", "bcast");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        if (args[0].equalsIgnoreCase("chat")) {
        BroadcastPacket broadcastPacket;
        broadcastPacket = new BroadcastPacket(BroadcastType.CHAT, StringUtils.join(args, " ").replace("chat", ""));
        core.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);           
    }
       if (args[0].equalsIgnoreCase("actionbar")) {
       BroadcastPacket broadcastPacket;
       broadcastPacket = new BroadcastPacket(BroadcastType.ACTIONBAR, StringUtils.join(args, " ").replace("actionbar", ""));
        core.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);
    }
       if (args[0].equalsIgnoreCase("title")) {
       BroadcastPacket broadcastPacket;
       broadcastPacket = new BroadcastPacket(BroadcastType.TITLE, StringUtils.join(args, " ").replace("title", ""));
       core.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);
    }
      if (args[0].equalsIgnoreCase("sb")) {
      BroadcastPacket broadcastPacket;
      broadcastPacket = new BroadcastPacket(BroadcastType.SUBTITLE, StringUtils.join(args, " ").replace("sb", ""));
      core.getPlugin().getRedisService().publishAsync("broadcast", broadcastPacket);
}
    return true;
}
}
