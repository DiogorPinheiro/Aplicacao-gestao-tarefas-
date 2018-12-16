package trabalhopratico;

import java.util.ArrayList;
import myinputs.Ler;
import java.io.*;
import static java.lang.System.exit;

public class TrabalhoPratico {

    public static void main(String[] args) {
        
        
        ArrayList<Pessoa> membros = new ArrayList<Pessoa> ();
        ArrayList<Espaco> espacos = new ArrayList<Espaco>();
        
           membros = lerFile();
        
        
        if(membros.size() == 0)
        {
            System.out.println("Não existe ninguem, criem admin");
            System.out.println("NOME:");
            String nome = Ler.umaString();
            System.out.println("PASSWORD:");
            String pass = Ler.umaString();
            Admin a = new Admin(nome, pass);
            membros.add(a);
        }
        
        
        try{
           iniciarSessao(membros,espacos);
        }
        catch(Exception e)
                {
                    System.out.println(e.getMessage());
                    System.exit(2);
                }
    }      
        
    public static void menuAdmin(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos) {
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
                case 3:
                    menuTarefas(membros, espacos);
                    break;
                case 4:
                        break;     // Sair do menu
                default:  
                    System.out.println("Opção não existente!!\n");
                    break;
            }            
        }
            

    } /// FALTA COLOCAR A DISTRIBUIÇÃO DAS TAREFAS

    public static void menuGestaoPessoas(ArrayList<Pessoa> membros){
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
                            System.out.print("Password :");
                            pass = Ler.umaString();
                            novap = new Pessoa(nome,pass);
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
        } /// JA ESTÁ A FUNCIONAR

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

        } /// JA ESTA A FUNCIONAR

    public static void menuContas (ArrayList<Pessoa> membros,int ind){
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
                            for(int i = 0; i < membros.get(ind).getContasHist().size(); i++)
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
    } /// FALTA COLOCAR AS CONTAS PAGAS NO HISTORICO

    public static void menuTarefas(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos) { /*Por fazer*/
            int escolha = 0;
            String nome;
            
            
            while (escolha !=5) {       // Menu Inicial
                System.out.println("Menu Espaços:");
                System.out.println("1 – Adicionar espaços;\n2 – Remover espaços;\n3 – Consultar espaços;\n4- Sortear espaços\n5 – Sair.");
                escolha = Ler.umInt();
                switch (escolha) {
                    case 1: 
                        Espaco novoEspaco;
                        System.out.println("Introduza o nome do espaços:");
                        nome = Ler.umaString();
                        novoEspaco = new Espaco(nome); 
                        espacos.add(novoEspaco);
                        System.out.println("Foi criado com sucesso um novo espaço.");
                        break;
                    case 2: 
                        int i;
                        Espaco removeEspaco;
                        System.out.println("Nome da Pessoa : ");
                        nome = Ler.umaString();

                        for( i = 0; i < espacos.size(); i++)
                        {
                            removeEspaco = (Espaco) espacos.get(i);
                            if( removeEspaco.getNome().equals(nome) )
                            {
                                espacos.remove(i);
                                System.out.println("Foi removido com sucesso o espaço.");
                                break;
                            }                              
                        }
                        
                        if(i == espacos.size())
                            System.out.println("Não foi remover o espaço.");
                        break;
                    case 3:
                        for( i = 0; i < espacos.size(); i++)
                        {
                            System.out.println(espacos.get(i).getNome());
                        }
                        break;
                    case 4:
                        sortearTarefas(membros, espacos);
                        break;
                    case 5:
                        break;// Sair do menu
                        
                    default:
                        System.out.println("Opção não existente!!");
                        break;
                }
                System.out.println("Opção : ");
                escolha = Ler.umInt();
            } 
    }
    
    public static void sortearTarefas(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos )
    {
        int contador = 0, aleatorioE = 0;
        Pessoa pessoa;
        ArrayList <Espaco> tarefas;
        
        for(int i = 0; i < membros.size(); i++)
        {   
            pessoa = (Pessoa) membros.get(i);
            tarefas = pessoa.getTasks();
            for(int j = tarefas.size(); j > 0; j-- )
            {
                tarefas.remove(j);
            }   
            pessoa.setTasks(tarefas);
            membros.set(i, pessoa);
        } 
        
        for(int i = 0; i < espacos.size(); i++)
        {   
            aleatorioE = (int)(Math.random() * (espacos.size()));
            
            if(i % (membros.size()-1) == 0 )
                contador++;           
            
            for(int j = 0; j < membros.size(); j++)
            {               
                pessoa = (Pessoa) membros.get(i);
                tarefas = pessoa.getTasks();      

                if(tarefas.size()-1 < contador )
                {
                    tarefas.add(espacos.get(aleatorioE));
                    pessoa.setTasks(tarefas);
                    membros.set(j, pessoa);
                }  
            }     
     
        }
    }

    
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
     } /// JÁ ESTÁ A FUNCIONAR

    public static void iniciarSessao(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos) throws Exception{
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
         
            
         if(membros.get(ind).getClass() == Admin.class)
        {
            menuPrincipalAdmin(membros,ind, espacos);
        }
        else
        {
            menuPrincipalResidente(membros,ind,espacos);
        }
        
     } /// JÁ ESTA A FUNCIONAR

    public static void menuPrincipalResidente(ArrayList<Pessoa> membros, int ind, ArrayList<Espaco> espacos) throws IOException, ClassNotFoundException{
        
        int escolha;
        do
        {
            System.out.println("1 - Contas");
            System.out.println("2 - Tarefas");
            System.out.println("3 - Notas");
            System.out.println("4 - Mudar Utilizador");
            System.out.println("5 - Sair");
            System.out.println("");
            System.out.print("ESCOLHA :");
            
            escolha = Ler.umInt();
            
            switch(escolha)
            {
                case 1 : 
                    menuContas(membros,ind);
                    break;
                
                case 2 : 
                    menuTarefas(membros,espacos);
                    break;
                
                case 3 : menuNotas(membros,ind);
                    
                    break;
                
                case 4 :
                 try{
                    iniciarSessao(membros,espacos);
                }
                 catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                        exit(1);
                    }
                 
                    
                    break;
                case 5 :            
                        escreverFile(membros);
                        break;
            }
            
        }
        while(escolha != 5);
    } /// JÁ ESTA A FUNCIONAR
    
    public static void menuPrincipalAdmin(ArrayList<Pessoa> membros, int ind, ArrayList<Espaco> espacos) throws IOException, ClassNotFoundException{
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
                    menuAdmin(membros,espacos);
                    break;
                    
                case 2 : 
                    menuContas(membros,ind);
                    break;
                
                case 3 :
                    menuTarefas(membros,espacos);
                    break;
                
                case 4 : menuNotas(membros,ind);
                    
                    break;
                
                case 5 :
                 try{
                    iniciarSessao(membros,espacos);
                }
                 catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                        exit(1);
                    }
                 
                    
                    break;
                case 6 :
                                escreverFile(membros);
                                break;
            }
            
        }
        while(escolha != 6);   
    
    } /// JÁ ESTA A FUNCIONAR

    public static void escreverFile(ArrayList<Pessoa> membros){ // NA ALTURA SÓ EU E DEUS SABIAMOS O QUE ISTO ESTÁ A FAZER
        File file1 = new File("teste.dat");                     //           AGORA SÓ MESMO DEUS ... -_-
        try {
        FileOutputStream fo = new FileOutputStream(file1);
        ObjectOutputStream output = new ObjectOutputStream(fo);
        output.writeInt(membros.size());
        output.writeObject((Admin)membros.get(0));
        for(int i = 1; i < membros.size(); i++)
        {
            output.writeObject(membros.get(i));
        }
        output.close();
        fo.close();
        } 
        catch (FileNotFoundException e) {
            System.out.println("ERRO: O FICHEIRO NAO FOI ENCONTRADO");
        }
        catch (IOException e){
            System.out.println("ERRO: NÃO FOI BEM ESCRITO");
        }
    }
    
    public static ArrayList<Pessoa> lerFile(){
        ArrayList<Pessoa> membros= new ArrayList<> ();
        try {
            
            File file2 = new File("teste.dat");
            FileInputStream fi = new FileInputStream(file2);
            ObjectInputStream input = new ObjectInputStream(fi);
            int size = (int) input.readInt();
            Admin admin = (Admin) input.readObject();
            Pessoa aux;
            membros.add(admin);
            for(int i = 1; i < size; i++)
            {
                aux = (Pessoa) input.readObject();
                membros.add(aux);
            }
        } 
        catch (FileNotFoundException e) {
            System.out.println("ERRO: NÃO FOI ENCONTRADO O FICHEIRO");
        }
        catch(IOException e)
        {
            System.out.println("ERRO: PROBLEMAS DE ENTRADA");  
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("ERRO: CLASSE NÃO ENCONTRADA");
        }
        return membros;
    }
}