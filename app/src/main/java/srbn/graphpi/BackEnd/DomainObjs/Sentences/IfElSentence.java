package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;

public class IfElSentence extends Sentence implements java.io.Serializable{

    private Conditional condition;
    private ArrayList<Graph> elseGraphs;

    public IfElSentence(ArrayList<Graph> graps, ArrayList<Graph> elseGraps, Conditional condition){
        super(graps, 2);
        this.elseGraphs = elseGraps;
        this.condition = condition;
    }

    public IfElSentence(ArrayList<Graph> graphs,Conditional condition) {
        super(graphs, 2);
        this.condition = condition;
    }

    public Conditional getCondition() {
        return condition;
    }

    public ArrayList<Graph> getElseGraphs() {
        return elseGraphs;
    }

}
