/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acoes.DAO;

import Acoes.Model.AcoesModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author v3-14
 */
public class AcoesDAO {

    
    private Connection conexao;
    
    public AcoesModel getLastRow(){
        ResultSet rs= null;
        AcoesModel acoesmodel=new AcoesModel();
        
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt= conexao.prepareStatement("SELECT precoMedio, saldo FROM acoes");
            
            rs = stmt.executeQuery();
            
            if(rs.last()){
                acoesmodel.setPMByDAO(rs.getDouble("precoMedio"));
                acoesmodel.setSaldo(rs.getInt("saldo"));
//                acoesmodel.setPMDAO(rs.getDouble("precoMedio"));
               
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            Conexao.desconectar(conexao);
        }
        return acoesmodel;
    }
    public AcoesModel getLastPM(){
        ResultSet rs= null;
        AcoesModel acoesmodel=new AcoesModel();
        
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt= conexao.prepareStatement("SELECT precoMedio FROM  acoes");
            
            rs = stmt.executeQuery();
            
            if(rs.last()){
                acoesmodel.setPMByDAO(rs.getDouble("precoMedio"));
               
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            Conexao.desconectar(conexao);
        }
        return acoesmodel;
    } 
    public AcoesModel getLastSaldo(){
        ResultSet rs= null;
        AcoesModel acoesmodel=new AcoesModel();
        
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt= conexao.prepareStatement("SELECT saldo FROM  acoes");
            
            rs = stmt.executeQuery();
            
            if(rs.last()){
                acoesmodel.setSaldo(rs.getInt("saldo"));
               
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            Conexao.desconectar(conexao);
        }
        return acoesmodel;
    }
    
    public boolean inserir(AcoesModel objeto) {
        
    if(objeto.getOperacao()==1){
        try {
            conexao = Conexao.conectar();
            PreparedStatement statement = conexao.prepareStatement("insert into acoes (nome, valor, qtd, operacao, saldo, precoMedio, data) values (?,?,?,?,?,?,?)");
            statement.setString(1, objeto.getNome());
            statement.setDouble(2, objeto.getTotal());
            statement.setDouble(3, objeto.getQtd());
            statement.setDouble(4, objeto.getOperacao());

//            pega pm anterior e saldo anterior
            
//NÃO CALCULA PM DO NOVO SÓ PEGA DO ANTIGO            calcular PM
//            objeto.setPM();
            AcoesModel objAnterior=getLastRow();
            int saldoTotal=objAnterior.getSaldo()+objeto.getQtd();
//                                    System.out.println("OBJA"+objAnterior.toString());
//                                    System.out.println("OBJ"+objeto.toString());
//                                    System.out.println("saldototal"+saldoTotal);

            double somaAnterior= (objAnterior.getSaldo())*(objAnterior.getPM());
            double somaAtual= (objeto.getQtd())*(objeto.getTotal());
            double PMDAO=(somaAnterior+somaAtual)/saldoTotal;
            PMDAO=Math.round(PMDAO*100d)/100d;
//            System.out.println("SA"+somaAnterior+"SAt"+somaAtual);
//            System.out.println("PMDAO"+PMDAO);
            statement.setDouble(5, saldoTotal);
            statement.setDouble(6, PMDAO);
            statement.setDate(7, objeto.getData());            
//                        System.out.println(statement.toString());
//                        System.out.println("47ron");
//            System.exit(0);
            statement.execute();
            statement.close();
            return true;
        } catch (Exception e) {

            System.out.println("Ocorreu um erro: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conexao);
        }
         }else{
          try {
            conexao = Conexao.conectar();
            PreparedStatement statement = conexao.prepareStatement("insert into acoes (nome, valor, qtd, operacao, saldo, precoMedio, data) values (?,?,?,?,?,?,?)");
            statement.setString(1, objeto.getNome());
            statement.setDouble(2, objeto.getTotal());
            statement.setDouble(3, objeto.getQtd());
            statement.setDouble(4, objeto.getOperacao());

            AcoesModel objAnterior=getLastRow();
            int saldoTotal=objAnterior.getSaldo()-objeto.getQtd();
//                                    System.out.println("OBJA"+objAnterior.toString());
//                                    System.out.println("OBJ"+objeto.toString());
//                                    System.out.println("saldototal"+saldoTotal);

            double PMDAO=objAnterior.getPM();
            PMDAO=Math.round(PMDAO*100d)/100d;
//            System.out.println("SA"+somaAnterior+"SAt"+somaAtual);
            System.out.println("PMDAO"+PMDAO);
            statement.setDouble(5, saldoTotal);
            statement.setDouble(6, PMDAO);
            statement.setDate(7, objeto.getData());  
System.out.println("PMDAO"+PMDAO + "ST"+saldoTotal);
//                        System.out.println(statement.toString());
//                        System.out.println("47ron");
//            System.exit(0);
            statement.execute();
            statement.close();
            return true;
        } catch (Exception e) {

            System.out.println("Ocorreu um erro: " + e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conexao);
        }
    }
    
    }

    public List<AcoesModel> buscar() {
        ResultSet rs= null;
        List<AcoesModel> lista = new ArrayList<AcoesModel>();
        
        try {
            conexao = Conexao.conectar();
            PreparedStatement stmt= conexao.prepareStatement("SELECT * FROM  acoes order by data");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                AcoesModel acoesmodel=new AcoesModel();
                
                acoesmodel.setId(rs.getInt("id"));
                acoesmodel.setNome(rs.getString("nome"));
                acoesmodel.setTotal(rs.getDouble("valor"));
                acoesmodel.setQtd(rs.getInt("qtd"));
                acoesmodel.setSaldo(rs.getInt("saldo"));
                acoesmodel.setOperacao(rs.getInt("operacao"));
                acoesmodel.setData(rs.getDate("data"));
                acoesmodel.setPMByDAO(rs.getDouble("precoMedio"));
            
                lista.add(acoesmodel);
                        }
            
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally{
            Conexao.desconectar(conexao);
            return lista;
        }
    }

    public boolean deletar(AcoesModel obj) {
        
        try {
            conexao = Conexao.conectar();
            
            PreparedStatement stmt= conexao.prepareStatement("DELETE FROM acoes WHERE id=?");
            stmt.setInt(1, obj.getId());
            
            stmt.execute();
            stmt.close();
            return true;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            Conexao.desconectar(conexao);
        }
    }
    
}
