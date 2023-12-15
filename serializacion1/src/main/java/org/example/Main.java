package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Serialización
        MClase object1 = new MClase("ola", -7, 2.7E10);
        try {
            FileOutputStream fileOut = new FileOutputStream("serial");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object1);
            out.close();
            fileOut.close();
            System.out.println("Serialización completada.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialización
        MClase object2 = null;
        try {
            FileInputStream fileIn = new FileInputStream("serial");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            object2 = (MClase) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Comprobación antes del cambio
        System.out.println("object1: " + object1.toString());

        // Comprobación después del cambio (variable int como transient)
        System.out.println("object2: " + object2.toString());
        System.out.println("Fin de la comprobación: se recuperan los datos excepto 'i' por ser transient");
    }
}
