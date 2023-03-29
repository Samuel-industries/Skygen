package pl.samuel.skygen.commands.dev;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
import api.messages.Config;
import org.bukkit.command.*;
public class ConfigCommand extends Command
{
    public ConfigCommand() {
        super("core", "Informacje o Core", "/core <reload>", "core.cmd.dev", "config", "configuration");
    }
    
    @Override
    public boolean onExecute(final CommandSender sender, final String[] args) {
        if (args.length < 1) {
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        if (!args[0].equalsIgnoreCase("reload")) {
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
        }
        if (args[0].equalsIgnoreCase("reload")) {
            Config.reloadConfig();
            Config.reloadConfig();
            return ChatUtil.sendMessage(sender, "&aConfig przeladowany");
        }
        return ChatUtil.sendMessage(sender, Config.USE(this.getUsage()));
    }
}
