package pl.samuel.skygen.commands.Moderator;


import org.bukkit.command.CommandSender;

import api.messages.Config;
import api.packet.broadcast.BroadcastPacket;
import api.packet.chat.ChatClearPacket;
import api.packet.chat.ChatDisablePacket;
import api.packet.chat.ChatEnablePacket;
import api.packet.chat.ChatVipDisablePacket;
import api.packet.chat.ChatVipEnablePacket;
import api.redis.BroadcastType;
import pl.samuel.skygen.core;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;

public class ChatCommand extends Command
{
    private String player;

    public ChatCommand() {
        super("chat", "zarzadzanie chatem", "/chat <cc|on|off|<vipon>|<vipoff>", "core.cmd.helper", "czat");
    }

    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
      if (args.length < 1) {
      return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
       if (args[0].equalsIgnoreCase("cc")) {
       ChatClearPacket ChatClearPacket;
       ChatClearPacket = new ChatClearPacket("&7Chat zostal &awyczyszczony");
       core.getPlugin().getRedisService().publishAsync("spigot", ChatClearPacket);
            }
       if (args[0].equalsIgnoreCase("on")) {
       if (Config.ENABLE_CHAT) {
       return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Chat jest juz wlaczony!");
            }
       ChatEnablePacket ChatEnablePacket;
       ChatEnablePacket = new ChatEnablePacket("&7Chat zostal &awlaczony");
       core.getPlugin().getRedisService().publishAsync("spigot", ChatEnablePacket);
            }
       if (args[0].equalsIgnoreCase("off")) {
       if (!Config.ENABLE_CHAT) {
       return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Chat jest wylaczony!");
            }
       ChatDisablePacket ChatDisablePacket;
       ChatDisablePacket = new ChatDisablePacket("&7Chat zostal &cwylaczony");
       core.getPlugin().getRedisService().publishAsync("spigot", ChatDisablePacket);
           }
       if (args[0].equalsIgnoreCase("vipon")) {
       ChatVipEnablePacket ChatVipEnablePacket;
       ChatVipEnablePacket = new ChatVipEnablePacket("&7Chat dla vipow zostal &awlaczony");
       core.getPlugin().getRedisService().publishAsync("spigot", ChatVipEnablePacket);
         }
       if (args[0].equalsIgnoreCase("vipoff")) {
       ChatVipDisablePacket ChatVipDisablePacket;
       ChatVipDisablePacket = new ChatVipDisablePacket("&7Chat dla vipow zostal &cwylaczony");
       core.getPlugin().getRedisService().publishAsync("spigot", ChatVipDisablePacket);
                }
       if (args[0].equalsIgnoreCase("slow")) {
       if (args.length < 2) {
       return ChatUtil.sendMessage(sender, ("/chat slow <czas w sekundach>"));
            }
       if (!ChatUtil.isInteger(args[1])) {
       return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7To nie jest liczba");
            }
       final int slow = Config.OTHER_CHATSLOWMODE = Integer.parseInt(args[1]);  
       BroadcastPacket broadcastPacket;
       broadcastPacket = new BroadcastPacket(BroadcastType.DISPATCH_COMMAND, "chat slow " + slow);
       core.getPlugin().getRedisService().publishAsync("spigot", broadcastPacket);
       return ChatUtil.sendMessage(sender, "&7Ustawiles slow chatu na &r" + slow + " &7sekundy!");
            } 
		return false;

    }
}
