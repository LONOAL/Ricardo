
package ovendasp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Ovendasp {

    
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        ResultSet resultSet1 = null;
        ResultSet resultSet2 = null;
           ResultSet resultSet3 = null;
          
           Statement statement1;
             Statement statement2;
             Statement statement3;
              Statement statement4;
             
            String linea;
          int prezo;
          int totalObtido=0;
          String nome="";
      
    
      
        String driver = "jdbc:postgresql:";
        String host = "//localhost:";
        String porto = "5432";
        String sid = "postgres";
        String usuario = "postgres";
        String password = "postgres";
        String url = driver + host+ porto + "/" + sid;
        
        Connection conn = DriverManager.getConnection(url,usuario,password);
         statement1 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
          statement2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           statement3 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement4 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
      
    
      // Lese obxectos do ficheiro platoss
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("totalvendas.txt"))); 
      BufferedReader reader = new BufferedReader(new FileReader("totalvendas.txt"));
     
            FileInputStream fileInputStream = new FileInputStream("/home/postgres/Descargas/vendas");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

              // Iterar sobre os obxectos lidos
              Vendas venda;
            while ((venda=(Vendas) objectInputStream.readObject())!=null){
               
                    
            
                    
                    // Recuperar o código do prato e o nome
                    int nv = venda.getNv();
                    String codp=venda.getCodp();
                    int cantv= venda.getCantv();
                    String des= venda.getDes();
                   
                     
                     
                     
     resultSet1=statement1.executeQuery("select prezo from prezos where codp='" + codp + "'");
     resultSet2=statement2.executeQuery("select de from prezos where codp='" + codp + "'");
 while(resultSet1.next()){
     while(resultSet2.next()){
       prezo=resultSet1.getInt("prezo");
        
         if(des.equals("s")){
             totalObtido=cantv*(prezo-resultSet2.getInt("de"));
            
         }
         else {
             totalObtido=cantv*prezo;
             
         }
 }
 }
 resultSet3=statement3.executeQuery("select nomp from stock where codp='" + codp + "'");
  while(resultSet3.next()){
   nome=resultSet3.getString("nomp");
  }
   statement4.executeUpdate("UPDATE stock set cants=cants-'" + cantv + "' where codp='" + codp + "'");
     writer.println(nv+"\t"+nome +"\t"+cantv+"\t"+totalObtido);           
                
              
           
    }
             writer.close();
              while ((linea = reader.readLine()) != null) {
           String[] campos = linea.split("\t");

                int nvDel = Integer.parseInt(campos[0]);
                String nompDel = campos[1];
                int cantvDel = Integer.parseInt(campos[2]);
                int totalObtidoDel=Integer.parseInt(campos[3]);


                System.out.println("Nv        " + nvDel);
                System.out.println("Nomp: " +nompDel);
                System.out.println("Cantv:       " + cantvDel);
                  System.out.println("TotalObtido:       " + totalObtidoDel);
       }     
          
    reader.close();
     
              objectInputStream.close();
            fileInputStream.close();
             statement1.close();

}
}