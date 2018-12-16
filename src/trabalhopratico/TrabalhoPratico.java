package trabalhopratico;

import java.util.ArrayList;

import java.io.*;
import static java.lang.System.exit;

public class TrabalhoPratico {

    public static void main(String[] args) {
        
        
        ArrayList<Pessoa> membros = new ArrayList<Pessoa> ();
        ArrayList<Espaco> espacos = new ArrayList<Espaco>();
        
           membros = lerFile(espacos);
        
        
        if(membros.size() == 0)
        {
            System.out.println("\tPor favor crie um admin");
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
                    System.exit(0) ;
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
                            int verifica = 0;
                            for(i = 0; i < membros.size(); i++)
                            {
                                test = (Pessoa) membros.get(i);
                                if( test.getNome().equals(nome) )
                                {
                                    membros.remove(i);
                                    System.out.println("Foi removido com sucesso o residente.");
                                    verifica =1;
                                    break;
                                }                              
                            }

                            if( verifica == 0 )
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
        } // JA ESTÁ A FUNCIONAR

    public static void menuContaAdmin(ArrayList<Pessoa> membros){
            int escolha3 = 0;
            int n = 0;
            double valor = 0;
            double pagar = 0;
            String s;

            System.out.println("1 - Adicionar Conta a Pagar;\n2 - Sair ");
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
                case 2:
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
                
                while (escolha != 4) {       // Menu Inicial
                    System.out.println("Menu Contas:");
                System.out.println("1 – Remover contas a pagar;\n" + "2 – Consultar contas a Pagar;\n" + "3 – Historico de contas;\n" + "4 – Sair.\n");
                escolha = Ler.umInt();
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
                        int i, flag = 0;
                        Espaco removeEspaco;
                        System.out.println("Nome do espaço : ");
                        nome = Ler.umaString();

                        for( i = 0; i < espacos.size(); i++)
                        {
                            removeEspaco = (Espaco) espacos.get(i);
                            if( removeEspaco.getNome().equals(nome) )
                            {
                                flag = 1;
                                espacos.remove(i);
                                System.out.println("Foi removido com sucesso o espaço.");
                                break;
                            }                              
                        }
                        
                        if(flag == 0)
                            System.out.println("Não foi possivel remover o espaço. Não existe nenhum espaço com esse nome.");
                        break;
                    case 3:
                        System.out.println("Espaços:");
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
            } 
    }
    
    public static void sortearTarefas(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos )
    {
        int contador = 0, aleatorioE = 0, flag = 0, existe = 0, repete = 0;
        int pessoaAleatoria;
        ArrayList<Integer> espacosSorteados = new ArrayList <Integer>();
        Pessoa pessoa;
        ArrayList <Espaco> tarefas;
        
                
        for(int i = 0; i < membros.size(); i++) // remove todas as tarefas anteriores
        {   
            pessoa = (Pessoa) membros.get(i);
            tarefas =  new ArrayList <Espaco>();          
            pessoa.setTasks(tarefas);
            membros.set(i, pessoa);
        } 
        
        if(espacos.size() > membros.size())
        {
            flag = (espacos.size() / membros.size()) + (espacos.size() %membros.size() );
            
            //System.out.println(espacos.size() % membros.size());
        }
        else
            flag = 1;
        
        for(int i = 0; i < espacos.size(); i++)
        {   
            do // Para que não exista repetição de espaços
            {
                existe = 0;
                aleatorioE = (int)(Math.random() * (espacos.size()));

                for(int k = 0; k < espacosSorteados.size(); k++)
                {
                    if(aleatorioE == espacosSorteados.get(k))
                    {                        
                        existe = 1;
                        break;
                    }                   
                }
            }while(existe == 1);
            
            espacosSorteados.add(aleatorioE);
            
            do
            {
                repete = 0;
                pessoaAleatoria = (int)(Math.random() * (membros.size()));
                pessoa = (Pessoa) membros.get(pessoaAleatoria);               
                tarefas = pessoa.getTasks();
                
                if(tarefas.size() < flag )
                {
                    //System.out.println(pessoa.getNome());
                    tarefas.add(espacos.get(aleatorioE));
                    //System.out.println(espacos.get(aleatorioE).getNome());
                    pessoa.setTasks(tarefas);
                    membros.set(pessoaAleatoria, pessoa);
                    break;
                } 
                else
                    repete = 1;               
            }while(repete == 1);     
     
        }
        
        System.out.println("Tarefas distribuidas:");
        for(int i = 0; i < membros.size(); i++)
        {   
            
            pessoa = (Pessoa) membros.get(i);
            tarefas = pessoa.getTasks();
               
            for(int j = 0; j < tarefas.size(); j++ )
            {
                    System.out.print(pessoa.getNome() + ": ");
                    Espaco test = tarefas.get(j);               
                    System.out.println(test.getNome());
             
            }
        } 
    }

    
    public static void menuNotas(ArrayList<Pessoa> membros,int ind){
         int escolha = 0;
               
                while (escolha != 3) {       // Menu Inicial
                    System.out.println("1 – Ver Notas;\n" + "2 – Alterar Nota;\n" + "3 – Sair.\n");
                    System.out.println("Opção:");
                    escolha = Ler.umInt();
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
                            break;     // Sair do menu
                        default:
                            System.out.println("Opção não existente!!");
                            break;
                    }
                    
                } 
     } /// JÁ ESTÁ A FUNCIONAR

