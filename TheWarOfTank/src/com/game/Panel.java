package com.game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class Panel extends JPanel implements KeyListener, Runnable {

    private StarTank starTank = null;
    private Recorder recorder = new Recorder();
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private Vector<Bomb> bombs = new Vector<>();

    Image image1;
    Image image2;
    Image image3;
    Image mapImage;

    public Panel() {
        starTank = new StarTank(100, 100);

        // initPoint to display an accumulation perform from the first to the last game
        starTank.setPoints(recorder);


        for (int i = 0; i < 4; i++) {
            EnemyTank enemyTank = new EnemyTank((i + 3) * 100, 50);
            new Thread(enemyTank).start();

            Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, 2);
            enemyTank.shots.add(shot);
            new Thread(shot).start();

            enemyTanks.add(enemyTank);
        }

        // ** recover the last enemyTank layout **
//        enemyTanks = Recorder.readTank();
//        for (EnemyTank enemyTank : enemyTanks) {
//            new Thread(enemyTank).start();
//            for (Shot shot : enemyTank.shots) {
//                new Thread(shot).start();
//            }
//        }

        EnemyTank.enemyTanks = this.enemyTanks;

        // initialize boom image
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image1.jpg"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image2.jpg"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/image3.jpg"));
        mapImage = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/map.PNG"));

        recorder.refreshRecord(starTank.getPoints()); // creat txt to avoid FileNotFoundException
        recorder.setEnemyTanks(this.enemyTanks); // setTankDate (传的是个指针，所以不要怕这里的坦克集合变了，那里的没变)

        new Thread(new PlayAudio("src\\bg.wav")).start(); // musical
    }

    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0, 0, 1000, 750);
        // g.drawImage(mapImage, 0, 0, 1000, 750, this);

        showInfo(g);

        for (int i = 0; i < bombs.size(); i++) { // display boom image for dynamic feeling
            Bomb bomb = bombs.get(i);

            if (bomb.getLife() > 6) {

                try {
                    Thread.sleep(30); // this delay 30ms for loading the image (maybe?)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                g.drawImage(image1, bomb.getX(), bomb.getY(), 80, 80, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 80, 80, this);
            } else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 80, 80, this);
            }

            bomb.lifeDown();

            if (!bomb.isLive())
                bombs.remove(bomb);

        }

        for (EnemyTank enemyTank : enemyTanks) { // paint enemyTanks
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDir(), 1);

            for (int j = 0; j < enemyTank.shots.size(); j++) { // paint enemyShots
                Shot enemyShot = enemyTank.shots.get(j);

                if (enemyShot.isLive()) {
                    drawShot(enemyShot.getX(), enemyShot.getY(), g, Color.yellow);
                } else {
                    enemyTank.shots.remove(enemyShot);
                }

            }
        }

//        if (starTank.getShot() != null && starTank.getShot().isLive()) {
//            drawShot(starTank.getShot().getX(),starTank.getShot().getY(), g);
//        }

        if (starTank != null) { // paint starTank
            drawTank(starTank.getX(), starTank.getY(), g, starTank.getDir(), 0);

            for (int i = 0; i < starTank.shots.size(); i++) { // paint starTankShots
                Shot shot = starTank.shots.get(i);

                if (shot.isLive()) {
                    drawShot(shot.getX(), shot.getY(), g, Color.cyan);
                } else {
                    starTank.minusNum();
                    starTank.shots.remove(shot);
                }
            }
        }

    }

    /**
     * @param x    X Coordinate
     * @param y    Y Coordinate
     * @param g    Paint Ban
     * @param dir  Direction
     * @param type Tank Type
     */
    public void drawTank(int x, int y, Graphics g, int dir, int type) {

        switch (type) {
            case 0: // 友方
                g.setColor(Color.cyan);
                break;
            case 1: // 敌方 enemy
                g.setColor(Color.yellow);
                break;
        }

        switch (dir) {
            case 0: // 向上 upward
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1: // 向左 left
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2: // 向下 downward
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3: // 向右 right
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("error");
        }


    }

    public void drawShot(int x, int y, Graphics g, Color color) {
        g.setColor(color);
        g.fill3DRect(x, y, 5, 5, false);
    }

    public void showInfo(Graphics g) {

        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("累积击毁敌方坦克", 1040, 30);
        drawTank(1040, 60, g, 0, 1);

        g.setColor(Color.black);
        g.drawString("*", 1100, 100);
        g.drawString(recorder.readRecord(), 1140, 100);
    }

    public void hitTank(Shot shot, Tank tank) {

        switch (tank.getDir()) {
            case 0:
            case 2:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 40
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 60) {
                    shot.setLive(false);
                    tank.setLive(false);
                    // get a bomb (because the image display also Take the top left corner as the starting point)
                    Bomb bomb = new Bomb(tank.getX(), tank.getY()); // so the x and y is right
                    bombs.add(bomb);
                }
                break;

            case 1:
            case 3:
                if (shot.getX() > tank.getX() && shot.getX() < tank.getX() + 60
                        && shot.getY() > tank.getY() && shot.getY() < tank.getY() + 40) {
                    shot.setLive(false);
                    tank.setLive(false);

                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char k = e.getKeyChar();
        if (starTank != null) {
            if (k == 'w') {
                System.out.println("upward");
                starTank.setDir(0);
                starTank.up();
            } else if (k == 'd') {
                System.out.println("right");
                starTank.setDir(1);
                starTank.right();
            } else if (k == 's') {
                System.out.println("backward");
                starTank.setDir(2);
                starTank.down();
            } else if (k == 'a') {
                System.out.println("left");
                starTank.setDir(3);
                starTank.left();
            }
            if (e.getKeyChar() == 'j') {
                System.out.println("shooting");
                starTank.shot();
            }
        }
        if (e.getKeyChar() == 'o') {
            System.out.println("play again");
            starTank = new StarTank(100, 100);
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void isHitEnemyTank() {
        if (starTank != null) {
            for (int i = 0; i < starTank.shots.size(); i++) {
                Shot shot = starTank.shots.get(i);

                if (shot.isLive()) {

                    for (int j = 0; j < enemyTanks.size(); j++) { // ergodic enemyTank
                        EnemyTank enemyTank = enemyTanks.get(j);

                        hitTank(shot, enemyTank);
                        if (!enemyTank.isLive()) {
                            System.out.println("nice shot!");
                            starTank.increasePoints();
                            recorder.refreshRecord(starTank.getPoints());
                            enemyTanks.remove(enemyTank); // delete enemyTank
                        }
                    }
                }

            }
        }
    }

    public void isHitStarTank() {
        for (EnemyTank enemyTank : enemyTanks) { // ergodic enemyTank

            for (int i = 0; i < enemyTank.shots.size(); i++) { // ergodic enemyTank shots

                if (starTank != null) {
                    hitTank(enemyTank.shots.get(i), starTank);

                    if (!starTank.isLive()) {
                        System.out.println("mission fail");
                        starTank = null;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // check had the shots hit enemyTank
            isHitEnemyTank();

            // check had the shots hit starTank
            isHitStarTank();

            this.repaint();

        }
    }
}
