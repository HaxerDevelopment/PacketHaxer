package io.haxerdevelopment.Plugins;

public class BasicPlugin implements Plugin{
    @Override
    public String action(String s) {
        System.out.println("BasicPlugin action");
        return "BasicPlugin action";
    }
}
