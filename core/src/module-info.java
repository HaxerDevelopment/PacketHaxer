import io.haxerdevelopment.Plugins.BasicPlugin;
import io.haxerdevelopment.Plugins.Plugin;

module core {
        requires java.desktop;
        exports io.haxerdevelopment.Plugins;

        uses Plugin;
        provides Plugin with BasicPlugin;
}