package Module_7.Homework7.FruitCorporation.Sell;

import Module_7.Homework7.FruitCorporation.Type;


public class Client {
    private String name;
    private Type type;
    private int count;

    public Client(String name, Type type, int count) {
        this.name = name;
        this.type = type;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", count=" + count +
                '}';
    }
}
