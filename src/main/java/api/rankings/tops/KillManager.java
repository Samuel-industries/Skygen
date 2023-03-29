package api.rankings.tops;

import java.util.LinkedList;
import java.util.List;

import api.data.base.user.User;
import api.rankings.Comparator.user.UserKillsComparator;

public class KillManager
{
    private static List<User> kills;
    private static KillManager inst;
    
    static {
    KillManager.kills = new LinkedList<User>();
    }
    
    public static List<User> getKills() {
    return KillManager.kills;
    }
    
 
    
    public static void addKill(final User kill) {
    KillManager.kills.add(kill);
    sortUserKills();
    }
    

    
    public static void removeKill(final User kill) {
    KillManager.kills.remove(kill);
    sortUserKills();
    }
    
 
    
    public static KillManager getInst() {
     if (KillManager.inst == null) {
     new KillManager();
       }
     return KillManager.inst;
    }
    
    public static void sortUserKills() {
    KillManager.kills.sort(new UserKillsComparator());
    }
    

    
    public static int getPlaceUser(final User user) {
     for (int num = 0; num < KillManager.kills.size(); ++num) {
     if (KillManager.kills.get(num).equals(user)) {
     return num + 1;
            }
        }
      return 0;
    }

}
