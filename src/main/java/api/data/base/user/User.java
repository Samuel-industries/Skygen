package api.data.base.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import api.data.base.Database;
import pl.samuel.skygen.core;
import pl.samuel.skygen.utils.ChatUtil;

public class User
{
    private String name;
    private boolean teczowy;
    private int kills;
    private int deaths;
    private long lastChat;
    private String firstIP;
    private String lastKill;
    private long lastKillTime;
    private boolean god;
    private String nick;  
    private boolean vanish;
    private List<Player> ignoreTell;
    private boolean helpop;
    private long turboCoins;
    private int coins;

    
    public User(final Player p) {
    
        this.name = p.getName();
        this.teczowy = false;
        this.kills = 0;
        this.deaths = 0;
        this.coins = 0;
        this.firstIP = p.getAddress().getAddress().getHostAddress();
        this.lastKill = "-";
        this.lastKillTime = 0L;
        this.lastChat = 0L;
        this.god = false;
        this.vanish = false;
        this.ignoreTell = new ArrayList<Player>();
        this.helpop = true;     
        this.turboCoins = 0L;
       
    }
   
    public User(final ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.teczowy = false;
        this.kills = rs.getInt("kills");
        this.deaths = rs.getInt("deaths");
        this.coins = rs.getInt("coins");
        this.firstIP = rs.getString("firstIP");
        this.lastKill = rs.getString("lastKill");
        this.lastKillTime = rs.getLong("lastKillTime");
        this.lastChat = 0L;        
        this.god = (rs.getInt("god") == 1);
        this.vanish = (rs.getInt("vanish") == 1);
        this.vanish = false;
        this.ignoreTell = new ArrayList<Player>();
        this.helpop = true;
        this.turboCoins = rs.getLong("turboCoins");
    }

    public boolean isVanish() {
        return this.vanish;
    }
    
    public void setVanish(final boolean vanish) {
        this.vanish = vanish;
    }
  
    public long getTurboCoins() {
        return this.turboCoins;
    }
    
    public void setTurboCoins(final long turboCoins) {
        this.turboCoins = turboCoins;
    }
    
    public boolean isTurboCoins() {
        return this.getTurboCoins() > System.currentTimeMillis();
    }
    
    public void addCoins(final int paramInt) {
        this.coins += paramInt;
    }
    
    public void removeCoins(final int paramInt) {
        this.coins -= paramInt;
    }      
    public int getCoins() {
        return this.coins;
    }  
    
    public void setCoins(final int coins) {
        this.coins = coins;
    }
  
    public List<Player> getIgnoreTell() {
        return this.ignoreTell;
    }
    
    public boolean isIgnoreTell(final Player p) {
        return this.ignoreTell.contains(p);
    }
    
    public void setIgnoreTell(final List<Player> ignoreTell) {
        this.ignoreTell = ignoreTell;
    }
    
    public void addIgnoreTell(final Player p) {
        this.ignoreTell.add(p);
    }
    
    public void removeIgnoreTell(final Player p) {
        this.ignoreTell.remove(p);
    }      
    public boolean isHelpop(final boolean b) {
        return this.helpop;
    } 
    public void setHelpop(final boolean helpop) {
        this.helpop = helpop;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    public int getKills() {
        return this.kills;
    }
    
    public void setKills(final int kills) {
        this.kills = kills;
    }
    
    public int getDeaths() {
        return this.deaths;
    }
    
    public void setDeaths(final int deaths) {
        this.deaths = deaths;
    }
    public String getFirstIP() {
        return this.firstIP;
    }
    
    public void setFirstIP(final String firstIP) {
        this.firstIP = firstIP;
    }
    public Player getPlayer() {
        return Bukkit.getPlayer(this.getName());
    }
    
    public boolean isOnline() {
        return this.getPlayer() != null;
    }
    
    public String getLastKill() {
        return this.lastKill;
    }
    
    public void setLastKill(final String lastKill) {
        this.lastKill = lastKill;
    }
    
    public long getLastKillTime() {
        return this.lastKillTime;
    }
    
    public void setLastKillTime(final long lastKillTime) {
        this.lastKillTime = lastKillTime;
    }
    
    public boolean isChat() {
        return System.currentTimeMillis() > this.lastChat;
    }   
    public void setLastChat(final long lastChat) {
        this.lastChat = lastChat;
    }    
    public long getLastChat() {
        return this.lastChat;
    }
    public void addKills(final int index) {
        this.kills += index;
    }
    
    public void addDeaths(final int index) {
        this.deaths += index;
    }
    

    
    public void removeKills(final int index) {
        this.kills -= index;
    }
    
    public void removeDeaths(final int index) {
        this.deaths -= index;
    }
    
    
    public boolean isGod() {
        return this.god;
    }
    
    public void setGod(final boolean god) {
        this.god = god;
    }
     
    public void insert() {
    	Database.update(false, "INSERT INTO `users`(`id`, `name`, `teczowy`, `kills`, `deaths`,  `coins`,  `firstIP`,  `lastKill`, `lastKillTime`,  `god`, `vanish`,  `turboCoins`) VALUES (NULL, '" + this.getName() + "','" + (this.isTeczowy() ? 1 : 0) + "','" + this.getKills() + "','" + this.getDeaths() +  "','" + this.getCoins() +  "','" + this.getFirstIP() +  "','" + this.getLastKill() + "','" + this.getLastKillTime() +  "','" + (this.isGod() ? 1 : 0) +  "','" +  (this.isVanish() ? 1 : 0) + "', '" + this.getTurboCoins() + "');");
    }
    
    public void save() {
    	Database.update(false, "UPDATE `users` SET `kills` = '" + this.getKills() + "', `deaths` = '" + this.getDeaths() +  "', `coins` = '" + this.getCoins() +  "', `firstIP` = '" + this.getFirstIP()  + "', `lastKill` = '" + this.getLastKill() + "', `lastKillTime` = '" + this.getLastKillTime() +  "', `god` = '" + (this.isGod() ? 1 : 0) +  "', `teczowy` = '" + (this.isTeczowy() ? 1 : 0) +  "', `vanish` = '" + (this.isVanish() ? 1 : 0) + "',`turboCoins`='" + this.getTurboCoins() +  "' WHERE `name` ='" + this.getName() + "';");
   
    }
  
    public String getNick() {
        return this.nick;
    }

    public boolean isTeczowy() {
        return this.teczowy;
    }
    
    public void setTeczowy(final boolean teczowy) {
        this.teczowy = teczowy;
    }
    

}
