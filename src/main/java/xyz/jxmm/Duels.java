package xyz.jxmm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.jxmm.commands.MainCommand;

import java.lang.reflect.Field;

public final class Duels extends JavaPlugin {
    public static String mainCmd = "bw", link ="https://github.com/jxmm52547/duels";
    private static Duels plugin;

    @Override
    public void onEnable() {
        plugin = this;
        try{
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register(mainCmd, new MainCommand("duels"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        this.getLogger().info(ChatColor.AQUA + "成功加载");
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
