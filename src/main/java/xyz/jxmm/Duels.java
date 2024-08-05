package xyz.jxmm;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;
import xyz.jxmm.commands.MainCommand;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Properties;

public final class Duels extends JavaPlugin {
    public static String mainCmd = "duels", link ="https://github.com/jxmm52547/duels";
    public static JsonObject config = new JsonObject();
    private static Duels plugin;

    private static String lobbyWorld = "";
    static Location lobbyLocation = null;

    @Override
    public void onEnable() {
        plugin = this;

        //配置文件
        try {
            config = xyz.jxmm.config.Main.main();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //设置主大厅
        if (!config.getAsJsonObject("lobby").get("world").isJsonArray()){
            setLobbyLocation();
        }

        //注册主命令
        try{
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(mainCmd, new MainCommand("duels"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        //监听玩家连接到服务器
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);


        this.getLogger().info("成功加载");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    public static void setLobbyLocation(){

        lobbyWorld = config.getAsJsonObject("lobby").get("world").getAsString();
        lobbyLocation = new Location(
                Bukkit.getWorld(lobbyWorld),
                config.getAsJsonObject("lobby").get("x").getAsDouble(),
                config.getAsJsonObject("lobby").get("y").getAsDouble(),
                config.getAsJsonObject("lobby").get("z").getAsDouble());
    }
}
