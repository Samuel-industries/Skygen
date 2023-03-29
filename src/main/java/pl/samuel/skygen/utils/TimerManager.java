package pl.samuel.skygen.utils;

import org.bukkit.scheduler.*;

import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

public class TimerManager
{
    public static HashMap<String, ScheduledFuture<?>> executors;
    public static HashMap<String, BukkitTask> x;
    public static HashMap<String, Integer> d;
    
    static {
        TimerManager.executors = new HashMap<String, ScheduledFuture<?>>();
        TimerManager.x = new HashMap<String, BukkitTask>();
        TimerManager.d = new HashMap<String, Integer>();
    }
    
    public static void stopAll() {
        for (final ScheduledFuture<?> s : TimerManager.executors.values()) {
            s.cancel(true);
        }
        for (final BukkitTask s2 : TimerManager.x.values()) {
            s2.cancel();
        }
    }
}
