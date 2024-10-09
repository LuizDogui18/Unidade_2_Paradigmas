package Model;

import java.util.concurrent.CountDownLatch;

public class SomaWorker implements Runnable {

    private String[] linhas;
    private int[] resultado;
    private int indice;
    private CountDownLatch latch;

    public SomaWorker(String[] linhas, int[] resultado, int indice, CountDownLatch latch) {
        this.linhas = linhas;
        this.resultado = resultado;
        this.indice = indice;
        this.latch = latch;
    }

    @Override
    public void run() {
        int somaParcial = 0;
        for (String linha : linhas) {
            Character primeiroDigito = null;
            Character ultimoDigito = null;

            // Encontrar o primeiro e último dígito
            for (char ch : linha.toCharArray()) {
                if (Character.isDigit(ch)) {
                    primeiroDigito = ch;
                    break;
                }
            }
            for (int i = linha.length() - 1; i >= 0; i--) {
                if (Character.isDigit(linha.charAt(i))) {
                    ultimoDigito = linha.charAt(i);
                    break;
                }
            }

            // Se encontrar os dois dígitos, soma
            if (primeiroDigito != null && ultimoDigito != null) {
                somaParcial += Integer.parseInt("" + primeiroDigito + ultimoDigito);
            }
        }
        // Armazena a soma parcial no resultado
        resultado[indice] = somaParcial;

        // Conta uma execução finalizada no latch
        latch.countDown();
    }
}

