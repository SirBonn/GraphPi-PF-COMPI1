package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;

public class Sentence implements java.io.Serializable{

    private ArrayList<Graph> graphs;
    private int type;

    public Sentence(ArrayList<Graph> graphs, int type){
        this.graphs = graphs;
        this.type = type;
    }

    public ArrayList<Graph> getGraphs() {
        return graphs;
    }

    public int getType() {
        return type;
    }

}
