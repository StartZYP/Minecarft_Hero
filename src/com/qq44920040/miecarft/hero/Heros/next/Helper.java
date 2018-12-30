/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Fireball
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Snowball
 */
package com.qq44920040.miecarft.hero.Heros.next;

import java.util.UUID;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.util.Util;

public class Helper
extends Hero {
    public Helper() {
        super(Hero.HeroType.HELPER, Data.helper_title);
        this.addSkill("fix", Data.helper_fix_maybe, Data.helper_fix_wait);
        this.addSkill("nodamage", Data.helper_nodamage_maybe, Data.helper_nodamage_wait);
    }

    public UUID Skill_fix(Player player) {
        if (this.isUseSkill("fix", player)) {
            UUID uuid = Util.PlayerLaunchProjectile(player, Snowball.class);
            player.sendMessage(Data.title + Data.helper_fix);
            return uuid;
        }
        return null;
    }

    public UUID Skill_nodamage(Player player) {
        if (this.isUseSkill("nodamage", player)) {
            UUID uuid = Util.PlayerLaunchProjectile(player, Fireball.class);
            player.sendMessage(Data.title + Data.helper_nodamage);
            return uuid;
        }
        return null;
    }
}

