/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.command.CommandSender
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerKickEvent
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.event.player.PlayerQuitEvent
 */
package com.qq44920040.miecarft.hero.Heros.Event;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class HeroEvent
implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Data.getHero(event.getPlayer().getName());
    }

    @EventHandler
    public void PlayerKickEvent(PlayerKickEvent event) {
        Player player = event.getPlayer();
        Data.OutputPlayerData(player.getName(), Data.getHero(player.getName()));
        Data.RemoveHero(player.getName());
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Data.OutputPlayerData(player.getName(), Data.getHero(player.getName()));
        Data.RemoveHero(player.getName());
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (Data.getHero(player.getName()) == null) {
            Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), (String)Data.server_command.replace("%player%", player.getName()));
        }
    }

    @EventHandler
    public void PlayerMessage(AsyncPlayerChatEvent event) {
        Hero hero = Data.getHero(event.getPlayer().getName());
        if (hero != null) {
            event.setFormat(Data.chartitle.replace("%title%", hero.getTitle()) + event.getFormat());
        }
    }
}

