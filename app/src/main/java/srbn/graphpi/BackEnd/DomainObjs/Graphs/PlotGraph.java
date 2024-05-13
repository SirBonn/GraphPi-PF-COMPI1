package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.io.Serializable;
import java.util.ArrayList;

public class PlotGraph extends Graph implements Serializable {

    private ChartGraph chartGraph;
    public PlotGraph(ArrayList<DataGraph> data, ChartGraph chartGraph) {
        super(data, 2);
        this.chartGraph = chartGraph;
    }
    public PlotGraph(ArrayList<DataGraph> data) {
        super(data, 2);
    }

    public ChartGraph getChartGraph() {
        return chartGraph;
    }
}
