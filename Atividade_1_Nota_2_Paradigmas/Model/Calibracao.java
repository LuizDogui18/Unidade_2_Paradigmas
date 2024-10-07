package Atividade_1_Nota_2_Paradigmas.main.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Calibracao {

    public static int calcularValoresDeCalibracao(String caminhoArquivo) throws IOException, InterruptedException {
        List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
        int numThreads = 8;
        int tamanhoBloco = linhas.size() / numThreads;
        Thread[] threads = new Thread[numThreads];
        int[] resultado = new int[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int inicio = i * tamanhoBloco;
            int fim = (i == numThreads - 1) ? linhas.size() : (i + 1) * tamanhoBloco;
            List<String> bloco = linhas.subList(inicio, fim);
            threads[i] = new Thread(new CalcularSoma(bloco, resultado, i));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int somaTotal = 0;
        for (int valor : resultado) {
            somaTotal += valor;
        }

        return somaTotal;
    }
}