package Model;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CalibracaoManager {

    private ArquivoProcessor arquivoProcessor;
    private int numThreads;

    public CalibracaoManager(String caminhoDoArquivo, int numThreads) {
        this.arquivoProcessor = new ArquivoProcessor(caminhoDoArquivo);
        this.numThreads = numThreads;
    }

    // Método para calcular os valores de calibração (a soma total)
    public int calcularValoresDeCalibracao() throws IOException, InterruptedException {
        // Lê todas as linhas do arquivo
        List<String> linhas = arquivoProcessor.lerLinhas();

        // Divide as linhas em blocos
        List<String[]> blocos = arquivoProcessor.dividirLinhasEmBlocos(linhas, numThreads);

        // Array para armazenar os resultados parciais
        int[] resultado = new int[numThreads];

        // Cria o CountDownLatch com o número de threads
        CountDownLatch latch = new CountDownLatch(numThreads);

        // Cria e inicia as threads
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(new SomaWorker(blocos.get(i), resultado, i, latch));
            thread.start();
        }

        // Aguarda todas as threads terminarem
        latch.await();

        // Calcula a soma total
        int somaTotal = 0;
        for (int somaParcial : resultado) {
            somaTotal += somaParcial;
        }

        return somaTotal;
    }
}

