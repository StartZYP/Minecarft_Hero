/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.LivingEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.qq44920040.miecarft.hero.Heros.next;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class GhostInk
extends Hero {
    private static final PotionEffect SPEED_FOUR = new PotionEffect(PotionEffectType.SPEED, 100, 3);
    private static final PotionEffect DAMAGERESISTANCE_FOUR = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 3);
    private static final PotionEffect HEAL_FOUR = new PotionEffect(PotionEffectType.HEAL, 100, 3);
    private static final PotionEffect WEAKNESS_FOUR = new PotionEffect(PotionEffectType.WEAKNESS, 100, 3);
    public static final PotionEffect SLOW_TWO = new PotionEffect(PotionEffectType.SLOW, 80, 1);
    public static final PotionEffect POISON_THREE = new PotionEffect(PotionEffectType.POISON, 80, 1);

    public GhostInk() {
        super(Hero.HeroType.GHOSTINK, Data.ghostInk_title);
        this.addSkill("effect", Data.ghostInk_effect_maybe, Data.ghostInk_effect_wait);
    }

    public void skillEffect(Player player) {
        if (this.isUseSkill("effect", player)) {
            GhostInk.addPotionEffect((LivingEntity)player, SPEED_FOUR);
            GhostInk.addPotionEffect((LivingEntity)player, DAMAGERESISTANCE_FOUR);
            GhostInk.addPotionEffect((LivingEntity)player, HEAL_FOUR);
            GhostInk.addPotionEffect((LivingEntity)player, WEAKNESS_FOUR);
            player.sendMessage(Data.title + Data.ghostInk_effect);
        }
    }

    public static void addPotionEffect(LivingEntity le, PotionEffect pe) {
        if (le.hasPotionEffect(pe.getType())) {
            le.removePotionEffect(pe.getType());
        }
        le.addPotionEffect(pe);
    }
}

