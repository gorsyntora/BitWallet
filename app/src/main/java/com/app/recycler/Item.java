package com.app.recycler;


public class Item {
    private String serverName;

    public int getSpeedNet() {
        return speedNet;
    }

    private int speedNet;

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    int drawable;

    public Item(String source, int link) {

        this.speedNet = 12;
        this.drawable= link;
        this.serverName = source;

    }

    public String getServerName() {
        return serverName;
    }

    public int getDrawable() {

        return drawable;

    }
}
