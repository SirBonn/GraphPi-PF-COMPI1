package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;

public class WhileSentence extends Sentence implements java.io.Serializable{

    private Conditional conditional;

    public WhileSentence(ArrayList<Graph> graphs, Conditional conditional){
        super(graphs, 4);
        this.conditional = conditional;
    }

    public Conditional getConditional() {
        return conditional;
    }

    public void setConditional(Conditional conditional) {
        this.conditional = conditional;
    }
}
