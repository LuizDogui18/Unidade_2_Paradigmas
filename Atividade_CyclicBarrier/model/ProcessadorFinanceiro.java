package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ProcessadorFinanceiro implements Runnable {

    private String nomeArquivo;
    private AgregadorFinanceiro agregador;
    private CyclicBarrier barreira;

    public ProcessadorFinanceiro(String nomeArquivo, AgregadorFinanceiro agregador, CyclicBarrier barreira) {
        this.nomeArquivo = nomeArquivo;
        this.agregador = agregador;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            boolean isPrimeiraLinha = true; // Para ignorar cabeçalhos

            while ((linha = bufferedReader.readLine()) != null) {
                // Ignorar a primeira linha se for um cabeçalho
                if (isPrimeiraLinha) {
                    isPrimeiraLinha = false;
                    continue; // Pula a primeira linha
                }

                String[] partes = linha.split(",");

                // Certifique-se de que a linha tem pelo menos dois campos para evitar erros
                if (partes.length < 2 || partes[1].trim().isEmpty()) {
                    System.out.println("Linha inválida ou vazia encontrada, ignorando...");
                    continue; // Ignora linhas inválidas ou vazias
                }

                String data = partes[0];
                String valorStr = partes[1].replace("\"", ""); // Remove as aspas

                // Tente converter o valor em double
                try {
                    double valor = Double.parseDouble(valorStr);
                    System.out.println("Adicionando valor: " + valor + " para a data: " + data); // Depuração
                    agregador.adicionarRegistro(data, valor); // Adiciona o valor agregado
                } catch (NumberFormatException e) {
                    System.out.println("Erro ao converter valor: " + valorStr);
                    continue; // Ignora linhas com valor inválido
                }
            }

            barreira.await(); // Espera pelos outros threads
        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
