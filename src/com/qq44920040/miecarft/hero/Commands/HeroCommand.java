/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 */
package com.qq44920040.miecarft.hero.Commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.HeroPlugin;
import com.qq44920040.miecarft.hero.Heros.Hero;

import static com.qq44920040.miecarft.hero.Data.getHero;


public class HeroCommand
implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command com, String label, String[] args) {
        if (label.equalsIgnoreCase("hp")) {
            if (args.length < 1) {
                return HeroCommand.Help(sender);
            }
            if (args[0].equalsIgnoreCase("sl") && sender instanceof Player && args.length > 1 && sender.hasPermission("hero.commands.sl")) {
                return HeroCommand.select((Player)sender, args);
            }
            if (args[0].equalsIgnoreCase("look") && sender instanceof Player && sender.hasPermission("hero.commands.look")) {
                return HeroCommand.look((Player)sender, args);
            }
            if (args[0].equalsIgnoreCase("change") && sender.isOp() && args.length > 2) {
                return HeroCommand.change(sender, args);
            }
            if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("hero.commands.reload")) {
                return HeroCommand.reload(sender);
            }
            if (args[0].equalsIgnoreCase("itemLook") && sender.hasPermission("hero.commands.itemlook") && sender instanceof Player) {
                return HeroCommand.itemLook((Player)sender);
            }
            return HeroCommand.Help(sender);
        }
        return false;
    }

    private static boolean itemLook(Player player) {
        ItemStack item = player.getItemInHand();
        if (item == null || item.getItemMeta() == null || !item.getItemMeta().hasLore()) {
            player.sendMessage("\u00a7c\u7269\u54c1\u4e0d\u5b58\u5728\u6216\u8005\u4e0d\u5b58\u5728Lore! ");
            return true;
        }
        for (String lore : item.getItemMeta().getLore()) {
            player.sendMessage(lore.replace('\u00a7', '&').replace(" ", "[\u7a7a]"));
        }
        return true;
    }

    private static boolean look(Player player, String[] args) {
        Hero hero = getHero(player.getName());
        player.sendMessage("\u00a7e\u60a8\u7684\u804c\u4e1a: \u00a7c" + hero.getType().toString());
        return true;
    }

    private static boolean reload(CommandSender sender) {
        HeroPlugin hp = HeroPlugin.getPlugin();
        hp.reloadConfig();
        hp.Config();
        sender.sendMessage("\u00a7e\u91cd\u8f7d\u63d2\u4ef6\u6210\u529f!");
        return true;
    }

    private static boolean change(CommandSender sender, String[] args) {
        try {
            for (World world : Bukkit.getWorlds()) {
                for (Player player : world.getPlayers()) {
                    if (!player.getName().equals(args[1])) continue;
                    Data.addHero(player.getName(), Hero.HeroType.valueOf(args[2].toUpperCase()));
                }
            }
            sender.sendMessage("\u00a7c\u6210\u529f\u4e3a\u73a9\u5bb6\u66f4\u6362\u804c\u4e1a!");
        }
        catch (Exception e) {
            sender.sendMessage("\u00a7c\u804c\u4e1a\u5217\u8868: ");
            for (Hero.HeroType t : Hero.HeroType.values()) {
                sender.sendMessage("\u00a7c" + t.toString());
            }
        }
        return true;
    }

    private static boolean select(Player player, String[] args) {
        block4 : {
            if (getHero(player.getName()) != null) {
                player.sendMessage("\u00a7c\u65e0\u6cd5\u91cd\u590d\u9009\u62e9\u804c\u4e1a");
                return true;
            }
            try {
                if (!Data.addHero(player.getName(), Hero.HeroType.valueOf(args[1].toUpperCase()))) break block4;
                player.sendMessage("\u00a7c\u606d\u559c\u60a8\u6210\u529f\u9009\u62e9\u804c\u4e1a!");
            }
            catch (Exception e) {
                player.sendMessage("\u00a7c\u804c\u4e1a\u5217\u8868: ");
                for (Hero.HeroType t : Hero.HeroType.values()) {
                    player.sendMessage("\u00a7c" + t.toString());
                }
            }
        }
        return true;
    }

    private static boolean Help(CommandSender sender) {
        sender.sendMessage("\u00a7c/hp sl [\u804c\u4e1a\u540d] \u00a7e//\u9009\u62e9\u804c\u4e1a");
        sender.sendMessage("\u00a7c/hp look \u00a7e//\u67e5\u770b\u81ea\u5df1\u7684\u804c\u4e1a");
        if (sender.isOp()) {
            sender.sendMessage("\u00a7c/hp itemLook \u00a7e//\u67e5\u770b\u624b\u4e2d\u7269\u54c1\u7684Lore");
            sender.sendMessage("\u00a7c/hp change [\u73a9\u5bb6\u540d] [\u804c\u4e1a\u540d] \u00a7e//\u66f4\u6362\u73a9\u5bb6\u7684\u804c\u4e1a");
            sender.sendMessage("\u00a7c/hp reload \u00a7e//\u91cd\u8f7d\u914d\u7f6e\u6587\u4ef6");
        }
        return true;
    }
}

