/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 */
package com.qq44920040.miecarft.hero.Heros.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.DarkInator;
import com.qq44920040.miecarft.hero.util.Util;

public class DarkInatorEvent
implements Listener {
    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent event) {
        if (event.hasItem() && (event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK))) {
            Player player = event.getPlayer();
            Hero hero = Data.getHero(player.getName());
            if (!(hero instanceof DarkInator) || !Util.ItemHasLore(event.getItem(), Data.darkinator_vanish_lore)) {
                return;
            }
            DarkInator di = (DarkInator)hero;
            di.Skill_vanish(player);
        }
    }

    @EventHandler
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getDamager();
        Hero hero = Data.getHero(player.getName());
        if (hero == null || !(hero instanceof DarkInator)) {
            return;
        }
        DarkInator di = (DarkInator)hero;
        di.Skill_behindKill(event);
    }
}

