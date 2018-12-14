/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;
/**
 *
 * @author Diogo Pinheiro
 */
public class Contas {
    
    private double valor ;
    private String descricao;
    private static int conta;
    private int ID;
    
    public Contas(double valor, String descricao){
        this.valor = valor;
        this.descricao = descricao;
        conta++;            // Incrementar valor da conta
        ID = conta;         // Atribuir esse valor a um ID de forma a este ser o número seguinte à conta criada anteriormente
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

       
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return ("Conta Nº" + ID +" : Valor - "+ valor + "; Descrição - "+ descricao);
    }
    
    
    
}
