package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;

public class ForSentence extends Sentence implements java.io.Serializable {

    private String idVar;
    private int init;
    private String logicOp;
    private String forCondition;
    private String passOp;

    public ForSentence(ArrayList<Graph> graphs, String idVar, int init, String logicOp, String forCondition, String passOp) {
        super(graphs, 1);
        this.idVar = idVar;
        this.init = init;
        this.logicOp = logicOp;
        this.forCondition = forCondition;
        this.passOp = passOp;
    }

    public String getIdVar() {
        return idVar;
    }

    public void setIdVar(String idVar) {
        this.idVar = idVar;
    }

    public String getLogicOp() {
        return logicOp;
    }

    public void setLogicOp(String logicOp) {
        this.logicOp = logicOp;
    }

    public String getForCondition() {
        return forCondition;
    }

    public void setForCondition(String forCondition) {
        this.forCondition = forCondition;
    }

    public String getPassOp() {
        return passOp;
    }

    public void setPassOp(String passOp) {
        this.passOp = passOp;
    }
}
