package pl.samuel.skygen.commands.Admin;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class StpCommand extends PlayerCommand
{
    public StpCommand() {
        super("stp", "stp", "/stp <gracz>", "core.cmd.helper", "tphere", "s");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
        return ChatUtil.sendMessage(p, Config.USE(this.getUsage()));
        }
        final String nickja = args[0];
        if (nickja.equalsIgnoreCase(p.getName())) {
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Nie mozesz przeteleportowac sie sam do siebie! ;(");
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
        }
        o.teleport(p.getLocation());
        ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Przeteleportowales gracza &c" + o.getName() + " &7do gracza &c" + p.getName());
        return ChatUtil.sendMessage(o, "&8[&C&l!&8] &7Zostales przeteleportowany do gracza &c" + p.getName());
    }
}
