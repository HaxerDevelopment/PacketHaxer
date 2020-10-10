package io.haxerdevelopment.plugins;

public class BasicPlugin implements Plugin{
    @Override
    public String action(String s) {
        System.out.println("BasicPlugin action");
        return "BasicPlugin action";
    }
}
