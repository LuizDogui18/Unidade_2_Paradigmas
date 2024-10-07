package Atividade_1_Nota_2_Paradigmas.main;

import java.io.File;
import java.io.IOException;
import Atividade_1_Nota_2_Paradigmas.main.Model.*;

public class Main {

    public static void main(String[] args) {
        String caminhoArquivo = "C:\\Users\\DELL\\OneDrive\\Área de Trabalho\\Atv1Uni2\\new_calibration_text.txt";
        File arquivo = new File(caminhoArquivo);

        if (arquivo.exists()) {
            System.out.println("Arquivo encontrado!");
            GerenciadorCalibracao gerenciador = new GerenciadorCalibracao();
            try {
                int somaTotal = gerenciador.calcularValoresDeCalibracao(caminhoArquivo);
                System.out.println("Soma total: " + somaTotal);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Arquivo não encontrado!");
        }
    }
}

