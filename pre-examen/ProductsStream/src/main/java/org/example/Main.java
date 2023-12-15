package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Crear objetos de Product con datos
            Product product1 = new Product("cod1", "parafusos", 3.0);
            Product product2 = new Product("cod2", "cravos", 4.0);
            Product product3 = new Product("cod3", "tachas", 6.0);
            Product product4 = new Product("cod4", "grapas", 2.0);

            // Guardar los valores de los productos en productos.txt
            FileOutputStream fileOutputStream = new FileOutputStream("productos.txt");
            DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

            dataOutputStream.writeUTF(product1.getCodigo());
            dataOutputStream.writeUTF(product1.getDescricion());
            dataOutputStream.writeDouble(product1.getPrezo());

            dataOutputStream.writeUTF(product2.getCodigo());
            dataOutputStream.writeUTF(product2.getDescricion());
            dataOutputStream.writeDouble(product2.getPrezo());

            dataOutputStream.writeUTF(product3.getCodigo());
            dataOutputStream.writeUTF(product3.getDescricion());
            dataOutputStream.writeDouble(product3.getPrezo());

            dataOutputStream.writeUTF(product4.getCodigo());
            dataOutputStream.writeUTF(product4.getDescricion());
            dataOutputStream.writeDouble(product4.getPrezo());

            dataOutputStream.close();

            // Leer y mostrar los valores de los productos desde productos.txt
            FileInputStream fileInputStream = new FileInputStream("productos.txt");
            DataInputStream dataInputStream = new DataInputStream(fileInputStream);

            System.out.println("Lista de productos guardados en el archivo productos.txt:");
            while (dataInputStream.available() > 0) {
                String codigo = dataInputStream.readUTF();
                String descricion = dataInputStream.readUTF();
                double prezo = dataInputStream.readDouble();

                System.out.println(codigo + " , " + descricion + " , " + prezo);
            }

            dataInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Product {
    private String codigo;
    private String descricion;
    private double prezo;

    public Product() {
        this.codigo = null;
        this.descricion = null;
        this.prezo = 0.0;
    }

    public Product(String codigo, String descricion, double prezo) {
        this.codigo = codigo;
        this.descricion = descricion;
        this.prezo = prezo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public double getPrezo() {
        return prezo;
    }

    public void setPrezo(double prezo) {
        this.prezo = prezo;
    }
}





