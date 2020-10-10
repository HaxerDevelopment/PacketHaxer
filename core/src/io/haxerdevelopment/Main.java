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
        UI.initializeUI();

        // Rule tests
        // No matter what, they are executed in order:
        // 1. Global (first in first out)
        // 2. Targetted (first in first out)
        // TODO: Make override's priority higher than others'
        ReplaceRule rule = new ReplaceRule(); // Looks weird
        rule.type = ReplaceType.PLAIN;
        rule.match = "a";
        rule.replace = "o";
        rule.isGlobal = true;
        Globals.replaceManager.replaceRules.add(rule);
        rule = new ReplaceRule();
        rule.type = ReplaceType.OVERRIDE; // And now do it for only target website
        rule.replace = "AniDUB sucks, but as for now no AniLibria here";
        rule.isGlobal = false;
        rule.url = "info.cern.ch";
        Globals.replaceManager.replaceRules.add(rule);
        rule = new ReplaceRule();
        rule.type = ReplaceType.PLAIN; // We can also replace content of the page
        rule.match = "o";
        rule.replace = "[contact Eimaen for free ROBUX]";
        rule.isGlobal = false;
        rule.url = "e-m-b.org";
        Globals.replaceManager.replaceRules.add(rule);
        /*
        rule = new ReplaceRule(); // Removed because too toxic
        rule.type = ReplaceType.REGEX; // Meet RegEx
        rule.match = "*";
        rule.replace = "xmblr дно в CS1.6";
        rule.isGlobal = true;
        Globals.replaceManager.replaceRules.add(rule);
        */

        new Thread(() -> {
            CommonProxyHTTP proxyHTTP = new CommonProxyHTTP(8888);
            proxyHTTP.listen();
        }).start();
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

}
