package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class JoinArena extends SubCommand implements ParentCommand {
    /**
     * @param parent parent command
     * @param name   sub-command name
     */
    public JoinArena(ParentCommand parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (args.length == 1){
            if (s instanceof Player){
                Player p = (Player) s;
                p.teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
                p.setGameMode(GameMode.CREATIVE);
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
        List<String> worlds = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            worlds.add(world.getName());
        }
        return worlds;
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
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
