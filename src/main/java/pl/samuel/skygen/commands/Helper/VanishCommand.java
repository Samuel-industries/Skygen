package pl.samuel.skygen.commands.Helper;

import api.data.base.user.User;
import api.managers.User.UserManager;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class VanishCommand extends PlayerCommand
{
    public VanishCommand() {
        super("vanish", "prywatne wiadomosci do graczy", "/vanish <gracz>", "core.cmd.helper", "v");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        if (args.length == 0) {
            final User u = UserManager.getUser(p);
            if (u == null) {
            return true;
            }
            u.setVanish(!u.isVanish());
            setVanish(p, u);
            u.save();
            p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_ADDITIONS, 1.0f, 1.0f);
            for (final Player po : Bukkit.getOnlinePlayers()) {
            if ((po.hasPermission("core.cmd.helper") || po.isOp()) && !po.equals(p)) {
            ChatUtil.sendMessage(po, "&7[&aI&7] &7Administrator &c" + p.getName() + (u.isVanish() ? " &7jest teraz &cniewidzialny" : " &7stal sie &awidoczny"));
                }
            }
            return ChatUtil.sendMessage(p, "&8[&C&l!&8] " + (u.isVanish() ? "&7Zostales &cukryty" : "&7Stales sie &awidoczny") + "&7!");
            } else {
            final Player o = Bukkit.getPlayer(args[0]);
            if (o == null) {
             return ChatUtil.sendMessage(p, "&8[&C&l!&8] &cGracz jest offline lub  nie znaleziono go w bazie danych!");
            }
            final User user = UserManager.getUser(o);
            if (user == null) {
             return true;
            }
            user.setVanish(!user.isVanish());
            setVanish(o, user);
            user.save();
            p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_ADDITIONS, 1.0f, 1.0f);
            for (final Player po2 : Bukkit.getOnlinePlayers()) {
            if ((po2.hasPermission("core.cmd.helper") || po2.isOp()) && !po2.equals(p)) {
            ChatUtil.sendMessage(po2, "&7[&aI&7] &7Vanish &c" + (user.isVanish() ? "&aWlaczony" : "&cWylaczony") + " &7dla &c" + o.getName() + " &7przez &c" + p.getName());
              }
            }
            ChatUtil.sendMessage(o, "&7[&aI&7] &7Twoj tryb vanish zostal ustawiony &7" + (user.isVanish() ? "&aWlaczony" : "&cWylaczony") + "&7 przez &c" + p.getName() + "&7!");
            return ChatUtil.sendMessage(p, "&7[&aI&7] &7Vanish " + (user.isVanish() ? "&aWlaczony" : "&cWylaczony") + "&7  dla &c" + o.getName());
        }
    }
    
    public static void setVanish(final Player p, final User u) {
    if (u.isVanish()) {
    for (final Player all : Bukkit.getOnlinePlayers()) {
    if (all.hasPermission("core.cmd.helper")) {
    all.showPlayer(p);
     } else {
    all.hidePlayer(p);
       }
     p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_ADDITIONS, 2.0f, 2.0f);
    }
      } else {
     for (final Player all : Bukkit.getOnlinePlayers()) {
     all.showPlayer(p);
      }
      p.playSound(p.getLocation(), Sound.AMBIENT_BASALT_DELTAS_ADDITIONS, 2.0f, 2.0f);

      }
    }     
}
