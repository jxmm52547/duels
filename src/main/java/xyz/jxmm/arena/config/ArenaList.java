package xyz.jxmm.arena.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.Plugin;
import xyz.jxmm.Duels;
import xyz.jxmm.arena.SetupSession;
import xyz.jxmm.utils.FileReaderMethod;

import java.io.File;


public class ArenaList {
    static Plugin plugin = Duels.getPlugin();
    static File file = new File(plugin.getDataFolder() + "\\arenas");
    static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static void main(){
        //遍历file目录下的所有文件
        for (File f : file.listFiles()) {
            //判断是否是文件夹
            if (!f.isDirectory()) {
                JsonObject json = gson.fromJson(FileReaderMethod.fileReader(f.getPath()), JsonObject.class);
                String worldName = json.get("world").getAsString();
                JsonObject loc = json.getAsJsonObject("lobbyLoc");
                loadWorld(worldName, loc);

            }
        }
    }
    public static void loadWorld(String worldName, JsonObject loc){
        WorldCreator wc = new WorldCreator(worldName);
        Bukkit.getScheduler().runTask(plugin, () -> {
            try {
                File level = new File(Bukkit.getWorldContainer(), worldName + "/region");
                if (level.exists()) {
                    plugin.getLogger().info(ChatColor.GREEN + "Loading " + worldName + " from Bukkit worlds container.");
                    SetupSession.deleteWorldTrash(worldName);
                    World w = Bukkit.createWorld(wc);
                    w.setKeepSpawnInMemory(true);
                    w.setSpawnLocation(
                            loc.get("x").getAsInt(),
                            loc.get("y").getAsInt(),
                            loc.get("z").getAsInt());

                } else {
                    try {
                        plugin.getLogger().info(ChatColor.GREEN + "Creating a new void map: " + worldName);
                        World w = Bukkit.createWorld(wc);
                        w.setKeepSpawnInMemory(true);
                        w.setSpawnLocation(0, 64, 0);
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}
