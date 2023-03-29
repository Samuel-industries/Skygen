
package api.managers.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import api.data.base.Database;
import api.messages.Config;
import pl.samuel.skygen.core;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.ItemBuilder1;
import pl.samuel.skygen.utils.ItemUtil;
import pl.samuel.skygen.utils.Logger;
import pl.samuel.skygen.utils.PotionUtil;

public class SyncEqManager {

	public void saveDataPlayer (final Player p) {
	final Long startTime = System.currentTimeMillis();
	if (Config.OTHER_debug) {
	Logger.warning("Trwa zapisywanie " + p.getName());
	}
	final String is = ItemUtil.itemStackArrayToBase64(p.getInventory().getContents());
	final String ec = ItemUtil.itemStackArrayToBase64(p.getEnderChest().getContents());
	final String potions = PotionUtil.serializePotionEffects(p);
	final double y = p.getLocation().getY() + 1.0;		
	final String armorr = ItemUtil.itemStackArrayToBase64(p.getInventory().getArmorContents());
	Database.executeQuery("DELETE FROM `player_data` WHERE `nick` = '" + p.getName() + "'");
	Database.executeQuery("INSERT INTO `player_data` (`id`, `nick`, `x`, `y`, `z`, `yaw`, `pitch`, `hp`, `gamemode`, `inventory`, `armor`, `enderchest`, `potions`, `level`, `exp`) VALUES (NULL, '" + p.getName() + "', '" + p.getLocation().getX() + "', '" + y + "', '" + p.getLocation().getZ() + "', '" + p.getLocation().getYaw() + "', '" + p.getLocation().getPitch() + "', '" + p.getHealth() +     "', '"  + p.getGameMode().getValue() + "', '"   + is + "', '" + armorr + "', '" + ec + "', '" + potions + "', '" + p.getLevel() + "', '" + p.getExp() + "', '0');");
	if (Config.OTHER_debug) {
	final long startTime2 = System.currentTimeMillis() - startTime;
	Logger.warning("wykonano akcje w " + startTime2 + "ms");
		}
	}
	

	public void downloadPlayerInfo(final Player p) {
	final Long startTime = System.currentTimeMillis();
	if (Config.OTHER_debug) {
	Logger.warning("ladowanie danych gracza " + p.getName());
	}
	Bukkit.getScheduler().runTask(core.getPlugin(), new Runnable() {
	@Override
	public void run() {
	try {
    final ResultSet rs = Database.getQueryResult("SELECT * FROM `player_data` WHERE `nick` = '" + p.getName() + "' LIMIT 1;");
    if (rs.next()) {
	if (Config.OTHER_debug) { Logger.warning("Znaleziono dane gracza.");
	}					
	final Location location = new Location(Bukkit.getWorld("world"),
	rs.getInt("x"),
	rs.getInt("y"), 
	rs.getInt("z"));
	location.setYaw((float) 
	rs.getInt("yaw"));
	location.setPitch((float)
	rs.getInt("pitch"));
	p.teleport(location);	
	ItemStack[] is = ItemUtil.itemStackArrayFromBase64(rs.getString("inventory"));
	ItemStack[] ec = ItemUtil.itemStackArrayFromBase64(rs.getString("enderchest"));
	ItemStack[] armor = ItemUtil.itemStackArrayFromBase64(rs.getString("armor"));
	Collection<PotionEffect> potions = PotionUtil.deserializePotionEffects(rs.getString("potions"));
	p.addPotionEffects(potions);
	p.getInventory().setContents(is);
	p.getEnderChest().setContents(ec);
	p.getInventory().setArmorContents(armor);	
	p.giveExp(rs.getInt("exp"));
	p.setLevel(rs.getInt("level"));						
	p.setGameMode(GameMode.getByValue(rs.getInt("gamemode")));
	p.setHealth(20);
	p.setFireTicks(0);
	p.setMaxHealth(20.0);	
	p.setFoodLevel(20);						
	p.updateInventory();
	}	else {
	Bukkit.getServer().getScheduler().runTaskLater(core.getPlugin(), new Runnable() {
	public void run(){
	p.getPlayer().getInventory().setItem(1,new ItemBuilder1(Material.DIRT, 8).toItemStack());               		
	saveDataPlayer(p);
	ChatUtil.sendMessage(p, "&8»&c Twoje konto zostalo utworzone.");
	if(Config.OTHER_debug){
	Logger.warning("Stworzono " + p.getName());
	}
	}
	}, 0);
	}
	rs.close();
	if(Config.OTHER_debug){
	final Long startTime2 = System.currentTimeMillis() - startTime;
	Logger.warning("wykonano akcje w " + startTime2 + "ms");
	}	
	} catch (SQLException | IOException e) {
	 e.printStackTrace();
				}
			}
		});
		
	}

}

