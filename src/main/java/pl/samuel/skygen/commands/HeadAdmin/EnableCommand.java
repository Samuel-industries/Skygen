package pl.samuel.skygen.commands.HeadAdmin;

import org.bukkit.command.CommandSender;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.utils.ChatUtil;
public class EnableCommand extends Command
{
    public EnableCommand() {
        super("enable", "komenda do enable", "/enable <logout><srodek|create|<lobby><break>|<true/false>", "core.cmd.admin");
    }
    
    @Override
    public boolean onExecute(final CommandSender p, final String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendMessage(p, Config.USE(this.getUsage()));
        }
        switch (((((args[0]))))) {
        case "srodek": {
        final boolean check = Config.ENABLE_TELEPORTSRODEK = Boolean.parseBoolean(args[1]);
        Config.saveConfig();
        return ChatUtil.sendMessage(p, "&8&8» &7Teleportacja na srodek map zostala &c" + (check ? "wlaczona" : "wylaczona"));
            }
        case "lobby": {
        final boolean check = Config.ENABLE_TELEPORTLOBBY = Boolean.parseBoolean(args[1]);
        Config.saveConfig();
        return ChatUtil.sendMessage(p, "&8&8» &7Lobby zostalo &c" + (check ? "wlaczone" : "wylaczone"));
            } 
        default:
         break;
        }
        return ChatUtil.sendMessage(p, Config.USE(this.getUsage()));
    }
}
