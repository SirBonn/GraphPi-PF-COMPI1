package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.util.ArrayList;

public class LinealGraph extends Graph {

    private ArrayList<LinealData> linealData;
    private ChartGraph chartGraph;

    public LinealGraph(ArrayList<LinealData> linealData, ChartGraph chartGraph) {
        super(3);
        this.linealData = linealData;
        this.chartGraph = chartGraph;
    }

    public LinealGraph(ArrayList<LinealData> linealData) {
        super(3);
        this.linealData = linealData;
    }

    public ArrayList<LinealData> getLinealData() {
        return linealData;
    }

    public void setLinealData(ArrayList<LinealData> linealData) {
        this.linealData = linealData;
    }

    public ChartGraph getChart() {
        return chartGraph;
    }

    public void setChart(ChartGraph chartGraph) {
        this.chartGraph = chartGraph;
    }

}
