package xyz.jxmm.config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import xyz.jxmm.Duels;
import xyz.jxmm.utils.FileReaderMethod;
import xyz.jxmm.utils.FileWriterMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Change {
    static File cfg = new File(Duels.getPlugin().getDataFolder(), "config.json");
    static Gson gson = new Gson();
    public static void change(String[] args, String value){
        JsonObject json;
        try {
            json = gson.fromJson(new InputStreamReader(Files.newInputStream(cfg.toPath()), StandardCharsets.UTF_8), JsonObject.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (args[0].equals("lobby")) {
            json.getAsJsonObject("lobby").addProperty(args[1], value);
        }
        FileWriterMethod.fileWriter(cfg.getPath(), gson.toJson(json));

    }
}