    public static void iniciarSessao(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos) throws Exception{
         int ind = -1;
         System.out.println("\tLOGIN");
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

    public static void subMenuTarefas(ArrayList<Pessoa> membros, int ind)
    {
        Espaco aux;
        Pessoa pessoa;
        int escolha = 0, i = 0;
        String s, nomeEspaco;
        ArrayList<Espaco> tarefas;
        Espaco test;
        
        do
        {
            System.out.println(" Menu Tarefas:");
            System.out.println(" 1 - Consultar Tarefas");
            System.out.println(" 2 - Tarefa realizada");
            System.out.println(" 3 - Sair do menu tarefas");
            
            System.out.println("Introduza a opção");
            escolha = Ler.umInt(); 
            
            switch(escolha)
            {
                   case 1: 
                           for( i = 0; i < membros.get(ind).getTasks().size(); i++)
                           {
                               aux = membros.get(ind).getTasks().get(i);
                               s = aux.toString();
                               System.out.println(s);  
                           }
                           break;
                   case 2: 
                           int flag = 0;
                           System.out.println("Introduza o nome da tarefa: ");
                           nomeEspaco = Ler.umaString();
                           
                           pessoa = (Pessoa) membros.get(ind);
                           tarefas = pessoa.getTasks();
                           for(i = 0; i < tarefas.size(); i++ )
                           {
                               test = tarefas.get(i);
                               if( test.getNome().equals(nomeEspaco) )
                               {
                                   tarefas.remove(i);
                                   pessoa.setTasks(tarefas);
                                   membros.set(ind, pessoa);
                                   System.out.println("Já foi atualizada a lista de tarefas.");
                                   flag =1;
                                   break;
                               }
                           }
                           if(flag == 0)
                               System.out.println("Não existe nenhuma tarefa com esse nome.");
                   case 3:
                            break;
                   default: 
                            System.out.println("Não existe nenhuma tarefa com esse nome.");
                            break;                           
            }
        }while(escolha != 3);
    }
    public static void menuPrincipalResidente(ArrayList<Pessoa> membros, int ind, ArrayList<Espaco> espacos) throws IOException, ClassNotFoundException{
        
        String s;
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
                    subMenuTarefas(membros, ind);
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
                        escreverFile(membros,espacos);
                        break;
            }
            
        }
        while(escolha != 5);
    } /// JÁ ESTA A FUNCIONAR
    
    public static void menuPrincipalAdmin(ArrayList<Pessoa> membros, int ind, ArrayList<Espaco> espacos) throws IOException, ClassNotFoundException{
        int escolha;
        Espaco aux;
        String s;
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
                    subMenuTarefas(membros, ind);
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
                                escreverFile(membros,espacos);
                                return;
            }
            
        }
        while(escolha != 6);   
    
    } /// JÁ ESTA A FUNCIONAR

    public static void escreverFile(ArrayList<Pessoa> membros, ArrayList<Espaco> espacos){ // NA ALTURA SÓ EU E DEUS SABIAMOS O QUE ISTO ESTÁ A FAZER
        File file1 = new File("teste.dat");                     //           AGORA SÓ MESMO DEUS ... -_-
        try {
        FileOutputStream fo = new FileOutputStream(file1);
        ObjectOutputStream output = new ObjectOutputStream(fo);
        output.writeInt(membros.size());
        Admin a = (Admin)membros.get(0);
        int tamanho = espacos.size();
        output.writeInt(tamanho);
        for(int i = 0; i < tamanho; i++)
        {
            output.writeObject(espacos.get(i));
        }
        output.writeObject((Admin)membros.get(0));
        for(int i = 1; i < membros.size(); i++)
        {
            output.writeObject(membros.get(i));
        }
        int conta = Contas.getConta();
        output.writeInt(conta);
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
    
    public static ArrayList<Pessoa> lerFile(ArrayList<Espaco> espacos){
        ArrayList<Pessoa> membros = new ArrayList<> ();
        
        Pessoa aux1;
        Espaco aux2;
        try {
            
            File file2 = new File("teste.dat");
            FileInputStream fi = new FileInputStream(file2);
            ObjectInputStream input = new ObjectInputStream(fi);
            int sizeP = (int) input.readInt();
            int sizeE = (int) input.readInt();
            for(int i = 0; i < sizeE; i++)
            {
                aux2 = (Espaco) input.readObject();
                espacos.add(aux2);
            }
            Admin admin = (Admin) input.readObject();
            membros.add(admin);
            for(int i = 1; i < sizeP; i++)
            {
                aux1 = (Pessoa) input.readObject();
                membros.add(aux1);
            }
            int conta = (int) input.readInt();
            
            Contas.setConta(conta);
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