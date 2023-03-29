package pl.samuel.skygen.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import api.packet.player.UpdatePlayerPacket;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import pl.samuel.skygen.core;
import pl.samuel.skygen.utils.ChatUtil;

public class PlayerDeathListener implements Listener {

    static LuckPerms api = LuckPermsProvider.get();
	

	@EventHandler(priority = EventPriority.HIGH)
	public void onDeath(final PlayerDeathEvent eve) {
		eve.setDeathMessage(null);
		final Player p = eve.getEntity();		
		final User user = UserManager.getUser(p);
        final Player k = p.getKiller();
    	Bukkit.getScheduler().runTaskLater(core.getPlugin(), new Runnable() {
			@Override
			public void run() {	
				p.setGameMode(GameMode.ADVENTURE);
				p.setHealth(20.0);
				p.setFoodLevel(20);
				p.setExp(0.0f);
				eve.setDroppedExp(0);
				user.addDeaths(1);
				user.save();
				eve.setDeathMessage(null);	
			    UpdatePlayerPacket uzytkownik;
			    uzytkownik = new UpdatePlayerPacket(user.getName());
	            core.getPlugin().getRedisService().publishAsync("spigot", uzytkownik);			  
			}
			}, 10);
	      if (k != null) { if (user == null || p.equals(k)) { return; }
		   final User kUser = UserManager.getUser(k);		
		   final User v2 = UserManager.getUser(p);		
		   kUser.addKills(1);
		   kUser.setLastKill(p.getName());
		   kUser.save();		
		   UpdatePlayerPacket ofiara;
		   ofiara = new UpdatePlayerPacket(v2.getName());
           core.getPlugin().getRedisService().publishAsync("spigot", ofiara);               
           UpdatePlayerPacket killer;
           killer = new UpdatePlayerPacket(kUser.getName());
           core.getPlugin().getRedisService().publishAsync("spigot", killer);
		   ChatUtil.sendTitle(k.getPlayer(), "", ChatUtil.fixColor("&7zabiles &f" + (v2.getName())), 55, 55, 55);		
		   Bukkit.broadcastMessage(ChatUtil.fixColor("&7Gracz &r" + (v2.getName())) + "&7zostal zabity przez &r" + kUser.getName());			
			kUser.addCoins(10);
			if (Config.OTHER_TURBOCOINS2 > System.currentTimeMillis()) {
			kUser.addCoins(20);
	}
	     }
	}	
}