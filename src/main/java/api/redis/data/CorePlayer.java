package api.redis.data;

import java.util.*;

import api.redis.player.PlayerMerger;

public class CorePlayer extends PlayerMerger
{
    private int coins;
    private boolean vanished;
    
    public CorePlayer(final UUID uuid, final String name) {
        super(uuid, name);
        this.vanished = false;
    }
    
    public boolean isVanished() {
        return this.vanished;
    }
    
    public void setVanished(final boolean vanished) {
        this.vanished = vanished;
    }
}
