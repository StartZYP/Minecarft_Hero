/*
 * Decompiled with CFR 0.138.
 * 
 * Could not load the following classes:
 *  org.bukkit.Location
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.Projectile
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.util.Vector
 */
package com.qq44920040.miecarft.hero.util;


import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;


public class Util {
    public static UUID PlayerLaunchProjectile(Player player, Class<? extends Projectile> c) {
        Projectile ball = player.launchProjectile(c, player.getLocation().getDirection().multiply(5));
        return ball.getUniqueId();
    }

    public static boolean ItemHasLore(ItemStack is, String label) {
        if (is != null && is.getItemMeta() != null && is.getItemMeta().hasLore()) {
            for (String lore : is.getItemMeta().getLore()) {
                if (!label.equals(lore)) continue;
                return true;
            }
        }
        return false;
    }

    public static boolean isEntityInIt(Location entityLct, Location start, Location end) {
        boolean y;
        boolean x;
        x = start.getX() >= end.getX() ? entityLct.getX() >= end.getX() && entityLct.getX() <= start.getX() : (x = entityLct.getX() >= start.getX() && entityLct.getX() <= end.getX());
        y = start.getY() >= end.getY() ? entityLct.getY() >= end.getY() && entityLct.getY() <= start.getY() : (y = entityLct.getY() >= start.getY() && entityLct.getY() <= end.getY());
        boolean z = start.getZ() >= end.getZ() ? entityLct.getZ() >= end.getZ() && entityLct.getZ() <= start.getZ() : entityLct.getZ() >= start.getZ() && entityLct.getZ() <= end.getZ();
        return x && y && z;
    }

    public static boolean isEntityRange(Location lct, Location lct1, int r) {
        double cy = Math.abs(lct.getY() - lct1.getY());
        double xy = Math.abs(lct.getX() - lct1.getX());
        double zy = Math.abs(lct.getZ() - lct1.getZ());
        return cy <= (double)r && xy <= (double)r && zy <= (double)r;
    }
}

