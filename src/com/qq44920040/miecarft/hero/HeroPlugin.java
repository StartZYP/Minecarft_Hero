/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.Server
 *  org.bukkit.World
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.PluginCommand
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.entity.Player
 *  org.bukkit.event.Listener
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.java.JavaPlugin
 */
package com.qq44920040.miecarft.hero;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import com.qq44920040.miecarft.hero.Commands.HeroCommand;
import com.qq44920040.miecarft.hero.Heros.Event.DarkInatorEvent;
import com.qq44920040.miecarft.hero.Heros.Event.GhostInkEvent;
import com.qq44920040.miecarft.hero.Heros.Event.HeroEvent;
import com.qq44920040.miecarft.hero.Heros.Event.ProjectileEvent;
import com.qq44920040.miecarft.hero.Heros.Event.ShooterEvent;
import com.qq44920040.miecarft.hero.Heros.Event.TankEvent;
import com.qq44920040.miecarft.hero.Heros.Event.WarriorEvent;

public class HeroPlugin
extends JavaPlugin {
    private File config = new File(this.getDataFolder(), "config.yml");
    public static HeroPlugin plugin;

    public void onEnable() {
        this.Config();
        plugin = this;
        Bukkit.getPluginManager().registerEvents((Listener)new HeroEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new DarkInatorEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ShooterEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new TankEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new WarriorEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new GhostInkEvent(), (Plugin)this);
        Bukkit.getPluginManager().registerEvents((Listener)new ProjectileEvent(), (Plugin)this);
        this.getServer().getPluginCommand("hp").setExecutor((CommandExecutor)new HeroCommand());
    }

    public void onDisable() {
        for (World world : Bukkit.getWorlds()) {
            for (Player player : world.getPlayers()) {
                Data.OutputPlayerData(player.getName(), Data.getHero(player.getName()));
            }
        }
    }

    public void Config() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
        if (!this.config.exists()) {
            this.saveDefaultConfig();
        }
        Data.title = this.getConfig().getString("title").replace('&', '\u00a7');
        Data.tank_hard = this.getConfig().getString("tank.skills.hard.message").replace('&', '\u00a7');
        Data.tank_hard_maybe = this.getConfig().getInt("tank.skills.hard.maybe");
        Data.tank_hard_wait = this.getConfig().getInt("tank.skills.hard.wait");
        Data.tank_title = this.getConfig().getString("tank.title").replace('&', '\u00a7');
        Data.warrior_crit = this.getConfig().getString("warrior.skills.crit.message").replace('&', '\u00a7');
        Data.warrior_fly = this.getConfig().getString("warrior.skills.fly.message").replace('&', '\u00a7');
        Data.warrior_crit_maybe = this.getConfig().getInt("warrior.skills.crit.maybe");
        Data.warrior_fly_maybe = this.getConfig().getInt("warrior.skills.fly.maybe");
        Data.warrior_crit_wait = this.getConfig().getInt("warrior.skills.crit.wait");
        Data.warrior_fly_wait = this.getConfig().getInt("warrior.skills.fly.wait");
        Data.warrior_title = this.getConfig().getString("warrior.title").replace('&', '\u00a7');
        Data.healer_Healone = this.getConfig().getString("healer.skills.healone.message").replace('&', '\u00a7');
        Data.healer_HealRange = this.getConfig().getString("healer.skills.healRange.message").replace('&', '\u00a7');
        Data.healer_Healone_maybe = this.getConfig().getInt("healer.skills.healone.maybe");
        Data.healer_HealRange_maybe = this.getConfig().getInt("healer.skills.healRange.maybe");
        Data.healer_Healone_wait = this.getConfig().getInt("healer.skills.healone.wait");
        Data.healer_HealRange_wait = this.getConfig().getInt("healer.skills.healRange.wait");
        Data.healer_Healone_lore = this.getConfig().getString("healer.skills.healone.lore").replace('&', '\u00a7');
        Data.healer_HealRange_lore = this.getConfig().getString("healer.skills.healRange.lore").replace('&', '\u00a7');
        Data.healer_title = this.getConfig().getString("healer.title").replace('&', '\u00a7');
        Data.darkinator_behindKill = this.getConfig().getString("darkinator.skills.behindKill.message").replace('&', '\u00a7');
        Data.darkinator_vanish = this.getConfig().getString("darkinator.skills.vanish.message").replace('&', '\u00a7');
        Data.darkinator_behindKill_maybe = this.getConfig().getInt("darkinator.skills.behindKill.maybe");
        Data.darkinator_vanish_maybe = this.getConfig().getInt("darkinator.skills.vanish.maybe");
        Data.darkinator_behindKill_wait = this.getConfig().getInt("darkinator.skills.behindKill.wait");
        Data.darkinator_vanish_wait = this.getConfig().getInt("darkinator.skills.vanish.wait");
        Data.darkinator_vanish_lore = this.getConfig().getString("darkinator.skills.vanish.lore").replace('&', '\u00a7');
        Data.darkinator_title = this.getConfig().getString("darkinator.title").replace('&', '\u00a7');
        Data.helper_fix = this.getConfig().getString("helper.skills.fix.message").replace('&', '\u00a7');
        Data.helper_nodamage = this.getConfig().getString("helper.skills.nodamage.message").replace('&', '\u00a7');
        Data.helper_fix_lore = this.getConfig().getString("helper.skills.fix.lore").replace('&', '\u00a7');
        Data.helper_nodamage_lore = this.getConfig().getString("helper.skills.nodamage.lore").replace('&', '\u00a7');
        Data.helper_fix_maybe = this.getConfig().getInt("helper.skills.fix.maybe");
        Data.helper_nodamage_maybe = this.getConfig().getInt("helper.skills.nodamage.maybe");
        Data.helper_fix_wait = this.getConfig().getInt("helper.skills.fix.wait");
        Data.helper_nodamage_wait = this.getConfig().getInt("helper.skills.nodamage.wait");
        Data.helper_title = this.getConfig().getString("helper.title").replace('&', '\u00a7');
        Data.magician_fire = this.getConfig().getString("magician.skills.fire.message").replace('&', '\u00a7');
        Data.magician_ghost = this.getConfig().getString("magician.skills.ghost.message").replace('&', '\u00a7');
        Data.magician_fire_lore = this.getConfig().getString("magician.skills.fire.lore").replace('&', '\u00a7');
        Data.magician_ghost_lore = this.getConfig().getString("magician.skills.ghost.lore").replace('&', '\u00a7');
        Data.magician_fire_maybe = this.getConfig().getInt("magician.skills.fire.maybe");
        Data.magician_ghost_maybe = this.getConfig().getInt("magician.skills.ghost.maybe");
        Data.magician_fire_wait = this.getConfig().getInt("magician.skills.fire.wait");
        Data.magician_ghost_wait = this.getConfig().getInt("magician.skills.ghost.wait");
        Data.magician_title = this.getConfig().getString("magician.title").replace('&', '\u00a7');
        Data.shooter_crit = this.getConfig().getString("shooter.skills.crit.message").replace('&', '\u00a7');
        Data.shooter_arrows = this.getConfig().getString("shooter.skills.arrows.message").replace('&', '\u00a7');
        Data.shooter_crit_maybe = this.getConfig().getInt("shooter.skills.crit.maybe");
        Data.shooter_arrows_maybe = this.getConfig().getInt("shooter.skills.arrows.maybe");
        Data.shooter_crit_wait = this.getConfig().getInt("shooter.skills.crit.wait");
        Data.shooter_arrows_wait = this.getConfig().getInt("shooter.skills.arrows.wait");
        Data.shooter_title = this.getConfig().getString("shooter.title").replace('&', '\u00a7');
        Data.ghostInk_effect = this.getConfig().getString("ghostInk.skills.effect.message").replace('&', '\u00a7');
        Data.ghostInk_effect_lore = this.getConfig().getString("ghostInk.skills.effect.lore").replace('&', '\u00a7');
        Data.ghostInk_effect_maybe = this.getConfig().getInt("ghostInk.skills.effect.maybe");
        Data.ghostInk_effect_wait = this.getConfig().getInt("ghostInk.skills.effect.wait");
        Data.ghostInk_title = this.getConfig().getString("ghostInk.title").replace('&', '\u00a7');
        Data.server_command = this.getConfig().getString("server_command").replace('&', '\u00a7');
        Data.Wait = this.getConfig().getString("wait").replace('&', '\u00a7');
        Data.chartitle = this.getConfig().getString("chartitle").replace('&', '\u00a7');
        this.onDisable();
        Data.clear();
        for (World world : Bukkit.getWorlds()) {
            for (Player player : world.getPlayers()) {
                Data.InputPlayerData(player.getName());
            }
        }
    }

    public static HeroPlugin getPlugin() {
        return plugin;
    }
}

