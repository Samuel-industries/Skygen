package pl.samuel.skygen.commands.dev;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class GcCommand extends PlayerCommand
{
    public GcCommand() {
        super("gc", "statystki serwera", "/gc", "core.cmd.dev");
    }
    
    @Override
    public boolean onCommand(final Player player, final String[] args) {
        ChatUtil.sendMessage(player, "");
        ChatUtil.sendMessage(player, "&8» &7Online serwer: &r" + Bukkit.getOnlinePlayers().size());
        ChatUtil.sendMessage(player, "&8» &7Uzyty RAM: &r" + Runtime.getRuntime().maxMemory() / 1024L / 1024L + "MB");
        ChatUtil.sendMessage(player, "&8» &7Wolny RAM: &r" + Runtime.getRuntime().freeMemory() / 1024L / 1024L + "MB");
        ChatUtil.sendMessage(player, "&8» &7Zuzycie procesora: &r" + getCpuUsage() + "%");
        ChatUtil.sendMessage(player, "");
        return true;
    }
    
    public double getCpuUsage() {
        final OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        return operatingSystemMXBean.getSystemLoadAverage() / operatingSystemMXBean.getAvailableProcessors();
    }
}
