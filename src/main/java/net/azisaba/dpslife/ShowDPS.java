package net.azisaba.dpslife;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ShowDPS {

    private static final Set<String> PLAYERS = new HashSet<>();

    public void on(@NotNull Player p) {
        PLAYERS.add(p.getName());
    }

    public void off(@NotNull Player p) {PLAYERS.remove(p.getName());}

    public static boolean isOn(@NotNull Player p) {return PLAYERS.contains(p.getName());}

    public static void show(@NotNull Player p, String damage) {
        String comp = "                                              DPS " + damage;
        p.sendTitle("", comp, 0, 100, 0);
    }
}
