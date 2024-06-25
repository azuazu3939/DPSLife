package net.azisaba.dpslife;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPSCommand implements TabExecutor {
    @Override
    public boolean onCommand(@org.jetbrains.annotations.NotNull CommandSender commandSender, @org.jetbrains.annotations.NotNull Command command, @org.jetbrains.annotations.NotNull String s, @org.jetbrains.annotations.NotNull String[] strings) {
        if (!(commandSender instanceof Player)) return false;
        Player p = (Player) commandSender;
        if (strings.length != 1) {
            sendMessage(p);
            return true;
        }

        switch (strings[0]) {
            case "on":
                new ShowDPS().on(p);
                p.sendMessage("DPSの表示をonにしました。");
                break;
            case "off":
                new ShowDPS().off(p);
                p.sendMessage("DPSの表示をoffにしました。");
                break;
            case "clear":
                new DPS().clear(p.getUniqueId());
                p.sendMessage("DPSの履歴を全て消しました。");
                break;
            default:
                sendMessage(p);
                return true;
        }
        return false;
    }

    @Override
    public @org.jetbrains.annotations.Nullable List<String> onTabComplete(@org.jetbrains.annotations.NotNull CommandSender commandSender, @org.jetbrains.annotations.NotNull Command command, @org.jetbrains.annotations.NotNull String s, @org.jetbrains.annotations.NotNull String @NotNull [] strings) {
        if (strings.length == 1) return new ArrayList<>(Arrays.asList("on", "off", "clear"));
        return null;
    }

    private void sendMessage(@NotNull Player p) {
        p.sendMessage("/dps [on, off, clear]");
    }
}
