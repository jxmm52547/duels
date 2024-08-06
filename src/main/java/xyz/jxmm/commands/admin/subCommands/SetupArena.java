package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.command.CommandSender;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.List;

public class SetupArena extends SubCommand {
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
    public List<String> getTabComplete() {
       return null;
    }

}
