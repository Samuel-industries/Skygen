package api.cache.player;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.packet.player.UpdatePlayerPacket;
import api.redis.PacketListener;

public class UpdatePlayerPacketCache implements PacketListener<UpdatePlayerPacket> {

    @Override
    public void handle(UpdatePlayerPacket packet) {
        System.out.println("dodano message do cache");
        String content = packet.getContent(); 
        final User u = UserManager.getUser(content);
        UserManager.LoadPlayerData(content);  
    }
}
