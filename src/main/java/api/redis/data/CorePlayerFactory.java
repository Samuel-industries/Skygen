package api.redis.data;

import api.redis.player.PlayerFactory;

public class CorePlayerFactory extends PlayerFactory<CorePlayer> {

    public CorePlayerFactory() {
        super(CorePlayer::new);
    }


}
