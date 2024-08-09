package xyz.jxmm.commands.admin;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.Arrays;
import java.util.List;

public class SetGamemode extends SubCommand {
    /**
     * @param parent parent command
     * @param name   sub-command name
     */
    public SetGamemode(ParentCommand parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (s instanceof Player) {
            Player p = (Player) s;
            s.sendMessage(args[0]);
            switch (args[0]) {
                case "0":
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendMessage("§a游戏模式已切换为生存模式");
                    break;
                case "1":
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage("§a游戏模式已切换为创造模式");
                    break;
                case "2":
                    p.setGameMode(GameMode.ADVENTURE);
                    break;
                case "3":
                    p.setGameMode(GameMode.SPECTATOR);
                    break;
            }
        }
        return true;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
        return Arrays.asList("0", "1", "2", "3");
    }
}
