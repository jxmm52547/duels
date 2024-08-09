package xyz.jxmm.commands.admin.subCommands.setupArena;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.utils.FileReaderMethod;
import xyz.jxmm.utils.FileWriterMethod;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class SetTeamSpawnPoint extends SubCommand {
    private ParentCommand parent;
    private String name;
    static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    static File arenasFolder = new File(Duels.getPlugin().getDataFolder() + "\\arenas");;
    /**
     * @param parent parent command
     * @param name   sub-command name
     */
    public SetTeamSpawnPoint(ParentCommand parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (!(s instanceof Player p)) return false;

        if (!p.isOp()) return false;

        if (p.getWorld().getName().equals(Duels.lobbyWorld)){
            p.sendMessage(ChatColor.RED + "无法在主大厅设置队伍");
            return false;
        }

        if (args.length == 1){
            JsonObject json = gson.fromJson(FileReaderMethod.fileReader(arenasFolder + "\\" + p.getWorld().getName() + ".json"), JsonObject.class);
            JsonObject loc = new JsonObject();
            loc.addProperty("x", p.getLocation().getX());
            loc.addProperty("y", p.getLocation().getY());
            loc.addProperty("z", p.getLocation().getZ());
            loc.addProperty("yaw", p.getLocation().getYaw());
            loc.addProperty("pitch", p.getLocation().getPitch());

            if (args[0].equalsIgnoreCase("A") || args[0].equalsIgnoreCase("B")){
                json.getAsJsonObject("teamSpawnPoint").add(args[0], loc);
            }

            FileWriterMethod.fileWriter(arenasFolder + "\\" + p.getWorld().getName() + ".json", gson.toJson(json));
            p.sendMessage(ChatColor.GREEN + "成功设置队伍 " + args[0] + " 出生点");
            return true;
        }
        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
        return Arrays.asList("A", "B");
    }
}
