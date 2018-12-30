/*
 * Decompiled with CFR 0.138.
 */
package com.qq44920040.miecarft.hero.module.entity;

public class SpecialProjectile {
    private SpecialProjectileType type;

    public SpecialProjectile(SpecialProjectileType type) {
        this.type = type;
    }

    public void setType(SpecialProjectileType type) {
        this.type = type;
    }

    public SpecialProjectileType getType() {
        return this.type;
    }

    public static enum SpecialProjectileType {
        HEALER_SKILL_ONE,
        HELPER_SKILL_FIX,
        HELPER_SKILL_NODAMAGE,
        MAGICIAN_SKILL_FIRE,
        MAGICIAN_SKILL_GHOST;
        
    }

}

