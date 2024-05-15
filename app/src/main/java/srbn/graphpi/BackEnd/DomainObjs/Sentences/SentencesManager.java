package srbn.graphpi.BackEnd.DomainObjs.Sentences;

import java.util.ArrayList;

import srbn.graphpi.BackEnd.DomainObjs.Errors.ErrorP;
import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph;
import srbn.graphpi.BackEnd.DomainObjs.SymTable;
import srbn.graphpi.BackEnd.GManagement.GenerateChart;

public class SentencesManager {

    private ArrayList<Sentence> sentences;
    private SymTable symTable;

    private GenerateChart generateChart;

    public SentencesManager(ArrayList<Sentence> sentences, SymTable symTable, GenerateChart generateChart) {
        this.sentences = sentences;
        this.symTable = symTable;
        this.generateChart = generateChart;
    }

    public void evaluateSentence(Sentence sentence) {
        switch (sentence.getType()) {
            case 1:
                executeForSentence((ForSentence) sentence);
                break;
            case 2:
                executeIfElSentence((IfElSentence) sentence);
                break;
            case 3:
                executeDoSentence((DoSentence) sentence);
                break;
            case 4:
                executeWhileSentence((WhileSentence) sentence);
                break;
            default:
                break;
        }
    }

    public void executeSentences() {
        for (Sentence sentence : sentences) {
            evaluateSentence(sentence);
        }
    }

    public void executeForSentence(ForSentence forSentence) {

        String idVar = forSentence.getIdVar();
        String logicOp = forSentence.getLogicOp();
        int finalValue = getIntValue(forSentence.getForCondition());
        String passOp = forSentence.getPassOp();

        while (valuateCondition(logicOp, idVar, finalValue)) {
            for (Graph graph : forSentence.getGraphs()) {
                generateChart.createChart(graph);
            }
            updateValue(idVar, passOp);
        }
    }

    private boolean valuateCondition(Conditional condition){
        if(!condition.isNumericOp()){
            return valuateCondition(condition.getComparable(), condition.getComparator());
        }else{
            return valuateCondition(condition.getLogicalSym(), condition.getComparator(), getIntValue(condition.getComparable()));
        }
    }

    private void updateValue(String idVar, String passOp) {
        switch (passOp) {
            case "++":
                symTable.updateVar(idVar, getIntValue(idVar) + 1);
                break;
            case "--":
                symTable.updateVar(idVar, getIntValue(idVar) - 1);
                break;
            default:
                generateChart.getErrors().add(new ErrorP("Pass operation not found, " + passOp));
                break;
        }
    }

    private boolean valuateCondition(String logicOp, String idVar, int finalValue) {
        switch (logicOp) {
            case "<":
                return getIntValue(idVar) < finalValue;
            case ">":
                return getIntValue(idVar) > finalValue;
            case "<=":
                return getIntValue(idVar) <= finalValue;
            case ">=":
                return getIntValue(idVar) >= finalValue;
            case "==":
                return getIntValue(idVar) == finalValue;
            case "!=":
                return getIntValue(idVar) != finalValue;
            default:
                return false;
        }
    }

    private boolean valuateCondition(String comparable, String comparator) {
        return getStrValue(comparable).equals(comparator);
    }

    public void executeIfElSentence(IfElSentence ifElSentence) {
        if (valuateCondition(ifElSentence.getCondition())) {
            for (Graph graph : ifElSentence.getGraphs()) {
                generateChart.createChart(graph);
            }
        } else {
            for (Graph graph : ifElSentence.getElseGraphs()) {
                generateChart.createChart(graph);
            }
        }
    }

    public void executeDoSentence(DoSentence doSentence) {
        //TODO
    }

    public void executeWhileSentence(WhileSentence whileSentence) {
        //TODO
    }

    private String getStrValue(String var) {
        if (symTable.containsVar(var)) {
            return symTable.getVar(var).toString();
        } else {
            //add errorP, variable not found, value returned is ""
            generateChart.getErrors().add(new ErrorP("Variable not found, " + var));
            return "";
        }
    }

    private int getIntValue(String var) {

        if (symTable.containsVar(var)) {
            return Integer.parseInt(symTable.getVar(var).toString());
        } else {
            //add errorP, variable not found, value returned is 0
            try {
                return Integer.parseInt(var);
            } catch (NumberFormatException e) {
                generateChart.getErrors().add(new ErrorP("Variable not found, " + var));
            }
            return 0;
        }
    }

}
