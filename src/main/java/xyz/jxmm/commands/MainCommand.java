package xyz.jxmm.commands;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.commands.admin.Admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCommand extends Command implements ParentCommand {

    /* SubCommands ArenaList */
    private static List<SubCommand> subCommandList = new ArrayList<>();
    /* MainCommand instance*/
    private static MainCommand instance;
    public MainCommand(String name) {
        super(name);
        setAliases(List.of("d"));
        new Admin(this,"admin");

    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            sender.sendMessage("This command is for player");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(" ");
            sender.sendMessage("§8§l|-" + " §6" +
                    Duels.getPlugin().getDescription().getName() + " v" +
                    Duels.getPlugin().getDescription().getVersion() + " §7- §cCommands");
            sender.sendMessage(" ");
            sender.sendMessage(commandLabel);
            sender.sendMessage(args);
            return true;
        }

        boolean commandFound = false;
        for (SubCommand sb : getSubCommands()) {
            if (sb.getSubCommandName().equalsIgnoreCase(args[0])) {
                if (sb.hasPermission(sender)) {
                    commandFound = sb.execute(Arrays.copyOfRange(args, 1, args.length), sender);
                }
            }
        }

        if (!commandFound) {
            sender.sendMessage(Color.RED + "未知指令!");
        }

        return true;
    }

    @Override
    public boolean hasSubCommand(String name) {
        for (SubCommand sc : getSubCommands()) {
            if (sc.getSubCommandName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addSubCommand(SubCommand subCommand) {
        subCommandList.add(subCommand);

    }

    @Override
    public void sendSubCommands(Player p) {

    }

    @Override
    public List<SubCommand> getSubCommands() {
        return subCommandList;
    }
}
