package pl.samuel.skygen.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import api.messages.Config;

public class BorderMapListener implements Listener {
	@EventHandler
	public void onMove(final PlayerMoveEvent event) {
	final Player player = event.getPlayer();
	final Location to = event.getTo();
	if (event.getTo().getBlockX() > Config.OTHER_BORDERWORLDRADIUS || event.getTo().getBlockX() < -Config.OTHER_BORDERWORLDRADIUS  || event.getTo().getBlockZ() > Config.OTHER_BORDERWORLDRADIUS  || event.getTo().getBlockZ() < -Config.OTHER_BORDERWORLDRADIUS ) {
	event.setTo(event.getFrom());
	if (to.getBlockX() != -500 && to.getBlockY() != -500 && to.getBlockZ() != -500) {
	}
		}
	}

	public static boolean canPlaceByBorder(final Location loc) {
	return Math.abs(Config.OTHER_BORDERWORLDRADIUS - loc.getBlockX()) >= 10 && Math.abs(Config.OTHER_BORDERWORLDRADIUS - loc.getBlockZ()) >= 10 
	&& Math.abs(-Config.OTHER_BORDERWORLDRADIUS  - loc.getBlockX()) >= 10
	&& Math.abs(-Config.OTHER_BORDERWORLDRADIUS  - loc.getBlockZ()) >= 10
	&& Math.abs(-Config.OTHER_BORDERWORLDRADIUS  - loc.getBlockX()) >= 10
	&& Math.abs(Config.OTHER_BORDERWORLDRADIUS  - loc.getBlockZ()) >= 10
	&& Math.abs(Config.OTHER_BORDERWORLDRADIUS  - loc.getBlockX()) >= 10
	&& Math.abs(-Config.OTHER_BORDERWORLDRADIUS  - loc.getBlockZ()) >= 10;
	}

	@EventHandler
	public void onPlayerPlace(final BlockPlaceEvent e) {
	if (!canPlaceByBorder(e.getBlock().getLocation())) {
	e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerBreak(final BlockBreakEvent e) {
	if (!canPlaceByBorder(e.getBlock().getLocation())) {
	e.setCancelled(true);
		}
	}
}
