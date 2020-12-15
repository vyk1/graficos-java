package Acoes.Model;

//import java.util.Date;

import java.sql.Date;

public class AcoesModel {
    String nome;
    double total;
    int qtd, id, operacao, saldo;
    Date data;
    double PM;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

//    public void setPM(){
//        this.PM=this.total/this.qtd;
//    }
    public double getPM() {
        return PM;
    }

    public void setPMByDAO(double PM) {
        this.PM = PM;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AcoesModel{" + "nome=" + nome + ", total=" + total + ", PM=" + PM + ", qtd=" + qtd + ", id=" + id + ", operacao=" + operacao + ", saldo=" + saldo + ", data=" + data + '}';
    }

    
}