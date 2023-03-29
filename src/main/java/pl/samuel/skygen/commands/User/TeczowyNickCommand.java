package pl.samuel.skygen.commands.User;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import api.data.base.user.User;
import api.managers.User.UserManager;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class TeczowyNickCommand extends PlayerCommand
{
    private static final HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    public static HashMap<UUID, Long> getTimes() {
        return TeczowyNickCommand.times;
    }
    
    public TeczowyNickCommand() {
        super("teczowy", "kolorowynick", "/kolorowynick", "core.cmd.premium");
    }
    
    @Override
    public boolean onCommand(final Player sender, final String[] args) {
        final User u = UserManager.getUser(sender.getName());
        u.setTeczowy(!u.isTeczowy());
        u.save();
        final Long t = TeczowyNickCommand.times.get(sender.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 1000L) {
        return ChatUtil.sendMessage(sender, "&cNie mozesz tak ccesto uzywac tej komendy!");
        }
        if (u.isTeczowy()) {
        TeczowyNickCommand.times.put(sender.getUniqueId(), System.currentTimeMillis());
        return ChatUtil.sendMessage(sender, " &8» &7Twoj kolorowy nick zostal " + (u.isTeczowy() ? "&awlaczony" : "&cwylaczony"));
        }
        TeczowyNickCommand.times.put(sender.getUniqueId(), System.currentTimeMillis());
        return ChatUtil.sendMessage(sender, " &8» &7Twoj kolorowy nick zostal " + (u.isTeczowy() ? "&awlaczony" : "&cwylaczony"));
    }
}
