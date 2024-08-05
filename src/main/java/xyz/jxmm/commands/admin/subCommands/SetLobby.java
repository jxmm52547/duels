package xyz.jxmm.commands.admin.subCommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.jxmm.Duels;
import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.command.SubCommand;

import java.util.List;

public class SetLobby extends SubCommand{
    /**
     * Create a sub-command for a bedWars command
     * Make sure you return true or it will say command not found
     *
     * @param parent parent command
     * @param name   sub-command name
     */
    public SetLobby(ParentCommand parent, String name) {
        super(parent, name);
    }


    @Override
    public boolean execute(String[] args, CommandSender s) {
        Player p = (Player) s;
        xyz.jxmm.config.Change.change(new String[]{"lobby", "world"}, p.getLocation().getWorld().getName());

        int x = (int) p.getLocation().getX();
        int y = (int) p.getLocation().getY();
        int z = (int) p.getLocation().getZ();
        int yaw = (int) p.getLocation().getYaw();
        int pitch = (int) p.getLocation().getPitch();

        xyz.jxmm.config.Change.change(new String[]{"lobby", "x"}, String.valueOf(x));
        xyz.jxmm.config.Change.change(new String[]{"lobby", "y"}, String.valueOf(y));
        xyz.jxmm.config.Change.change(new String[]{"lobby", "z"}, String.valueOf(z));
        xyz.jxmm.config.Change.change(new String[]{"lobby", "yaw"}, String.valueOf(yaw));
        xyz.jxmm.config.Change.change(new String[]{"lobby", "pitch"}, String.valueOf(pitch));

        Duels.setLobbyLocation();
        s.sendMessage("设置成功");
        return true;
    }

    @Override
    public List<String> getTabComplete() {
        return null;
    }
}
