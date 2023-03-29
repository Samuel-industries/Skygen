package pl.samuel.skygen.commands.Admin;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;
import api.messages.Config;
import org.bukkit.command.*;

public class SlowmodeCommand extends Command
{
    public SlowmodeCommand() {
        super("slowmode", "ustawianie slowmode czatu", "/slowmode <czas>", "core.cmd.moderator", "slow", "smode");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
       if (args.length != 1) {
        String msg = Config.MSG_ERRORDONTHAVEPERMISSION;
        msg = msg.replace("{USAGE}", this.getUsage());
        return ChatUtil.sendMessage(sender, msg);
        }
        if (!ChatUtil.isInteger(args[0])) {
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &cPodana wartosc nie jest liczba!");
        }
        final int slowmode = Config.OTHER_CHATSLOWMODE = Integer.parseInt(args[0]);
         Config.saveConfig();
        return ChatUtil.sendMessage(sender, "&8[&C&l!&8] &7Pomyslnie ustawiono slowmode czatu na &c" + DataUtil.secondsToString(slowmode) + "&7!");
    }
}
