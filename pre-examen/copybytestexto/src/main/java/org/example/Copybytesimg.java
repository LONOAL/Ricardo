package org.example;

import java.io.*;

public class Copybytesimg {

    File file = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\foto.jpg");
    File file2 = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\foto2.jpg");

    public void copy(){
        try {
            FileOutputStream out = new FileOutputStream(file2,true);
            FileInputStream in = new FileInputStream(file);
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
