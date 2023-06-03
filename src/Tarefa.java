import java.util.ArrayList;

/* Classe Tarefa referente a tarefa 2 avaliativa da disciplina GAT108 UFLA 2023/1 
 * By Marcus Vinicius Ferreira
 */
public class Tarefa implements Runnable {
    private int id; // identificador da tarefa
    private int j, c, p, d; // parâmetros fornecidos pela tabela
    private ArrayList<Tarefa> tarefaInterferencia; // lista de tarefas que interferem
    private Thread thread;
    private int wAux, rAux; // W e R auxiliares para armazenar o valor e exibir ao usuario

    // construtor
    public Tarefa(int id, int j, int c, int p, int d, ArrayList<Tarefa> tarefaInterferencia) {
        this.id = id;
        this.j = j;
        this.c = c;
        this.p = p;
        this.d = d;
        this.tarefaInterferencia = tarefaInterferencia;
        this.thread = new Thread(this); // instancia a thread
        this.thread.start();
    }

    // método que executa a tarefa da thread
    @Override
    public void run() {
        System.out.println("T" + id + " iniciada");
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

        while (elapsedTime < c) {
            long currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;
        }

        c = (int) elapsedTime;
        System.out.println("T" + id + " finalizada -> Tempo de computação: " + c + " ms");
    }

    // calcula o W, que é o intervalo entre a liberação e o término da tarefa
    private int getW(int wAnterior) {
        int acumulador = 0;
        for (Tarefa t : tarefaInterferencia) {
            acumulador += Math.ceil(((double) (wAnterior + t.j) / t.p)) * t.c;
        }
        wAux = c + acumulador;
        return wAux;
    }

    // calcula o tempo de resposta máximo R
    int getR() {
        // se for alguma tarefa que não sofre interferência de nenhuma outra
        if (tarefaInterferencia == null || tarefaInterferencia.isEmpty()) {
            wAux = c;
            return c + j;
        }

        // iteração
        int wAnterior = -1;
        int wAtual = 0;
        int contador = 0;
        while (wAnterior != wAtual) {
            if (contador == 0) {
                wAnterior = 0;
                wAtual = c;
            } else {
                wAnterior = wAtual;
                wAtual = getW(wAnterior);
            }
            contador++;
        }
        return wAtual + j;
    }

    String checarEscalabilidade() {
        rAux = getR();
        if (rAux <= d) {
            return ("Tarefa " + id + " eh escalonavel -> W = " + wAux + " / R = " + rAux + " / D = " + d);
        } else {
            return ("Tarefa " + id + " NAO eh escalonavel ->W = " + wAux + " / R = " + rAux + " / D = " + d);
        }
    }

    public Thread getThread() {
        return thread;
    }
}
