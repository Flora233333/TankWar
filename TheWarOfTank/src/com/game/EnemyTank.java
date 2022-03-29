package com.game;

import java.io.Serializable;
import java.util.Vector;

public class EnemyTank extends Tank implements Runnable, Serializable {


    public static Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
        super.setDir(2);
    }

    public boolean isTouched() {

        // avoid tank overlap
        switch (this.getDir()) {

            case 0: // upward
                for (EnemyTank enemyTank : enemyTanks) {

                    if (enemyTank != this) {
                        // upward or downward
                        if (enemyTank.getDir() == 0 || enemyTank.getDir() == 2) {

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // right or left
                        if (enemyTank.getDir() == 1 || enemyTank.getDir() == 3) {

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }
                break;

            case 1: // left
                for (EnemyTank enemyTank : enemyTanks) {

                    if (enemyTank != this) {
                        // upward or downward
                        if (enemyTank.getDir() == 0 || enemyTank.getDir() == 2) {

                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // right or left
                        if (enemyTank.getDir() == 1 || enemyTank.getDir() == 3) {

                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }
                break;

            case 2: // downward
                for (EnemyTank enemyTank : enemyTanks) {

                    if (enemyTank != this) {
                        // upward or downward
                        if (enemyTank.getDir() == 0 || enemyTank.getDir() == 2) {

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // right or left
                        if (enemyTank.getDir() == 1 || enemyTank.getDir() == 3) {

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }
                break;
            case 3: // right
                for (EnemyTank enemyTank : enemyTanks) {

                    if (enemyTank != this) {
                        // upward or downward
                        if (enemyTank.getDir() == 0 || enemyTank.getDir() == 2) {

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60) {
                                return true;
                            }

                        }
                        // right or left
                        if (enemyTank.getDir() == 1 || enemyTank.getDir() == 3) {

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40) {
                                return true;
                            }

                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40) {
                                return true;
                            }

                        }

                    }

                }

                break;
        }
        return false;
    }

    private void shot() {
        Shot shot = null;

        switch (getDir()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }

        new Thread(shot).start();
        shots.add(shot);

    }

    @Override
    public void run() {

        while (true) {

            if (shots.size() == 0)
                shot();

            for (int i = 0; i < 10; i++) { // enemyTank move

                if (!isTouched()) {

                    switch (getDir()) {
                        case 0:
                            up();
                            break;
                        case 1:
                            right();
                            break;
                        case 2:
                            down();
                            break;
                        case 3:
                            left();
                            break;
                    }

                }

                try { // sleep for moving smoothly
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!isLive()) { // judge is enemyTank living
                System.out.println("enemy has been destroy");
                break;
            }

            setDir((int) (Math.random() * 100) % 4);

        }
    }
}
