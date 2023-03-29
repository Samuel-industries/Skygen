package api.redis.player;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PlayerCache<V extends PlayerMerger> {

    private final Map<UUID, V> playersByUUID = new HashMap<>();
    private final Map<String, V> playersByName = new HashMap<>();

    public void add(V value) {
        this.playersByUUID.put(value.getUUID(), value);
        this.playersByName.put(value.getName(), value);
    }

    public void remove(V value) {
        this.playersByUUID.remove(value.getUUID(), value);
        this.playersByName.remove(value.getName(), value);
    }

    public V findByUUID(UUID uuid) {
        return this.playersByUUID.get(uuid);
    }

    public V findByName(String name) {
        return this.playersByName.get(name);
    }

    public V findByPlayer(Player player) {
        return this.findByUUID(player.getUniqueId());
    }

    public Collection<V> getPlayersByUUID() {
        return Collections.unmodifiableCollection(this.playersByUUID.values());
    }

    public Collection<V> getPlayersByName() {
        return Collections.unmodifiableCollection(this.playersByName.values());
    }
}
