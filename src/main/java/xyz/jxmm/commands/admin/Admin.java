package xyz.jxmm.commands.admin;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.commands.admin.subCommands.GetName;
import xyz.jxmm.commands.admin.subCommands.SetLobby;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Admin extends SubCommand implements ParentCommand {

    private List<SubCommand> subCommands = new ArrayList<>();
    /**
     * Create a sub-command for a bedWars command
     * Make sure you return true or it will say command not found
     *
     * @param parent parent command
     * @param name   sub-command name
     */
    public Admin(ParentCommand parent, String name) {
        super(parent, name);
        setDisplayInfo(new TextComponent("§c管理员指令"));
        addSubCommand(new GetName(this, "getname"));
        addSubCommand(new SetLobby(this, "steuplobby"));
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (args.length > 0) {
            for (SubCommand subCommand : subCommands) {
                if (subCommand.getSubCommandName().equalsIgnoreCase(args[0])) {
                    if (subCommand.hasPermission(s)) {
                        subCommand.execute(Arrays.copyOfRange(args, 1, args.length), s);
                    } else {
                        s.sendMessage("§cYou don't have permission to use this command");
                    }
                    return true;
                }
            }
        }

        sendDefaultMessage(args,s);

        return true;
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
        return xyz.jxmm.commands.tools.TabComplete.tabComplete(s,alias,args,location,subCommands);
    }

    @Override
    public List<String> getTabComplete() {
        List<String> tabComplete = new ArrayList<>();
        for (SubCommand subCommand : subCommands) {
            tabComplete.add(subCommand.getSubCommandName());
        }
        return tabComplete;
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



    @Override
    public String getName() {
        return null;
    }
}
