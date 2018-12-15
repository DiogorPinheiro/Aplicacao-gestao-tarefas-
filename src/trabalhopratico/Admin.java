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
    private Tarefas tasks = new Tarefas();
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

    public Pessoa foundPessoa(String nome){
        for(int i = 0; i<pessoa.size();i++){
            if(pessoa.get(i).getNome().equals(nome))
                return pessoa.get(i);
        }
        return null;
    }

    public void setContasAdmin(Contas a)
    {
        this.contas.add(a);
    }
    
    public Espaco foundLocal(String nome){
        for(int i = 0; i<espacos.size();i++){
            if(espacos.get(i).getNome().equals(nome))
                return espacos.get(i);
        }
        return null;
    }
    /*
    public void menuGestaoPessoas()
    {
        Pessoa novap;
        Pessoa test;
        String nome;
        int opcao = 0;
       
        
        do
        {
            System.out.println("\t\t Menu de Gestão de Pessoas");
            System.out.println("\t 1 - Adicionar Pessoa; \n\t 2 - Remover Pessoa;\n \t 2 - Consultar Pessoas;\n \t 4 - Sair para o menu principal\n");
            opcao = Ler.umInt();
            
            switch(opcao){       // Sub-menu 1
                case 1: // Inserir Pessoa
                        System.out.println("Nome da Pessoa : ");
                        nome = Ler.umaString();
                        novap = new Pessoa(nome);
                        pessoa.add(novap);
                  break;
                case 2: // Remover Pessoa
                        System.out.println("Nome da Pessoa : ");
                        nome = Ler.umaString();

                        for(int i = 0; i < pessoa.size(); i++)
                        {
                            test = (Pessoa) pessoa.get(i);
                            if( test.getNome().equals(nome) )
                                pessoa.remove(i);

                        }                  
                        break;
                case 3: System.out.println(toStringPessoa()+"\n");
                        break;
                case 4: break;
                default: 
                       System.out.println("Introduziu uma opção que não existe!!!");
            }
        }while(opcao != 3);
    }
    /*
    public void menuConta(){
        int escolha3 = 0;
        int n = 0;
        double valor = 0;
        double pagar = 0;
        String s;
        
        System.out.println("1 - Adicionar Conta a Pagar;\n" + "2 - Remover Conta a Pagar;\n" +"3 - Consultar Contas.\n");
        escolha3 = Ler.umInt();
       
        switch(escolha3){       // Sub-menu Contas
            case 1: // Inserir conta        FALTA DIVIDIR PELAS PESSOAS
                System.out.println("Valor da conta : ");
                valor = Ler.umDouble();
                System.out.println("Descrição da conta : ");
                s = Ler.umaString();
                Contas npr = new Contas(valor,s);
                contas.add(npr);    // Arraylist que armazena valor total de cada conta a pagar
                n = pessoa.size();  // tamanho array pessoas
                pagar = valor/n; // Set valor para cada pessoa : valor/nºpessoas
                // Adicionar valor individual que cada pessoa tem a pagar
                ArrayList<Contas> cont = new ArrayList<Contas>();
                Contas npr2 = new Contas(pagar,s);
                cont.add(npr2);
                // Assumir que Admin está no arrayList pessoa -> VERIFICAR  
                for(int i = 0; i < n ; i++){
                    super.setContas(cont);
                }
                break;
            case 2: // Remover conta pagar -> passar para conta (manter histórico)
                System.out.println("Escreva ID : ");  
                n = Ler.umInt();            
                for(int i = 0; i< pessoa.size();i++){   // Fazer em todas as pessoas
                    super.removerContasPagar(n);
                }
                break;
            case 3: // Consultar Contas
                for(Contas e : contas) {
                    e.toString();
                }
                
                break;
            default:  
                System.out.println("Opção não existente!!");
                break;
            }
        
    }
    */
    public void menuTarefas() { /*Por fazer*/
            int escolha = 0;
            System.out.println("1 – Tarefas Decididas;\n" + "2 – Locais de trabalho;\n" + "3 – Consultar todos os locais;\n" + "4 – Sair.\n");
            escolha = Ler.umInt();
            while (true) {       // Menu Inicial
                switch (escolha) {
                    case 1: for (int i = 0; i < pessoa.size();i++){
                        System.out.println("> "+pessoa.get(i)+":\n"+pessoa.get(i).toStringTarefas()+"\n");
                    }
                        System.out.println("\n");
                        break;
                    case 2: menuLocais();
                        break;
                    case 3: System.out.println(toStringEspaco()+"\n");
                        break;
                    case 4:
                        return;     // Sair do menu
                    default:
                        System.out.println("Opção não existente!!");
                        break;
                }
                System.out.println("Opção : ");
                escolha = Ler.umInt();
            } 
    }
    
    public void menuLocais(){
        int escolha = 0;
        String nome;
        System.out.println("1 – Adicionar;\n" + "2 – Remover;\n" + "3 – Consultar locais de trabalho;\n" + "4 - Alterar limpezas" + "5 - Sair.");
        escolha = Ler.umInt();
        while (true) {       // Menu Inicial
            switch (escolha) {
                case 1:
                    System.out.println("Nome do local: ");
                    nome=Ler.umaString();
                    System.out.println(tasks.addTarefa(this.foundLocal(nome))+"\n");
                    break;
                case 2:
                    System.out.println("Nome do local: ");
                    nome=Ler.umaString();
                    System.out.println(tasks.removeTarefa(tasks.foundLocal(nome).getNome())+"\n");
                    break;
                case 3:
                    System.out.println(tasks.toStringEspaco()+"\n");
                    break;
                case 4: System.out.println(tasks.setPessoa(pessoa)+"\n");
                        tasks.randomTarefas(); /*De forma WIP este comando serve para testar a minha bela função ^_^ by Tyago*/
                        break;
                case 5:
                    return;     // Sai do menu
                default:
                    System.out.println("Opção não existente!!");
                    break;
            }
            System.out.println("Opção : ");
            escolha = Ler.umInt();
        }
    }

    @Override
    public String toString() {
        return "Admin: "+this.toStringPessoa() + "\n" + this.toStringEspaco() + "\n";
    }
    public String toStringPessoa(){
        String s="";
        for(int i=0;i<pessoa.size();i++){
            s = s + pessoa.get(i).getNome()+"; ";
        }
        return s;
    }

    public String toStringEspaco(){
        String s="";
        for(int i=0;i<espacos.size();i++){
            s = s + espacos.get(i).getNome()+"; ";
        }
        return s;
    }

    /*
    public void menuAdmin(){
        int escolha = 0;
        
        while(escolha != 4){       // Menu Inicial
            System.out.println("1 – Gestão Pessoas;\n" + "2 – Gestão Contas;\n" + "3 – Gestão Tarefas;\n" + "4 – Sair.\n");
            escolha = Ler.umInt();
        
            switch(escolha){
                case 1: 
                        menuGestaoPessoas();
                        break;
                case 2:
                    menuConta();
                    break;
                case 3:
                    menuTarefas();
                case 4:
                    break;     // Sair do menu
                default:  
                    System.out.println("Opção não existente!!\n");
                    break;
            }            
        }
     
    }
*/
}

