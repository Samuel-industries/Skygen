package pl.samuel.skygen.listeners;

import api.messages.Config;
import pl.samuel.skygen.utils.ChatUtil;
import api.messages.Config;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class CheckLoginListener implements Listener {
	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onLogin(final PlayerLoginEvent e) {
		final Player p = e.getPlayer();
		if (Config.ENABLE_WHITELIST && !Config.WL_LIST.contains(p.getName())) {
		e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, "\n" + ChatUtil.fixColor(Config.WL_REASON));
		return;
		}
		if (!p.hasPermission("core.cmd.premium") && Bukkit.getOnlinePlayers().size() >= Config.OTHER_SLOT) {
		e.disallow(PlayerLoginEvent.Result.KICK_FULL, ChatUtil.fixColor("\n" + Config.MSG_FULLSERVER));
		}
	}

}
