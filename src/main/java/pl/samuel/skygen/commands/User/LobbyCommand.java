package pl.samuel.skygen.commands.User;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.ConnectUtil;

public class LobbyCommand extends PlayerCommand
{
    public LobbyCommand() {
        super("lobby", "lobby", "/lobby", "core.cmd.user", "lobby", "lobby", "lobby");
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
    	if (!Config.ENABLE_TELEPORTLOBBY) {
    	return ChatUtil.sendMessage(player,"&8[&C&l!&8] &cTa opcja zostala tymczasowo wylaczona przez administratora!");
		}
        ConnectUtil.connect(player, "lobby");
        return true;
        
    }
}
