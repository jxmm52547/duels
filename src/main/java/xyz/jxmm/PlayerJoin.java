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
    public void onPlayerJoin(org.bukkit.event.player.PlayerJoinEvent e)
    {
        org.bukkit.entity.Player p = e.getPlayer();
        p.teleport(Duels.lobbyLocation);

    }
}
