import io.haxerdevelopment.plugins.BasicPlugin;
import io.haxerdevelopment.plugins.Plugin;

module core {
        requires java.desktop;
        exports io.haxerdevelopment.plugins;

        uses Plugin;
        provides Plugin with BasicPlugin;
}