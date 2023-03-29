package api.webhook.util;

import org.bukkit.*;
import org.bukkit.entity.*;

import java.text.Normalizer;

public class DiscordUtil
{
    public static String replace(final String s) {
        String s2 = s;
        s2 = s2.replace(">>", "\ufffd");
        s2 = s2.replace("<<", "\ufffd");
        s2 = s2.replace("{O}", "\u2022");
        s2 = s2.replace("<3", "&4\u2764");
        s2 = ChatColor.translateAlternateColorCodes('&', s2);
        return s2;
    }
    
    public static void sendMessage(final Player player, final String s) {
        player.sendMessage(replace(s));
    }
    
    public static String unaccent(final String src) {
        return Normalizer.normalize(src, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
