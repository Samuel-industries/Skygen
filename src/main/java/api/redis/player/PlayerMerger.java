package api.redis.player;

import java.io.Serializable;
import java.util.UUID;

public class PlayerMerger implements Serializable {

    private final UUID uuid;
    private String name;

    public PlayerMerger(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
