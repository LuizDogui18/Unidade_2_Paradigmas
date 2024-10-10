package model;

import java.util.HashMap;
import java.util.Map;

public class AgregadorFinanceiro {

    private Map<String, Double> registrosAgregados = new HashMap<>();
    private double total = 0;

    public void adicionarRegistro(String data, double valor) {
        // Verifica se já existe um registro para a data, se sim, soma o valor
        registrosAgregados.put(data, registrosAgregados.getOrDefault(data, 0.0) + valor);
        total += valor; // Soma o valor total
    }

    public Map<String, Double> getRegistrosAgregados() {
        return registrosAgregados;
    }

    public double getTotal() {
        return total;
    }
}
