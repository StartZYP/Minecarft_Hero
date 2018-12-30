/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.qq44920040.miecarft.hero.Heros;

import org.bukkit.entity.Player;
import com.qq44920040.miecarft.hero.Data;

public final class HeroSkill {
    private int odds;
    private long wait;
    private long endWait;

    public HeroSkill(int odds, long wait) {
        this.odds = odds;
        this.wait = wait;
    }

    public void setOdds(int odds) {
        this.odds = odds;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getOdds() {
        return this.odds;
    }

    public long getWait() {
        return this.wait;
    }

    public boolean isUse(Player player) {
        long nowtime = System.currentTimeMillis();
        if (nowtime >= this.endWait) {
            if (HeroSkill.Maybe(this.odds)) {
                this.endWait = nowtime + this.wait * 1000L;
                return true;
            }
        } else {
            player.sendMessage(Data.Wait.replace("%time%", String.valueOf(this.endWait / 1000L - nowtime / 1000L)));
        }
        return false;
    }

    private static boolean Maybe(int maybe) {
        return (int)(Math.random() * 100.0) < maybe;
    }
}

