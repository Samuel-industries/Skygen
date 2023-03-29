package pl.samuel.skygen.commands.Admin;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class InvCommand extends Command
{
    public InvCommand() {
        super("inv", "zarzadzanie ekwipunkiem", "/inv <gracz>", "core.cmd.admin", "invsee");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        final Player player = (Player)sender;
        if (args.length == 0) {
        String msg = Config.MSG_USAGE;
        msg = msg.replace("{USAGE}", this.getUsage());
        return ChatUtil.sendMessage(sender, msg);
        }
        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
        }
        if (args.length > 0) {
        final Player other = Bukkit.getPlayer(args[0]);
        if (other != null) {
         player.openInventory(other.getInventory());
            }
        }
        return false;
    }
}
