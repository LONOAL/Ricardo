package exa15brevep;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;

public class Query {

    // Iniciar a conexión coa base de datos
    Connection connection = null;
    String driver = "jdbc:postgresql:";
    String host = "//localhost:";
    String porto = "5432";
    String sid = "postgres";
    String usuario = "postgres";
    String password = "postgres";
    String url = driver + host+ porto + "/" + sid;

    {
        try {
            // Establecer a conexión
            connection = DriverManager.getConnection(url, usuario, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void readObject(){
        Platos plato = null;

        try {
            FileWriter obj= new FileWriter("platoss.xml");
            XMLOutputFactory obj1= XMLOutputFactory.newInstance();
            XMLStreamWriter obj2=obj1.createXMLStreamWriter(obj);
            FileInputStream fileIn = new FileInputStream("src/main/java/exa15brevep/platoss");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            PreparedStatement pst = null;
            ResultSet rs = null;
            obj2.writeStartDocument("1.0");
            obj2.writeStartElement("Platos");
            while ((plato = (Platos) in.readObject())!=null){
                pst = connection.prepareStatement("SELECT\n" +
                        "    c.codp AS codigo_plato,\n" +
                        "    '"+plato.getNomep()+"' AS nombre_plato,\n" +
                        "    SUM(comp.graxa * (c.peso/100)) AS suma_grasa_componentes\n" +
                        "FROM\n" +
                        "    COMPOSICION c\n" +
                        "JOIN\n" +
                        "    COMPONENTES comp ON c.codc = comp.codc\n" +
                        "WHERE\n" +
                        "    c.codp = '"+plato.getCodigop()+"'\n" +
                        "GROUP BY\n" +
                        "    c.codp;\n");


                rs = pst.executeQuery();
                while (rs.next()){
                    System.out.println(rs.getString(1));
                    System.out.println(rs.getString(2));
                    System.out.println(rs.getString(3));
                    obj2.writeStartElement("Plato");
                    obj2.writeAttribute("Codigop",rs.getString(1));
                    obj2.writeStartElement("nomep");
                    obj2.writeCharacters(rs.getString(2));
                    obj2.writeEndElement();
                    obj2.writeStartElement("graxatotal");
                    obj2.writeCharacters(String.valueOf(rs.getString(3)));
                    obj2.writeEndElement();
                    obj2.writeEndElement();
                }
            }

            obj2.writeEndElement();
            obj2.close();

            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }



}
