package org.example;

import java.io.*;

public class Copybytesimg2 {

    File file = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\foto.jpg");
    File file2 = new File("C:\\Users\\PC-LORENZO\\Desktop\\si\\foto2.jpg");

    public void copy(){
        try {
            FileOutputStream out = new FileOutputStream(file2);
            FileInputStream in = new FileInputStream(file);
            BufferedInputStream in2 = new BufferedInputStream(in);
            BufferedOutputStream out2 = new BufferedOutputStream(out);
            while(in2.available()!=0){
                out2.write(in2.read());
            }
            out2.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
