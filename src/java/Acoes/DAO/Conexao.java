package Acoes.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    static final String JDBC_DRIVER="com.mysql.cj.jdbc.Driver";
    static final String JDBC_TIMEZONE="useTimezone=true&serverTimezone=UTC";
    static final String JDBC_URL="jdbc:mysql://localhost:3306/tfpwii?";
    static final String JDBC_USUARIO="root";
    static final String JDBC_SENHA="";
    
    public static Connection conectar(){
        try{
            Connection conexao;
            Class.forName(JDBC_DRIVER);
            conexao=DriverManager.getConnection(
            JDBC_URL+JDBC_TIMEZONE,
            JDBC_USUARIO,
            JDBC_SENHA);
            System.out.println("Conexão estabelecida");
            return conexao;
        } catch(Exception e){
            System.out.println(e.toString());
            return null;
        }
        
    }
    public static void desconectar(Connection c){
        try{
            c.close();
            System.out.println("Conexão encerrada");
        
        }catch(SQLException e){
            System.out.println("Erro ao fechar a conexao"+e.getMessage());
            
        }finally{
            c=null;
        }
    }
    
}
