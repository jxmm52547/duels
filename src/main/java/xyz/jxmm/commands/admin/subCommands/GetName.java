package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;
import xyz.jxmm.commands.admin.Admin;

import java.util.List;

public class GetName {

    public static void execute(CommandSender s) {
        if (s instanceof ConsoleCommandSender) {
            s.sendMessage("§c该命令只能由玩家执行");
        }

        Player player = (Player) s;
        player.sendTitle("§a你的名字是：" + player.getName(), "§b你的UUID是：" + player.getUniqueId(), 10, 20, 10);
    }

}
