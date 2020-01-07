
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBD {
   
    
    public static ConexionBD ConActual;
    private Connection con;
    
   
    
    private void conectar(){       
 
             String sql = "jdbc:sqlserver://<server>:<port>;databaseName=dbfinanciera;user=<sys>;password=<password>";
            try{
                con = DriverManager.getConnection(sql);
                
            }catch(SQLException SQL){
                System.out.println("error en la conexion"+SQL.getLocalizedMessage());
            }
       
    }
    
   private  ConexionBD() {     
        conectar();
    }   
 
    public static synchronized ConexionBD EstadoCon(){
        if(ConActual==null){
            ConActual= new ConexionBD();
        }
       return ConActual;
    }

    public Connection getConexion() {
        return con;
    }
      
    public void CerrarConeccion(){
       if(con !=null){
           try {
               con.close();
           } catch (SQLException ex) {
               Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
           }
       }       
    }    
    
   
    
    
}  