package pasaxeirosvoosserializadooracle_3;

import java.io.FileInputStream;
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
        Reserva reserva = null;

        try {
            FileInputStream fileIn = new FileInputStream("src/main/java/pasaxeirosvoosserializadooracle_3/reservas");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            PreparedStatement pst = null;
            PreparedStatement pst2 = null;
            ResultSet rs = null;
            int prezo = 0;
            while ((reserva = (Reserva) in.readObject())!=null){
                prezo = 0;
                pst = connection.prepareStatement("UPDATE pasaxeiros SET nreservas=nreservas+1 WHERE dni='"+reserva.getDni()+"'");
                pst.execute();
                pst = connection.prepareStatement("INSERT INTO reservasfeitas(codr,dni,nome,prezoreserva) VALUES (?,?,?,?)");
                pst2 = connection.prepareStatement("SELECT nome from pasaxeiros where dni='" + reserva.getDni()+"'");
                rs = pst2.executeQuery();
                rs.next();
                pst.setInt(1, reserva.getCodr());
                pst.setString(2,reserva.getDni());
                pst.setString(3,rs.getString(1));
                pst2 = connection.prepareStatement("SELECT prezo from voos where voo='"+reserva.getIdvooida()+"'");
                rs = pst2.executeQuery();
                rs.next();
                prezo += rs.getInt(1);
                pst2 = connection.prepareStatement("SELECT prezo from voos where voo='"+reserva.getIdvoovolta()+"'");
                rs = pst2.executeQuery();
                rs.next();
                prezo += rs.getInt(1);
                pst.setInt(4,prezo);
                pst.execute();
                System.out.println(reserva.toString());
            }
            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
