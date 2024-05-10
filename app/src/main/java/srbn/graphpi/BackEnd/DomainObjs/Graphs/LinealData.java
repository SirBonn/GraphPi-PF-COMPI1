package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.util.ArrayList;

public class LinealData {

    private String name;
    private String line;
    private String color;
    ArrayList<DataGraph> data;

    public LinealData(String name, ArrayList<DataGraph> data) {
        this.name = name;
        this.data = data;
    }
    public LinealData(String name, String color, String line, ArrayList<DataGraph> data) {
        this.name = name;
        this.color = color;
        this.line = line;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
