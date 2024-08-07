package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.ArrayList;
import java.util.List;

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

        return false;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
       return tabComplete(s, alias, args, location);
    }

    @Override
    public List<String> tabComplete(CommandSender s, String alias, String[] args, Location location) throws IllegalArgumentException {
        List<String> tab = new ArrayList<>();
        tab.add(String.valueOf(Duels.getPlugin().getServer().getWorldContainer()));
        s.sendMessage(tab.toString());
        return tab;
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
}
