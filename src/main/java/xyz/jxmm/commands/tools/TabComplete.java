package xyz.jxmm.commands.tools;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import xyz.jxmm.api.command.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class TabComplete {
    public static List<String> tabComplete(CommandSender s, String alias, String[] args, Location location, List<SubCommand> subCommands){
        if (args.length == 1) {
            List<String> sub = new ArrayList<>();
            for (SubCommand sb : subCommands) {
                sub.add(sb.getSubCommandName());
            }
            return sub;
        } else if (args.length == 2) {
            if (hasSubCommand(args[0],subCommands)) {
                return getSubCommand(args[0],subCommands).getTabComplete();
            }
        }
        return null;
    }

    public static boolean hasSubCommand(String name, List<SubCommand> subCommands) {
        for (SubCommand sc : subCommands) {
            if (sc.getSubCommandName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public static SubCommand getSubCommand(String name, List<SubCommand> subCommands) {
        for (SubCommand sc : subCommands) {
            if (sc.getSubCommandName().equalsIgnoreCase(name)) {
                return sc;
            }
        }
        return null;
    }
}
