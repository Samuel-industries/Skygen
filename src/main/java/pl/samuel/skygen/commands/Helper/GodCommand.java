package pl.samuel.skygen.commands.Helper;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import api.data.base.user.User;
import api.managers.User.UserManager;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class GodCommand extends PlayerCommand {
    public GodCommand() {
        super("god", "zarzadzanie trybem goda graczy", "/god [gracz]", "core.cmd.helper");
    }

    @Override
    public boolean onCommand(final Player p, final String[] args) {
    if (args.length == 0) {
    final User u = UserManager.getUser(p.getName());
    if (u == null) {
     return true;
     }
     u.setGod(!u.isGod());
     u.save();
     return ChatUtil.sendMessage(p, "&8� &7God zostal &c" + (u.isGod() ? "wlaczony" : "wylaczony"));
     } else {
     final Player o = Bukkit.getPlayer(args[0]);
     if (o == null) {
     return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
     }
     final User user = UserManager.getUser(o);
     if (user == null) {
     return true;
     }
    user.setGod(!user.isGod());
    user.save();
    ChatUtil.sendMessage(o, "&8[&C&l!&8] &7God zostal &c" + (user.isGod() ? "wlaczony" : "wylaczony") + " &7przez &c" + p.getName());
    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7God zostal &c" + (user.isGod() ? "wlaczony" : "wylaczony") + " &7dla &c" + o.getName());
        }
    }
}
