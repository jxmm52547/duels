package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.command.CommandSender;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.ArrayList;
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
        List<String> arenaList = new ArrayList<>();
        //获取目录下的存档文件夹列表
        for (String arena : Duels.getPlugin().getServer().getWorldContainer().list()) {
            if (arena.endsWith(".json")) {
                arenaList.add(arena.replace(".json", ""));
            }
        }


        return null;
    }

}
