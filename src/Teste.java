import java.util.ArrayList;

/* Classe Teste referente a tarefa 2 avaliativa da disciplina GAT108 UFLA 2023/1 
 * By Marcus Vinicius Ferreira
 */
public class Teste {
    public static void main(String[] args) throws Exception {

        // a lista 1, 9 e 10 não sofrem interferência
        ArrayList<Tarefa> tarefasQueInterferemEm2 = new ArrayList<>();
        ArrayList<Tarefa> tarefasQueInterferemEm3 = new ArrayList<>();
        ArrayList<Tarefa> tarefasQueInterferemEm4 = new ArrayList<>();
        ArrayList<Tarefa> tarefasQueInterferemEm5 = new ArrayList<>();
        ArrayList<Tarefa> tarefasQueInterferemEm6 = new ArrayList<>();
        ArrayList<Tarefa> tarefasQueInterferemEm7 = new ArrayList<>();
        ArrayList<Tarefa> tarefasQueInterferemEm8 = new ArrayList<>();

        // tarefa 1
        Tarefa t1 = new Tarefa(1, 1, 10, 40, 40, null);
        tarefasQueInterferemEm2.add(0, t1); // t1 interfere em t2
        tarefasQueInterferemEm3.add(0, t1); // t1 interfere em t3
        tarefasQueInterferemEm4.add(0, t1); // t1 interfere em t4
        tarefasQueInterferemEm5.add(0, t1); // t1 interfere em t5

        // tarefa 2
        Tarefa t2 = new Tarefa(2, 3, 10, 80, 50, tarefasQueInterferemEm2);

        // tarefa 3
        Tarefa t3 = new Tarefa(3, t2.getR(), 5, 80, 50, tarefasQueInterferemEm3) {
            @Override
            public void run() {
                try {
                    t2.getThread().join(); // espera a thread 2 finalizar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        tarefasQueInterferemEm4.add(0, t3);

        // tarefa 4
        Tarefa t4 = new Tarefa(4, t2.getR(), 10, 80, 80, tarefasQueInterferemEm4) {
            @Override
            public void run() {
                try {
                    t2.getThread().join(); // espera a thread 2 finalizar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        tarefasQueInterferemEm5.add(0, t4);

        // tarefa 5
        Tarefa t5 = new Tarefa(5, 1, 5, 40, 40, tarefasQueInterferemEm5);
        tarefasQueInterferemEm6.add(0, t5); // t5 interfere em t6
        tarefasQueInterferemEm7.add(0, t5); // t5 interfere em t7

        // tarefa 6
        Tarefa t6 = new Tarefa(6, 1, 10, 40, 40, tarefasQueInterferemEm6);

        // tarefa 7
        Tarefa t7 = new Tarefa(7, t6.getR(), 20, 150, 80, tarefasQueInterferemEm7) {
            @Override
            public void run() {
                try {
                    t6.getThread().join(); // espera a thread 6 finalizar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };
        tarefasQueInterferemEm8.add(0, t7); // t7 interfere em t8

        // tarefa 8
        Tarefa t8 = new Tarefa(8, t6.getR(), 10, 100, 50, tarefasQueInterferemEm8) {
            @Override
            public void run() {
                try {
                    t6.getThread().join(); // espera a thread 6 finalizar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };

        // tarefa 9
        Tarefa t9 = new Tarefa(9, 3, 10, 80, 25, null);

        // tarefa 10
        Tarefa t10 = new Tarefa(10, t9.getR(), 20, 80, 50, null) {
            @Override
            public void run() {
                try {
                    t9.getThread().join(); // espera a thread 9 finalizar
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.run();
            }
        };

        // aguarda que todas as threads sejam finalizadas
        t1.getThread().join();
        t2.getThread().join();
        t3.getThread().join();
        t4.getThread().join();
        t5.getThread().join();
        t6.getThread().join();
        t7.getThread().join();
        t8.getThread().join();
        t9.getThread().join();
        t10.getThread().join();

        // exibe quais tarefas são escaláveis
        System.out.println(t1.checarEscalabilidade());
        System.out.println(t2.checarEscalabilidade());
        System.out.println(t3.checarEscalabilidade());
        System.out.println(t4.checarEscalabilidade());
        System.out.println(t5.checarEscalabilidade());
        System.out.println(t6.checarEscalabilidade());
        System.out.println(t7.checarEscalabilidade());
        System.out.println(t8.checarEscalabilidade());
        System.out.println(t9.checarEscalabilidade());
        System.out.println(t10.checarEscalabilidade());
    }
}
