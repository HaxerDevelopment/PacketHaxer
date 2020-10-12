package io.haxerdevelopment;

import io.haxerdevelopment.config.ConfigManager;
import io.haxerdevelopment.proxy.logging.PacketLogManager;
import io.haxerdevelopment.replace.ReplaceManager;
import io.haxerdevelopment.web.WebApiServer;

public class Globals { // Really sorry, using static "Global" classes in all my apps (left by Eimaen)
    public static ReplaceManager replaceManager = new ReplaceManager();
    public static ConfigManager configManager = new ConfigManager();
    public static MainWindow userInterface = new MainWindow();
    public static WebApiServer webServer = new WebApiServer();
    public static PacketLogManager logManager = new PacketLogManager();
}
