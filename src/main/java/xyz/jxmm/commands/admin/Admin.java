package xyz.jxmm.commands.admin;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.commands.admin.subCommands.GetName;

import java.util.Arrays;
import java.util.List;

public class Admin extends SubCommand {
    /**
     * Create a sub-command for a bedWars command
     * Make sure you return true or it will say command not found
     *
     * @param parent parent command
     * @param name   sub-command name
     */
    public Admin(ParentCommand parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (args.length > 0) {
            switch (args[0]){
                case "getname":
                    GetName.execute(s);
                    return true;
                default:
                    sendDefaultMessage(args, s);
            }
        } else {
            if (s instanceof ConsoleCommandSender){
                Duels.getPlugin().getLogger().info("This command is for Player");
                return true;
            }
        }



        return true;
    }

    public void sendDefaultMessage(String[] args, CommandSender s){
        if (args.length == 0){
            s.sendMessage(" ");
            s.sendMessage("§8§l|-" + " §6" +
                    Duels.getPlugin().getDescription().getName() + " v" +
                    Duels.getPlugin().getDescription().getVersion() + " §7- §cCommands");
            s.sendMessage(" ");
            s.sendMessage(args);

        }
    }

    @Override
    public List<String> getTabComplete() {
        return null;
    }
}
