package Atividade_1_Nota_2_Paradigmas.main.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LeitorArquivo {

    public static List<String> lerLinhas(String caminhoArquivo) throws IOException {
        return Files.readAllLines(Paths.get(caminhoArquivo));
    }
}
