package com.game;

import java.io.Serializable;
import java.util.Vector;

public class Tank implements Serializable {

    public Vector<Shot> shots = new Vector<>();

    int num = 0;

    private int x;
    private int y;
    private int dir = 0;
    private int speed = 10;
    private boolean isLive = true;

    public void minusNum() {
        num--;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void up() {
        if (y >= 10)
            y -= speed;
    }

    public void down() {
        if (y <= 640)
            y += speed;
    }

    public void left() {
        if (x >= 10)
            x -= speed;
    }

    public void right() {
        if (x <= 920)
            x += speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDir() {
        return dir;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
