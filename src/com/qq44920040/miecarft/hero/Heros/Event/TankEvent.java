/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.v1_7_R4.EntityLiving
 *  net.minecraft.server.v1_7_R4.MobEffect
 *  net.minecraft.server.v1_7_R4.MobEffectList
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 *  org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.Player
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.entity.EntityDamageEvent
 *  org.bukkit.event.entity.EntityDamageEvent$DamageModifier
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 *  org.bukkit.scheduler.BukkitRunnable
 *  org.bukkit.scheduler.BukkitTask
 */
package com.qq44920040.miecarft.hero.Heros.Event;

import net.minecraft.server.v1_7_R4.EntityLiving;
import net.minecraft.server.v1_7_R4.MobEffectList;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftLivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.HeroPlugin;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.Tank;

public class TankEvent
implements Listener {
    private static final PotionEffect NODAMAGE = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 240, 1);

    public TankEvent() {
        new BukkitRunnable(){

            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    for (Player player : world.getPlayers()) {
                        Hero hero = Data.getHero(player.getName());
                        if (!(hero instanceof Tank)) continue;
                        CraftLivingEntity cle = (CraftLivingEntity)player;
                        EntityLiving el = cle.getHandle();
                        if (player.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
                            if (el.getEffect(MobEffectList.byId[PotionEffectType.DAMAGE_RESISTANCE.getId()]).getAmplifier() > NODAMAGE.getAmplifier()) continue;
                            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                        }
                        player.addPotionEffect(NODAMAGE);
                    }
                }
            }
        }.runTaskTimer((Plugin)HeroPlugin.getPlugin(), 0L, 200L);
    }

    @EventHandler(priority=EventPriority.LOW)
    public void EntityDamageByPlayerEvent(EntityDamageByEntityEvent event) {
        if (event.isCancelled() || !(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player)event.getDamager();
        Hero hero = Data.getHero(player.getName());
        if (hero instanceof Tank) {
            ((Tank)hero).Skill_hard(player, event.getDamage(EntityDamageEvent.DamageModifier.BASE));
        }
    }

}

