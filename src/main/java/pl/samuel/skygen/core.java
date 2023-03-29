package pl.samuel.skygen;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import api.cache.broadcast.BroadcastPacketCache;
import api.cache.broadcast.BroadcastTurboCoinsCache;
import api.cache.broadcast.BroadcastTurboRangiCache;
import api.cache.chat.ChatClearPacketCache;
import api.cache.chat.ChatDisablePacketCache;
import api.cache.chat.ChatEnablePacketCache;
import api.cache.chat.ChatPacketCache;
import api.cache.chat.ChatVipDisablePacketCache;
import api.cache.chat.ChatVipEnablePacketCache;
import api.cache.player.UpdatePlayerPacketCache;
import api.data.base.Database;
import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import api.packet.broadcast.BroadcastEventyPacket;
import api.packet.broadcast.BroadcastPacket;
import api.packet.chat.ChatClearPacket;
import api.packet.chat.ChatDisablePacket;
import api.packet.chat.ChatEnablePacket;
import api.packet.chat.ChatPacket;
import api.packet.chat.ChatVipDisablePacket;
import api.packet.chat.ChatVipEnablePacket;
import api.packet.player.UpdatePlayerPacket;
import api.redis.RedisService;
import api.redis.data.CorePlayerCache;
import net.luckperms.api.LuckPerms;
import pl.samuel.skygen.commands.Admin.BroadcastCommand;
import pl.samuel.skygen.commands.Admin.GamemodeCommand;
import pl.samuel.skygen.commands.Admin.InvCommand;
import pl.samuel.skygen.commands.Admin.SlowmodeCommand;
import pl.samuel.skygen.commands.Admin.StpCommand;
import pl.samuel.skygen.commands.Admin.TurboCoinsCommand;
import pl.samuel.skygen.commands.Api.Command;
import pl.samuel.skygen.commands.Api.CommandManager;
import pl.samuel.skygen.commands.HeadAdmin.EnableCommand;
import pl.samuel.skygen.commands.HeadAdmin.EventCommand;
import pl.samuel.skygen.commands.HeadAdmin.GiveCommand;
import pl.samuel.skygen.commands.HeadAdmin.GroupCommand;
import pl.samuel.skygen.commands.HeadAdmin.IsCommand;
import pl.samuel.skygen.commands.HeadAdmin.SlotCommand;
import pl.samuel.skygen.commands.HeadAdmin.StatsCommand;
import pl.samuel.skygen.commands.HeadAdmin.WhiteListCommand;
import pl.samuel.skygen.commands.Helper.FlyCommand;
import pl.samuel.skygen.commands.Helper.GodCommand;
import pl.samuel.skygen.commands.Helper.TeleportCommand;
import pl.samuel.skygen.commands.Helper.VanishCommand;
import pl.samuel.skygen.commands.Moderator.ChatCommand;
import pl.samuel.skygen.commands.Moderator.ClearCommand;
import pl.samuel.skygen.commands.Moderator.HealCommand;
import pl.samuel.skygen.commands.Moderator.SpeedCommand;
import pl.samuel.skygen.commands.User.HelpCommand;
import pl.samuel.skygen.commands.User.HelpOpCommand;
import pl.samuel.skygen.commands.User.LobbyCommand;
import pl.samuel.skygen.commands.User.SpawnCommand;
import pl.samuel.skygen.commands.User.TeczowyNickCommand;
import pl.samuel.skygen.commands.User.rangiCommand;
import pl.samuel.skygen.commands.dev.ConfigCommand;
import pl.samuel.skygen.commands.dev.GcCommand;
import pl.samuel.skygen.tasks.ActionbarTask;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.Logger;


public class core extends JavaPlugin {   
   
	private static core plugin;     
    public final RedisService redisService = new RedisService();
    private final CorePlayerCache corePlayerCache = new CorePlayerCache();
 
    
	public RedisService getRedisService() {
	    return redisService;
	 }
	public CorePlayerCache getCorePlayerCache() {
	    return corePlayerCache;
	}
    
    
   public static core getPlugin() {
        return core.plugin;
    }

 
    public void onEnable() {
    	core.plugin = this;
        this.redisInit();
        Config.reloadConfig();
        this.registerDatabase();
        this.registerManager();
        this.registerCommand();     
        this.loadListeners();
        this.registerTasks();
        Bukkit.getMessenger().registerOutgoingPluginChannel(core.plugin, "BungeeCord");
        final RegisteredServiceProvider<LuckPerms> provider = (RegisteredServiceProvider<LuckPerms>)Bukkit.getServicesManager().getRegistration((Class)LuckPerms.class);
        if (provider != null) {
            final LuckPerms luckPerms = provider.getProvider();
        }
    }

   
	 public void redisInit() {
		  this.getLogger().warning("zaladowano redis");
		  this.redisService.subscribe("broadcast", new BroadcastPacketCache(), BroadcastPacket.class);				 
		  this.redisService.subscribe("spigot", new BroadcastTurboRangiCache(), BroadcastEventyPacket.class);			 		  
		  this.redisService.subscribe("spigot", new BroadcastTurboCoinsCache(), BroadcastEventyPacket.class);			 		  			 
		  this.redisService.subscribe("chatskygen", new ChatPacketCache(), ChatPacket.class);
		  this.redisService.subscribe("spigot", new ChatEnablePacketCache(), ChatEnablePacket.class);
		  this.redisService.subscribe("spigot", new ChatDisablePacketCache(), ChatDisablePacket.class);
		  this.redisService.subscribe("spigot", new ChatClearPacketCache(), ChatClearPacket.class);
		  this.redisService.subscribe("spigot", new ChatVipEnablePacketCache(), ChatVipEnablePacket.class);
		  this.redisService.subscribe("spigot", new ChatVipDisablePacketCache(), ChatVipDisablePacket.class);
		  this.redisService.subscribe("spigot", new UpdatePlayerPacketCache(), UpdatePlayerPacket.class);
					 
	 }	
     

