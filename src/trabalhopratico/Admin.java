/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;
import java.util.ArrayList;
import myinputs.*;
import java.io.*;
/**
 *
 * @author Diogo Pinheiro
 */
public class Admin extends Pessoa implements Serializable{
    private ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();
    private ArrayList<Espaco> espacos = new ArrayList<Espaco>();
    private ArrayList<Contas> contas = new ArrayList<Contas>();
    
    /*Para quem não percebeu aqui entra as pessoas ou espaços que vão ser manipulados
     * os outros espaços ou pessoas serão simplesmente guardadas no sistema
      * para ser usufruida no futuro, Caso que queiram colocar sempre todas as pessoas
      * mal adicionam ao sistema basta eliminar as pessoas, por exemplo, e as manipular
      * dentro das Tarefas, afinal lá é onde tudo vai ser manipulado e decidido as
      * pessoas que devem ser controladas*/
   
    public Admin(Pessoa pessoa){
        super(pessoa);  
    }

    public Admin(String nome, String pass) {
        super(nome, pass);
    }
    
    
    
    public void insert(Pessoa a){
        pessoa.add(a);
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

