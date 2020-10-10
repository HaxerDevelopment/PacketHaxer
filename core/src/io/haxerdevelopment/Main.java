package io.haxerdevelopment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import io.haxerdevelopment.Plugins.Plugin;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception{
        UI.initializeUI();
        importPlugins();
        System.out.println("Enter packet to hax\n");
        //String s = reader.readLine();
        //int a = Integer.parseInt(s);
        System.out.println("Let's hack some packets");
        Thread.sleep(1000);
        for (int i = 0; i <= 100; i++) {
            System.out.println(i+"%");
            //UI.label1.setText(i + "%");
            Thread.sleep((100));
        }
        System.out.println("Successfully hacked");
    }
    public static void importPlugins(){
        try {
        Path pluginsDir = Paths.get("Plugins");
        UI.b2.setLabel(pluginsDir.toAbsolutePath().toString());
        // Будем искать плагины в папке plugins
        ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

        // Пусть ModuleFinder найдёт все модули в папке plugins и вернёт нам список их имён
        List<String> pluginsList = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .collect(Collectors.toList());

        // Создадим конфигурацию, которая выполнит резолюцию указанных модулей (проверит корректность графа зависимостей)
        Configuration pluginsConfiguration = ModuleLayer
                .boot()
                .configuration()
                .resolve(pluginsFinder, ModuleFinder.of(), pluginsList);

        // Создадим слой модулей для плагинов
        ModuleLayer layer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());

        // Найдём все реализации сервиса IService в слое плагинов и в слое Boot
            List<Plugin> plugins1 = Plugin.getServices(layer);
            UI.b1.setLabel(plugins1.size() + "");
            for (Plugin plugin : plugins1) {
               // UI.b1.setLabel("3434");
                UI.b2.setLabel(plugin.action("AsYJljL"));
            }
        }catch(Exception e){
            UI.label1.setText(e.toString());
        }
    }
}
