package io.haxerdevelopment.replace;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReplaceManager {
    public ArrayList<ReplaceRule> replaceRules;
    public RegexReplacer regularExpressionReplacer;

    public ReplaceManager() {
        replaceRules = new ArrayList<ReplaceRule>();
        regularExpressionReplacer = new RegexReplacer();
    }

    public void saveRules(String filePath) throws IOException {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(replaceRules);
        oos.flush();
        oos.close();
    }

    public void loadRules(String filePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filePath);
        ObjectInputStream oin = new ObjectInputStream(fis);
        replaceRules = (ArrayList<ReplaceRule>) oin.readObject();
    }

    public String replace(String string) {
        for (ReplaceRule rule : replaceRules) {
            switch (rule.type)
            {
                case PLAIN -> string = string.replace(rule.match, rule.replace);
                case REGEX -> string = regularExpressionReplacer.processString(string, rule.match, rule.replace);
                case OVERRIDE -> string = rule.replace;
            }
        }
        return string;
    }
}
