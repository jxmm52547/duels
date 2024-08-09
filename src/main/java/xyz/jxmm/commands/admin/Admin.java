package xyz.jxmm.commands.admin;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.commands.admin.subCommands.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Admin extends SubCommand implements ParentCommand {

    private List<SubCommand> subCommands = new ArrayList<>();
    private String name;
    private ParentCommand parent;
    /**
     * Create a sub-command for a bedWars command
     * Make sure you return true or it will say command not found
     *
     * @param parent parent command
     * @param name   sub-command name
     */
    public Admin(ParentCommand parent, String name) {
        super(parent, name);
        this.name = name;
        this.parent = parent;
        setDisplayInfo(new TextComponent("§c管理员指令"));
        addSubCommand(new BackToLobby(this, "backToLobby"));
        addSubCommand(new GetName(this, "getName"));
        addSubCommand(new SetLobby(this, "setupLobby"));
        addSubCommand(new SetupArena(this, "setupArena"));
        addSubCommand(new JoinArena(this, "joinArena"));
        addSubCommand(new SetGamemode(this, "setGamemode"));
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getSubCommandName().equalsIgnoreCase(args[0])) {
                    if (s.isOp()) {
                        subCommand.execute(Arrays.copyOfRange(args, 1, args.length), s);
                    } else {
                        s.sendMessage("§cYou don't have permission to use this command");
                    }
                    return true;
                }
            }
        }

        if (args.length == 0){
            sendDefaultMessage(args,s);
        }

        return true;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
        return tabComplete(s, alias, args, null);
    }

    public void sendDefaultMessage(String[] args, CommandSender s){
        if (args.length == 0){
            s.sendMessage(" ");
            s.sendMessage("§8§l|-" + " §6" +
                    Duels.getPlugin().getDescription().getName() + " v" +
                    Duels.getPlugin().getDescription().getVersion() + " §7- §cAdmin");
            s.sendMessage(" ");

        }
    }

    @Override
    public List<String> tabComplete(CommandSender s, String alias, String[] args, Location location) throws IllegalArgumentException {
        if (args.length == 1) {
            List<String> sub = new ArrayList<>();
            for (SubCommand sb : getSubCommands()) {
                sub.add(sb.getSubCommandName());
            }
            return sub;
        } else if (args.length > 1){
            return getSubCommand(args[0]).getTabComplete(s,alias,Arrays.copyOfRange(args, 1, args.length), null);
        }

        return null;
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

    public SubCommand getSubCommand(String name) {
        for (SubCommand sc : getSubCommands()) {
            if (sc.getSubCommandName().equalsIgnoreCase(name)) {
                return sc;
            }
        }
        return null;
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
