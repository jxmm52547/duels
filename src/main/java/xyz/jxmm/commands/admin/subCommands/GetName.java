package xyz.jxmm.commands.admin.subCommands;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.commands.admin.Admin;

import java.util.List;

public class GetName extends SubCommand {

    /**
     * Create a sub-command for a bedWars command
     * Make sure you return true or it will say command not found
     *
     * @param parent parent command
     * @param name   sub-command name
     */
    public GetName(ParentCommand parent, String name) {
        super(parent, name);
        setDisplayInfo(new TextComponent("§a§l获取玩家名§r"));
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (s instanceof Player){
            Player p = (Player) s;
            p.sendTitle("§a§l玩家名§r", p.getName(), 10, 20, 10);
            return true;
        }
        return true;
    }

    @Override
    public List<String> getTabComplete() {
        return null;
    }
}
