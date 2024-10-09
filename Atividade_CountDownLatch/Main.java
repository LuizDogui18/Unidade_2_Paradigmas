import Model.CalibracaoManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String caminhoDoArquivo = "C:\\Users\\DELL\\OneDrive\\Área de Trabalho\\Atv1Uni2\\new_calibration_text.txt";
        int numThreads = 8; // Número de threads

        try {
            if (new java.io.File(caminhoDoArquivo).exists()) {
                System.out.println("Arquivo encontrado!");

                // Cria o gerenciador de calibração
                CalibracaoManager calibracaoManager = new CalibracaoManager(caminhoDoArquivo, numThreads);

                // Calcula a soma total
                int somaTotal = calibracaoManager.calcularValoresDeCalibracao();
                System.out.println("Soma total: " + somaTotal);
            } else {
                System.out.println("Arquivo não encontrado!");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
