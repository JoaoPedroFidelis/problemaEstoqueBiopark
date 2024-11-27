package estoqueObjects;

public class Item {
    private Integer id;
    private Double volume;
    private Double value;
    private String name;

    public Item(Integer id, Double volume, Double value) {
        this.id = id;
        this.volume = volume;
        this.value = value;
    }
    public Item(Integer id, Double volume, Double value, String name) {
        this.id = id;
        this.volume = volume;
        this.value = value;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
    public Double getVolume() {
        return volume;
    }
    public Double getValue() {
        return value;
    }
    public String getName() {
        return name;
    }
    public void apresentar(){
        System.out.printf("Id:%d, Nome: %s, Volume: %.2fm, Valor: R$%.2f%n", id, name, volume, value);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id= " + id +
                ", name=" + name +
                ", volume=" + volume +
                ", value=" + value +
                '}';
    }
}
