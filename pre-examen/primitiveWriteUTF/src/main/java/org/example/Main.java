package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\PC-LORENZO\\Desktop\\si\\file.txt")));

            for (int i=0;i<3;i++){
                out.writeUTF("o tempo está xélido");
                System.out.println("escribindo a cadea: o tempo está xélido");
                System.out.println(out.size());
            }
            out.close();

            DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\PC-LORENZO\\Desktop\\si\\file.txt")));
            while (in.available()!=0){
                System.out.println(in.available());
                System.out.println(in.readUTF());
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}