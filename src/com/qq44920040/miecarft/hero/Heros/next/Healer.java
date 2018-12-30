/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.World
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Snowball
 *  org.bukkit.potion.PotionEffect
 *  org.bukkit.potion.PotionEffectType
 */
package com.qq44920040.miecarft.hero.Heros.next;


import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import com.qq44920040.miecarft.hero.Data;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.util.Util;

public class Healer
extends Hero {
    private static final PotionEffect SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED, 100, 2);

    public Healer() {
        super(Hero.HeroType.HEALER, Data.healer_title);
        this.addSkill("Healone", Data.healer_Healone_maybe, Data.healer_Healone_wait);
        this.addSkill("HealRange", Data.healer_HealRange_maybe, Data.healer_HealRange_wait);
    }

    public void Skill_HealRange(Player player) {
        if (this.isUseSkill("HealRange", player)) {
            Location lct = player.getLocation();
            int r = 5;
            double minx = lct.getX() - (double)r;
            double minz = lct.getZ() - (double)r;
            double miny = lct.getY() - (double)r;
            double maxx = lct.getX() + (double)r;
            double maxz = lct.getZ() + (double)r;
            double maxy = lct.getY() + (double)r;
            for (Player p2 : lct.getWorld().getPlayers()) {
//                Player d = player;
//                if (!Util.isEntityRange(p2.getLocation(), lct, 5)) continue;
//                if (!p2.hasPotionEffect(PotionEffectType.SPEED)) {
//                    p2.addPotionEffect(SPEED_EFFECT);
//                }
//                d.setHealth(0.0 + d.getMaxHealth());
                Location lct2 = p2.getLocation();
                if ( lct2.getX() < minx || lct2.getX() > maxx || lct2.getZ() < minz || lct2.getZ() > maxz || lct2.getY() < miny || lct2.getY() > maxy){
                    p2.setHealth(0.0+p2.getMaxHealth());
                }
            }
            player.sendMessage(Data.title + Data.healer_HealRange);
        }
    }

    public UUID Skill_Healone(Player healer) {
        if (this.isUseSkill("Healone", healer)) {
            UUID uuid = Util.PlayerLaunchProjectile(healer, Snowball.class);
            healer.sendMessage(Data.title + Data.healer_Healone);
            return uuid;
        }
        return null;
    }
}

