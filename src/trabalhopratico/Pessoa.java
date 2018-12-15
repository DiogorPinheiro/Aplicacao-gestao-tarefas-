/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;
import java.util.ArrayList;
import myinputs.Ler;
import java.io.*;
/**
 *
 * @author Diogo Pinheiro
 */
public class Pessoa implements Serializable {
    private static int num = 0;
    private String nome;
    private String pass;
    private ArrayList<Espaco> tasks = new ArrayList<Espaco>();
    private ArrayList<Contas> contasPagar = new ArrayList<Contas>();
    private ArrayList<Contas> contasHist = new ArrayList<Contas>();
    private String Nota;
    private static int sucessos; /*(Tasks) Quantos "tasks" têm sucesso em todos*/
    /*Detalhes: Usando sucessos posso ver se todas as pessoas já tem 1 task e se sim vai
    * dizer ao programa que deve começar a criar 2 tasks para cada um azar se tiveste 2 tasks
    * e o outro 1, afinal não à outra forma "justa" de o fazer
    * se querem queixar do random, queixem com o programa Java*/
    
    private Pessoa(){
    }
    
    public Pessoa(String nome){
        this.nome = nome;
        this.pass = "";
        this.Nota = "";
        this.num++;
    }

    public Pessoa(String nome, String pass) {
        this.nome = nome;
        this.pass = pass;
        this.Nota = "";
    }
    
     public Pessoa(Pessoa pessoa){
        this.nome = pessoa.getNome();
        this.pass = pessoa.getPass();
        setTasks(pessoa.getTasks());
        setContas(pessoa.getContas());                    
        setContasHist(pessoa.getContasHist());             
        Nota = pessoa.getNota();
    }

     public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getPass() {
        return pass;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public static void setSucessos(int sucessos) {
        Pessoa.sucessos = sucessos;
    }
    
    public static void setSucessos() {
        Pessoa.sucessos++;
    }
    
     public static int getSucessos() {
        return sucessos;
    }

    public boolean taskDone(int day,String nome){
        int i = this.showIDTasks(nome);
        if(i!=-1){
            Espaco e = tasks.get(i);
            e.setData(day,1);
            e.addHistorico(day,num+") "+this.nome);
            tasks.set(i,e);
            return true;
        }
        return false;
    }
   
    public ArrayList<Espaco> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Espaco> tasks) {
        ArrayList<Espaco> ntarefas = new ArrayList<Espaco>();
        
        for(int i = 0; i < tasks.size(); i++)
        {
            ntarefas.add(tasks.get(i));
        }
        this.tasks = ntarefas;
    }
    
    public boolean foundTasks(String nome){
        for(int i = 0; i < tasks.size(); i++)
        {
            if(tasks.get(i).equals(nome)){
                return true;
            }
        }
        return false;
    }
    
    public int showIDTasks(String nome){
        for(int i = 0; i < tasks.size(); i++)
        {
            if(tasks.get(i).equals(nome)){
                return i;
            }
        }
        return -1;
    }

    public void addTasks(Espaco tasks){
        this.tasks.add(tasks);
    }

    public ArrayList<Contas> getContas() {
        return contasPagar;
    }

    public void setContas(Contas contas) {
       this.contasPagar.add(contas);
    }

    public void setContas(ArrayList<Contas> contas) {
        ArrayList<Contas> nContas = new ArrayList<Contas>();
        
        for(int i = 0; i < contas.size(); i++)
        {
            nContas.add(contas.get(i));
        }
        this.contasPagar = nContas;
    }
  
    public ArrayList<Contas> getContasHist() {
        return contasHist;
    }

    public void setContasHist(ArrayList<Contas> contasHist) {
        ArrayList<Contas> nContasHist = new ArrayList<Contas>();
        
        for(int i = 0; i < contasHist.size(); i++)
        {
            nContasHist.add(contasHist.get(i));
        }
        this.contasHist = nContasHist;
    }
    
    public void removerContasPagar(int valor, ArrayList<Contas> contas, ArrayList<Contas> hist){
        for(int i = 0; i <contas.size(); i++ ) 
        {
            if(contas.get(i).getID() == valor)
            {
                hist.add(contas.get(i));
                contas.remove(i);
            }
        }
        
    }
    
    public void consultas(){        // Consultar contas a pagar e histórico
        System.out.println("1. Contas a Pagar.\n2. Histórico de Contas");
        int escolha4 = 0;
        escolha4 = Ler.umInt();
        switch(escolha4){
            case 1:
                System.out.println("Contas: \n");
                for(Contas e : contasPagar) {
                    e.toString();
                }
                break;
            case 2:
                System.out.println("Histórico de Contas: \n");
                for(Contas e : contasHist) {
                    e.toString();
                }
                break;
            default:
                System.out.println("Opção não existente!!");
                break;
        } 
    }
    
    public static int getNum() {
        return num;
    }

    public void setNota(String Nota) {
        this.Nota = Nota;
    }
    
    public String getNota()
    {
        return (nome + "\n NOTA: " + Nota + "\n\n" );
    }
 
    public int getNtask() {
        return ntask;
    }

    public void setNtask(int ntask) {
        this.ntask = ntask;
    }

    public void setContasPagar(ArrayList<Contas> contasPagar) {
        this.contasPagar = contasPagar;
    }

    public String toStringTarefas() {
        String s="";
        if(tasks.size() > 0) {
            for (int i = 0; i < tasks.size(); i++) {
                s = s + " " + tasks.get(i).getNome() + ";";
            }
        }
        return s;
    }
   
    
}
