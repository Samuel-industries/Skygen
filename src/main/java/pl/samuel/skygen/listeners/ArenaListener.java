package pl.samuel.skygen.listeners;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import api.data.base.user.User;
import api.managers.User.UserManager;
import pl.samuel.skygen.utils.ChatUtil;

public class ArenaListener implements Listener  {

	
	@EventHandler
	public void onBreakknock(final BlockBreakEvent e) {
	final Player p = e.getPlayer();
	if (p.getWorld().getName().equals("world") && !p.hasPermission("core.arena.budowanie")) {
	e.setCancelled(true);			
		}
	}
	@EventHandler
	public void onplaceknock(final BlockPlaceEvent e) {
	final Player p = e.getPlayer();
	if (p.getWorld().getName().equals("world") && !p.hasPermission("core.arena.budowanie")) {
	e.setCancelled(true);
		}	
	}	
    @EventHandler
    public void onPlayerCraft(final CraftItemEvent event) {
    event.setCancelled(true);  
    }
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteract2222(final InventoryClickEvent e) {
	final Player p = (Player) e.getWhoClicked();
	if (e.getView().getTitle().equalsIgnoreCase(ChatUtil.fixColor("&7RANGI"))) {
	e.setCancelled(true);
	e.setResult(Event.Result.DENY);
		}
	}
	@EventHandler
	public void podnoszenie(final PlayerPickupItemEvent e) {
	final Player player = e.getPlayer();
	final User u = UserManager.getUser(player);
	if (u.isVanish() && (!player.hasPermission("core.cmd.admin"))) {
	e.setCancelled(true);
		}
	}
	@EventHandler
	public void niszczenie(final PlayerInteractEvent e) {
	final Player player = e.getPlayer();
	final User u = UserManager.getUser(player);
	if (u.isVanish() && (!player.hasPermission("core.cmd.admin")) && e.getAction() == Action.LEFT_CLICK_BLOCK) {
	if (player.hasPermission("core.cmd.user")) {
	e.setCancelled(true);
	final Player p = e.getPlayer();
	p.sendMessage(ChatUtil.fixColor("&8[&C&l!&8] &cNie mozesz niczczyc blokow w trybie vanish"));
	  }
		}
	}
	@EventHandler
	public void budowanie(final PlayerInteractEvent e) {
	final Player player = e.getPlayer();
	final User u = UserManager.getUser(player);
	final Player p = e.getPlayer();
	if (u.isVanish() && (!player.hasPermission("core.cmd.admin") && e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
	e.setCancelled(true);
	p.sendMessage(ChatUtil.fixColor("&8[&C&l!&8] &cNie mozesz budowac w trybie vanish"));
		}
	}
	@EventHandler
	public void wyrzucanie(final PlayerDropItemEvent e) {
	final Player player = e.getPlayer();
	final User u = UserManager.getUser(player);
	final Player p = e.getPlayer();
	if (u.isVanish() && (!player.hasPermission("core.cmd.admin"))) {
	e.setCancelled(true);
	p.sendMessage(ChatUtil.fixColor("&8[&C&l!&8] &cNie mozesz wyrzucac itemow w trybie vanish"));
		}
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent e) {
	e.getEntity();
	e.setCancelled(true);
	}
	@EventHandler
	public void onWeatherChange(WeatherChangeEvent e) {
	e.setCancelled(e.toWeatherState());
	}

	@EventHandler
	public void onCraft(final CraftItemEvent e) {
	if (e.getInventory().getType().equals(InventoryType.WORKBENCH) && e.getSlotType().toString().equalsIgnoreCase("RESULT") && e.getCurrentItem().getType().name().equalsIgnoreCase("JUKEBOX") && e.getCurrentItem().getType().name().equalsIgnoreCase("WORKBENCH") && e.getCurrentItem().getType().name().equalsIgnoreCase("CRAFTING TABLE")) {
	e.setCancelled(true);
		}
	}
	@EventHandler
	public static void onBlockPhysic(final BlockPhysicsEvent event) {
	event.getBlock();
	event.setCancelled(true);
	}
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
	World ew = e.getWorld();
	if (ew.hasStorm()) {
	ew.setWeatherDuration(0);
		}
	}

	}
