package xyz.jxmm.commands.admin.subCommands.setupArena;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.arena.Misc;
import xyz.jxmm.utils.FileReaderMethod;
import xyz.jxmm.utils.FileWriterMethod;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateTeam extends SubCommand implements ParentCommand {
    private ParentCommand parent;
    private String name;
    private List<SubCommand> subCommands = new ArrayList<>();
    static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    static File arenasFolder = new File(Duels.getPlugin().getDataFolder() + "\\arenas");

    /**
     * @param parent parent command
     * @param name   sub-command name
     */
    public CreateTeam(ParentCommand parent, String name) {
        super(parent, name);
        this.parent = parent;
        this.name = name;
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (!(s instanceof Player p)) return false;

        if (!p.isOp()) return false;

        if (p.getWorld().getName().equals(Duels.lobbyWorld)){
            p.sendMessage(ChatColor.RED + "无法在主大厅设置队伍");
        }

        if (args.length > 0) {
            World w = Bukkit.getWorld(p.getWorld().getName());
            String worldName = w.getName();

            try {
                Team team = Bukkit.getScoreboardManager().getMainScoreboard().registerNewTeam(worldName + args[0]);
                switch (args[0]) {
                    case "A":
                        team.setColor(ChatColor.BLUE);
                    case "B":
                        team.setColor(ChatColor.RED);
                }
            } catch (IllegalArgumentException e){
                s.sendMessage(ChatColor.RED + "队伍 " + args[0] + " 已存在");
            }



            JsonObject json = gson.fromJson(FileReaderMethod.fileReader(arenasFolder + "\\" + p.getWorld().getName() + ".json"), JsonObject.class);
            json.add("teamSpawnPoint", new JsonObject());

            FileWriterMethod.fileWriter(arenasFolder + "\\" + p.getWorld().getName() + ".json", gson.toJson(json));
            p.sendMessage(ChatColor.GREEN + "队伍 " + args[0] + " 创建成功");

            p.spigot().sendMessage(Misc.msgHoverClick(ChatColor.BLUE + "     ▪     " + ChatColor.GOLD + "CLICK HERE TO CREATE TEAM " + args[0] + " SPAWN POINT    " + ChatColor.BLUE + " ▪", ChatColor.LIGHT_PURPLE + "Click to create the team " + args[0] + " spawn point.", "/" + Duels.mainCmd + " setTeamSpawnPoint " + args[0], ClickEvent.Action.RUN_COMMAND));
            return true;
        }
        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
        return tabComplete(s, alias, args, location);
    }

    @Override
    public List<String> tabComplete(CommandSender s, String alias, String[] args, Location location) throws IllegalArgumentException {
        return Arrays.asList("A", "B");
    }

    @Override
    public boolean hasSubCommand(String name) {
        return false;
    }

    @Override
    public void addSubCommand(SubCommand subCommand) {
        subCommands.add(subCommand);
    }

    @Override
    public void sendSubCommands(Player p) {

    }

    @Override
    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    public SubCommand getSubCommand(String name) {
        for (SubCommand sc : getSubCommands()) {
            if (sc.getSubCommandName().equalsIgnoreCase(name)) {
                return sc;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
