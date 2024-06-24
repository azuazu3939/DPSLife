package net.azisaba.dpslife;

import java.util.UUID;

public class DamageData {

    private final double damage;
    private final UUID uuid;

    public DamageData(UUID uuid, double damage) {
        this.damage = damage;
        this.uuid = uuid;
    }

    public double damage() {
        return damage;
    }

    public UUID uuid() {
        return uuid;
    }
}
