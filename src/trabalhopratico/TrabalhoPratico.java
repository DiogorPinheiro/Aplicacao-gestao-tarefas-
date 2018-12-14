/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhopratico;

import java.util.ArrayList;
import myinputs.Ler;
import java.io.*;
import static java.lang.System.exit;

/**
 *
 * @author Diogo Pinheiro
 */
public class TrabalhoPratico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        ArrayList<Pessoa> membros = new ArrayList<Pessoa> ();
        Admin a = new Admin("fabio","putas");
        a.setNota("HELLO MA DUDES");
        membros.add(a);

        int ind;
        
        try{
            ind = iniciarSessao(membros);
        }
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    ind = -1;
                    System.exit(1);
                }
        
        
        
        for (int i = 0; i < membros.size(); i++)     // APRESENTA AS NOTAS DOS 
        {                                               // RESIDENTES
            System.out.println(membros.get(i).getNota());
        }
        
        
        //-------------------------------------------------------//
        
        int escolha;
        do
        {
            System.out.println("1 - Menu Admin");
            System.out.println("2 - Contas");
            System.out.println("3 - Tarefas");
            System.out.println("4 - Notas");
            System.out.println("5 - Mudar Utilizador");
            System.out.println("6 - Sair");
            System.out.println("");
            System.out.print("ESCOLHA :");
            
            escolha = Ler.umInt();
            
            switch(escolha)
            {
                case 1 :
                    MenuAdmin(membros);
                    break;
                    
                case 2 : 
                    menuContas(membros,ind);
                    break;
                
                case 3 : break;
                
                case 4 : menuNotas(membros,ind);
                    
                    break;
                
                case 5 :
                 try{
                    ind = iniciarSessao(membros);
                }
                 catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                        exit(1);
                    }
                 
                    
                    break;
                case 6 :
                                // gravar no ficheiro//
                                break;
            }
            
        }
        while(escolha != 6);   
    }

    private static void MenuAdmin(ArrayList<Pessoa> membros) {
      int escolha = 0;
        
        while(escolha != 4){       // Menu Inicial
            System.out.println("1 – Gestão Pessoas;\n" + "2 – Gestão Contas;\n" + "3 – Gestão Tarefas;\n" + "4 – Sair.\n");
            escolha = Ler.umInt();
        
            switch(escolha){
                case 1: 
                        menuGestaoPessoas(membros);
                        break;
                case 2:
                        menuContaAdmin(membros);
                        break;
                        
                case 4:
                        break;     // Sair do menu
                default:  
                    System.out.println("Opção não existente!!\n");
                    break;
            }            
        }
            

    }



public static void menuGestaoPessoas(ArrayList<Pessoa> membros)
    {
        Pessoa novap;
        Pessoa test;
        String nome;
        String pass;
        
        int opcao = 0;
       
        
        int i = 0, flag = 0;
        
          do
          {
            System.out.println("\t Menu de Gestão de Pessoas");
            System.out.println("1 - Adicionar Pessoa; \n2 - Remover Pessoa;\n3 - Mostrar residentes \n4 - Sair para o menu principal\n");
            opcao = Ler.umInt();
            
            switch(opcao){       // Sub-menu 1
                case 1: // Inserir Pessoa
                        do
                        {
                            flag = 0;
                            System.out.println("Nome da Pessoa : ");
                            nome = Ler.umaString();

                            for(i = 0; i < membros.size(); i++) // verifica se existem pessoas com o mesmo nome
                            {
                                test = (Pessoa) membros.get(i);
                                if( test.getNome().equals(nome) )
                                {
                                    System.out.println("Já existe um residente com esse nome.Introduza um novo nome.");
                                    flag = 1;
                                    break;
                                }                              
                            }
                        }while(flag != 0);
                        novap = new Pessoa(nome);
                        membros.add(novap);
                        System.out.println("Foi criado um novo residente.");
                        break;
                        
                case 2: // Remover Pessoa
                        System.out.println("Nome da Pessoa : ");
                        nome = Ler.umaString();

                        for(i = 0; i < membros.size(); i++)
                        {
                            test = (Pessoa) membros.get(i);
                            if( test.getNome().equals(nome) )
                            {
                                membros.remove(i);
                                System.out.println("Foi removido com sucesso o residente.");
                                break;
                            }                              
                        }
                        
                        if( i == membros.size())
                        {
                            System.out.println("Não existe nenhum residente com esse nome.");
                        }
                        break;
                        
                case 3:                       
                        if(membros.isEmpty())
                            System.out.println("Não existem residentes");
                        else
                        {
                            System.out.println("Mostrar residentes:");
                            for( i = 0; i < membros.size(); i++)
                            {
                                System.out.println(membros.get(i).getNome());
                            }
                        }
                        break;
                        
                case 4: 
                        break;
                        
                default: 
                       System.out.println("Introduziu uma opção que não existe!");
            }
        }while(opcao != 4);
    }


