package net.azisaba.dpslife;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.NotNull;

public class DPSListener implements Listener {

    @EventHandler
    public void onDeath(@NotNull EntityDeathEvent e) {
        LivingEntity living = e.getEntity();
        if (living instanceof Player) return;
        DPSLife.getPlugin().runAsync(()-> DPS.remove(living.getUniqueId()));
    }

    @EventHandler
    public void onDamage(@NotNull EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof LivingEntity)) return;
        Player p = (Player) e.getDamager();
        LivingEntity living = (LivingEntity) e.getEntity();

        if (!ShowDPS.isOn(p)) return;
        DPSLife.getPlugin().runAsync(()-> {
            DPS.add(p.getUniqueId(), living.getUniqueId(), e.getDamage());

            ShowDPS.show(p, DPS.getDamage(p.getUniqueId(), living.getUniqueId()));
        });
    }
}
