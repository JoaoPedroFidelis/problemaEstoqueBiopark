import estoqueObjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Double capacidade_estoque = 50.0;
        boolean gerar_itens = false;

        List<Item> items = new ArrayList<>();
        if(gerar_itens == true) {
            // Gerar itens aleatórios
            int num_itens = 10;
            items = generateRandomItems(num_itens, 5.0, 50.0, 0.5, 300.0);
            System.out.println("[" + num_itens + " PRODUTOS GERADOS]");
        } else {
            // Itens normais
            items.add(new Item(1, 20.0, 300.0, "xbox 360"));
            items.add(new Item(2, 8.0, 150.0, "playstation 2"));
            items.add(new Item(3, 8.0, 190.0, "nintendo switch"));
            items.add(new Item(4, 7.0, 100.0, "dreamcast"));
            items.add(new Item(5, 8.0, 50.0, "polystation"));
            items.add(new Item(6, 5.0, 105.0, "megadrive"));
            items.add(new Item(7, 15.0, 55.0, "atari"));
            items.add(new Item(1, 20.0, 300.0, "xbox 360"));
            System.out.println("[PRODUTOS CADASTRADOS]");
        }

        items.forEach(item -> item.apresentar());
        System.out.println("");

        // Criar uma mochila com a capacidade definida e a lista de itens gerados
        Estoque estoque = new Estoque(capacidade_estoque, items);

        // Resolver o problema da estoque com o algoritimo da mochila usando o solver
        EstoqueSolver solver = new EstoqueSolver();
        EstoqueState bestState = solver.solve(estoque);
        List<Item> melhores_itens = bestState.selectedItems;

        // Exibir os resultados
        System.out.println("[ITENS ESTOQUE]");
        for (Item item : melhores_itens) {
            item.apresentar();
        }
        System.out.println("");
        System.out.printf("Volume máximo: %.2fm%n", capacidade_estoque);
        System.out.printf("Volume total: %.2fm%n", bestState.getTotalVolume());
        System.out.printf("Valor total: R$%.2f%n", bestState.getTotalValue());
    }

    public static List<Item> generateRandomItems(int count, double minVolume, double maxVolume, double minValue, double maxValue) {
        List<Item> items = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {

            double volume = minVolume + (maxVolume - minVolume) * random.nextDouble();
            double value = minValue + (maxValue - minValue) * random.nextDouble();
            items.add(new Item(i+1, volume, value, "produto#" + (i+1)));
        }

        return items;
    }
}
