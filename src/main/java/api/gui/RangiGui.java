package api.gui;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

import pl.samuel.skygen.utils.ChatUtil;
import pl.samuel.skygen.utils.ItemBuilder1;

public class RangiGui
{
    public static InventoryView show(final Player p) {
        final ItemBuilder1 ciemne = new ItemBuilder1(Material.BLACK_STAINED_GLASS_PANE, 1, (short)7).setTitle(ChatUtil.fixColor("&8[&AI&8]"));
        final ItemBuilder1 zielone = new ItemBuilder1(Material.GREEN_STAINED_GLASS_PANE, 1, (short)5).setTitle(ChatUtil.fixColor("&8[&AI&8]"));
        final ItemBuilder1 BY = new ItemBuilder1(Material.BOOK).setTitle(ChatUtil.fixColor("&8[------| &A&lSVIP &8|------]")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Przywileje &b&lBy")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dodatkowy  &CSlot&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/Pay&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/Incognito&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/fly&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &Ckolorowego pisania&7.")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Coinsy x2 za zabicie &7(&c30&7)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Kupisz tutaj: &CNAZWA.PL")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Czas trwania: &C1 edycja")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8[------| &a&lSVIP &8|------]"));
        final ItemBuilder1 SPONSOR = new ItemBuilder1(Material.BOOK).setTitle(ChatUtil.fixColor("&8[------| &9SPONSOR &8|------]")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Przywileje &9Sponsor'a:")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dodatkowy  &CSlot&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/Pay&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/Incognito&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/fly&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &Ckolorowego pisania&7.")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Coinsy x2 za zabicie &7(&c17&7)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Kupisz tutaj: &CNAZWA.PL")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Czas trwania: &C1 edycja")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8[------| &9SPONSOR &8|------]"));
        final ItemBuilder1 VIP = new ItemBuilder1(Material.BOOK).setTitle(ChatUtil.fixColor("&8[------| &6VIP &8|------]")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Przywileje &6VIP'a:")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dodatkowy  &CSlot&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/Pay&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &C/Incognito&7")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Dostep do  &Ckolorowego pisania&7.")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Coinsy x2 za zabicie &7(&c30&7)")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Kupisz tutaj: &CNAZWA.PL")).addLore(ChatUtil.fixColor("&7[&AI&7] &7Czas trwania: &C1 edycja")).addLore(ChatUtil.fixColor("")).addLore(ChatUtil.fixColor("&8[------| &6VIP &8|------]"));
        final Inventory inv = Bukkit.createInventory(null, 27, ChatUtil.fixColor("&7RANGI"));
        inv.setItem(inv.getSize() - 27, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 26, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 25, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 24, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 23, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 22, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 21, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 20, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 19, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 18, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 17, zielone.toItemStack());
        inv.setItem(inv.getSize() - 16, BY.toItemStack());
        inv.setItem(inv.getSize() - 15, zielone.toItemStack());
        inv.setItem(inv.getSize() - 14, SPONSOR.toItemStack());
        inv.setItem(inv.getSize() - 13, zielone.toItemStack());
        inv.setItem(inv.getSize() - 12, VIP.toItemStack());
        inv.setItem(inv.getSize() - 11, zielone.toItemStack());
        inv.setItem(inv.getSize() - 10, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 9, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 8, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 7, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 6, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 5, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 4, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 3, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 2, ciemne.toItemStack());
        inv.setItem(inv.getSize() - 1, ciemne.toItemStack());
        return p.openInventory(inv);
    }
}
