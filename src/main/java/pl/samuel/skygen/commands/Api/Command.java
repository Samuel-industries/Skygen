package pl.samuel.skygen.commands.Api;

import api.messages.Config;
import pl.samuel.skygen.utils.ChatUtil;

import org.bukkit.command.*;

import java.util.Arrays;
import java.util.List;

public abstract class Command extends org.bukkit.command.Command
{
    private final String name;
    private final String usage;
    private final String desc;
    private final String permission;
    
    public Command(final String name, final String desc, final String usage, final String permission, final String... aliases) {
        super(name, desc, usage, Arrays.asList(aliases));
        this.name = name;
        this.usage = usage;
        this.desc = desc;
        this.permission = permission;
    }
    
    public boolean execute(final CommandSender player, final String label, final String[] args) {
    if ((this.permission != null || this.permission != "") && !player.hasPermission(this.permission)) {
    String msg = Config.MSG_ERRORDONTHAVEPERMISSION;
    msg = msg.replace("{PERM}", this.getPermission());
    return ChatUtil.sendMessage(player, ChatUtil.fixColor(msg));
      }
     return this.onExecute(player, args);
    }
    
    public abstract boolean onExecute(final CommandSender p0, final String[] p1);
    
    public String getName() {
        return this.name;
    }
    
    public String getUsage() {
        return this.usage;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public String getPermission() {
        return this.permission;
    }
}
