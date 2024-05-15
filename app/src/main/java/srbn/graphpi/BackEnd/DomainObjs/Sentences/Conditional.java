package srbn.graphpi.BackEnd.DomainObjs.Sentences;

public class Conditional implements java.io.Serializable{

    private String comparator;
    private String logicalSym;
    private String comparable;
    private boolean isNumericOp;

    public Conditional(String comparator, String logicalSym, String comparable, boolean isNumericOp) {
        this.comparator = comparator;
        this.logicalSym = logicalSym;
        this.comparable = comparable;
        this.isNumericOp = isNumericOp;
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

    public boolean isNumericOp() {
        return isNumericOp;
    }

}
