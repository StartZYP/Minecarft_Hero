/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Item
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageModifier
 *  org.bukkit.event.player.PlayerPickupItemEvent
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package com.qq44920040.miecarft.hero.Heros.Event;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.HeroPlugin;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.Shooter;

public class ShooterEvent
implements Listener {
    public static final Map<UUID, Double> map = new HashMap<UUID, Double>();
    private static final PotionEffect JUMP_EFFECT = new PotionEffect(PotionEffectType.JUMP ,Integer.MAX_VALUE, 1);
    private static final PotionEffect SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED ,Integer.MAX_VALUE, 2);
    private static final PotionEffect NIGHT_VISION_EFFECT = new PotionEffect(PotionEffectType.NIGHT_VISION ,Integer.MAX_VALUE, 1);


    public ShooterEvent() {
        new BukkitRunnable(){

            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Entity entity : world.getEntities()) {
                        if (!ShooterEvent.map.containsKey(entity.getUniqueId())) continue;
                        entity.remove();
                        ShooterEvent.map.remove(entity.getUniqueId());
                    }
                }
            }
        }.runTaskTimer((Plugin)HeroPlugin.getPlugin(), 0L, 1200L);
    }

    @EventHandler
    public void PlayerPickEvent(PlayerPickupItemEvent event) {
        if (map.containsKey(event.getItem().getUniqueId())) {
            event.setCancelled(true);
            map.remove(event.getItem().getUniqueId());
            event.getItem().remove();
        }
    }


    @EventHandler(priority=EventPriority.LOW)
    public void PlayerDamageByEntityEvent(EntityDamageByEntityEvent event) {
        Hero hero;
        Player player;
        Arrow arrow;
        if (event.getDamager() instanceof Player){
            ((Player) event.getDamager()).addPotionEffect(JUMP_EFFECT);
            ((Player) event.getDamager()).addPotionEffect(SPEED_EFFECT);
            ((Player) event.getDamager()).addPotionEffect(NIGHT_VISION_EFFECT);
        }
        if (map.containsKey(event.getDamager().getUniqueId())) {
            event.setDamage(map.get(event.getDamager().getUniqueId()).doubleValue());
            map.remove(event.getDamager().getUniqueId());
            return;
        }
        if (event.getDamager() instanceof Arrow && (arrow = (Arrow)event.getDamager()).getShooter() instanceof Player && (hero = Data.getHero((player = (Player)arrow.getShooter()).getName())) != null && hero instanceof Shooter) {
            Shooter shooter = (Shooter)hero;
            event.setDamage(shooter.Skill_crit(player, event.getDamage(EntityDamageEvent.DamageModifier.BASE)));
            shooter.Skill_arrows(player, event.getDamage(EntityDamageEvent.DamageModifier.BASE));
        }
    }

}

