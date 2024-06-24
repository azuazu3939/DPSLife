package net.azisaba.dpslife;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.UUID;

public class DPS {
    private static final Multimap<UUID, DamageData> DAMAGES = Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    public static void add(UUID player, UUID victim, Double damage) {
        DamageData data = new DamageData(victim, damage);
        DAMAGES.put(player, data);
        DPSLife.getPlugin().runAsyncDelayed(()-> DAMAGES.remove(player, data), 100);
    }

    public void clear(UUID player) {
        DAMAGES.removeAll(player);
    }

    public static String getDamage(UUID player, UUID victim) {
        double v = 0;
        for (DamageData d : new ArrayList<>(DAMAGES.get(player))) {
            if (d.uuid() != victim) continue;
            v+= d.damage();
        }

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        return nf.format(v / 5);
    }

    public static void remove(UUID victim) {
        for (UUID key : DAMAGES.keySet()) {
            for (DamageData d : new ArrayList<>(DAMAGES.get(key))) {
                if (d.uuid() == victim) DAMAGES.remove(key, d);
            }
        }
    }
}
