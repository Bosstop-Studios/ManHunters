package ml.bosstop.commons.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ml.bosstop.commons.structures.storage.ConfigFormat;
import ml.bosstop.manhunters.ManHunters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONStore {

    private static final ManHunters instance = ManHunters.getInstance();

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    private static ConfigFormat createDefaultConfig() {
        ConfigFormat config = new ConfigFormat()
                .setChat("&7[&b%role%&7] &b%player% &7» &f%message%", true)
                .setTablist("&b%role% &7» &b%player%", true);
        return config;
    }

    private static void loadConfig() throws IOException {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdir();
        }

        File file = new File(instance.getDataFolder(), "config.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
                String json = gson.toJson(createDefaultConfig());
                FileWriter writer = new FileWriter(file);
                writer.write(json);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                ConfigFormat configFormat = gson.fromJson(FileUtil.readFile(instance.getDataFolder() + "/config.json"), ConfigFormat.class);
                instance.setMHConfig(configFormat);
            }
        } else {
            ConfigFormat configFormat = gson.fromJson(FileUtil.readFile(instance.getDataFolder() + "/config.json"), ConfigFormat.class);
            instance.setMHConfig(configFormat);
        }
    }

    private static void saveConfig() {
        ConfigFormat configFormat = instance.getMHConfig();
        File file = new File(instance.getDataFolder(), "config.json");
        try (FileWriter fileWriter = new FileWriter(file)) {
            String json = gson.toJson(configFormat);
            fileWriter.write(json);
            fileWriter.flush();
        } catch(IOException e){
            System.out.println(e);
        }
    }

    public static void enable() {
        try {
            loadConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void disable() {
        try {
            saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
