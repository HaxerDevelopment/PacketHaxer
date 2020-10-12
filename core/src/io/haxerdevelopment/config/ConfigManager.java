package io.haxerdevelopment.config;

import java.io.*;
import java.util.ArrayList;

public class ConfigManager {
    public ArrayList<Config> loadedConfigurations;
    private int currentConfigId;

    public ConfigManager() {
        loadedConfigurations = new ArrayList<Config>();
        currentConfigId = 0;
        loadedConfigurations.add(new Config());
    }

    public Config getActiveConfiguration() {
        return loadedConfigurations.get(currentConfigId);
    }

    public int getActiveConfigurationId() {
        return currentConfigId;
    }

    public void setActiveConfigurationId(int id) {
        currentConfigId = id;
    }

    public void loadConfigFromFile(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream oin = new ObjectInputStream(fis);
        loadedConfigurations.add((Config)oin.readObject());
    }

    public void saveConfigToFile(int id, String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(loadedConfigurations.get(id));
        oos.flush();
        oos.close();
    }
}
