package org.ybygjy.basic.basic.tenum;

/**
 * Created by leye on 2017/4/7.
 */
public enum Color {
    RED("红色", 1),GREEN("绿色", 2),BLANK("白色", 3),YELLOW("黄色", 4);
    private String name;
    private int index;
    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public static String getName(int index) {
        for (Color color : Color.values()) {
            if (color.getIndex() == index) {
                return color.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
    public String toString() {
        return this.index + "_" + this.name;
    }
    public static void main(String[] args) {
        System.out.println("Hello Enumeration.");
        for (Food.Coffee coffee : Food.Coffee.values()) {
            System.out.println(coffee);
        }
    }
}
enum Signal {
    GREEN,YELLOW,RED
}
class TrafficLight {
    Signal color = Signal.GREEN;
    public void change() {
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case YELLOW:
                color = Signal.RED;
            case GREEN:
                color = Signal.YELLOW;
                break;
        }
    }
}
interface Behaviour {
    void print();
    String getInfo();
}
enum BehaviourColor implements Behaviour {
    RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLOW("黄色", 4);
    private String name;
    private int index;
    private BehaviourColor(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public void print() {
        System.out.println(this.name + "_" + this.index);
    }
    public String getInfo() {
        return this.name + "_" + this.index;
    }
}

interface Food {
    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, LATTE, CAPPUCCINO
    }
    enum Dessert implements Food {
        FRUIT, CAKE, GELATO
    }
}
