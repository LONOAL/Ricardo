package org.example;

import org.produtos.Product; // Asegúrate de que la importación sea correcta
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderExercise {
    public static void main(String[] args) {
        List<Product> productList = new ArrayList<>();

        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileReader("products.xml"));

            Product product = null;
            String content = null;

            while (reader.hasNext()) {
                int event = reader.next();

                switch (event) {
                    case XMLStreamConstants.START_ELEMENT:
                        String elementName = reader.getLocalName();
                        if ("produto".equals(elementName)) {
                            product = new Product("", "", 0); // Aquí deberías tener un constructor adecuado para Product
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        content = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        elementName = reader.getLocalName();
                        if ("codigo".equals(elementName)) {
                            product.setCodigo(content);
                        } else if ("descricion".equals(elementName)) {
                            product.setDescricion(content);
                        } else if ("prezo".equals(elementName)) {
                            product.setPrezo(Integer.parseInt(content));
                        } else if ("produto".equals(elementName)) {
                            productList.add(product);
                        }
                        break;
                }
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Imprimir los datos de los objetos Product leídos desde el archivo XML
        for (Product product : productList) {
            System.out.println("Código: " + product.getCodigo());
            System.out.println("Descrición: " + product.getDescricion());
            System.out.println("Prezo: " + product.getPrezo());
            System.out.println("----------------------");
        }
    }
}
