package org.example;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;

public class XMLProba0 {
    public static void main(String[] args) {
        try {
            // Crear un objeto XMLOutputFactory
            XMLOutputFactory factory = XMLOutputFactory.newInstance();

            // Crear un objeto XMLStreamWriter usando un FileWriter para escribir en un archivo
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("prueba.xml"));

            // Comenzar a escribir el documento XML
            writer.writeStartDocument("1.0");
            writer.writeStartElement("autores");

            // Escribir el primer autor
            writer.writeStartElement("autor");
            writer.writeAttribute("codigo", "a1");
            writer.writeStartElement("nome");
            writer.writeCharacters("Alexandre Dumas");
            writer.writeEndElement();
            writer.writeStartElement("titulo");
            writer.writeCharacters("El conde de Montecristo");
            writer.writeEndElement();
            writer.writeStartElement("titulo");
            writer.writeCharacters("Los miserables");
            writer.writeEndElement();
            writer.writeEndElement(); // Cerrar el autor

            // Escribir el segundo autor
            writer.writeStartElement("autor");
            writer.writeAttribute("codigo", "a2");
            writer.writeStartElement("nome");
            writer.writeCharacters("Fiodor Dostoyevski");
            writer.writeEndElement();
            writer.writeStartElement("titulo");
            writer.writeCharacters("El idiota");
            writer.writeEndElement();
            writer.writeStartElement("titulo");
            writer.writeCharacters("Noches blancas");
            writer.writeEndElement();
            writer.writeEndElement(); // Cerrar el autor

            // Cerrar el elemento raíz y el documento
            writer.writeEndElement();
            writer.writeEndDocument();

            // Cerrar el escritor
            writer.close();

            System.out.println("El archivo autores.xml se ha creado con éxito.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
