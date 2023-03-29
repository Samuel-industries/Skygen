package pl.samuel.skygen.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
public class GroupCommand extends Command
{
    public GroupCommand() {
        super("group", "Ustawianie grupy dla gracza", "/group <nick>  <|Lider>|<By>|chatmod|<Debil>|<admin>|<mod>|<helper>|<thelper>|vip|<sponsor>|<yt>|<yt+>|<friend>", "core.cmd.headadmin", "group");
    }
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
    if (args.length < 2) {
    return ChatUtil.sendMessage(p, Config.USE(this.getUsage()));
        }
    final User u = UserManager.getUser(args[0]);
    if (u == null) {
    return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub nie znaleziono go w bazie danych!");
    }
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[0] + " group set " + args[1]);
    ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Zmieniles range &r" + args[0] + " &7na &f" + args[1]);
    if (u.getPlayer() != null) {
     ChatUtil.sendMessage(u.getPlayer(), "&8[&C&l!&8] &7Twoja ranga zostala zmieniona na &r" + args[1] + " &7przez &f" + p.getName());
        }
    return false;
    }
    

}
