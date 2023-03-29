package pl.samuel.skygen.commands.Helper;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class FlyCommand extends PlayerCommand
{
    public FlyCommand() {
        super("fly", "zarzadzanie trybem latania graczy", "/fly [gracz]", "core.cmd.helper");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
    if (args.length == 0) {
     p.setAllowFlight(!p.getAllowFlight());
     return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Fly zostal &c" + (p.getAllowFlight() ? "wlaczony" : "wylaczony"));
    }
    final Player o = Bukkit.getPlayer(args[0]);
    if (o == null) {
    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
    }
    o.setAllowFlight(!o.getAllowFlight());
    ChatUtil.sendMessage(o, "&8[&C&l!&8] &7Fly zostal &c" + (o.getAllowFlight() ? "wlaczony" : "wylaczony") + " &7przez &c" + p.getName());
     return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Fly zostal &c" + (o.getAllowFlight() ? "wlaczony" : "wylaczony") + " &7dla &c" + o.getName());
    }
}
