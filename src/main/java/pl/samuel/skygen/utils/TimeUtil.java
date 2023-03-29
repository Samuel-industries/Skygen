package pl.samuel.skygen.utils;

import java.text.SimpleDateFormat;

public enum TimeUtil
{
    TICK("TICK", 0, "TICK", 0, "TICK", 0, "TICK", 0, "TICK", 0, "TICK", 0, "TICK", 0, 50, 50), 
    MILLISECOND("MILLISECOND", 1, "MILLISECOND", 1, "MILLISECOND", 1, "MILLISECOND", 1, "MILLISECOND", 1, "MILLISECOND", 1, "MILLISECOND", 1, 1, 1), 
    SECOND("SECOND", 2, "SECOND", 2, "SECOND", 2, "SECOND", 2, "SECOND", 2, "SECOND", 2, "SECOND", 2, 1000, 1000), 
    MINUTE("MINUTE", 3, "MINUTE", 3, "MINUTE", 3, "MINUTE", 3, "MINUTE", 3, "MINUTE", 3, "MINUTE", 3, 60000, 60), 
    HOUR("HOUR", 4, "HOUR", 4, "HOUR", 4, "HOUR", 4, "HOUR", 4, "HOUR", 4, "HOUR", 4, 3600000, 60), 
    DAY("DAY", 5, "DAY", 5, "DAY", 5, "DAY", 5, "DAY", 5, "DAY", 5, "DAY", 5, 86400000, 24), 
    WEEK("WEEK", 6, "WEEK", 6, "WEEK", 6, "WEEK", 6, "WEEK", 6, "WEEK", 6, "WEEK", 6, 604800000, 7);
    
	
    private final int time;
    private final int timeMulti;
    public static int MPT;

    static SimpleDateFormat timeFormat;
    public static String sec;
    public static String min;
    public static String hr;
    public static String day;
    static {
        TimeUtil.MPT = 50;
        TimeUtil.timeFormat = new SimpleDateFormat("HH:mm:ss");
        TimeUtil.sec = "sek";
        TimeUtil.min = "min";
        TimeUtil.hr = "godz";
        TimeUtil.day = "d";
    }
    TimeUtil(final String s7, final int n7, final String s6, final int n6, final String s5, final int n5, final String s4, final int n4, final String s3, final int n3, final String s2, final int n2, final String s, final int n, final int time, final int timeMulti) {
        this.time = time;
        this.timeMulti = timeMulti;
    }
    
    public int getMulti() {
        return this.timeMulti;
    }
    
    public int getTime() {
        return this.time;
    }
    
    public int getTick() {
        return this.time / 50;
    }
    
    public int getTime(final int multi) {
        return this.time * multi;
    }
    
    public int getTick(final int multi) {
        return this.getTick() * multi;
    }

    public static String getTime2(final long l, int i, int j) {
        if (l < 60L) {
            return l + TimeUtil.sec;
        }
        final int minutes = (int)(l / 60L);
        final int s = 60 * minutes;
        final int secondsLeft = (int)(l - s);
        if (minutes < 60) {
            return (secondsLeft > 0) ? (minutes + TimeUtil.min + " " + secondsLeft + TimeUtil.sec) : (minutes + TimeUtil.min);
        }
        if (minutes < 1440) {
            String time = "";
            final int days = minutes / 60;
            time = days + TimeUtil.hr;
            final int inMins = 60 * days;
            final int leftOver = minutes - inMins;
            if (leftOver >= 1) {
                time = time + " " + leftOver + TimeUtil.min;
            }
            if (secondsLeft > 0) {
                time = time + " " + secondsLeft + TimeUtil.sec;
            }
            return time;
        }
        String time = "";
        final int days = minutes / 1440;
        time = days + TimeUtil.day;
        final int inMins = 1440 * days;
        final int leftOver = minutes - inMins;
        if (leftOver >= 1) {
            if (leftOver < 60) {
                time = time + " " + leftOver + TimeUtil.min;
            }
            else {
                final int hours2 = leftOver / 60;
                time = time + " " + hours2 + TimeUtil.hr;
                final int hoursInMins = 60 * hours2;
                final int minsLeft = leftOver - hoursInMins;
                if (leftOver >= 1) {
                    time = time + " " + minsLeft + TimeUtil.min;
                }
            }
        }
        if (secondsLeft > 0) {
            time = time + " " + secondsLeft + TimeUtil.sec;
        }
        return time;
    }

    
}
