package api.rankings.tops;

import api.data.base.user.User;
import api.rankings.Comparator.user.UserCoinsComparator;

import java.util.LinkedList;
import java.util.List;

public class CoinsManager
{
    private static List<User> coins;
    private static CoinsManager inst;  
    static {
    CoinsManager.coins = new LinkedList<User>();
    }    
    public static List<User> getcoins() {
    return CoinsManager.coins;
    }  
    public static CoinsManager getInst() {
    if (CoinsManager.inst == null) {
    new CoinsManager();
    }
    return CoinsManager.inst;
    }   
    public static void addCoins(final User Coins) {
    CoinsManager.coins.add(Coins);
    sortUserCoins();
    }   
    public static void removeCoins(final User Coins) {
    CoinsManager.coins.remove(Coins);
    sortUserCoins();
    }
      
    public static void sortUserCoins() {
     CoinsManager.coins.sort(new UserCoinsComparator());
    }
    
    public static int getPlaceUser(final User user) {
    for (int num = 0; num < CoinsManager.coins.size(); ++num) {
     if (CoinsManager.coins.get(num).equals(user)) {
    return num + 1;
      }
        }
        return 0;
    }
    
}