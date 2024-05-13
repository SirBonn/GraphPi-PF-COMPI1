package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.io.Serializable;
import java.util.ArrayList;

public class Graph implements Serializable {
    private ArrayList<DataGraph> data;
    private int type;

    public Graph(ArrayList<DataGraph> data) {
        this.data = data;
    }

    public Graph(int type) {
        this.type = type;
    }

    public Graph(ArrayList<DataGraph> data, int type) {
        this.data = data;
        this.type = type;
    }

    public ArrayList<DataGraph> getData() {
        return data;
    }

    public void setData(ArrayList<DataGraph> data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
