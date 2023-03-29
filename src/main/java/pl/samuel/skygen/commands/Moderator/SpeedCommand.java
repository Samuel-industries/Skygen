package pl.samuel.skygen.commands.Moderator;

import org.bukkit.entity.Player;

import api.messages.Config;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class SpeedCommand extends PlayerCommand
{
 
    public SpeedCommand() {
        super("speed", "speed", "/speed <1-10>", "core.cmd.helper");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length < 1) {
        return ChatUtil.sendMessage(p, Config.USE(this.getUsage()));
        }
        final float speed = Float.parseFloat(args[0]);
        if (speed > 10.0f) {
       return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Predkosc speed musi wynosic 1-10");
        }
        if (speed < 1.0f) {
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Predkosc speed musi wynosic 1-10");
        }
        final float finalSpeed = speed / 10.0f;
        p.setFlySpeed(finalSpeed);
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Ustawiles predkosc latania na &c" + speed + "&7!");
    }
}
