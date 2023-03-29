package pl.samuel.skygen.commands.User;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class SpawnCommand extends PlayerCommand
{
    public SpawnCommand() {
        super("spawn", "spawn", "/spawn", "core.cmd.user", "spawn", "spawn", "spawn");
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
    if (!Config.ENABLE_TELEPORTSRODEK) {
    return ChatUtil.sendMessage(player,"&8[&C&l!&8] &cTa opcja zostala tymczasowo wylaczona przez administratora!");
		}    
    final Location loc = new Location(Bukkit.getWorld("world"), -24, 37, 31, 0.3f, 1.2f);
    player.teleport(loc);	
     return true;
        
    }
}
