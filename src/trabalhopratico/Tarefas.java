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
public class Tarefas implements Serializable {
    private ArrayList<Espaco> task = new ArrayList<Espaco>();   /*Espaços de combate, não vale sair das barreiras!!!*/
    /*Basicamente espaços que serão realmente usados para limpar*/
    private ArrayList<Pessoa> pessoa = new ArrayList<Pessoa>(); /*Pessoas que participam nas limpezas*/
    private static int taskultimo = 0, pessoaultimo = 0;
    /* PS: Este teste somente vai para 1 pessoa para cada divisão
    * se querem 2 pessoas para 1 divisão no espaço precisam
    * de multiplicar pelo menos o nome do espaço
    * para o sorteiro sortear 2 pessoas
    * incluido O caso de declarar que são 2 pessoas para limpar a casa de banho
    * e calhar 2 vezes a mesma pois já faz uma verificação de repetição
    * */
    public String toStringPessoa(){
        String s="";
        for(int i=0;i<pessoa.size();i++){
            s = s + pessoa.get(i).getNome()+"; ";
        }
        return s;
    }
    public String toStringEspaco(){
        String s="";
        for(int i=0;i<task.size();i++){
            s = s + task.get(i).getNome()+"; ";
        }
        return s;
    }
    public Pessoa foundPessoa(String nome){
        for(int i = 0; i<pessoa.size();i++){
            if(pessoa.get(i).getNome().equals(nome))
                return pessoa.get(i);
        }
        return null;
    }

    public Espaco foundLocal(String nome){
        for(int i = 0; i<task.size();i++){
            if(task.get(i).getNome().equals(nome))
                return task.get(i);
        }
        return null;
    }
    public String addTarefa(Espaco o) {           /*Quem está pronto para um novo desafio?*/
        if (o==null)
            return ("Espaço inválido, porfavor adicione esse lugar!\n");
        taskultimo++;
        task.add(o);
        return "Lugar adicionado!\n";
    }

    public String removeTarefa(String nome) {         /*Não quero limpar a cozinha, é muito oleosa*/
        for (int i = 0; i < task.size(); i++) {
            if (nome.equals(task.get(i).getNome())) ;
            task.remove(i);
            return "Sucesso!";
        }
        return "Desculpe, não achamos esse lugar...\n";
    }

    public String addPessoa(Pessoa o) {   /*Adicionar candidato a limpar a casa de banho :D*/
        if(o==null)
            return "Pessoa não existente, porfavor adicione essa pessoa!\n";
        pessoaultimo++;
        pessoa.add(o);
        return "Pessoa adicionada com sucesso!\n";
    }
    public String removePessoa(String nome) {         /*Encontramos um preguiçoso!!!*/
        for (int i = 0; i < pessoa.size(); i++) {
            if (nome.equals(pessoa.get(i).getNome())) ;
            pessoa.remove(i);
            return "Sucesso!";
        }
        return "Desculpe, não achamos essa pessoa...\n";
    }

    public String setPessoa(ArrayList<Pessoa> o) { /*Adicionar candidatos "anteriores" */
        if(o==null)
            return "Lista inválida\n";
        pessoaultimo = o.size();
        pessoa.clear();
        for (int i = 0; i < o.size(); i++) {
            pessoa.add(o.get(i));
        }
        return "Sucesso\n";
    }

    public void randomTarefas() {     /*Rodem os dados, a batalha vai começar!!!*/
        int tasks, i, tenta, max = 1,tentar;
        Pessoa e = this.pessoa.get(0);
        Espaco s;
        for (i = 0; i < pessoaultimo; i++) {
            e = this.pessoa.get(i);
            e.setTasks(null);
            e.setNtask(0);
            this.pessoa.set(i, e);
        }
        for(i=0; i< taskultimo; i++){
            s = this.task.get(i);
            s.setEstado(0);
        }
        if (e != null) {
            e.setSucessos(0);
        }
        tenta = 4 * pessoaultimo; /*Acho um bom numero de tentativas para conseguir pelo menos 1 vez*/
        for (i = 0; i < taskultimo; i++) {
            tentar = tenta; /*Evitar fazer contas again and again*/
            do {
                tasks = (int) Math.random() * pessoaultimo; /*Sorteia a pessoa, vais ser tu a lavar a louça?*/
                tentar--; /*Por coveniência, evitar loops infinitos*/
            }
            while (this.pessoa.get(tasks).foundTasks(task.get(tasks).getNome()) || pessoa.get(tasks).getNtask() > max || tentar != 0 || max >= (pessoa.get(0).getSucessos() / pessoaultimo));
                    if(max < (pessoa.get(0).getSucessos() / pessoaultimo))
                        max++; /*Agora tens mais 1 coisa para fazeres, que tristeza!! D:*/
                    if(tentar==0){
                        /*Help? Que erro deveria colocar cá?*/
                    }
                    e.setSucessos();
                    e=pessoa.get(i);
                    e.addTasks(this.task.get(i));
                    pessoa.set(tasks,e);

            /*Classe aparentemente completa mas com possibilidade da existência bugs, convêm dar uma olhadela*/
        }
    }
}
