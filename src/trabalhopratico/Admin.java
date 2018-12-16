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
public class Admin extends Pessoa implements Serializable{
    
    private ArrayList<Contas> contas = new ArrayList<Contas>();

    public Admin(Pessoa pessoa){
        super(pessoa);  
    }

    public Admin(String nome, String pass) {
        super(nome, pass);
    }
    
    
    

       public void setContasAdmin(Contas a)
    {
        this.contas.add(a);
    }

   
       
       
    
   @Override
    public String toString() {
        return "Admin: "+this.getNome();
    }
    
}

