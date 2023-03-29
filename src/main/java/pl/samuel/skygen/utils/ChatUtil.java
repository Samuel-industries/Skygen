package pl.samuel.skygen.utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.Packet;
import net.minecraft.server.v1_16_R3.PacketPlayOutTitle;

public class ChatUtil {
    public static String iiIi;
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#\\w{6})");
    public final static char COLOR_CHAR = ChatColor.COLOR_CHAR;
    private static final List<String> blockedWords;

    static {
        blockedWords = new ArrayList<String>(Arrays.asList(".pl", ".eu", ".net", ".com", ".", "zapraszamy na", "zapraszam", "ip", "wbijajcie na"));

    }


    public static String fixColor(final String s) {
        if (s == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace(">>", "»").replace("<<", "«"));
    }

    public static String translateHexColorCodes(String str) {
        Matcher matcher = HEX_PATTERN.matcher(ChatColor.translateAlternateColorCodes('&', str));
        StringBuffer buffer = new StringBuffer();

        while (matcher.find())
            matcher.appendReplacement(buffer, ChatColor.of(matcher.group(1)).toString());

        return matcher.appendTail(buffer).toString();
    }


    public static boolean isBlocked(final String mess) {
        for (final String s : blockedWords) {
            if (mess.toLowerCase().contains(s.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static boolean sendMessage(final CommandSender sender, final String message, final String permission) {
        if (sender instanceof ConsoleCommandSender) {
            sendMessage(sender, message);
        }
        return permission != null && permission != "" && sender.hasPermission(permission) && sendMessage(sender, message);
    }

    public static boolean sendMessage(final Collection<? extends Player> collection, final String message, final boolean b) {
        for (final CommandSender cs : collection) {
            sendMessage(cs, message);
        }
        return true;
    }

    public static boolean isInteger(final String string) {
        return Pattern.matches("-?[0-9]+", string.subSequence(0, string.length()));
    }


    public static boolean sendMessage(final CommandSender p, final String message) {
        p.sendMessage(fixColor(message));
        return false;
    }


    public static void sendMessage(final Player p, final List<String> message) {
        for (int i = 0; i < message.size(); ++i) {
            sendMessage(fixColor(message.get(i)));
        }
    }


    public static void sendTitle(final Player p, final String title, final String subttitle) {
        sendTitle(p, title, subttitle, 30, 40, 30);
    }

    public static void sendTitle(final Player player, String title, String subtitle, final int fadeIn, final int stay, final int fadeOut) {
        if (title == null) {
            title = "";
        }
        if (subtitle == null) {
            subtitle = "";
        }
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        final PacketPlayOutTitle packetTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut);
        craftPlayer.getHandle().playerConnection.sendPacket(packetTimes);
        final IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + fixColor(title) + "\"}");
        final PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetTitle);
        final IChatBaseComponent chatSubtitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + fixColor(subtitle) + "\"}");
        final PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubtitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetSubtitle);
    }

    public static void giveItems(final Player p, final ItemStack... items) {
        final Inventory i = p.getInventory();
        final HashMap<Integer, ItemStack> notStored = i.addItem(items);
        for (final Map.Entry<Integer, ItemStack> e : notStored.entrySet()) {
            p.getWorld().dropItem(p.getLocation(), e.getValue());
            sendMessage(p, "&cNie miales miejsca w ekwipunku, przedmioty wypadly na ziemie.");
        }
    }

    public static int getAmount(final Player arg0, final ItemStack arg1) {
        if (arg1 == null) {
            return 0;
        }
        int amount = 0;
        for (int i = 0; i < 36; ++i) {
            final ItemStack slot = arg0.getInventory().getItem(i);
            if (slot != null && slot.isSimilar(arg1)) {
                amount += slot.getAmount();
            }
        }
        return amount;
    }

    public static int first_digit(double n) {
        while (n > 10.0) {
            n /= 10.0;
        }
        return (int) n;
    }

    public static String sendMessage(final String s) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(s);
        }
        return s;
    }

    public static Location locationFromString(final String msg) {
        if (msg == null || msg.equalsIgnoreCase("") || !msg.contains(",")) {
            return new Location(Bukkit.getWorlds().get(0), 0.0, 0.0, 0.0);
        }
        final String[] m = msg.split(",");
        final World world = Bukkit.getWorld(m[0]);
        final double x = Double.parseDouble(m[1]);
        final double y = Double.parseDouble(m[2]);
        final double z = Double.parseDouble(m[3]);
        if (m.length <= 4) {
            return new Location(world, x, y, z);
        }
        final float yaw = Float.parseFloat(m[4]);
        final float pitch = Float.parseFloat(m[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static Map<String, Location> stringToHomes(final String string) {
        final HashMap<String, Location> homes = new HashMap<String, Location>();
        if (string == null || string.isEmpty()) {
            return homes;
        }
        String[] array;
        for (int length = (array = string.split(";")).length, i = 0; i < length; ++i) {
            final String ss = array[i];
            final String[] s = ss.split(":");
            homes.put(s[0], locationFromString(s[1]));
        }
        return homes;
    }

    public static String locationLongToString(final Location loc) {
        if (loc == null) {
            return "";
        }
        String sb = Objects.requireNonNull(loc.getWorld()).getName() +
                "," + loc.getX() +
                "," + loc.getY() +
                "," + loc.getZ() +
                "," + loc.getYaw() +
                "," + loc.getPitch();
        return sb;
    }

    public static String homesToString(final Map<String, Location> homes) {
        if (homes == null || homes.isEmpty()) {
            return "";
        }
        int i = 0;
        final StringBuilder sb = new StringBuilder();
        for (final Map.Entry<String, Location> e : homes.entrySet()) {
            if (i == 0) {
                sb.append(String.valueOf(String.valueOf(String.valueOf(String.valueOf(e.getKey())))) + ":" + locationLongToString(e.getValue()));
            } else {
                sb.append(";" + e.getKey() + ":" + locationLongToString(e.getValue()));
            }
            ++i;
        }
        return sb.toString();
    }

    public static String toStringSpace(final List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            if (i == 0) {
                sb.append(list.get(i));
            } else {
                sb.append(", " + list.get(i));
            }
        }
        return sb.toString();
    }


    public static double round(final double value, final int decimals) {
        final double p = Math.pow(10.0, decimals);
        return Math.round(value * p) / p;
    }

    public static Material getMaterial(final String materialName) {
        Material returnMaterial = null;
        if (isInteger(materialName)) {
            returnMaterial = Material.getMaterial(materialName);
        } else {
            returnMaterial = Material.matchMaterial(materialName);
        }
        return returnMaterial;
    }


}