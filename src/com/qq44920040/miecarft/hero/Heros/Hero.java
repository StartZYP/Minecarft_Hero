/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.entity.Player
 */
package com.qq44920040.miecarft.hero.Heros;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public abstract class Hero {
    private final HeroType type;
    private Map<String, HeroSkill> skills = new HashMap<String, HeroSkill>();
    private int level = 1;
    private final String title;

    public Hero(HeroType type, String title) {
        this.type = type;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    protected void addSkill(String name, int maybe, long wait) {
        this.skills.put(name, new HeroSkill(maybe, wait));
    }

    protected void setSkillMaybe(String name, int maybe) {
        if (!this.skills.containsKey(name)) {
            return;
        }
        this.skills.get(name).setOdds(maybe);
    }

    protected void setSkillWait(String name, int wait) {
        if (!this.skills.containsKey(name)) {
            return;
        }
        this.skills.get(name).setWait(wait);
    }

    protected boolean isUseSkill(String name, Player player) {
        if (!this.skills.containsKey(name)) {
            return false;
        }
        return this.skills.get(name).isUse(player);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }

    public HeroType getType() {
        return this.type;
    }

    public String toString() {
        return this.type.toString() + "_" + this.level;
    }

    public static enum HeroType {
        TANK,
        WARRIOR,
        SHOOTER,
        HEALER,
        DARKINATOR,
        HELPER,
        MAGICIAN,
        GHOSTINK;
        
    }

}

