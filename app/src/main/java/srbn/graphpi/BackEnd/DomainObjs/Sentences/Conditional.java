package srbn.graphpi.BackEnd.DomainObjs.Sentences;

public class Conditional implements java.io.Serializable{

    private String comparator;
    private String logicalSym;

    private String comparable;

    public Conditional(String comparator, String logicalSym, String comparable){
        this.comparator = comparator;
        this.logicalSym = logicalSym;
        this.comparable = comparable;
    }

    public String getComparator() {
        return comparator;
    }

    public String getLogicalSym() {
        return logicalSym;
    }

    public String getComparable() {
        return comparable;
    }

}
