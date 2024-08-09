package xyz.jxmm.arena;

import com.google.gson.JsonObject;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import xyz.jxmm.Duels;
import xyz.jxmm.utils.FileWriterMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SetupSession implements ISetupSession{
    private static List<SetupSession> setupSessions = new ArrayList<>();
    private Player player;
    private String worldName;
    static Plugin plugin = Duels.getPlugin();
    static File arenasFolder = new File(plugin.getDataFolder().getPath() + "\\arenas");
    public SetupSession(Player player, String worldName) {
        this.player = player;
        this.worldName = worldName;
        getSetupSessions().add(this);
        createWorld(this);


    }

    public static List<SetupSession> getSetupSessions() {
        return setupSessions;
    }

    @Override
    public String getWorldName() {
        return worldName;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void teleportPlayer() {
        player.teleport(Bukkit.getWorld(worldName).getSpawnLocation());
        player.setGameMode(GameMode.CREATIVE);

        JsonObject json = new JsonObject();
        json.addProperty("world", worldName);

        JsonObject waitLoc = new JsonObject();
        World w = Bukkit.getWorld(worldName);
        waitLoc.addProperty("x", w.getSpawnLocation().getX());
        waitLoc.addProperty("y", w.getSpawnLocation().getY());
        waitLoc.addProperty("z", w.getSpawnLocation().getZ());

        json.add("lobbyLoc", waitLoc);

        File arenaFile = new File(arenasFolder, worldName + ".json");
        FileWriterMethod.fileWriter(arenaFile.getPath(), json.toString());

        player.spigot().sendMessage(Misc.msgHoverClick(ChatColor.BLUE + "     ▪     " + ChatColor.GOLD + "CLICK HERE TO SET THE WAITING LOBBY    " + ChatColor.BLUE + " ▪", ChatColor.LIGHT_PURPLE + "Click to set the waiting spawn.", "/" + Duels.mainCmd + " setWaitingSpawn", ClickEvent.Action.RUN_COMMAND));


    }

    @Override
    public void close() {

    }

    static void createWorld(ISetupSession s){
        WorldCreator wc = new WorldCreator(s.getWorldName());
        wc.generator(new VoidChunkGenerator());
        wc.generateStructures(false);
        Bukkit.getScheduler().runTask(plugin, () -> {
            try {
                File level = new File(Bukkit.getWorldContainer(), s.getWorldName() + "/region");
                if (level.exists()) {
                    s.getPlayer().sendMessage(ChatColor.GREEN + "Loading " + s.getWorldName() + " from Bukkit worlds container.");
                    deleteWorldTrash(s.getWorldName());
                    World w = Bukkit.createWorld(wc);
                    w.setKeepSpawnInMemory(true);
                } else {
                    try {
                        s.getPlayer().sendMessage(ChatColor.GREEN + "Creating a new void map: " + s.getWorldName());
                        World w = Bukkit.createWorld(wc);
                        w.setKeepSpawnInMemory(true);
                        w.setSpawnLocation(0, 64, 0);
                        Bukkit.getScheduler().runTaskLater(plugin, s::teleportPlayer, 20L);
                    } catch (Exception ex){
                        ex.printStackTrace();
                        s.close();
                    }
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                s.close();
                return;
            }
            Bukkit.getScheduler().runTaskLater(plugin, s::teleportPlayer, 20L);
        });
    }

    public static void deleteWorldTrash(String world) {
        for (File f : new File[]{new File(Bukkit.getWorldContainer(), world + "/level.dat"),
                new File(Bukkit.getWorldContainer(), world + "/level.dat_mcr"),
                new File(Bukkit.getWorldContainer(), world + "/level.dat_old"),
                new File(Bukkit.getWorldContainer(), world + "/session.lock"),
                new File(Bukkit.getWorldContainer(), world + "/uid.dat")}) {
            if (f.exists()) {
                if (!f.delete()) {
                    Duels.getPlugin().getLogger().warning("Could not delete: " + f.getPath());
                    Duels.getPlugin().getLogger().warning("This may cause issues!");
                }
            }
        }
    }
}
