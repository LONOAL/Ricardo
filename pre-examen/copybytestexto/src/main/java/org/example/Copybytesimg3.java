package org.example;

import java.io.*;

public class Copybytesimg3 {

    File file = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\foto.jpg");
    File file2 = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\foto2.jpg");
    File texto = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\texto1.txt");
    File ok = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\ok.jpg");
    File out1 = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\out1.jpg");
    File out2 = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\out2.txt");
    File out3 = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\out3.jpg");


    public void copy(){
        try {
            FileOutputStream out = new FileOutputStream(file2);
            FileInputStream in = new FileInputStream(file);
            while(in.available()!=0){
                out.write(in.read());
            }
            in = new FileInputStream(texto);
            out = new FileOutputStream(file2, true);
            while(in.available()!=0){
                out.write(in.read());
            }
            in = new FileInputStream(ok);
            while(in.available()!=0){
                out.write(in.read());
            }
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void extract(){
        try {
            FileOutputStream out = new FileOutputStream(out1);
            FileInputStream in = new FileInputStream(file2);
            while(in.read()==){
                out.write(in.read());
            }
            in = new FileInputStream(texto);
            out = new FileOutputStream(file2, true);
            while(in.available()!=0){
                out.write(in.read());
            }
            in = new FileInputStream(ok);
            while(in.available()!=0){
                out.write(in.read());
            }
            out.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
