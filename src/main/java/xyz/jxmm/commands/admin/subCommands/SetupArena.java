package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.arena.SetupSession;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SetupArena extends SubCommand implements ParentCommand {
    private List<SubCommand> subCommands = new ArrayList<>();
    private String name;
    private ParentCommand parent;
    /**
     * @param parent parent command
     * @param name   sub-command name
     */
    public SetupArena(ParentCommand parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (args.length == 1){
            if (s instanceof Player) {
                Player p = (Player) s;
                new SetupSession(p, args[0]);
            }


        }
        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
       return tabComplete(s, alias, args, location);
    }

    @Override
    public List<String> tabComplete(CommandSender s, String alias, String[] args, Location location) throws IllegalArgumentException {
        if (args.length == 1) {
            return getWorldsList();
        }
        return null;
    }

    @Override
    public boolean hasSubCommand(String name) {
        return false;
    }

    @Override
    public void addSubCommand(SubCommand subCommand) {

    }

    @Override
    public void sendSubCommands(Player p) {

    }

    @Override
    public List<SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<String> getWorldsList() {
        List<String> worlds = new ArrayList<>();
        File dir = Bukkit.getWorldContainer();
        if (dir.exists()) {
            File[] fls = dir.listFiles();
            for (File fl : Objects.requireNonNull(fls)) {
                if (fl.isDirectory()) {
                    File dat = new File(fl.getName() + "/region");
                    if (dat.exists() && !fl.getName().startsWith("bw_temp")) {
                        worlds.add(fl.getName());
                    }
                }
            }
        }

        for (World w : Bukkit.getWorlds()){
            worlds.remove(w.getName());
        }
        return worlds;
    }
}
