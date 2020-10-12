package io.haxerdevelopment.config;

import java.io.Serializable;

public class Config implements Serializable {
    public String configName = "default";
    public boolean packetEditingEnabled = true;

    public void restoreDefaults() {
        configName = "default";
        packetEditingEnabled = true;
    }
}
