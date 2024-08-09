package xyz.jxmm.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import xyz.jxmm.Duels;
import xyz.jxmm.utils.FileReaderMethod;
import xyz.jxmm.utils.FileWriterMethod;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Change {
    static File cfg = new File(Duels.getPlugin().getDataFolder(), "config.json");
    static Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
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

        try {
            Duels.config = gson.fromJson(new InputStreamReader(new FileInputStream(cfg), StandardCharsets.UTF_8), JsonObject.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
