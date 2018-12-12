/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;
import java.util.ArrayList;
import myinputs.*;
/**
 *
 * @author Diogo Pinheiro
 */
public class Admin extends Pessoa{
    private ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>();
    private ArrayList<Espaco> espacos = new ArrayList<Espaco>();
    private ArrayList<Contas> contas = new ArrayList<Contas>();
    
   
    public Admin(Pessoa pessoa){
        super(pessoa);  
    }
    
    public void insert(Pessoa a){
        pessoa.add(a);
    }
    
    public void menuGestaoPessoas()
    {
        Pessoa novap;
        Pessoa test;
        String nome;
        int opcao = 0;
       
        
        do
        {
            System.out.println("\t\t Menu de Gestão de Pessoas");
            System.out.println("\t 1 - Adicionar Pessoa; \n\t 2 - Remover Pessoa;\n \t 3 - Sair para o menu principal\n");
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
                case 3:
                        break;
                default: 
                       System.out.println("Introduziu uma opção que não existe!!!");
            }
        }while(opcao != 3);
    }
    
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
    
    public void menuTarefas() { /*Por fazer*/
            int escolha = 0;
            System.out.println("1 – Adicionar;\n" + "2 – Remover;\n" + "3 – Consultar;\n" + "4 – Sair.\n");
            escolha = Ler.umInt();
            while (true) {       // Menu Inicial
                switch (escolha) {
                    case 1:

                        break;
                    case 2:
                        break;
                    case 3:
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
                        
                case 4:
                    break;     // Sair do menu
                default:  
                    System.out.println("Opção não existente!!\n");
                    break;
            }            
        }
     
    }
}

