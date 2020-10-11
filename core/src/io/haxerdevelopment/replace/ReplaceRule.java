package io.haxerdevelopment.replace;

import java.io.Serializable;

public class ReplaceRule implements Serializable {
    public ReplaceType type;
    public String url;
    public String match;
    public String replace;
    public boolean isGlobal;
}
