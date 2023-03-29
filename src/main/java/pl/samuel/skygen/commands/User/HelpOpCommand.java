package pl.samuel.skygen.commands.User;

import api.data.base.user.User;
import api.managers.User.UserManager;
import api.messages.Config;
import api.webhook.DiscordEmbed;
import api.webhook.DiscordMessage;
import api.webhook.TemmieWebhook;
import api.webhook.embed.FooterEmbed;
import api.webhook.embed.ThumbnailEmbed;
import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.DataUtil;

import org.apache.commons.lang.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.HashMap;
import java.util.UUID;

public class HelpOpCommand extends PlayerCommand
{
    private static final HashMap<UUID, Long> times;
    
    static {
        times = new HashMap<UUID, Long>();
    }
    
    public static HashMap<UUID, Long> getTimes() {
        return HelpOpCommand.times;
    }
    
    public HelpOpCommand() {
        super("helpop", "Wiadomosc do administracji", "/helpop <wiadomosc>", "core.cmd.user");
    }
    
    
    public boolean onCommand(final Player player, final String[] args) {
        final User user = UserManager.getUser(player);
        if (args.length == 0) {
        player.sendMessage(ChatUtil.fixColor("&8[&C&l!&8] &cPoprawne uzycie: &9/helpop <tresc>"));
        return false;
        }
        final Long t = HelpOpCommand.times.get(player.getUniqueId());
        if (t != null && System.currentTimeMillis() - t < 30000L) {
       return ChatUtil.sendMessage(player, "&8[&C&l!&8] &cZgloszenia mozna pisac co 30 sekund!");
        }
        final String msg = StringUtils.join(args, " ");
            HelpOpCommand.times.put(player.getUniqueId(), System.currentTimeMillis());
            player.sendMessage(ChatUtil.fixColor("&7Pomyslnie wyslano zgloszenie o tresci: &a" + msg));
            final TemmieWebhook temmie = new TemmieWebhook(Config.Weebhook_Url);
            final DiscordEmbed de = new DiscordEmbed("Zgloszenie -", "**Autor:** " + player.getName() + "\n\n **Zawarta wiadomosc w zgloszeniu:** \n" + msg + "");
            de.setColor(43520);
            final ThumbnailEmbed te = new ThumbnailEmbed();
            te.setUrl(Config.Weebhook_IconUrl);
            te.setHeight(96);
            te.setWidth(96);
            te.setUrl("https://minotar.net/avatar/" + player.getName() + "/500.png");
            de.setThumbnail(te);
            final FooterEmbed fe = new FooterEmbed();
            fe.setText("Zgloszenie utworzone zostalo o godzinie " + DataUtil.getDate(System.currentTimeMillis()));
            de.setFooter(fe);
            final DiscordMessage dm = new DiscordMessage("Zgloszenie graczy", "", Config.Weebhook_IconUrl);
            dm.getEmbeds().add(de);
            dm.getAvatarUrl();
            temmie.sendMessage(dm);
            ChatUtil.sendTitle(player, "", ChatUtil.fixColor("&aPomyslnie wyslano zgloszenie!"), 30, 50, 10);
            return true;
        }

  
}