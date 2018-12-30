/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.PotionEffect
 */
package com.qq44920040.miecarft.hero.Heros.Event;


import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.GhostInk;
import com.qq44920040.miecarft.hero.util.Util;

public class GhostInkEvent
implements Listener {
    @EventHandler
    public void PlayerRightClick(PlayerInteractEvent event) {
        Player player;
        Hero hero;
        if (event.hasItem() && (hero = Data.getHero((player = event.getPlayer()).getName())) instanceof GhostInk) {
            GhostInk m = (GhostInk)hero;
            if ((event.getAction().equals((Object)Action.RIGHT_CLICK_AIR) || event.getAction().equals((Object)Action.RIGHT_CLICK_BLOCK)) && Util.ItemHasLore(player.getItemInHand(), Data.ghostInk_effect_lore)) {
                m.skillEffect(player);
            }
        }
    }

    @EventHandler(priority=EventPriority.LOW)
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getDamager();
        Hero hero = Data.getHero(player.getName());
        if (hero instanceof GhostInk && event.getEntity() instanceof LivingEntity) {
            LivingEntity le = (LivingEntity)event.getEntity();
            GhostInk.addPotionEffect(le, GhostInk.SLOW_TWO);
            GhostInk.addPotionEffect(le, GhostInk.POISON_THREE);
        }
    }
}

