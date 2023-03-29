package api.rankings.tops;

import java.util.LinkedList;
import java.util.List;

import api.data.base.user.User;
import api.rankings.Comparator.user.UserDeathsComparator;

public class DeathManager
{
    private static List<User> deaths;
    private static DeathManager inst;
    
    static {
    DeathManager.deaths = new LinkedList<User>();
    }
    
    public static DeathManager getInst() {
    if (DeathManager.inst == null) {
     new DeathManager();
     }
    return DeathManager.inst;
    }
    
    public static List<User> getDeaths() {
   return DeathManager.deaths;
    }
    
 
    
    public static void addDeath(final User death) {
    DeathManager.deaths.add(death);
    sortUserDeaths();
    }
    
 
    
    public static void removeDeath(final User death) {
    DeathManager.deaths.remove(death);
    sortUserDeaths();
    }
 
    
    public static void sortUserDeaths() {
     DeathManager.deaths.sort(new UserDeathsComparator());
    }
  
    public static int getPlaceUser(final User user) {
    for (int num = 0; num < DeathManager.deaths.size(); ++num) {
    if (DeathManager.deaths.get(num).equals(user)) {
    return num + 1;
            }
        }
    return 0;
    }

}
