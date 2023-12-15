package org.example;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class XMLProba0ler {
    public static void main(String[] args) {
        try {
            // Crear un objeto XMLInputFactory
            XMLInputFactory factory = XMLInputFactory.newInstance();

            // Crear un objeto XMLStreamReader para leer el archivo XML
            FileInputStream fileInputStream = new FileInputStream("prueba.xml");
            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);

            // Recorrer el documento XML
            while (reader.hasNext()) {
                int eventType = reader.getEventType();

                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    // Comprobar si es un elemento de inicio
                    String localName = reader.getLocalName();
                    System.out.println("Elemento de inicio: " + localName);

                    // Leer atributos si los hay
                    for (int i = 0; i < reader.getAttributeCount(); i++) {
                        String attributeName = reader.getAttributeLocalName(i);
                        String attributeValue = reader.getAttributeValue(i);
                        System.out.println("Atributo: " + attributeName + " = " + attributeValue);
                    }
                } else if (eventType == XMLStreamConstants.CHARACTERS) {
                    // Comprobar si es contenido de texto
                    String text = reader.getText();
                    System.out.println("Contenido de texto: " + text);
                } else if (eventType == XMLStreamConstants.END_ELEMENT) {
                    // Comprobar si es un elemento de cierre
                    String localName = reader.getLocalName();
                    System.out.println("Elemento de cierre: " + localName);
                }

                // Avanzar al siguiente evento
                reader.next();
            }

            // Cerrar el lector
            reader.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
