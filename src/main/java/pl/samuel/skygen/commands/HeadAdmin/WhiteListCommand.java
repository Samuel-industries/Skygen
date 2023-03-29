package pl.samuel.skygen.commands.HeadAdmin;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;



public class WhiteListCommand extends Command
{
    public WhiteListCommand() {
        super("whitelist", "whitelist serwera", "/whitelist <on|off|add|remove|list> [gracz]", "core.cmd.headadmin", "wl");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
            return ChatUtil.sendMessage(sender, Config.USE("/wl <add|remove|list|reason|on|off>"));
        }               
        if (args[0].equalsIgnoreCase("on")) {
            if (Config.ENABLE_WHITELIST) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest juz on!");
           }
            Config.ENABLE_WHITELIST = true;
            Config.saveConfig();
            return ChatUtil.sendMessage(sender, "&8» &aWhitelist zostala wlaczona!");
        }        
        if (args[0].equalsIgnoreCase("off")) {
            if (!Config.ENABLE_WHITELIST) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWhitelist jest off!");
            }
            Config.ENABLE_WHITELIST = false;
            Config.saveConfig();
            return ChatUtil.sendMessage(sender, "&8» &cWhitelist zostala wylaczona!");
        }       
        if (args[0].equalsIgnoreCase("add")) {
            final String nick = args[1];
            if (Config.WL_LIST.contains(nick)) {
            return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &c" + nick + " &7jest juz na whitelist!");
            }
            Config.WL_LIST.add(nick);
            Config.saveConfig();
            return ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal dodany do whitelist!");
            }
       if (args[0].equalsIgnoreCase("remove")) {
           final String nick = args[1];
           if (!Config.WL_LIST.contains(nick)) {
           return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7" + nick + " nie jest na whitelist!");
            }
           Config.WL_LIST.remove(nick);
           Config.saveConfig();
           return ChatUtil.sendMessage(sender, "&8» &7Gracz &c" + nick + " &7zostal usuniety z whitelist!");
            }
       if (args[0].equalsIgnoreCase("reason")) {
          final String reason = StringUtils.join(args, " ", 1, args.length);
          Config.WL_REASON = ChatUtil.fixColor(reason);
          Config.saveConfig();
          return ChatUtil.sendMessage(sender, "&8» &7Ustawiles powod whitelist na: &c" + reason);
    }
      if (args[0].equalsIgnoreCase("list")) {
          return ChatUtil.sendMessage(sender, "&8» &7Lista graczy na whitelist: &c" + StringUtils.join(Config.WL_LIST, "&c, &7"));
            }     
		return true;
    }
}
        
    

