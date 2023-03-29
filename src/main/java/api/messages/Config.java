package api.messages;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

import pl.samuel.skygen.core;




public class Config
{

	

	public static String DATABASE_MYSQL_HOST;
    public static int DATABASE_MYSQL_PORT;
    public static String DATABASE_MYSQL_USER;
    public static String DATABASE_MYSQL_PASS;
    public static String DATABASE_MYSQL_NAME;  
    public static String OTHER_redisurl;
    public static String OTHER_redispassword;  
	public static String Weebhook_Url;
	public static String Weebhook_IconUrl;
    public static String CHAT_FORMAT_WLASCICIEL;
    public static String CHAT_FORMAT_HEADADMIN;
    public static String CHAT_FORMAT_ADMIN;
    public static String CHAT_FORMAT_MOD;
    public static String CHAT_FORMAT_HELPER;
    public static String CHAT_FORMAT_THELPER;
    public static String CHAT_FORMAT_PLAYER;
    public static String CHAT_FORMAT_GLOBAL;
	public static String OTHER_lobbyname;
    public static long OTHER_TURBOCOINS2;
    public static boolean OTHER_TURBOCOINS;
    public static int OTHER_SLOT;
    public static long OTHER_VOUCHERY;
    public static int OTHER_TURBOMNOZNIK;
    public static int OTHER_CHATSLOWMODE;    
   	public static boolean OTHER_debug;
    public static int OTHER_BORDERWORLDRADIUS;
    public static boolean ENABLE_WHITELIST;
    public static boolean ENABLE_TELEPORTSRODEK;
    public static boolean ENABLE_TELEPORTLOBBY;
    public static boolean ENABLE_CHAT;
    public static boolean ENABLE_VIPCHAT;
 
    public static String MSG_ERRORDONTHAVEPERMISSION;
    public static String MSG_USAGE;
    public static String MSG_FULLSERVER;
    public static List<String> WL_LIST;
    public static String WL_REASON;
    static {
    	
        Config.DATABASE_MYSQL_HOST = "127.0.0.1";
        Config.DATABASE_MYSQL_PORT = 3306;
        Config.DATABASE_MYSQL_USER = "root";
        Config.DATABASE_MYSQL_PASS = "haslo";
        Config.DATABASE_MYSQL_NAME = "skygen?useSSL=false";  

        
   ///chat
        Config.OTHER_CHATSLOWMODE = 10;   
        Config.CHAT_FORMAT_GLOBAL = "{GUILD}&7[&8{POINTS}&7] {PREFIX}{PLAYER} &8>> &r{SUFFIX}";        
        Config.CHAT_FORMAT_ADMIN = "{GUILD} {PREFIX}{PLAYER} &8> &r{SUFFIX}{MESSAGE}";      
        Config.CHAT_FORMAT_WLASCICIEL = "&8&l[&4&lW&8&l] &4{PLAYER} &8> &4{MESSAGE}";
        Config.CHAT_FORMAT_HEADADMIN = "&8&l[&C&LHA&8&l] &c{PLAYER} &8> &c{MESSAGE}";
        Config.CHAT_FORMAT_ADMIN = "&8&l[&c&lA&8&l] &c{PLAYER} &8> &c{MESSAGE}";
        Config.CHAT_FORMAT_MOD = "&8&l[&2&lM&8&l] &2{PLAYER} &88> &a{MESSAGE}";
        Config.CHAT_FORMAT_HELPER = "&8&l[&9&lH&8&l] &9{PLAYER} &88> &b{MESSAGE}";
        Config.CHAT_FORMAT_THELPER = "&8&l[&9&lTH&8&l] &9{PLAYER} &88> &b{MESSAGE}";
        Config.CHAT_FORMAT_PLAYER = "&7{PLAYER} &88> &7{MESSAGE}";
        ///tagi    
     
        Config.OTHER_TURBOCOINS = false;
        Config.OTHER_debug = false;
        Config.OTHER_TURBOCOINS2 = 0L;
        Config.OTHER_TURBOMNOZNIK = 12;
        Config.OTHER_VOUCHERY = 0L;
        Config.OTHER_SLOT = 500;
        Config.OTHER_BORDERWORLDRADIUS = 500;
        Config.OTHER_redisurl = "redis://127.0.0.1:6379";  
        Config.OTHER_redispassword = "mtk11";  
        Config.OTHER_lobbyname = "lobby";
        Config.Weebhook_Url = "link";
        Config.Weebhook_IconUrl = "link.png";  
        Config.MSG_ERRORDONTHAVEPERMISSION = "&7Ta komenda nie jest przeznaczona dla ciebie! &8(&f{PERM}&8)";
        Config.MSG_USAGE = "&8[&C&l!&8] &7Poprawne uzycie: &r{USAGE}";
        Config.MSG_FULLSERVER = "\n&7Serwer jest pelen &rgraczy&7!\n7Odczekaj chwile i dolacz do nas ponownie!";  
        ///enable
        Config.ENABLE_WHITELIST = false;
        Config.ENABLE_TELEPORTSRODEK = true;
        Config.ENABLE_TELEPORTLOBBY = true; 
        Config.ENABLE_CHAT = true;
        Config.ENABLE_VIPCHAT = true;
        Config.WL_LIST = Collections.singletonList("nick");
        Config.WL_REASON = "&cNie jestes na whitelist!";   
    }

    public static void loadConfig() {
        try {
        	core.getPlugin().saveDefaultConfig();
            final FileConfiguration c = core.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (c.isSet("config." + f.getName().toLowerCase().replace("_", "."))) {
                    f.set(null, c.get("config." + f.getName().toLowerCase().replace("_", ".")));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String USE(final String use) {
        return Config.MSG_USAGE.replace("{USAGE}", use);
    }
    
    public static void saveConfig() {
        try {
            final FileConfiguration c = core.getPlugin().getConfig();
            Field[] fields;
            for (int length = (fields = Config.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                c.set("config." + f.getName().toLowerCase().replace("_", "."), f.get(null));
            }
            core.getPlugin().saveConfig();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void reloadConfig() {
    	core.getPlugin().reloadConfig();
         loadConfig();
         saveConfig();
    }
}
