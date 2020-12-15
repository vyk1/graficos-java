/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acoes.Controller;

import Acoes.DAO.AcoesDAO;
import Acoes.Model.AcoesModel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author v3-14
 */
@WebServlet(name="graficos", urlPatterns = {"/grafico", "/graficos"})

public class AcoesController extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        AcoesDAO acoesdao = new AcoesDAO();
        List<AcoesModel> lista = acoesdao.buscar();
        
        request.setAttribute("lista", lista);
        RequestDispatcher rd = request.getRequestDispatcher("listar.jsp");
        
        rd.forward(request, response);
        
    }
    
    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException{
//            System.out.println("Ah não");
                    if(request.getParameter("acao").equals("cadastrar")){
                        
                        int operacao=Integer.parseInt(request.getParameter("operacao"));
//                        System.out.println("OPERACAO"+operacao);

                        String nome=request.getParameter("nome");
                        double valor=Double.valueOf(request.getParameter("valor"));
                        int qtd= Integer.parseInt(request.getParameter("qtd"));
                        double taxaCorretagem= Double.valueOf(request.getParameter("taxaCorretagem"));
                        double outrasTaxas=Double.valueOf(request.getParameter("outrasTx"));
                        
                        double total =valor+taxaCorretagem+outrasTaxas;
                        
                        
                        String data = request.getParameter("data");
//                        System.out.println(data);
                        
                        AcoesModel acao=new AcoesModel();
                        acao.setNome(nome);
                        acao.setQtd(qtd);
                        acao.setTotal(total);
                        acao.setOperacao(operacao);
                        
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                                
                            try {
                                Date newData= sdf.parse(data);     
//                                System.out.println(newData+"data:"+data);
                                java.sql.Date sDate = new java.sql.Date(newData.getTime());

                                acao.setData(sDate);
                                
                            } catch (Exception ex) {
                                System.out.println(ex);
                            }
                            
                        AcoesDAO acaoDAO=new AcoesDAO();
                        
                        AcoesModel acmod=acaoDAO.getLastRow();
//                        ;
//                        if(acao.getOperacao()==0){
//                            if((acmod.getSaldo()-acao.getQtd())<=0) {
//                                request.setAttribute("mensagem", "Saldo insuficiente para venda<br>");
////                                acao.setSaldo(0);
//
//                            } 
//                        }                        
                     
                        
                        if(acaoDAO.inserir(acao)){
                            request.setAttribute("mensagem", "Cadastro realizado com sucesso<br>");
                        }else{
                            acao.toString();
                            request.setAttribute("mensagem", "Ocorreu algo de errado<br>");                    
                        }
//                        request.setAttribute("mensagem", acao.toString());

                        RequestDispatcher rd=request.getRequestDispatcher("retorno.jsp");
                        rd.forward(request, response);
                               
                    }
                    if(request.getParameter("acao").equals("apagar")){
//                        System.out.println("APAGAR");    
                        int id=Integer.parseInt(request.getParameter("idAcao"));
                        AcoesModel amod=new AcoesModel();
                        
                        amod.setId(id);
                        AcoesDAO adao=new AcoesDAO();
                        
                        System.out.println(amod.toString());
                        if(adao.deletar(amod)){    
                            request.setAttribute("mensagem", "Ação de id "+amod.getId()+" apagada com sucesso<br>");    
                        }else{
                            request.setAttribute("mensagem", "Ocorreu um erro ao deletar a ação<br>");
                        }
                        
                        RequestDispatcher rd=request.getRequestDispatcher("retorno.jsp");
                        rd.forward(request, response);
                    }

    
    }
}

