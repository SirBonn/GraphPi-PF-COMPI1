package srbn.graphpi.BackEnd.DomainObjs.Graphs;

import java.io.Serializable;
import java.util.ArrayList;

public class PieGraph extends Graph implements Serializable {

    private ChartGraph chartGraph;
    private String icon;
    private String desc;
    private String link;

    public PieGraph(ArrayList<DataGraph> data) {
        super(data, 4);
    }

    public PieGraph(ArrayList<DataGraph> data, ChartGraph chartGraph) {
        super(data, 4);
        this.chartGraph = chartGraph;
    }

    //uses to create a information Card
    public PieGraph(ArrayList<DataGraph> data, String desc, String icon, String link) {
        super(data, 4);
        this.icon = icon;
        this.desc = desc;
        this.link = link;
    }

    public PieGraph(ArrayList<DataGraph> data, String desc){
        super(data, 4);
        this.desc = desc;
    }

    public ChartGraph getChartGraph() {
        return chartGraph;
    }

    public void setChartGraph(ChartGraph chartGraph) {
        this.chartGraph = chartGraph;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
