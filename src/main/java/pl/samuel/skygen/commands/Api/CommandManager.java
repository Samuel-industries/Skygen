package pl.samuel.skygen.commands.Api;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.plugin.*;

import java.util.HashMap;

public class CommandManager
{
    public static final HashMap<String, Command> commands;
    private static final Reflection.FieldAccessor<SimpleCommandMap> f;
    private static CommandMap cmdMap;
    
    static {
    commands = new HashMap<String, Command>();
    f = Reflection.getField(SimplePluginManager.class, "commandMap", SimpleCommandMap.class);
    CommandManager.cmdMap = CommandManager.f.get(Bukkit.getServer().getPluginManager());
    }
    
    public static void register(final Command cmd) {
    if (CommandManager.cmdMap == null) {
    CommandManager.cmdMap = CommandManager.f.get(Bukkit.getServer().getPluginManager());
    }
    CommandManager.cmdMap.register(cmd.getName(), cmd);
    CommandManager.commands.put(cmd.getName(), cmd);
    }
}
