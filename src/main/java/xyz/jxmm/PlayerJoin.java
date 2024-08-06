package xyz.jxmm;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.awt.*;

public class PlayerJoin implements Listener {
    public static PlayerJoin instance;

    public PlayerJoin() {
        instance = this;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e) {
        org.bukkit.entity.Player p = e.getPlayer();
        if (Duels.lobbyLocation != null){
            p.teleport(Duels.lobbyLocation);
            p.sendTitle("§a§l欢迎来到§b§l終末牽挂§a§l的§b§lDuels大厅", "§a§lWelcome back!", 10, 70, 20);
            p.setGameMode(org.bukkit.GameMode.ADVENTURE);
        }


    }
}
