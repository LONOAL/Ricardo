package org.example;

import org.produtos.Product;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class XMLWriterExercise {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("produtos.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<Product> productList = new ArrayList<>();

            try {
                Product produto;
                while ((produto = (Product) ois.readObject()) != null) {
                    productList.add(produto);
                }
            } catch (EOFException e) {
                // Fin del archivo alcanzado
            }

            ois.close();

            // Paso 2: Escribir la lista de productos en formato XML
            XMLOutputFactory factory = XMLOutputFactory.newFactory();
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("products.xml"));

            writer.writeStartDocument("1.0");
            writer.writeStartElement("produtos");

            for (Product product : productList) {
                writer.writeStartElement("produto");
                writer.writeStartElement("codigo");
                writer.writeCharacters(product.getCodigo());
                writer.writeEndElement();
                writer.writeStartElement("descricion");
                writer.writeCharacters(product.getDescricion());
                writer.writeEndElement();
                writer.writeStartElement("prezo");
                writer.writeCharacters(Integer.toString(product.getPrezo()));
                writer.writeEndElement();
                writer.writeEndElement();
            }

            writer.writeEndElement();
            writer.writeEndDocument();
            writer.close();


            System.out.println("La lista de productos se ha almacenado en 'products.xml' en formato XML.");
        } catch (ClassNotFoundException | IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }
}