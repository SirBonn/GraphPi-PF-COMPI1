package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;

public class DoSentence extends Sentence implements java.io.Serializable{

    private Conditional conditional;

    public DoSentence(ArrayList<Graph> graphs, Conditional conditional) {
        super(graphs, 3);
        this.conditional = conditional;
    }

    public Conditional getConditional() {
        return conditional;
    }

    public void setConditional(Conditional conditional) {
        this.conditional = conditional;
    }
}
