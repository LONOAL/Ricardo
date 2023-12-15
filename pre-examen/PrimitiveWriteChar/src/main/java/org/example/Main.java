package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        com.mycompany.primitivewritechars.PrimitiveWriteChars copia=new com.mycompany.primitivewritechars.PrimitiveWriteChars();
        copia.leeryCopiar("/home/postgres/Escritorio/texto3.txt");
    }
    int aux;
    String aux1="o tempo está xélido";
    int aux3;
    int longitud;
    String lectura="";
    int i;
    public void leeryCopiar(String rutadestino) throws FileNotFoundException, IOException{
        FileInputStream entrada= new FileInputStream(rutadestino);
        DataInputStream data = new DataInputStream(entrada);
        FileOutputStream salida= new FileOutputStream(rutadestino,false);
        DataOutputStream datasal= new DataOutputStream(salida);
        while(i<2){
            datasal.writeChars(aux1);
            System.out.println(aux1);
            longitud=aux1.length();
            System.out.println("a lonxitude desta cadea en carateres e "+aux1.length());
            aux= datasal.size();
            System.out.println("Llevamos escritos "+aux+" bytes");
            i++;
        }
        while(data.available()!=0){
            int x=0;
            while(x<longitud){
                lectura =lectura+data.readChar();

                x++;
            }
            aux3=data.available();
            System.out.println(lectura);
            System.out.println("Quedan "+aux3+" bytes por leer");

            lectura="";
        }


        data.close();




    }


}