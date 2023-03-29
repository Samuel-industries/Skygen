package api.managers.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import api.data.base.Database;
import api.data.base.user.User;
import api.messages.Config;
import api.rankings.tops.CoinsManager;
import api.rankings.tops.DeathManager;
import api.rankings.tops.KillManager;
import pl.samuel.skygen.core;
import pl.samuel.skygen.utils.Logger;

public class UserManager {
	public static final ConcurrentHashMap<String, User> users;
	private static List<User> online;

	static {
	users = new ConcurrentHashMap<String, User>();
	}

	public static User getUser(final String name) {
	for (final User u : UserManager.users.values()) {
	if (u.getName().equalsIgnoreCase(name)) {
	return u;
		}
		}
  return null;
	}

	public static List<User> getOnline() {
	return new ArrayList<User>(UserManager.online);
	}

	public static User getUser(Player p){
	if(users.containsKey(p.getName())) return users.get(p.getName());
	else return createUser(p);
	}

	public static User createUser(Player p){
	User user = new User(p);		
	users.put(p.getName(), user);
	return user;
	}

	
	///POTEZNE SYNC 
	public  void LoadPlayerData(final String player) {
		final Long startTime = System.currentTimeMillis();
		final User u = UserManager.getUser(player);
		if (Config.OTHER_debug) {
		Logger.warning("ladowanie danych gracza " + player);	
		}	
		Bukkit.getScheduler().runTask(core.getPlugin(), new Runnable() {
		@Override
		public void run() {
		try {
		final ResultSet rs = Database.getQueryResult("SELECT * FROM `users` WHERE `name` = '" + player + "'  LIMIT 1;");
		if (rs.next()) {					
		if (Config.OTHER_debug) {
		Logger.warning("Znaleziono dane gracza.");
		}
		u.setTeczowy(rs.getBoolean("teczowy"));
		u.setKills(rs.getInt("kills"));
	    u.setDeaths(rs.getInt("deaths"));			                
	    u.setCoins(rs.getInt("coins"));
		u.setFirstIP(rs.getString("firstIP"));
	    u.setLastKill(rs.getString("lastKill"));
		u.setLastKillTime(rs.getInt("lastKillTime"));
		u.setGod(rs.getBoolean("god"));
		u.setVanish(rs.getBoolean("vanish"));
	    u.setTurboCoins(rs.getInt("TurboCoins"));			
		} else {
		Bukkit.getScheduler().runTaskLater(core.getPlugin(), new Runnable() {
		@Override
		public void run() {				
		u.insert();									
		KillManager.addKill(u);
		DeathManager.addDeath(u);
		CoinsManager.addCoins(u);
		if (Config.OTHER_debug) {
		Logger.warning("utworzono " + player);	
		}
		}
		}, 0);					
		rs.close();
		if (Config.OTHER_debug) {
		final Long startTime2 = System.currentTimeMillis() - startTime;
		Logger.warning("wykonano akcje w " + startTime2 + "ms");
		}
			}
		} catch (SQLException e) {
		e.printStackTrace();
				}
			}
		});
	}


	public  void LoadPlayerData(final Player p) {
		final Long startTime = System.currentTimeMillis();
		final User u = UserManager.getUser(p);
		if (Config.OTHER_debug) {
			Logger.warning("ladowanie danych gracza " + p.getName());	
		}
		Bukkit.getScheduler().runTask(core.getPlugin(), new Runnable() {
		@Override
		public void run() {
		try {									
		final ResultSet rs = Database.getQueryResult("SELECT * FROM `users` WHERE `name` = '" + p.getName() + "'  LIMIT 1;");	   
		if (rs.next()) {					
		if (Config.OTHER_debug) {
		Logger.warning("Znaleziono dane gracza.");
		}						
		u.setTeczowy(rs.getBoolean("teczowy"));
		u.setKills(rs.getInt("kills"));
		u.setDeaths(rs.getInt("deaths"));			                
	    u.setCoins(rs.getInt("coins"));
	    u.setFirstIP(rs.getString("firstIP"));
	    u.setLastKill(rs.getString("lastKill"));
	    u.setLastKillTime(rs.getInt("lastKillTime"));
	    u.setGod(rs.getBoolean("god"));
		u.setVanish(rs.getBoolean("vanish"));
	    u.setTurboCoins(rs.getInt("TurboCoins"));					
		} else {
		Bukkit.getScheduler().runTaskLater(core.getPlugin(), new Runnable() {
		@Override
		public void run() {									
		u.insert();									
		KillManager.addKill(u);
		DeathManager.addDeath(u);
		CoinsManager.addCoins(u);
		if (Config.OTHER_debug) {
		Logger.warning("Utworzono " + p.getName());
		}
		}
		}, 0);					
		rs.close();
		if (Config.OTHER_debug) {
		final Long startTime2 = System.currentTimeMillis() - startTime;
		Logger.warning("wykonano akcje w " + startTime2 + "ms");
		}
		}
		} catch (SQLException e) {
		e.printStackTrace();
				}
			}
		});

	}

	public  void loadUsers() {
		try {
		final ResultSet rs = Database.query("SELECT * FROM `users`");
		while (rs.next()) {
		final User u = new User(rs);
		UserManager.users.put(u.getName(), u);
		KillManager.addKill(u);
		DeathManager.addDeath(u);
		CoinsManager.addCoins(u);
		}
		rs.close();
		Logger.info("Zaladowano " + UserManager.users.size() + " players");			
		KillManager.getKills();
		KillManager.sortUserKills();
		DeathManager.getInst();
		DeathManager.sortUserDeaths();
		CoinsManager.getInst();
		CoinsManager.sortUserCoins();		
		} catch (SQLException e) {
		Logger.info("Can not load players Error " + e.getMessage());
		e.printStackTrace();
		}
	}

	public  void deleteUser(final User u) {
	UserManager.users.remove(u.getName());
	KillManager.removeKill(u);
	DeathManager.removeDeath(u);
	CoinsManager.removeCoins(u);		
	Database.update(false, "DELETE FROM `users` WHERE `name` = '" + u.getName() + "'");
	}


	public static ConcurrentHashMap<String, User> getUsers() {
		return UserManager.users;
	}

}
