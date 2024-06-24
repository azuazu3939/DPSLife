package net.azisaba.dpslife;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DPSLife extends JavaPlugin {

    private static DPSLife plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Objects.requireNonNull(getCommand("dps")).setExecutor(new DPSCommand());
        getServer().getPluginManager().registerEvents(new DPSListener(), this);
    }

    public void runAsync(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(this, runnable);}

    public void runAsyncDelayed(Runnable runnable, long delay) {Bukkit.getScheduler().runTaskLaterAsynchronously(this, runnable, delay);}

    public static DPSLife getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
