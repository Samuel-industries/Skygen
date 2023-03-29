package pl.samuel.skygen.commands.Helper;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class TeleportCommand extends PlayerCommand
{
    public TeleportCommand() {
        super("teleport", "Teleport do graczy lub koordynaty", "/teleport [kto] <do kogo>  lub  [kto] <x> <y> <z>", "core.cmd.helper", "tp");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        switch (args.length) {
            case 0: {
            return ChatUtil.sendMessage(p, Config.USE(this.getUsage()));
            }
            case 1: {
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!"); 
                }
            p.teleport(o.getLocation());
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Zostales przeteleportowany do gracza &c" + o.getName());
            }
            case 2: {
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
           }
            final Player o2 = Bukkit.getPlayer(args[1]);
            if (o2 == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
                }
            o.teleport(o2.getLocation());
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Przeteleportowales gracza &c" + o.getName() + " &7do gracza &c" + o2.getName());
    
            }
            case 3: {
            final Double x = Double.parseDouble(args[0]);
            final Double y = Double.parseDouble(args[1]);
            final Double z = Double.parseDouble(args[2]);
            if (x.isNaN() && y.isNaN() && z.isNaN()) {
            return ChatUtil.sendMessage(p, "&8[&c&l!&8] &7Koordynaty musza byc liczbami!");
             }
            p.teleport(new Location(p.getWorld(), x, y, z));
           return ChatUtil.sendMessage(p, "&8>> &7Zostales przeteleportowany na kordy &7X: &c" + x + " &7Y: &c" + y + " &7Z: &c" + z);
            }
            case 4: {
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
            }
            final Double x2 = Double.parseDouble(args[1]);
            final Double y2 = Double.parseDouble(args[2]);
            final Double z2 = Double.parseDouble(args[3]);
            if (x2.isNaN() && y2.isNaN() && z2.isNaN()) {
            return ChatUtil.sendMessage(p, "&8[&c&l!&8] &7Koordynaty musza byc liczbami!");
             }
            o.teleport(new Location(o.getWorld(), x2, y2, z2));
            ChatUtil.sendMessage(o, "&8[&C&l!&8] &7Zostales przeteleportowany na kordy &7X: &c" + x2 + " &7Y: &c" + y2 + " &7Z: &c" + z2 + " &7przez &c" + p.getName());
             return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Przeteleportowales gracza &c" + o.getName() + " &7na kordy &7X: &c" + x2 + " &7Y: &c" + y2 + " &7Z: &c" + z2);
            }
            default: {
                return false;
            }
        }
    }
}
