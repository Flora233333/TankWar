package com.game;

public class StarTank extends Tank {

    private static int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(Recorder recorder) {
        points = Integer.parseInt(recorder.readRecord());
    }

    public void increasePoints() {
        points++;
    }

    public StarTank(int x, int y) {
        super(x, y);
        super.setDir(0);
    }

    public void shot() {
        switch (getDir()) {
            case 0:
                //shot = new Shot(getX() + 20, getY(), 0);
                shots.add(new Shot(getX() + 20, getY(), 0));
                break;
            case 1:
                //shot = new Shot(getX() + 60, getY() + 20, 1);
                shots.add(new Shot(getX() + 60, getY() + 20, 1));
                break;
            case 2:
                //shot = new Shot(getX() + 20, getY() + 60, 2);
                shots.add(new Shot(getX() + 20, getY() + 60, 2));
                break;
            case 3:
                //shot = new Shot(getX(), getY() + 20, 3);
                shots.add(new Shot(getX(), getY() + 20, 3));
                break;
        }

        new Thread(shots.get(num++)).start();
        if (num >= 50) { // avoid exceed RAM
            shots.clear();
            num = 0;
        }
    }

}
