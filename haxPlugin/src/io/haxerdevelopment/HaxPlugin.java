package io.haxerdevelopment;

import io.haxerdevelopment.Plugins.Plugin;

public class HaxPlugin implements Plugin {

    @Override
    public String action(String s) {
        return s.toLowerCase();
    }
}
