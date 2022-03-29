package com.game;

import java.io.Serializable;

public class Shot implements Runnable, Serializable { // 射击子弹

    private int x;
    private int y;
    private int dir;
    private boolean isLive = true;
    private int speed = 15;

    public Shot(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (dir) {
                case 0: // upward
                    y -= speed;
                    break;
                case 1: // right
                    x += speed;
                    break;
                case 2: // downward
                    y += speed;
                    break;
                case 3: // left
                    x -= speed;
                    break;
            }

            if (isLive) {
                System.out.println("X=" + x + "  Y=" + y);

                if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750)) {
                    isLive = false;
                    System.out.println("missing");
                }
            } else {
                break;
            }

        }
    }
}
