package com.test;

import java.io.*;
import java.util.Properties;

public class TestForIO {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        File file = new File("d:\\chip\\myTest");
//        File file1 = new File("d:\\chip\\myTest\\hello.txt");
//        BufferedReader bufferedReader = new BufferedReader(new FileReader("d:\\chip\\myTest\\hello.txt"));n

        Properties properties = new Properties();

        properties.load(new FileReader("src\\com\\test\\test.properties"));

        Dog dog = new Dog(properties.getProperty("name"), properties.getProperty("age"), properties.getProperty("color"));

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("d:\\chip\\class.dat"));
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("d:\\chip\\class.dat"));
        Dog o = (Dog)objectInputStream.readObject();

        objectInputStream.close();


    }
}

class Dog implements Serializable {
    String name;
    String age;
    String color;
    private static final long serialVersionUID = 1L;

    public Dog(String name, String age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
