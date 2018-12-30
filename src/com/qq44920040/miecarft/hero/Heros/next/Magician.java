/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.WitherSkull
 */
package com.qq44920040.miecarft.hero.Heros.next;

import java.util.UUID;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.util.Util;

public class Magician
extends Hero {
    public Magician() {
        super(Hero.HeroType.MAGICIAN, Data.magician_title);
        this.addSkill("fire", Data.magician_fire_maybe, Data.magician_fire_wait);
        this.addSkill("ghost", Data.magician_ghost_maybe, Data.magician_ghost_wait);
    }

    public UUID Skill_fire(Player player) {
        if (this.isUseSkill("fire", player)) {
            UUID uuid = Util.PlayerLaunchProjectile(player, Fireball.class);
            player.sendMessage(Data.title + Data.magician_fire);
            return uuid;
        }
        return null;
    }

    public UUID Skill_ghost(Player player) {
        if (this.isUseSkill("ghost", player)) {
            UUID uuid = Util.PlayerLaunchProjectile(player, WitherSkull.class);
            player.sendMessage(Data.title + Data.magician_ghost);
            return uuid;
        }
        return null;
    }
}

