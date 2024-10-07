package Atividade_1_Nota_2_Paradigmas.main.Model;

import java.util.List;

public class CalcularSoma implements Runnable {
    private List<String> linhas;
    private int[] resultado;
    private int indice;

    public CalcularSoma(List<String> linhas, int[] resultado, int indice) {
        this.linhas = linhas;
        this.resultado = resultado;
        this.indice = indice;
    }

    @Override
    public void run() {
        int somaParcial = 0;
        for (String linha : linhas) {
            Character primeiroDigito = null;
            Character ultimoDigito = null;

            // Pega o primeiro dígito
            for (char ch : linha.toCharArray()) {
                if (Character.isDigit(ch)) {
                    primeiroDigito = ch;
                    break;
                }
            }

            // Pega o último dígito
            for (int i = linha.length() - 1; i >= 0; i--) {
                char ch = linha.charAt(i);
                if (Character.isDigit(ch)) {
                    ultimoDigito = ch;
                    break;
                }
            }

            if (primeiroDigito != null && ultimoDigito != null) {
                somaParcial += Integer.parseInt("" + primeiroDigito + ultimoDigito);
            }
        }
        resultado[indice] = somaParcial;
    }
}