    public void onDisable() {
     Bukkit.getScheduler().cancelTasks(this);
     for (final Player p : Bukkit.getOnlinePlayers()) {
     p.kickPlayer(ChatUtil.fixColor("&cSerwer jest aktualnie restartowany!"));
    final User u = UserManager.getUser(p);
      u.save();
        }
     
    }
    
    public void registerCommand() {      
        registerCommand(new GodCommand());
        registerCommand(new EventCommand());
        registerCommand(new ChatCommand());
        registerCommand(new GamemodeCommand());
        registerCommand(new FlyCommand());
        registerCommand(new ClearCommand());
        registerCommand(new GcCommand());
        registerCommand(new GiveCommand());
        registerCommand(new HealCommand());
        registerCommand(new SlotCommand());
        registerCommand(new SlowmodeCommand());
        registerCommand(new StpCommand());
        registerCommand(new BroadcastCommand());
        registerCommand(new WhiteListCommand());
        registerCommand(new TeleportCommand());
        registerCommand(new IsCommand());
        registerCommand(new SpeedCommand());
        registerCommand(new StatsCommand());
        registerCommand(new EnableCommand());
        registerCommand(new ConfigCommand());
        registerCommand(new HelpCommand());
        registerCommand(new TeczowyNickCommand());
        registerCommand(new TurboCoinsCommand());
        registerCommand(new GroupCommand());     
        registerCommand(new LobbyCommand());
        registerCommand(new SpawnCommand());
        registerCommand(new InvCommand());
        registerCommand(new rangiCommand());
        registerCommand(new HelpOpCommand());
        registerCommand(new VanishCommand());
    }

    public void registerTasks() { 
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new ActionbarTask(), 40L, 20L);
          }

    public void registerManager() {
       
        UserManager.loadUsers();
    }
    
    private void registerDatabase() {
   new Database(Config.DATABASE_MYSQL_HOST, Config.DATABASE_MYSQL_PORT, Config.DATABASE_MYSQL_USER, Config.DATABASE_MYSQL_PASS, Config.DATABASE_MYSQL_NAME);
    if (Database.isConnected()) {
   Database.update(true, "CREATE TABLE IF NOT EXISTS `users` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,`name` varchar(32) NOT NULL, `teczowy` int(1) NOT NULL, `kills` int(11) NOT NULL, `deaths` int(11) NOT NULL,  `coins` int(11) NOT NULL, `firstIP` varchar(64) NOT NULL, `lastKill` varchar(32) NOT NULL, `lastKillTime` bigint(22) NOT NULL, `god` int(1) NOT NULL,  `vanish` int(1) NOT NULL, `turboCoins` bigint(22) NOT NULL);");             
   Database.update(true, "CREATE TABLE IF NOT EXISTS `player_data` (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,`nick` varchar(32) NOT NULL,`x` int(11) NOT NULL, `y` int(11) NOT NULL,`z` bigint(22) NOT NULL, `yaw` bigint(22) NOT NULL,`pitch` int(11) NOT NULL,  `hp` int(11) NOT NULL,  `gamemode` int(11) NOT NULL,  `inventory` text NOT NULL, `armor` text NOT NULL, `enderchest` text NOT NULL, `potions` text NOT NULL,  `level` int(1) NOT NULL, `exp` bigint(22) NOT NULL, `antylogout` bigint(22) NOT NULL);");
   System.out.println("Pomyslnie polaczono z MySQL");
        }
    }
    
	@SuppressWarnings("deprecation")
	public void loadListeners() {
		try {
			final ClassPath cp = ClassPath.from(getClass().getClassLoader());
			PluginManager manager = Bukkit.getPluginManager();

			for (ClassInfo classInfo : cp.getTopLevelClassesRecursive("pl.textr.randomtp.listeners")) {
				Class<?> listenerClass = classInfo.load();
				Listener listener = new Listener() {
				};
				try {
					listener = (Listener) listenerClass.newInstance();
				} catch (ReflectiveOperationException | ExceptionInInitializerError | SecurityException e) {
					continue;
				}
				manager.registerEvents(listener, this);
				   Logger.warning("Zarejestrowano " + classInfo );
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

public void registerCommand(final Command command) {
    CommandManager.register(command);
}


}
