package io.haxerdevelopment.Plugins;

import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

public interface Plugin {
    public String action(String s);
    static List<Plugin> getServices(ModuleLayer layer) {
        return ServiceLoader
                .load(layer, Plugin.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(Collectors.toList());
    }
}
