import model.AgregadorFinanceiro;
import model.ProcessadorFinanceiro;

import java.io.File;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class Main {

    public static void main(String[] args) {
        // Definir os arquivos CSV com o caminho absoluto ou relativo
        String[] nomesArquivos = {
                "C:/Users/DELL/OneDrive/Área de Trabalho/Dispesas/receitas.csv", // Caminho absoluto
                "C:/Users/DELL/OneDrive/Área de Trabalho//Dispesas/despesas.csv", // Caminho absoluto
                "C:/Users/DELL/OneDrive/Área de Trabalho/Dispesas/provisao.csv" // Caminho absoluto
        };

        // Verificar se os arquivos existem antes de iniciar o processamento
        for (String nomeArquivo : nomesArquivos) {
            File arquivo = new File(nomeArquivo);
            if (!arquivo.exists()) {
                System.out.println("Arquivo não encontrado: " + nomeArquivo);
                return; // Para a execução caso algum arquivo não seja encontrado
            }
        }

        // Define a quantidade de threads
        int numThreads = 3;
        CyclicBarrier barreira = new CyclicBarrier(numThreads + 1); // Inclui o thread principal

        // Cria um agregador para cada tipo de financeiro
        AgregadorFinanceiro agregadorReceita = new AgregadorFinanceiro();
        AgregadorFinanceiro agregadorDespesa = new AgregadorFinanceiro();
        AgregadorFinanceiro agregadorProvisao = new AgregadorFinanceiro();

        // Iniciar threads de processamento para cada tipo
        new Thread(new ProcessadorFinanceiro(nomesArquivos[0], agregadorReceita, barreira)).start();
        new Thread(new ProcessadorFinanceiro(nomesArquivos[1], agregadorDespesa, barreira)).start();
        new Thread(new ProcessadorFinanceiro(nomesArquivos[2], agregadorProvisao, barreira)).start();

        // Esperar até que todos os threads terminem
        try {
            barreira.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        // Exibir os resultados agregados
        System.out.println("Receitas:");
        agregadorReceita.getRegistrosAgregados().forEach((data, total) ->
                System.out.println(data + "," + total));

        System.out.println("Total das receitas: " + agregadorReceita.getTotal());

        System.out.println("\nDespesas:");
        agregadorDespesa.getRegistrosAgregados().forEach((data, total) ->
                System.out.println(data + "," + total));

        System.out.println("Total das despesas: " + agregadorDespesa.getTotal());

        System.out.println("\nProvisões:");
        agregadorProvisao.getRegistrosAgregados().forEach((data, total) ->
                System.out.println(data + "," + total));

        System.out.println("Total das provisões: " + agregadorProvisao.getTotal());
    }
}
