package pl.samuel.skygen.commands.HeadAdmin;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
import api.messages.Config;
import org.bukkit.command.*;

public class SlotCommand extends Command
{
    public SlotCommand() {
        super("slot", "ustawianie liczby slotow", "/slot <liczba>", "core.cmd.admin");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
       return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        if (!ChatUtil.isInteger(args[0])) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cTo nie liczba");
        }
        final int slot = Config.OTHER_SLOT = Integer.parseInt(args[0]);
        Config.saveConfig();
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Ustawiles sloty na &c" + slot);
    }
}
