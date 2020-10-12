package io.haxerdevelopment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

import io.haxerdevelopment.config.ConfigManager;
import io.haxerdevelopment.proxy.CommonProxyHTTP;
import io.haxerdevelopment.replace.ReplaceManager;
import io.haxerdevelopment.replace.ReplaceRule;
import io.haxerdevelopment.replace.ReplaceType;

public class Main {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception{
        // Rule tests
        // No matter what, they are executed in order:
        // 1. Global (first in first out)
        // 2. Targetted (first in first out)
        // TODO: Make override's priority higher than others'
        System.out.println("API access code: " + Globals.webServer.accessToken);
        /*ReplaceRule rule = new ReplaceRule();
        rule.type = ReplaceType.QUERY;
        rule.match = "title";
        rule.replace = "INSANE PACKETHAXER TEST";
        rule.isGlobal = true;
        Globals.replaceManager.replaceRules.add(rule);
        rule = new ReplaceRule();
        rule.type = ReplaceType.QUERY;
        rule.match = "a";
        rule.replace = "Fl3x Text";
        rule.isGlobal = true;
        Globals.replaceManager.replaceRules.add(rule);
        Globals.replaceManager.saveRules("rules.yarik-package");*/
        ReplaceRule rule = new ReplaceRule();
        rule.type = ReplaceType.REDIRECT;
        //rule.match = "a";
        rule.isGlobal=true;
        rule.replace = "https://www.anilibria.tv";

        Globals.replaceManager.addRule(rule);

        new Thread(() -> {
            CommonProxyHTTP proxyHTTP = new CommonProxyHTTP(8888);
            proxyHTTP.listen();
        }).start();
        System.out.println("Successfully loaded!");
    }

}
