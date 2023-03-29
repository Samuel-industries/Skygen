package api.redis;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedisService {
    private final RedissonClient client;

    public RedisService() {
        Config config = new Config();
        
     config.useSingleServer().setAddress(api.messages.Config.OTHER_redisurl).setPassword(api.messages.Config.OTHER_redispassword);
        
        this.client = Redisson.create(config);
    }

    public <T extends Packet> void subscribe(String channel, PacketListener<T> packetListener, Class<T> type) {
        this.client.getTopic(channel).addListener(type, (charSequence, packet) -> packetListener.handle(packet));
    }

    public void publishAsync(String channel, Packet packet) {
    	  System.out.println("wyslano pakiet");
        this.client.getTopic(channel).publishAsync(packet);
    }
}
