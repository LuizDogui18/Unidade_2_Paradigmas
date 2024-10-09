package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoProcessor {

    private String caminhoDoArquivo;

    public ArquivoProcessor(String caminhoDoArquivo) {
        this.caminhoDoArquivo = caminhoDoArquivo;
    }

    // Método para ler o arquivo e retornar uma lista com todas as linhas
    public List<String> lerLinhas() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(caminhoDoArquivo));
        List<String> linhas = new ArrayList<>();
        String linha;

        // Lê todas as linhas do arquivo e armazena na lista
        while ((linha = reader.readLine()) != null) {
            linhas.add(linha);
        }
        reader.close();
        return linhas;
    }

    // Método para dividir as linhas em blocos
    public List<String[]> dividirLinhasEmBlocos(List<String> linhas, int numBlocos) {
        int tamanhoDoBloco = linhas.size() / numBlocos;
        List<String[]> blocos = new ArrayList<>();

        for (int i = 0; i < numBlocos; i++) {
            int inicio = i * tamanhoDoBloco;
            int fim = (i == numBlocos - 1) ? linhas.size() : (i + 1) * tamanhoDoBloco;
            String[] bloco = linhas.subList(inicio, fim).toArray(new String[0]);
            blocos.add(bloco);
        }

        return blocos;
    }
}

