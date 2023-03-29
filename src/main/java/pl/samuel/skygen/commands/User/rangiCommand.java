package pl.samuel.skygen.commands.User;


import api.gui.RangiGui;
import pl.samuel.skygen.commands.Api.PlayerCommand;

import org.bukkit.entity.*;


public class rangiCommand extends PlayerCommand
{
    public rangiCommand() {
        super("rangi", "rangi", "/rangi", "core.cmd.user", "rangi");
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
    final Player p = player;
    RangiGui.show(player);
    return true;
    }
}
