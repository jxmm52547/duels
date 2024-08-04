package xyz.jxmm.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.commands.admin.Admin;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.ArrayList;
import java.util.List;

public class MainCommand extends BukkitCommand implements ParentCommand {

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
        sender.sendMessage(" ");
        sender.sendMessage("§8§l|-" + " §6" +
                Duels.getPlugin().getDescription().getName() + " v" +
                Duels.getPlugin().getDescription().getVersion() + " §7- §cCommands");
        sender.sendMessage(" ");
        sender.sendMessage(commandLabel);
        sender.sendMessage(args);
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

    }

    @Override
    public void sendSubCommands(Player p) {

    }

    @Override
    public List<SubCommand> getSubCommands() {
        return null;
    }
}