public static void menuContaAdmin(ArrayList<Pessoa> membros){
        int escolha3 = 0;
        int n = 0;
        double valor = 0;
        double pagar = 0;
        String s;
        
        System.out.println("1 - Adicionar Conta a Pagar;\n");
        escolha3 = Ler.umInt();
       
        switch(escolha3){       // Sub-menu Contas
            case 1: // Inserir conta        FALTA DIVIDIR PELAS PESSOAS
                System.out.println("Valor da conta : ");
                valor = Ler.umDouble();
                System.out.println("Descrição da conta : ");
                s = Ler.umaString();
                //Contas npr = new Contas(valor,s);
                //membros.get(0).setContasAdmin(npr);    // Arraylist que armazena valor total de cada conta a pagar TRATAR DEPOIS, NAO TE ESQUECAS DIOGO
                n = membros.size();  // tamanho array pessoas
                pagar = valor/n; // Set valor para cada pessoa : valor/nºpessoas
                // Adicionar valor individual que cada pessoa tem a pagar
                Contas npr2 = new Contas(pagar,s);
                // Assumir que Admin está no arrayList pessoa -> VERIFICAR  
                for(int i = 0; i < n ; i++)
                {
                    membros.get(i).setContas(npr2);
                }
                break;
                

            default:  
                System.out.println("Opção não existente!!");
                break;
            }
        
    }
   /* 
  public void menuTarefas() {
            int escolha = 0;
            System.out.println("1 – Trabalhadores;\n" + "2 – Locais de trabalho;\n" + "3 – Consultar todos os locais;\n" + "4 – Sair.\n");
            escolha = Ler.umInt();
            while (true) {       // Menu Inicial
                switch (escolha) {
                    case 1: menuTrabalhador();
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
*/

public static void menuContas (ArrayList<Pessoa> membros, int ind)
{
    Contas aux;
    int remove;
    String s;
     int escolha = 0;
            System.out.println("1 – Remover contas a pagar;\n" + "2 – Consultar contas a Pagar;\n" + "3 – Historico de contas;\n" + "4 – Sair.\n");
            escolha = Ler.umInt();
            while (escolha != 4) {       // Menu Inicial
                switch (escolha) {
                    case 1: 
                        
                        for(int i = 0; i < membros.get(ind).getContas().size(); i++)
                        {
                            aux = membros.get(ind).getContas().get(i);
                            s = aux.toString();
                            System.out.println(s);  
                        }
                        System.out.print("Conta a remover :");
                        remove = Ler.umInt();
                        membros.get(ind).removerContasPagar(remove,membros.get(ind).getContas(),membros.get(ind).getContasHist());
                        break;
                    case 2: 
                        for(int i = 0; i < membros.get(ind).getContas().size(); i++)
                        {
                            aux = membros.get(ind).getContas().get(i);
                            s = aux.toString();
                            System.out.println(s);  
                        }
                        break;
                    case 3: 
                        for(int i = 0; i < membros.get(ind).getContas().size(); i++)
                        {
                            aux = membros.get(ind).getContasHist().get(i);
                            s = aux.toString();
                            System.out.println(s);  
                        }
                        break;
                    case 4:
                            break;    
                    default:
                        System.out.println("Opção não existente!!");
                        break;
                }
                System.out.println("Opção : ");
                escolha = Ler.umInt();
            }
}

/*
 public static void menuLocais(){
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
                case 4: tasks.randomTarefas(); /*De forma WIP este comando serve para testar a minha bela função ^_^ by Tyago
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
 
 
 public static void menuTrabalhador(){
        int escolha = 0;
        String nome;
        System.out.println("1 – Adicionar;\n" + "2 – Remover;\n" + "3 – Consultar Trabalhadores;\n" + "4 - Sair.");
        escolha = Ler.umInt();
        while (true) {       // Menu Inicial
            switch (escolha) {
                case 1:
                    System.out.println("Nome da pessoa: ");
                    nome=Ler.umaString();
                    System.out.println(tasks.addPessoa(this.foundPessoa(nome))+"\n");
                    break;
                case 2:
                    System.out.println("Nome da pessoa: ");
                    nome=Ler.umaString();
                    System.out.println(tasks.removePessoa(tasks.foundPessoa(nome).getNome())+"\n");
                    break;
                case 3:
                    System.out.println(tasks.toStringPessoa()+"\n");
                    break;
                case 4:
                    return;     // Sai do menu
                default:
                    System.out.println("Opção não existente!!");
                    break;
            }
            System.out.println("Opção : ");
            escolha = Ler.umInt();
        }
    }
 */
 public static void menuNotas(ArrayList<Pessoa> membros,int ind){
     int escolha = 0;
            System.out.println("1 – Ver Notas;\n" + "2 – Alterar Nota;\n" + "3 – Sair.\n");
            escolha = Ler.umInt();
            while (true) {       // Menu Inicial
                switch (escolha) {
                    case 1: 
                        for (int i = 0; i < membros.size(); i++)     // APRESENTA AS NOTAS DOS 
                        {                                               // RESIDENTES
                            System.out.println(membros.get(i).getNota());
                        }
                        break;
                    case 2:
                        System.out.println("Nota nova:");
                        membros.get(ind).setNota(Ler.umaString());
                        break;
                    case 3:
                        return;     // Sair do menu
                    default:
                        System.out.println("Opção não existente!!");
                        break;
                }
                System.out.println("Opção : ");
                escolha = Ler.umInt();
            } 
 }
 
 public static int iniciarSessao(ArrayList<Pessoa> membros) throws Exception
 {
     int ind = -1;
     String alma;
     String password;
        do{                           // PROCURA A PESSOA NA LISTA E DEVOLVE O SEU
            System.out.print("Nome :");   // ID, REPETE-SE ENQUANTO O NOME NAO EXISTIR
            alma = Ler.umaString();                    // NA LISTA
            for(int i = 0; i < membros.size(); i++)
            {
                if(membros.get(i).getNome().equals(alma))
                {
                ind = i;
                break;
                }
            }
        }while(ind == -1);
        
        int tentativas = 0;
        System.out.println("");        // PEDE A PASSWORD ENQUANTO NÃO A ACERTA
        do{
            System.out.print("Password :");
            password = Ler.umaString();
            if(membros.get(ind).getPass().equals(password))
            {
               break; 
            }
            tentativas++;
        }while (tentativas < 5);
        
        if(tentativas == 5)
        {
            throw new Exception("Excedeste o número de tentativas");
        }
     return ind;
 }
}