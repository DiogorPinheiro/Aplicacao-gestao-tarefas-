/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;
import java.util.ArrayList;
import java.io.*;
/**
 *
 * @author Diogo Pinheiro
 */
public class Espaco implements Serializable{
    private int estado; // 1 - Limpo ; 0 -> Não limpo
    private ArrayList<String> historico;
    private ArrayList<Integer> data;
    private String nome;
    private static int nEspaco;
    
    public Espaco (String nome){
        this.nome = nome;
        estado=0;
        nEspaco++;
    }

    public String getNome() {
        return nome;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ArrayList<String> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<String> historico) {
        this.historico = historico;
    }
    public void setHistorico(int day,String conteudo) {
        this.historico.set(day, conteudo);
    }
    public void addHistorico(int day,String conteudo){
        this.historico.add(day,conteudo);
    }
    public ArrayList<Integer> getData(){
        return data;
    }
    
    public static int getNEspacos()
    {
        return nEspaco;
    }
    
    public static void setNEspacos(int valor)
    {
        nEspaco = valor;
    }
}
