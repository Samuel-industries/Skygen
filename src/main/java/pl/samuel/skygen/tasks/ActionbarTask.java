package pl.samuel.skygen.tasks;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;
import pl.samuel.skygen.utils.TimerManager;

public class ActionbarTask implements Runnable {


	public void run() {
		try {			
			Bukkit.getOnlinePlayers().forEach(p -> {
				final User v2 = UserManager.getUser(p);
				String messages;
				messages = "";
				if (v2.isVanish()) {
				if (!messages.isEmpty()) {
				messages = messages + " &8| ";
				}
				messages = messages + "&fJestes w trybie &aVanish";
				}		
				if (Config.OTHER_TURBOCOINS2 > System.currentTimeMillis()) {
				Config.OTHER_TURBOCOINS = false;
				if (!messages.isEmpty()) {
				messages = messages + " &8| ";
				}
				messages = messages + "&fTURBOCOINS &8(&a" + DataUtil.secondsToString(Config.OTHER_TURBOCOINS2) + "&8)";
				}
				if (Config.OTHER_VOUCHERY > System.currentTimeMillis()) {
				if (!messages.isEmpty()) {
				messages = messages + " &8| ";
				}
				messages = messages + "&fDROP RANG &8(&a" + DataUtil.secondsToString(Config.OTHER_VOUCHERY) + "&8)";
				}
	            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatUtil.fixColor(messages)));

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start(final ScheduledExecutorService executor) {
	TimerManager.executors.put("actionbar",
	executor.scheduleAtFixedRate(this, 0L, 10L, TimeUnit.MILLISECONDS));
	}
}
