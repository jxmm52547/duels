package xyz.jxmm;

import xyz.jxmm.api.command.ParentCommand;
import xyz.jxmm.api.server.RestoreAdapter;
import xyz.jxmm.commands.MainCommand;

public class API implements xyz.jxmm.api.Duels{
    private static RestoreAdapter restoreAdapter;
    @Override
    public IStats getStatsUtil() {
        return null;
    }

    @Override
    public ParentCommand getBedWarsCommand() {
        return MainCommand.instance;
    }

    @Override
    public RestoreAdapter getRestoreAdapter() {
        return restoreAdapter;
    }
}
