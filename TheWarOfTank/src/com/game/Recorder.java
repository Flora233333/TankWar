package com.game;

import org.testng.annotations.Test;

import java.io.*;
import java.util.Vector;

public class Recorder {

    private static Vector<EnemyTank> enemyTanks = null;

    public Recorder() {
        createRecordFile();
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    @Test
    public void createRecordFile() {

        File file = new File("d:\\TankWar");
        File file1 = new File("d:\\TankWar\\gameRecord.txt");
        file.mkdirs();
        if (!file1.exists()) {
            refreshRecord(0);
        }

    }

    public static void saveTank() {
        ObjectOutputStream objectOutputStream = null;
        try {

            objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\TankWar\\enemyTankObject.dat"));
            objectOutputStream.writeObject(enemyTanks);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (objectOutputStream != null)
                    objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    @SuppressWarnings("all")
    public static Vector<EnemyTank> readTank() {
        Vector<EnemyTank> o = null;
        ObjectInputStream objectInputStream = null;

        try {

            objectInputStream = new ObjectInputStream(new FileInputStream("d:\\TankWar\\enemyTankObject.dat"));
            o = (Vector<EnemyTank>) objectInputStream.readObject();


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {
                if (objectInputStream != null)
                    objectInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return o;
    }

    @Test
    public void refreshRecord(int points) {
        BufferedWriter bufferedWriter = null;

        try {

            bufferedWriter = new BufferedWriter(new FileWriter("d:\\TankWar\\gameRecord.txt"));
            bufferedWriter.write(String.valueOf(points));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Test
    public String readRecord() {
        String pointsStr = null;
        BufferedReader bufferedReader = null;

        try {

            bufferedReader = new BufferedReader(new FileReader("d:\\TankWar\\gameRecord.txt"));
            pointsStr = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (bufferedReader != null)
                    bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return pointsStr;

    }

}
