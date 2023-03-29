package pl.samuel.skygen.commands.HeadAdmin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import api.packet.player.UpdatePlayerPacket;
import pl.samuel.skygen.core;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
public class IsCommand extends Command
{
    public IsCommand() {
        super("is", "Wiadomosc o zakupie", "/is <gracz> sponsor|vip|coins ilosc|unban|svip|", "core.cmd.admin");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        final String name = args[0];
        final String s = args[1];
        switch (s) {
        case "vip": {
        Bukkit.broadcastMessage("&7Gracz &r" + name + " &7kupil range &6&lVIP");
        Bukkit.broadcastMessage("&aDziekujemy za wsparcie serwera!");   
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set vip");
        return true;
         }
       case "sponsor": {
       Bukkit.broadcastMessage("&7Gracz &r" + name + " &7kupil range &9&LSPONSOR");
       Bukkit.broadcastMessage("&aDziekujemy za wsparcie serwera!");
       Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set sponsor");
       return true;
       }
       case "svip": {
       Bukkit.broadcastMessage("&7Gracz &r" + name + " &7kupil range &a&lSVIP");
       Bukkit.broadcastMessage("&aDziekujemy za wsparcie serwera!");
       Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + name + " group set svip");
       return true;
       }
       case "unban": {
        Bukkit.broadcastMessage("&7Gracz &r" + name + " &7zakupil &c&lUnbana");
        Bukkit.broadcastMessage("&aDziekujemy za wsparcie serwera!");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "unban " + name);
        return true;
       }
        case "coins": {
        if (args.length < 3) {
        return ChatUtil.sendMessage(sender, Config.USE("/is <nick> coins <ilosc>"));
        }
        if (!ChatUtil.isInteger(args[2])) {
        return ChatUtil.sendMessage(sender, "&cTo nie liczba!");
        }
       final int amount = Integer.parseInt(args[2]);
       final User u = UserManager.getUser(name);
       Bukkit.broadcastMessage( "&7Gracz &r" + name + " &7kupil &rCoins  &7x&r" + amount);
       Bukkit.broadcastMessage("&aDziekujemy za wsparcie serwera!");
       u.addCoins(amount);
       UpdatePlayerPacket UpdatePlayerPacket;
       UpdatePlayerPacket = new UpdatePlayerPacket(u.getName());
       core.getPlugin().getRedisService().publishAsync("spigot", UpdatePlayerPacket);                                     
       return true;
       }
      default: {
      return false;
            }
        }
    }
}

