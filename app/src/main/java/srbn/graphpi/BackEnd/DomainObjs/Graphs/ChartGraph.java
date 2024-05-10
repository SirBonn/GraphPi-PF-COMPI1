package srbn.graphpi.BackEnd.DomainObjs.Graphs;

public class ChartGraph {

    private String tittle;
    private String xLabel;
    private String yLabel;
    private String legendPos;
    public ChartGraph(String tittle, String xLabel, String yLabel) {
        this.tittle = tittle;
        this.xLabel = xLabel;
        this.yLabel = yLabel;
    }

    public ChartGraph(String tittle,String legendPos) {
        this.tittle = tittle;
        this.legendPos = legendPos;
    }

    public String getTittle() {
        return tittle;
    }

    public String getxLabel() {
        return xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }

    public String getLegendPos() {
        return legendPos;
    }
}
