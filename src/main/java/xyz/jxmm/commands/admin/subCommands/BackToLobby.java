package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.List;

public class BackToLobby extends SubCommand {
    /**
     * @param parent parent command
     * @param name   sub-command name
     */
    public BackToLobby(ParentCommand parent, String name) {
        super(parent, name);
    }

    @Override
    public boolean execute(String[] args, CommandSender s) {
        if (s instanceof Player p) {
            p.teleport(Duels.lobbyLocation);
            p.setGameMode(GameMode.ADVENTURE);
        }
        return true;
    }

    @Override
    public List<String> getTabComplete(CommandSender s, String alias, String[] args, Location location) {
        return null;
    }
}
