/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Arrow
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.util.Vector
 */
package com.qq44920040.miecarft.hero.Heros.next;

import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Event.ShooterEvent;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class Shooter
extends Hero {
    public Shooter() {
        super(Hero.HeroType.SHOOTER, Data.shooter_title);
        this.addSkill("crit", Data.shooter_crit_maybe, Data.shooter_crit_wait);
        this.addSkill("arrows", Data.shooter_arrows_maybe, Data.shooter_arrows_wait);
    }

    public double Skill_crit(Player player, double damage) {
        if (this.isUseSkill("crit", player)) {
            player.sendMessage(Data.title + Data.shooter_crit);
            return damage * 2.0;
        }
        return damage;
    }

    public void Skill_arrows(Player player, double damage) {
        if (this.isUseSkill("arrows", player)) {
            int number = (int)(Math.random() * 10.0) + 5;
            for (int i = 0; i < number; ++i) {
                Arrow arrow = (Arrow)player.launchProjectile(Arrow.class);
                Vector vector = player.getLocation().getDirection().multiply(5);
                vector.setX(vector.getX() + Math.random() * 4.0 - Math.random() * 4.0);
                vector.setY(vector.getY() + Math.random() * 2.0 - Math.random() * 1.0);
                vector.setZ(vector.getZ() + Math.random() * 4.0 - Math.random() * 4.0);
                arrow.setVelocity(vector);
                ShooterEvent.map.put(arrow.getUniqueId(), damage);
            }
            player.sendMessage(Data.title + Data.shooter_arrows);
        }
    }
}

