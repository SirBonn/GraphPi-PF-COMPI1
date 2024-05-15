package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;
import srbn.graphpi.BackEnd.DomainObjs.SymTable;

public class DoSentence extends Sentence implements java.io.Serializable{

    private Conditional conditional;
    private SymTable symTable;

    public DoSentence(ArrayList<Graph> graphs, Conditional conditional,  SymTable s, SymTable s2) {
        super(graphs, 3);
        this.conditional = conditional;
        this.symTable = mergeSymTables(s, s2);
    }

    private SymTable mergeSymTables(SymTable s, SymTable s2) {

        SymTable newSymTable = new SymTable();
        if(s != null && s2 != null) {
            newSymTable.getSymTable().putAll(s2.getSymTable());
            newSymTable.getSymTable().putAll(s.getSymTable());
            return newSymTable;
        } else {
            if(s != null) {
                newSymTable.getSymTable().putAll(s.getSymTable());
            }
            if(s2 != null) {
                newSymTable.getSymTable().putAll(s2.getSymTable());
            }
        }
        return newSymTable;
    }
    public Conditional getConditional() {
        return conditional;
    }

    public void setConditional(Conditional conditional) {
        this.conditional = conditional;
    }

    public SymTable getSymTable() {
        return symTable;
    }
}
