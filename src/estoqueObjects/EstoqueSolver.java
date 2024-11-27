package estoqueObjects;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EstoqueSolver {
    // Define um limite para a profundidade e para o tamanho da fila de prioridade para evitar o consumo excessivo de memória.
    private static final int MAX_QUEUE_SIZE = 1000;  // Limite de tamanho da fila de prioridade
    private static final int MAX_DEPTH = 50;         // Limite de profundidade de expansão

    /**
     * Resolve o problema da mochila utilizando uma versão otimizada do Algoritmo A* com limite de profundidade e controle de memória.
     *
     * @param estoque A instância de Knapsack que contém a capacidade máxima e a lista de itens disponíveis.
     * @return O estado da mochila (KnapsackState) que representa a melhor solução encontrada, contendo os itens selecionados.
     */
    public EstoqueState solve(Estoque estoque) {
        List<Item> items = estoque.getItems();
        double capacity = estoque.getCapacity();

        // Fila de prioridade para armazenar estados
        PriorityQueue<EstoqueState> openList = new PriorityQueue<>(Comparator.comparingDouble(this::calculatePriority).reversed());

        EstoqueState initialState = new EstoqueState();
        openList.add(initialState);
        EstoqueState bestState = initialState;

        while (!openList.isEmpty() && openList.size() <= MAX_QUEUE_SIZE) {
            EstoqueState current = openList.poll();

            // Atualiza o melhor estado se necessário
            if (current.getTotalValue() > bestState.getTotalValue() && current.getTotalVolume() <= capacity) {
                bestState = current;
            }

            // Expande o estado atual se não tiver atingido o limite de profundidade
            if (current.selectedItems.size() < MAX_DEPTH) {
                for (Item item : items) {
                    if (!current.selectedItems.contains(item)) {
                        EstoqueState newState = new EstoqueState(current.selectedItems);
                        newState.addItem(item);

                        // Adiciona o novo estado à fila apenas se ele respeitar o limite de capacidade
                        // e tiver potencial para superar a melhor solução atual
                        if (newState.getTotalVolume() <= capacity && calculateBound(newState, items, capacity) > bestState.getTotalValue()) {
                            openList.add(newState);

                            // Limita o tamanho da fila de prioridade para evitar estouro de memória
                            if (openList.size() > MAX_QUEUE_SIZE) {
                                openList.poll();
                            }
                        }
                    }
                }
            }
        }

        return bestState;
    }

    /**
     * Calcula a prioridade de um estado com base no valor total e na heurística.
     *
     * @param state O estado da mochila.
     * @return A prioridade calculada para o estado.
     */
    private double calculatePriority(EstoqueState state) {
        return state.getTotalValue() / (state.getTotalVolume() + 1e-5); // Evita divisão por zero
    }

    /**
     * Calcula um limite superior (bound) para o valor de um estado usando uma aproximação fracionária.
     *
     * @param state O estado atual da mochila.
     * @param items A lista completa de itens.
     * @param capacity A capacidade total da mochila.
     * @return O valor máximo potencial para o estado.
     */
    private double calculateBound(EstoqueState state, List<Item> items, double capacity) {
        double bound = state.getTotalValue();
        double remainingCapacity = capacity - state.getTotalVolume();

        for (Item item : items) {
            if (!state.selectedItems.contains(item)) {
                if (item.getVolume() <= remainingCapacity) {
                    bound += item.getValue();
                    remainingCapacity -= item.getVolume();
                } else {
                    bound += item.getValue() * (remainingCapacity / item.getVolume());
                    break;
                }
            }
        }

        return bound;
    }

}
