package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.util.ArrayList;

public class PieGraph extends Graph {

    private ChartGraph chartGraph;

    public PieGraph(ArrayList<DataGraph> data) {
        super(data, 4);
    }

    public PieGraph(ArrayList<DataGraph> data, ChartGraph chartGraph) {
        super(data, 4);
        this.chartGraph = chartGraph;
    }

    public ChartGraph getChartGraph() {
        return chartGraph;
    }

    public void setChartGraph(ChartGraph chartGraph) {
        this.chartGraph = chartGraph;
    }
}
