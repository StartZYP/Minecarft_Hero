/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.qq44920040.miecarft.hero.Heros.next;

import org.bukkit.entity.Player;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;

public class Tank
extends Hero {
    public Tank() {
        super(Hero.HeroType.TANK, Data.tank_title);
        this.addSkill("hard", Data.tank_hard_maybe, Data.tank_hard_wait);
    }

    public void Skill_hard(Player player, double damage) {
        if (this.isUseSkill("hard", player)) {
            Player d = player;
            double heal = d.getHealth() + damage * 0.25;
            if (heal > d.getMaxHealth()) {
                d.setHealth(d.getMaxHealth());
            } else {
                d.setHealth(heal);
            }
            player.sendMessage(Data.title + Data.tank_hard);
        }
    }
}

