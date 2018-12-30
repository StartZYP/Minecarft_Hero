/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.com.google.common.collect.Maps
 *  org.bukkit.entity.Damageable
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.entity.EntityDamageByEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.qq44920040.miecarft.hero.Heros.Event;

import java.util.Map;
import java.util.UUID;
import net.minecraft.util.com.google.common.collect.Maps;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.Healer;
import com.qq44920040.miecarft.hero.Heros.next.Helper;
import com.qq44920040.miecarft.hero.Heros.next.Magician;
import com.qq44920040.miecarft.hero.module.entity.SpecialProjectile;
import com.qq44920040.miecarft.hero.util.Util;

public class ProjectileEvent
implements Listener {
    private Map<UUID, SpecialProjectile> balls = Maps.newHashMap();
    private static final PotionEffect HELP_SLOWEST = new PotionEffect(PotionEffectType.SLOW, 100, 20);
    private static final PotionEffect HELP_SLOW = new PotionEffect(PotionEffectType.SLOW, 100, 4);
    private static final PotionEffect HELP_NODAMAGE = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 4);
    private static final PotionEffect BLIND = new PotionEffect(PotionEffectType.BLINDNESS, 160, 0);

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Hero hero;
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        Action action = event.getAction();
        boolean isLeftClick = false;
        if (action.equals((Object)Action.PHYSICAL)) {
            return;
        }
        if (action.equals((Object)Action.LEFT_CLICK_AIR) || action.equals((Object)Action.LEFT_CLICK_BLOCK)) {
            isLeftClick = true;
        }
        if ((hero = Data.getHero(player.getName())) == null) {
            return;
        }
        switch (hero.getType()) {
            case HEALER: {
                Healer healer = (Healer)hero;
                if (isLeftClick && Util.ItemHasLore(item, Data.healer_Healone_lore)) {
                    this.balls.put(healer.Skill_Healone(player), new SpecialProjectile(SpecialProjectile.SpecialProjectileType.HEALER_SKILL_ONE));
                    break;
                }
                if (isLeftClick || !Util.ItemHasLore(item, Data.healer_HealRange_lore)) break;
                healer.Skill_HealRange(player);
                break;
            }
            case HELPER: {
                Helper helper = (Helper)hero;
                if (isLeftClick && Util.ItemHasLore(item, Data.helper_fix_lore)) {
                    this.balls.put(helper.Skill_fix(player), new SpecialProjectile(SpecialProjectile.SpecialProjectileType.HELPER_SKILL_FIX));
                    break;
                }
                if (isLeftClick || !Util.ItemHasLore(item, Data.helper_nodamage_lore)) break;
                this.balls.put(helper.Skill_nodamage(player), new SpecialProjectile(SpecialProjectile.SpecialProjectileType.HELPER_SKILL_NODAMAGE));
                break;
            }
            case MAGICIAN: {
                Magician m = (Magician)hero;
                if (isLeftClick && Util.ItemHasLore(item, Data.magician_fire_lore)) {
                    this.balls.put(m.Skill_fire(player), new SpecialProjectile(SpecialProjectile.SpecialProjectileType.MAGICIAN_SKILL_FIRE));
                    break;
                }
                if (isLeftClick || !Util.ItemHasLore(item, Data.magician_ghost_lore)) break;
                this.balls.put(m.Skill_ghost(player), new SpecialProjectile(SpecialProjectile.SpecialProjectileType.MAGICIAN_SKILL_GHOST));
                break;
            }
        }
    }

    @EventHandler(priority=EventPriority.LOWEST)
    public void ProjectileDamageByEntityEvent(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile) || !this.balls.containsKey(event.getDamager().getUniqueId())) {
            return;
        }
        Entity damagerEntity = event.getDamager();
        SpecialProjectile sball = this.balls.get(damagerEntity.getUniqueId());
        event.setDamage(0.0);
        switch (sball.getType()) {
            case HEALER_SKILL_ONE: {
                if (!(event.getEntity() instanceof Player)) break;
                Damageable d = (Damageable)event.getEntity();
                d.setHealth(d.getMaxHealth());
                break;
            }
            case HELPER_SKILL_FIX: {
                if (!(event.getEntity() instanceof LivingEntity)) break;
                LivingEntity le2 = (LivingEntity)event.getEntity();
                if (le2.hasPotionEffect(PotionEffectType.SLOW)) {
                    le2.removePotionEffect(PotionEffectType.SLOW);
                }
                le2.addPotionEffect(HELP_SLOWEST);
                break;
            }
            case HELPER_SKILL_NODAMAGE: {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity entity = (LivingEntity)event.getEntity();
                    if (entity.hasPotionEffect(PotionEffectType.SLOW)) {
                        entity.removePotionEffect(PotionEffectType.SLOW);
                    }
                    if (entity.hasPotionEffect(PotionEffectType.DAMAGE_RESISTANCE)) {
                        entity.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
                    }
                    entity.addPotionEffect(HELP_SLOW);
                    entity.addPotionEffect(HELP_NODAMAGE);
                }
            }
            case MAGICIAN_SKILL_FIRE: {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity le = (LivingEntity)event.getEntity();
                    le.setFireTicks(20);
                    event.setDamage(le.getMaxHealth() * 0.2);
                }
            }
            case MAGICIAN_SKILL_GHOST: {
                if (!(event.getEntity() instanceof LivingEntity)) break;
                LivingEntity le = (LivingEntity)event.getEntity();
                if (!le.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    le.addPotionEffect(BLIND);
                }
                event.setDamage(le.getMaxHealth() * 0.25);
            }
        }
    }

}

