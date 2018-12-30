/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 */
package com.qq44920040.miecarft.hero;

import com.google.common.collect.Maps;
import java.io.File;
import java.util.Map;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.qq44920040.miecarft.hero.Heros.Hero;
import com.qq44920040.miecarft.hero.Heros.next.DarkInator;
import com.qq44920040.miecarft.hero.Heros.next.GhostInk;
import com.qq44920040.miecarft.hero.Heros.next.Healer;
import com.qq44920040.miecarft.hero.Heros.next.Helper;
import com.qq44920040.miecarft.hero.Heros.next.Magician;
import com.qq44920040.miecarft.hero.Heros.next.Shooter;
import com.qq44920040.miecarft.hero.Heros.next.Tank;
import com.qq44920040.miecarft.hero.Heros.next.Warrior;

public abstract class Data {
    private static final File data = new File("plugins/Hero/Data/");
    private static Map<String, Hero> heros = Maps.newHashMap();
    public static String server_command;
    public static String title;
    public static String Wait;
    public static String chartitle;
    public static String tank_hard;
    public static int tank_hard_maybe;
    public static int tank_hard_wait;
    public static String tank_title;
    public static String warrior_crit;
    public static String warrior_fly;
    public static int warrior_crit_maybe;
    public static int warrior_fly_maybe;
    public static int warrior_crit_wait;
    public static int warrior_fly_wait;
    public static String warrior_title;
    public static String healer_Healone;
    public static String healer_HealRange;
    public static String healer_Healone_lore;
    public static String healer_HealRange_lore;
    public static int healer_Healone_maybe;
    public static int healer_HealRange_maybe;
    public static int healer_Healone_wait;
    public static int healer_HealRange_wait;
    public static String healer_title;
    public static String darkinator_behindKill;
    public static String darkinator_vanish;
    public static int darkinator_behindKill_maybe;
    public static int darkinator_vanish_maybe;
    public static int darkinator_behindKill_wait;
    public static int darkinator_vanish_wait;
    public static String darkinator_vanish_lore;
    public static String darkinator_title;
    public static String helper_fix;
    public static String helper_nodamage;
    public static String helper_fix_lore;
    public static String helper_nodamage_lore;
    public static int helper_fix_maybe;
    public static int helper_nodamage_maybe;
    public static int helper_fix_wait;
    public static int helper_nodamage_wait;
    public static String helper_title;
    public static String magician_fire;
    public static String magician_ghost;
    public static String magician_fire_lore;
    public static String magician_ghost_lore;
    public static int magician_fire_maybe;
    public static int magician_ghost_maybe;
    public static int magician_fire_wait;
    public static int magician_ghost_wait;
    public static String magician_title;
    public static String shooter_crit;
    public static String shooter_arrows;
    public static int shooter_crit_maybe;
    public static int shooter_crit_wait;
    public static int shooter_arrows_maybe;
    public static int shooter_arrows_wait;
    public static String shooter_title;
    public static String ghostInk_title;
    public static String ghostInk_effect;
    public static String ghostInk_effect_lore;
    public static int ghostInk_effect_maybe;
    public static int ghostInk_effect_wait;

    public static void clear() {
        heros = Maps.newHashMap();
    }

    public static boolean addHero(String name, Hero.HeroType type) {
        switch (type) {
            case TANK: {
                heros.put(name, new Tank());
                return true;
            }
            case WARRIOR: {
                heros.put(name, new Warrior());
                return true;
            }
            case HEALER: {
                heros.put(name, new Healer());
                return true;
            }
            case SHOOTER: {
                heros.put(name, new Shooter());
                return true;
            }
            case HELPER: {
                heros.put(name, new Helper());
                return true;
            }
            case DARKINATOR: {
                heros.put(name, new DarkInator());
                return true;
            }
            case MAGICIAN: {
                heros.put(name, new Magician());
                return true;
            }
            case GHOSTINK: {
                heros.put(name, new GhostInk());
                return true;
            }
        }
        return false;
    }

    public static Hero getHero(String name) {
        if (!heros.containsKey(name)) {
            Data.InputPlayerData(name);
            if (!heros.containsKey(name)) {
                return null;
            }
        }
        return heros.get(name);
    }

    public static void OutputPlayerData(String name, Hero hero) {
        FileConfiguration pdf;
        File pd;
        if (hero == null) {
            return;
        }
        if (!data.exists()) {
            data.mkdirs();
        }
        if ((pdf = Data.getFileConfiguration(pd = new File(data, name + ".yml"))) == null) {
            return;
        }
        pdf.set("type", (Object)hero.getType().toString());
        pdf.set("level", (Object)hero.getLevel());
        try {
            pdf.save(pd);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void InputPlayerData(String name) {
        File pd = new File(data, name + ".yml");
        if (!pd.exists()) {
            return;
        }
        FileConfiguration pdf = Data.getFileConfiguration(pd);
        if (pdf == null) {
            return;
        }
        try {
            Data.addHero(name, Hero.HeroType.valueOf(pdf.getString("type")));
            Data.getHero(name).setLevel(pdf.getInt("level"));
        }
        catch (Exception e) {
            pd.delete();
            return;
        }
    }

    public static void RemoveHero(String name) {
        heros.remove(name);
    }

    private static FileConfiguration getFileConfiguration(File file) {
        return YamlConfiguration.loadConfiguration((File)file);
    }

}

