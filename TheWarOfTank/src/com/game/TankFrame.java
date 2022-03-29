package com.game;

import javax.swing.*;
import java.awt.event.*;

/**
 * @anthor pxc
 * @span from 2022.3.14 to 2022.3.24
 */

public class TankFrame extends JFrame {

    Panel panel = null;

    public static void main(String[] args) {
        new TankFrame();
    }

    public TankFrame() {
        panel = new Panel();
        new Thread(panel).start();

        this.add(panel); // paint area
        this.setSize(1300, 750);
        this.addKeyListener(panel);

        this.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.saveTank(); // when close window, save the enemyTank layout
                System.exit(0);
            }
        });

    }
}
