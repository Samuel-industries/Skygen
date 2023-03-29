package pl.samuel.skygen.listeners;

import api.data.base.user.User;
import api.managers.User.UserManager;
import pl.samuel.skygen.utils.ChatUtil;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.bukkit.help.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PlayerCommandPreprocessListener implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent event) {
		final Player p = event.getPlayer();
		final String cmd = event.getMessage().split(" ")[0];
		final HelpTopic topic = Bukkit.getServer().getHelpMap().getHelpTopic(cmd);
		if (event.isCancelled()) {
			return;
		}
		if (topic != null) {
			return;
		}
		event.setCancelled(true);
		ChatUtil.sendMessage(p, "§8§ §7Nie odnaleziono Komendy §c{command}!".replace("{command}", cmd).replaceAll("//", "/").replaceAll("%newline%", "\n"));
		ChatUtil.sendMessage(p, "§8§ &7Wpisz &c/Pomoc &7aby zobaczyc komendy");
	}


	
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onPlayerCommandPreprocess1(final PlayerCommandPreprocessEvent event) {
		final Player player = event.getPlayer();
		final User u = UserManager.getUser(player);
		if (!player.hasPermission("core.plugins")) {
		final String message = event.getMessage();
		final String[] splittedMessage = message.split(" ");
		final String[] pluginCommands = { "/pl", "/plugins", "/?", "//?", "//", "/help", "/bukkit:help", "/skript",
		"//calc", "/worldedit:/calc", "/worldedit:/calculate", "//eval", "/worldedit:/solve", "//evaluate",
		"/worldedit:/eval", "/worldedit:/evaluate", "//solve", "//deop", "//solve", "//calculate",
		"/logout", "/bukkit:ban", "bukkit:ban", "logout", "sk", "/sk", "/help", "/about", "/bukkit:about",
		"/ver", "/version", "/bukkit:ver", "/bukkit:version", "/bukkit:?", "/logout", "/me", "/bukkit:me",
		"/say", "/bukkit:say", "/sk" };
		if (containsIgnoreCase(pluginCommands, splittedMessage[0])) {
		event.setCancelled(true);
		ChatUtil.sendMessage(player, "§8§ §7Nie odnaleziono Komendy §c{command}!".replace("{command}", message).replaceAll("//", "/").replaceAll("%newline%", "\n"));
		ChatUtil.sendMessage(player, "§8§ &7Wpisz &c/Pomoc &7aby zobaczyc komendy");
			}
		}
	}


	public boolean containsIgnoreCase(final String[] board, final String string) {
		for (final String otherstring : board) {
			if (otherstring.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsIgnoreCase(final List<String> board, final String string) {
		for (final String otherstring : board) {
			if (otherstring.equalsIgnoreCase(string)) {
				return true;
			}
		}
		return false;
	}
}
