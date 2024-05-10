package srbn.graphpi.BackEnd.DomainObjs.Graphs;

public class DataGraph {

    private String name;
    private int value;
    private String color;
    private int size;
    private int x;
    private int y;


    public DataGraph(String name, int value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public DataGraph(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public DataGraph(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public DataGraph(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public DataGraph(int x, int y, int size, String color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
