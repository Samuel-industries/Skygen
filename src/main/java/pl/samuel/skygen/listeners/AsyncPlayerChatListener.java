package pl.samuel.skygen.listeners;

import java.util.Collection;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import api.packet.chat.ChatPacket;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import pl.samuel.skygen.core;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;

public class AsyncPlayerChatListener implements Listener
{
    LuckPerms api;   
    public AsyncPlayerChatListener() {
    this.api = LuckPermsProvider.get();
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void onChat(final AsyncPlayerChatEvent e) {
     final Player p = e.getPlayer();
   final net.luckperms.api.model.user.User lpuser = this.api.getUserManager().getUser(p.getUniqueId());
   final Collection<Group> inheritedGroups = lpuser.getInheritedGroups(lpuser.getQueryOptions());
   final User u = UserManager.getUser(p);
   final String prefix = lpuser.getCachedData().getMetaData().getPrefix();
   if (e.getMessage().length() < 1) {
   ChatUtil.sendMessage(p, "&8[&C&l!&8] &cWiadomosc musi zawierac minimalnie 1 znak");
   e.setCancelled(true);
   return;
        }
   if (ChatUtil.isBlocked(e.getMessage()) && !p.hasPermission("core.cmd.helper")) {
   ChatUtil.sendMessage(p, "&8[&C&l!&8] &cNiektorych slow nie wolno uzywac na naszym serwerze");
    e.setCancelled(true);
    return;
        }
    if (!p.hasPermission("core.cmd.chatvip") && Config.ENABLE_VIPCHAT) {
    ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Chat jest dostepny tylko dla rangi &6premium");
    e.setCancelled(true);
    return;
        }
    if (!p.hasPermission("core.cmd.helper") && !Config.ENABLE_CHAT && !Config.ENABLE_VIPCHAT) {
    ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Chat jest aktualnie wylaczony!");
    e.setCancelled(true);
    return;
        }
    if (!p.hasPermission("core.cmd.helper") && !u.isChat()) {
    ChatUtil.sendMessage(p, "&8[&C&l!&8] &7Na czacie bedziesz mogl pisac dopiero za &f" + DataUtil.secondsToString(u.getLastChat()));
    e.setCancelled(true);
     return;
        }
   	 String globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_GLOBAL);
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("helper"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_HELPER);
     }
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("player"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_PLAYER);
     }
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("tHELPER"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_THELPER);
     }
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("moderator"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_MOD);
     }
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("admin"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_ADMIN);
     }
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("wlasciciel"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_WLASCICIEL);
     }
     if (inheritedGroups.stream().anyMatch(g -> g.getName().equals("headadmin"))) {
     globalFormat = ChatUtil.fixColor(Config.CHAT_FORMAT_HEADADMIN);
     }
     globalFormat = globalFormat.replace("{PREFIX}", prefix);
     globalFormat = globalFormat.replace("{PLAYER}", p.getName());
     globalFormat = globalFormat.replace("{MESSAGE}", e.getMessage());
     e.setCancelled(true);
     final String player = e.getPlayer().getName();
     ChatPacket chatpacket;
     chatpacket = new ChatPacket(ChatUtil.translateHexColorCodes(globalFormat).replace("%", "procent"));
     core.getPlugin().getRedisService().publishAsync("chatskygen", chatpacket);
     return;
    }

}

