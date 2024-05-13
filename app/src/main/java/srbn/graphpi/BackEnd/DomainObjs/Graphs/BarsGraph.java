package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.io.Serializable;
import java.util.ArrayList;
public class BarsGraph extends Graph implements Serializable {

    private ChartGraph chartGraph;

    public BarsGraph(ArrayList<DataGraph> data) {
        super(data, 1);
    }

    public BarsGraph(ArrayList<DataGraph> data, ChartGraph chartGraph) {
        super(data, 1);
        this.chartGraph = chartGraph;
    }

    public ChartGraph getChartGraph() {
        return chartGraph;
    }

    public void setChartGraph(ChartGraph chartGraph) {
        this.chartGraph = chartGraph;
    }
}
