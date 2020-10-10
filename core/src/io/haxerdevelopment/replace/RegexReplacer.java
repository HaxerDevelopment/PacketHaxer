package io.haxerdevelopment.replace;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexReplacer implements Replacer {
    @Override
    public String processString(String string, String pattern, String replace) {
        Pattern _pattern = Pattern.compile(pattern);
        Matcher matcher = _pattern.matcher(string);
        return matcher.replaceAll(replace);
    }
}
