package com.isep.harrypotter;

public class Wand {
    private Core core;
    private int size;
    public Wand(Core core) {
        this.core = core;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public Core getCore() {
        return core;
    }
}
