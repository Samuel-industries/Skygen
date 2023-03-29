package pl.samuel.skygen.commands.HeadAdmin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;

public class GiveCommand extends Command
{
    public GiveCommand() {
        super("give", "dawanie przemiotow graczom", "/give <gracz> <id[:base]> [ilosc]", "core.cmd.admin", "giveitem");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 2) {
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        final Player p = Bukkit.getPlayer(args[0]);
        final String[] datas = args[1].split(":");
        final Material m = ChatUtil.getMaterial(datas[0]);
        Short data = 0;
        if (datas.length > 1) {
        data = Short.valueOf(datas[1]);
        }
        ItemStack item = null;
        if (p == null) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
        }
        if (m == null) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cNazwa lub ID przedmiotu jest bledne!");
        }
        if (args.length == 2) {
        item = new ItemStack(m, 1, data);
        }
        else if (args.length == 3) {
        item = new ItemStack(m, ChatUtil.isInteger(args[2]) ? Integer.parseInt(args[2]) : 1, data);
        }
        if (item == null) {
         return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cWystapil blad podczas dawania przedmiotu!");
        }
        ChatUtil.giveItems(p, item);
        p.updateInventory();
        ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Dales &C" + m.name() + "&8:&C" + data + " &7(&C" + item.getAmount() + "&7) graczowi &C" + p.getName() + "&7!");
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Otrzymales &C" + m.name() + "&8:&C" + data + " &7(&C" + item.getAmount() + "&7)!");
    }
}
