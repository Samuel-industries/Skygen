package pl.samuel.skygen.commands.Admin;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class GamemodeCommand extends Command {
	public GamemodeCommand() {
		super("gamemode", "Zmiana trybu gry graczy", "/gamemode [gracz] <tryb>", "core.cmd.admin", "gm", "gmode");
	}

	@Override
	public boolean onExecute(final CommandSender sender, final String[] args) {
		if (args.length < 1) {
		return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
		}
		if (this.getMode(args[0]) == null) {
	    return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cTryb gamemode nie odnaleziono!");
		}
		if (args.length == 1) {
	    final Player p = (Player) sender;
	    p.setGameMode(this.getMode(args[0]));
	    return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Twoj tryb gamemode zostal zmieniony na &c" + p.getGameMode().toString().toLowerCase() + "&7!");
		}
		if (args.length != 2) {
		return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
		}
		if (!sender.hasPermission("core.cmd.Headadmin")) {
		return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cNie masz uprawnien!");
		}
		final Player o = Bukkit.getPlayer(args[1]);
		if (o == null) {
		return ChatUtil.sendMessage(sender,"&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
		}
		o.setGameMode(this.getMode(args[0]));
		ChatUtil.sendMessage(o, "&8[&C&l!&8] &7Twoj tryb gamemode zostal zmieniony na &c" + o.getGameMode().toString().toLowerCase() + "&7 przez &c" + sender.getName() + "&7!");
		return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Zmieniles tryb gamemode graczowi &c" + o.getName() + " &7na &c" + o.getGameMode().toString().toLowerCase() + "&7!");
	}

	private GameMode getMode(final String args) {
	if (args.equalsIgnoreCase("1") || args.equalsIgnoreCase("creative")) {
	return GameMode.CREATIVE;
	}
	if (args.equalsIgnoreCase("0") || args.equalsIgnoreCase("survival")) {
	return GameMode.SURVIVAL;
	}
	if (args.equalsIgnoreCase("2") || args.equalsIgnoreCase("adventure")) {
	return GameMode.ADVENTURE;
	}
    if (args.equalsIgnoreCase("3") || args.equalsIgnoreCase("spectator")) {
	return GameMode.SPECTATOR;
}
	return null;
	}
}
