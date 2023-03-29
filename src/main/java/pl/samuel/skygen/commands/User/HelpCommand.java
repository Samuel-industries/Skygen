package pl.samuel.skygen.commands.User;


import org.bukkit.command.*;
import org.bukkit.entity.*;

import pl.samuel.skygen.commands.Api.PlayerCommand;
import pl.samuel.skygen.utils.ChatUtil;

public class HelpCommand extends PlayerCommand
{
    public HelpCommand() {
        super("help", "komenda do oi", "/help", "core.cmd.user", "pomoc");
    }
    
    @Override
    public boolean onCommand(final Player p, final String[] args) {
        ChatUtil.sendMessage(p, "&7&m----------------------------------------------");
        ChatUtil.sendMessage(p, " &7/&cgracz [nick] &8- &7Sprawdz swoj/kogos ranking");
        ChatUtil.sendMessage(p, " &7/&clobby &8- &7Teleportacja na lobby/spawn");
        ChatUtil.sendMessage(p, " &7/&cignore <nick> &8- &7Ignorowanie wiadomosci od kogos");
        ChatUtil.sendMessage(p, " &7/&cklan &8- &7Informacje o gildiach");
        ChatUtil.sendMessage(p, " &7/&chelpop <wiadomosc> &8- &7Szybka sprawa do administracji");
        ChatUtil.sendMessage(p, " &7/&crangi &8- &7Informacje o rangach");
        ChatUtil.sendMessage(p, " &7/&cpay <nick> &8- &7Przelewa coinsy do gracza");
        ChatUtil.sendMessage(p, " &7/&cIncognito &8- &7Dostep do Incognito");
        ChatUtil.sendMessage(p, " &7/&ckolorowynick &8- &7Dostep do kolorowego nicku");
        ChatUtil.sendMessage(p, "&7&m----------------------------------------------");       
        return true;
    }
}
