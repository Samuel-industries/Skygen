package pl.samuel.skygen.commands.Moderator;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class HealCommand extends PlayerCommand {
    public HealCommand() {
        super("heal", "Uleczanie graczy", "/heal [gracz]", "core.cmd.moderator");
    }

    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
         p.setFireTicks(0);
         p.setHealth(p.getMaxHealth());
         p.setFoodLevel(20);
         for (final PotionEffect effect : p.getActivePotionEffects()) {
         p.removePotionEffect(effect.getType());
         }
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Zostales uleczony!");
        }

        final Player o = Bukkit.getPlayer(args[0]);
        if (o == null) {
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Gracz jest offline!");
        }
        o.setFireTicks(0);
        o.setHealth(p.getMaxHealth());
        o.setFoodLevel(20);
        for (final PotionEffect effect2 : o.getActivePotionEffects()) {
        o.removePotionEffect(effect2.getType());
        }
        ChatUtil.sendMessage(o, "&8[&C&l!&8] &7Zostales uleczony przez &r" + p.getName());
        return ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Uleczyles &r" + o.getName());
    }
}
