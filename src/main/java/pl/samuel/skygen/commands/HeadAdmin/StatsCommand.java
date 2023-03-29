package pl.samuel.skygen.commands.HeadAdmin;

import org.bukkit.entity.Player;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import api.packet.player.UpdatePlayerPacket;
import pl.samuel.skygen.core;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class StatsCommand extends PlayerCommand
{
    public StatsCommand() {
        super("stats", "stats", "/stats <gracz> <kills/deaths/points/logouts/asysts/restart/coins> <ilosc>", "core.cmd.admin");
}

public boolean onCommand(final Player sender, final String[] args) {
    if (args.length < 2) {
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
    }
    final User u = UserManager.getUser(args[0]);
    if (u == null) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
    }
    if (!ChatUtil.isInteger(args[2])) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cTo nie liczba!");
    }
    final int i = Integer.parseInt(args[2]);
	if (args[1].equalsIgnoreCase("deaths")) {
	u.setDeaths(i);
	UpdatePlayerPacket UpdatePlayerPacket;
	UpdatePlayerPacket = new UpdatePlayerPacket(u.getName());
    core.getPlugin().getRedisService().publishAsync("spigot", UpdatePlayerPacket);
	return ChatUtil.sendMessage(sender, "&8» &7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
    }
    if (args[1].equalsIgnoreCase("kills")) {
    u.setKills(i);
    UpdatePlayerPacket UpdatePlayerPacket;
    UpdatePlayerPacket = new UpdatePlayerPacket(u.getName());
    core.getPlugin().getRedisService().publishAsync("spigot", UpdatePlayerPacket);
    return ChatUtil.sendMessage(sender, "&8» &7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
    }
    if (args[1].equalsIgnoreCase("restart")) {
    u.setKills(0);
    u.setDeaths(0);
    UpdatePlayerPacket UpdatePlayerPacket;
    UpdatePlayerPacket = new UpdatePlayerPacket(u.getName());
    core.getPlugin().getRedisService().publishAsync("spigot", UpdatePlayerPacket);
    return ChatUtil.sendMessage(sender, "&8» &7Resetowales staty gracza &c" + u.getName());
    }
    if (args[1].equalsIgnoreCase("coins")) {
    u.setCoins(i);       
    UpdatePlayerPacket UpdatePlayerPacket;
    UpdatePlayerPacket = new UpdatePlayerPacket(u.getName());
   core.getPlugin().getRedisService().publishAsync("spigot", UpdatePlayerPacket);
    return ChatUtil.sendMessage(sender, "&8» &7Ustawiles &c" + args[1] + " &7na &c" + i + " &7graczowi &c" + u.getName());
    }
	return true;
    }
}
