package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.io.Serializable;

public class DataGraph implements Serializable {

    private String name;
    private Float value;
    private String color;
    private Float size;
    private Float x;
    private Float y;


    public DataGraph(String name, Float value, String color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public DataGraph(String name, Float value) {
        this.name = name;
        this.value = value;
    }

    public DataGraph(Float x, Float y) {
        this.x = x;
        this.y = y;
    }

    public DataGraph(Float x, Float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public DataGraph(Float x, Float y, Float size, String color) {
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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }
}
