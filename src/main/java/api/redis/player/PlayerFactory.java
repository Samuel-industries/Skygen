package api.redis.player;

import java.util.UUID;
import java.util.function.BiFunction;

public class PlayerFactory<V extends PlayerMerger> {

    private final BiFunction<UUID, String, V> biFunction;

    public PlayerFactory(BiFunction<UUID, String, V> biFunction) {
        this.biFunction = biFunction;
    }

    public V create(UUID uuid, String name) {
        return this.biFunction.apply(uuid, name);
    }
}
