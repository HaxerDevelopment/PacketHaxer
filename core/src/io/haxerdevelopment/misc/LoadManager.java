package io.haxerdevelopment.misc;

public class LoadManager {
    public int getActiveThreadCount() {
        return activeThreadCount;
    }

    public void threadActivated() {
        this.activeThreadCount++;
    }

    public void threadDeactivated() {
        this.activeThreadCount--;
    }

    public int activeThreadCount;

    public LoadManager() {
        activeThreadCount = 1;
    }
}
