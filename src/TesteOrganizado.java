import estoqueObjects.*;

import java.util.ArrayList;
import java.util.List;

public class TesteOrganizado {
    public static void main(String[] args) {
        // Projeto de numero #4
        Double capacity = 50.0;

        List<Item> items = new ArrayList<>();

        int opt = 1;
        while(1 == 1){
            Scanf.writeln("1. Adicionar produto");
            Scanf.writeln("2. Remover produto");
            Scanf.writeln("3. Mostrar produtos");
            Scanf.writeln("4. Mostrar melhor estoque");
            Scanf.writeln("5. Alterar volume estoque");
            Scanf.writeln("6. Sair");
            Scanf.writeln("7. Gerar produtos aleatorios");
            Scanf.write("Escolha uma opcao: ");

            String check = "";
            do{
                check = Scanf.getText();
                if(check.length() != 0) opt = Integer.parseInt(check.trim());
            } while(check.length() == 0);

            Scanf.write("\n");
            switch(opt){
                case 1:
                    Scanf.write("Nome produto: ");
                    String name = Scanf.getText();
                    Scanf.write("Valor do produto: ");
                    Double value = Scanf.getDouble();
                    Scanf.write("Volume do produto (metros): ");
                    Double volume = Scanf.getDouble();

                    items.add(new Item(highterIndex(items) + 1, volume, value, name));
                    Scanf.write("\nItem adicionado com sucesso!");
                    break;
                case 2:
                    if(items.size() == 0){
                        Scanf.writeln("Lista sem itens...");
                        break;
                    }
                    Scanf.write("Id do item que deseja remover: ");
                    int i, id = Scanf.getInt();

                    for (i = 0; i < items.size(); i++) {
                        if(items.get(i).getId() == id) {
                            items.remove(i);
                            Scanf.write("\nItem removido com sucesso!");
                            i = -1;
                            break;
                        }
                    }
                    if(i != -1) Scanf.write("\nItem não encontrado...");
                    break;
                case 3:
                    if(items.size() == 0){
                        Scanf.writeln("Lista sem itens...");
                        break;
                    }
                    Scanf.writeln("[ITENS LISTA]");
                    items.forEach(item -> System.out.printf("Id: %s, Nome: %s, Volume: %.2f, Valor: %.2f%n", item.getId().toString(), item.getName(), item.getVolume(), item.getValue()));
                    break;
                case 4:
                    if(items.size() == 0){
                        Scanf.writeln("Lista sem itens...");
                        break;
                    }
                    Estoque estoque = new Estoque(capacity, items);
                    EstoqueSolver solver = new EstoqueSolver();
                    EstoqueState bestState = solver.solve(estoque);

                    // Exibir os resultados
                    Scanf.writeln("[ITENS ESTOQUE]");
                    for (Item item : bestState.selectedItems) {
                        System.out.printf("Id: %s, Nome: %s, Volume: %.2f, Valor: %.2f%n", item.getId().toString(), item.getName(), item.getVolume(), item.getValue());
                    }
                    System.out.println("");
                    System.out.printf("Volume máximo: %.2fm%n", capacity);
                    System.out.printf("Volume total: %.2fm%n", bestState.getTotalVolume());
                    System.out.printf("Valor total: R$%.2f%n", bestState.getTotalValue());
                    break;
                case 5:
                    Scanf.writeln("Capacidade atual: " + capacity);
                    Scanf.write("Nova capacidade de estoque: ");
                    capacity = Scanf.getDouble();
                    break;
                case 6:
                    Scanf.writeln("Sistema finalizado...");
                    return;
                case 7:
                    items = Main.generateRandomItems(10, 5.0, 50.0, 0.5, 300.0);;
                    Scanf.write("Itens gerados com sucesso!");
                    break;
            }
            Scanf.write("\n\n");
            Scanf.scanner.nextLine();
        }
    }

    public static int highterIndex(List<Item> items){
        int max = 0;
        for (Item item : items) {
            if(max < item.getId()) max = item.getId();
        }
        return max;
    }
}