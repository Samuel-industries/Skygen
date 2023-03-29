
package pl.samuel.skygen.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;

import api.data.base.user.User;
import api.managers.User.SyncEqManager;
import api.managers.User.UserManager;
import api.redis.data.CorePlayer;
import api.redis.data.CorePlayerCache;

public class PlayerQuitJoinListener implements Listener {
	
	private final CorePlayerCache corePlayerCache = new CorePlayerCache();

	@EventHandler
	public void onJoin2(final PlayerJoinEvent e) {
		final Player p = e.getPlayer();
		UserManager.LoadPlayerData(p);
		e.setJoinMessage(null);
		p.updateInventory();
		p.setGameMode(GameMode.SURVIVAL);
		SyncEqManager.downloadPlayerInfo(p);	
	    CorePlayer user = corePlayerCache.findByPlayer(p);
	    if (user == null) {
	    user = new CorePlayer(p.getUniqueId(), p.getName());
	    corePlayerCache.add(user);
	    System.out.println("dodano usera cache");
	        }
	        
	    }

	@EventHandler
	public void onKick(final PlayerKickEvent e) {
		final Player p = e.getPlayer();
		final User u = UserManager.getUser(p);
		e.setLeaveMessage(null);
		u.save();
		SyncEqManager.saveDataPlayer(p);
		for (PotionEffect effect : p.getActivePotionEffects()) {
		p.removePotionEffect(effect.getType());
		}
	}

	@EventHandler
	public void onQuit(final PlayerQuitEvent e) {
		final Player p = e.getPlayer();
		final User u = UserManager.getUser(p);
		e.setQuitMessage(null);
		u.save();
		SyncEqManager.saveDataPlayer(p);
		for (PotionEffect effect : p.getActivePotionEffects()) {
		p.removePotionEffect(effect.getType());
		}
	}

}
