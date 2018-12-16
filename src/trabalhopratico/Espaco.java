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
 * @author André Oliveira, Diogo Pinheiro, Fábio Correia, Tiago Marques
 */
public class Espaco implements Serializable{
    private String nome;
    private static int nEspaco;
    
    public Espaco (String nome){
        this.nome = nome;
        nEspaco++;
    }

    public String getNome() {
        return nome;
    }
    
    public static int getNEspacos()
    {
        return nEspaco;
    }
    
    public static void setNEspacos(int valor)
    {
        nEspaco = valor;
    }

    @Override
    public String toString() {
        return "Espacos :" + nome + "\n";
    }
    
    
}
