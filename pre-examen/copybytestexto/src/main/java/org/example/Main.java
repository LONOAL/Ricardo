package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {

    static File file = new File("C:\\Riot Games\\Riot Client\\RiotClientServices.exe");


    public static void main(String[] args) {
        Copybytesimg2 copy2 = new Copybytesimg2();
        Copybytesimg3 copy = new Copybytesimg3();
        copy.copy();
    }
    public static void copy(){
        try {
            FileInputStream in = new FileInputStream(file);
            ArrayList<Integer> bytes = new ArrayList<Integer>();
            while(in.available()!=0){
                bytes.add(in.read());
            }
            File file2 = new File("C:\\Riot Games\\Riot Client\\riot.txt");
            file2.createNewFile();
            FileOutputStream out = new FileOutputStream(file2);
            for (int i = 0; i < bytes.size(); i++) {
                out.write(bytes.get(i));
            }
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void copy2(){
        try {
            DataInputStream in = new DataInputStream(new FileInputStream ("C:\\Users\\PC-LORENZO\\Downloads\\mc server"));
            ArrayList<Integer> bytes = new ArrayList<Integer>();
            while(in.available()!=0){
                bytes.add(in.read());
            }
            DataOutputStream out = new DataOutputStream(new FileOutputStream("C:\\Users\\PC-LORENZO\\Downloads\\aiaiaia"));
            for (int i = 0; i < bytes.size(); i++) {
                out.write(bytes.get(i));
            }
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}