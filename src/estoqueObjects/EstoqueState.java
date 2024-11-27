package estoqueObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A classe KnapsackState representa um estado de seleção de itens no problema da mochila.
 * Cada estado armazena os itens selecionados, bem como o volume total e o valor total desses itens.
 * Esta classe é usada pelo algoritmo A* para avaliar diferentes combinações de itens e encontrar a solução ótima.
 */
public class EstoqueState {
    /**
     * Lista de itens selecionados no estado atual.
     */
    public final List<Item> selectedItems;

    /**
     * Volume total dos itens selecionados no estado atual.
     */
    protected double totalVolume;

    /**
     * Valor total dos itens selecionados no estado atual.
     */
    protected double totalValue;

    /**
     * Construtor padrão que inicializa o estado com uma lista vazia de itens,
     * e define o volume e valor total como 0.
     */
    public EstoqueState() {
        this.selectedItems = new ArrayList<>();
        this.totalVolume = 0.0;
        this.totalValue = 0.0;
    }

    /**
     * Construtor que inicializa o estado com uma lista específica de itens.
     * Calcula automaticamente o volume total e o valor total com base nos itens fornecidos.
     *
     * @param selectedItems A lista de itens a ser usada para inicializar o estado.
     */
    public EstoqueState(List<Item> selectedItems) {
        this.selectedItems = new ArrayList<>(selectedItems);
        for (Item item : selectedItems) {
            this.totalVolume += item.getVolume();
            this.totalValue += item.getValue();
        }
    }

    /**
     * Adiciona um item ao estado atual, atualizando o volume e o valor total.
     *
     * @param item O item a ser adicionado.
     */
    public void addItem(Item item) {
        selectedItems.add(item);
        totalVolume += item.getVolume();
        totalValue += item.getValue();
    }

    /**
     * Retorna o volume total dos itens selecionados no estado atual.
     *
     * @return O volume total dos itens selecionados.
     */
    public double getTotalVolume() {
        return totalVolume;
    }

    /**
     * Retorna o valor total dos itens selecionados no estado atual.
     *
     * @return O valor total dos itens selecionados.
     */
    public double getTotalValue() {
        return totalValue;
    }

    /**
     * Calcula a heurística para o estado atual, que representa a diferença entre
     * a capacidade da mochila e o volume total dos itens selecionados.
     * A heurística é usada para priorizar estados que estão mais próximos
     * da capacidade da mochila sem ultrapassá-la.
     *
     * @param capacity A capacidade máxima da mochila.
     * @return A heurística para o estado atual (diferença entre capacidade e volume total).
     */
    public double heuristic(double capacity) {
        return capacity - totalVolume;
    }

    @Override
    public String toString() {
        return "KnapsackState{" +
                "totalVolume=" + totalVolume +
                ", totalValue=" + totalValue +
                '}';
    }
}
