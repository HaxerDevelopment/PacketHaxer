package io.haxerdevelopment.config;

public class Config {
    public String configName = "default";
    public boolean packetEditingEnabled = true;

    public void restoreDefaults() {
        configName = "default";
        packetEditingEnabled = true;
    }
}
