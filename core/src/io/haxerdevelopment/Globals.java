package io.haxerdevelopment;

import io.haxerdevelopment.config.ConfigManager;
import io.haxerdevelopment.replace.ReplaceManager;

public class Globals { // Really sorry, using static "Global" classes in all my apps (left by Eimaen)
    public static ReplaceManager replaceManager = new ReplaceManager();
    public static ConfigManager configManager = new ConfigManager();
    public static MainWindow userInterface = new MainWindow();
}
