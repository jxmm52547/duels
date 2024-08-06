package xyz.jxmm.maprestore.internal;

import org.bukkit.Bukkit;
import xyz.jxmm.api.server.RestoreAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InternalAdapter extends RestoreAdapter {
    @Override
    public List<String> getWorldsList() {
        List<String> worlds = new ArrayList<>();
        File dir = Bukkit.getWorldContainer();
        if (dir.exists()) {
            File[] fls = dir.listFiles();
            for (File fl : Objects.requireNonNull(fls)) {
                if (fl.isDirectory()) {
                    File dat = new File(fl.getName() + "/region");
                    if (dat.exists() && !fl.getName().startsWith("bw_temp")) {
                        worlds.add(fl.getName());
                    }
                }
            }
        }
        return worlds;
    }
}
