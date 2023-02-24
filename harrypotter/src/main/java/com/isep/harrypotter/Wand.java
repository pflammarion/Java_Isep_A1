package com.isep.harrypotter;

public class Wand {
    private Core core;
    private int size;

    public Wand(Core core, int size) {
        this.core = core;
        this.size = size;
    }

    public void setCore(Core core) {
        this.core = core;
    }

    public Core getCore() {
        return core;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
