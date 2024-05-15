package srbn.graphpi.BackEnd.GManagement;

import android.content.Context;
import android.graphics.Color;
import android.service.autofill.Dataset;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import srbn.graphpi.BackEnd.DomainObjs.Errors.*;
import srbn.graphpi.BackEnd.DomainObjs.Graphs.*;
import srbn.graphpi.BackEnd.DomainObjs.Sentences.Sentence;
import srbn.graphpi.BackEnd.DomainObjs.SymTable;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.ScatterChart;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GenerateChart implements Serializable {

    private LinearLayout layout;
    private ArrayList<Graph> graphs;
    private ArrayList<ErrorP> errors;
    private Context context;

    private ArrayList<Chart> generatedCharts;

    public GenerateChart(ArrayList<Graph> graphs) {
        this.graphs = graphs;
        this.generatedCharts = new ArrayList<>();
    }

    public void createCharts() {
        for (Graph graph : graphs) {
            layout.addView(new TextView(context));
            createChart(graph);
        }
    }

    public void createChart(Graph graph) {
        switch (graph.getType()) {
            case 1:
                assert graph instanceof BarsGraph;
                layout.addView(createChartBar((BarsGraph) graph), 800, 800);
                break;
            case 2:
                assert graph instanceof PlotGraph;
                layout.addView(createChartPlot((PlotGraph) graph), 800, 800);
                break;
            case 3:
                assert graph instanceof LinealGraph;
                layout.addView(createChartLineal((LinealGraph) graph), 800, 800);
                break;
            case 4:
                assert graph instanceof PieGraph;
                layout.addView(createChartPie((PieGraph) graph), 800, 800);
                break;
            default:
                System.out.println("Graph type not found");
                errors.add(new ErrorP("Graph type not found, " + graph.getData()));
                break;
        }
    }

    private ScatterChart createChartPlot(PlotGraph graph) {
        ScatterChart scatterChart = new ScatterChart(context);
        ArrayList<IScatterDataSet> dataSets = new ArrayList<>();
        int j = 0;

        for (DataGraph data : graph.getData()) {
            j = (j > 4) ? 0 : j;
            ArrayList<Entry> entries = new ArrayList<>();

            entries.add(new Entry(data.getX(), data.getY()));
            ScatterDataSet dataSet = new ScatterDataSet(entries, data.getName());
            if (data.getColor() != null) {
                dataSet.setColor(Color.parseColor(data.getColor()));
            } else {
                dataSet.setColor(ColorTemplate.COLORFUL_COLORS[j]);
            }
            if (data.getSize() != 0) {
                dataSet.setScatterShapeHoleRadius(data.getSize());
            }
            dataSets.add(dataSet);
            j++;
        }


        scatterChart.setData(new ScatterData(dataSets));

        scatterChart.animateY(3000);
        scatterChart.invalidate();
        this.generatedCharts.add(scatterChart);
        return scatterChart;

    }

    private LineChart createChartLineal(LinealGraph graph) {
        int i = 0;

        LineChart lineChart = new LineChart(context);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        for (LinealData ld : graph.getLinealData()) {
            ArrayList<Entry> entries = new ArrayList<>();

            for (DataGraph data : ld.getData()) {
                entries.add(new Entry(data.getX(), data.getY()));
            }

            LineDataSet dataSet = new LineDataSet(entries, ld.getName());

            if (ld.getColor() != null) {
                dataSet.setColor(Color.parseColor(ld.getColor()));
            } else {
                i = (i > 4) ? 0 : i;
                dataSet.setColor(ColorTemplate.COLORFUL_COLORS[i]);
                i++;
            }
            if (ld.getLine() != null) {
                dataSet.setDrawCircles(false);
            }

            dataSets.add(dataSet);
        }

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        if (graph.getChart().getTittle() != null) {
            Description description = new Description();
            description.setText(graph.getChart().getTittle());
            lineChart.setDescription(description);
        } else {
            Description description = new Description();
            description.setText("Grafico sin titulo");
            lineChart.setDescription(description);
        }
        lineChart.animateY(3000);
        lineChart.invalidate();
        this.generatedCharts.add(lineChart);
        return lineChart;
    }

    private PieChart createChartPie(PieGraph graph) {
        PieChart pieChart = new PieChart(context);
        ArrayList<PieEntry> entries = new ArrayList<>();
        PieDataSet dataSet = null;

        for (DataGraph data : graph.getData()) {
            entries.add(new PieEntry(data.getValue(), data.getName()));
        }

        if (graph.getChartGraph() == null) {
            dataSet = new PieDataSet(entries, "Grafico sin titulo");
            dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        } else {
            dataSet = new PieDataSet(entries, graph.getChartGraph().getTittle());
            ArrayList<Integer> colors = new ArrayList<>();
            for (DataGraph data : graph.getData()) {
                colors.add(Color.parseColor(data.getColor()));
            }
            dataSet.setColors(colors);
        }

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        pieChart.animateY(3000);
        this.generatedCharts.add(pieChart);
        return pieChart;
    }

    private BarChart createChartBar(BarsGraph graph) {
        BarChart barChart = new BarChart(context);
        int i = 0;
        int j = 0;
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        for (DataGraph data : graph.getData()) {
            i = (i > 4) ? 0 : i;
            ArrayList<BarEntry> entries = new ArrayList<>();

            entries.add(new BarEntry((float) j, data.getValue()));
            BarDataSet dataSet = new BarDataSet(entries, data.getName());
            dataSet.setColor(ColorTemplate.COLORFUL_COLORS[i]);
            dataSets.add(dataSet);
            i++;
            j++;
        }

        barChart.setData(new BarData(dataSets));

        barChart.animateY(3000);
        barChart.invalidate();

        this.generatedCharts.add(barChart);
        return barChart;
    }

    public void setupGenerator(Context context, LinearLayout layout, ArrayList<ErrorP> errors) {
        this.context = context;
        this.layout = layout;
        this.errors = errors;
    }

    public ArrayList<ErrorP> getErrors() {
        return errors;
    }

    public ArrayList<Chart> getGeneratedCharts() {
        return generatedCharts;
    }
}
