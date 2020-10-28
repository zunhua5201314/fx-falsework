import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;

import java.util.ArrayList;

/**
 * @description:
 * @className: MyStackedAreaChart
 * @author: liwen
 * @date: 2020/9/9 16:42
 */
public class MyStackedAreaChart<X, Y> extends LineChart<X, Y> {
    private Line selectLine;
    private Region chartPlotBackground;
    private ObservableList<Circle> cirleList;
    private Tooltip selectorTooltip;


    public MyStackedAreaChart(Axis axis, Axis axis2) {
        super(axis, axis2);
        init();
        registerListeners();
    }

    public MyStackedAreaChart(Axis axis, Axis axis2, ObservableList data) {
        super(axis, axis2, data);
        init();
        registerListeners();
    }

    private void init() {

        cirleList = FXCollections.observableList(new ArrayList<>());

        for (int i = 0; i < getData().size(); i++) {
            // Add selector to chart
            Circle selector = new Circle();
            selector.getStyleClass().addAll("my-chart-symbol","my-chart-symbol"+i);
            cirleList.add(selector);
        }

        chartPlotBackground = getChartPlotBackground();

        selectLine = new Line();
        selectLine.setStroke(Color.RED);
        selectLine.setStrokeWidth(1f);
        selectorTooltip = new Tooltip("");
        Tooltip.install(selectLine, selectorTooltip);

        getChartChildren().add(selectLine);
        getChartChildren().addAll(cirleList);
    }

    public Region getChartPlotBackground() {
        if (null == chartPlotBackground) {
            for (Node node : lookupAll(".chart-plot-background")) {
                if (node instanceof Region) {
                    chartPlotBackground = (Region) node;
                    break;
                }
            }
        }
        return chartPlotBackground;
    }

    private void registerListeners() {

        setOnMouseMoved(event -> {
            final double EVENT_X = event.getX();
            final double EVENT_Y = event.getY();
            final double CHART_MIN_Y = chartPlotBackground.getBoundsInParent().getMinY();
            final double CHART_MAX_Y = chartPlotBackground.getBoundsInParent().getMaxY();

            final double shiftX = xSceneShift(chartPlotBackground);
            final double shiftY = ySceneShift(chartPlotBackground);
            double x = event.getSceneX() - shiftX;
            double y = event.getSceneY() - shiftY;
            X xValue = getXAxis().getValueForDisplay(x);

            double xx = getXAxis().getDisplayPosition(xValue) + shiftX - 5;

            if (!chartPlotBackground.getBoundsInParent().contains(EVENT_X, EVENT_Y) || xValue == null) {
                selectLine.setVisible(false);
                selectorTooltip.hide();
                return;
            }
            StringBuilder tooltipText = new StringBuilder(xValue.toString()).append("\n");

            VBox vBox = new VBox();
            vBox.setSpacing(5);
            for (int i = 0; i < getData().size(); i++) {
                Series<X, Y> series = getData().get(i);
                series.getNode().getStyle();
                Circle circle = cirleList.get(i);
                int finalI = i;
                series.getData().forEach(xyData -> {
                    if (xyData.getXValue() == xValue) {
                        HBox hBox = new HBox();
                        hBox.setSpacing(5);
                        Circle cir = new Circle();
                        cir.getStyleClass().add("my-chart-symbol" + finalI);
                        cir.setRadius(3);
                        cir.setStrokeWidth(6);
                        Label label=new Label(series.getName() + "：" + xyData.getYValue());
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().addAll(cir, label);
                        vBox.getChildren().add(hBox);
                        double xy = getYAxis().getDisplayPosition(xyData.getYValue())+shiftY-5 ;
                        circle.setCenterX(xx);
                        circle.setCenterY(xy);
                    }
                });

            }

            selectLine.setVisible(true);
            selectLine.setStartX(xx);
            selectLine.setEndX(xx);
            selectLine.setStartY(CHART_MIN_Y);
            selectLine.setEndY(CHART_MAX_Y);

            Point2D tooltipLocation = selectLine.localToScreen(event.getX(), event.getY());
            selectorTooltip.setGraphic(vBox);
            selectorTooltip.setX(tooltipLocation.getX());
            selectorTooltip.setY(tooltipLocation.getY());
            selectorTooltip.show(getScene().getWindow());

        });
    }

    /**
     * Returns an array of paths where the first entry represents the fill path
     * and the second entry represents the stroke path
     *
     * @param SERIES
     * @return an array of paths where [0] == FillPath and [1] == StrokePath
     */
    private Path[] getPaths(final Series<X, Y> SERIES) {
        if (!getData().contains(SERIES)) {
            return null;
        }

        Node seriesNode = SERIES.getNode();
        if (null == seriesNode) {
            return null;
        }

        Group seriesGroup = (Group) seriesNode;
        if (seriesGroup.getChildren().isEmpty() || seriesGroup.getChildren().size() < 2) {
            return null;
        }

        return new Path[]{ /* FillPath   */ (Path) (seriesGroup).getChildren().get(0),
                /* StrokePath */ (Path) (seriesGroup).getChildren().get(1)};
    }

    private double xSceneShift(Node node) {
        return node.getParent() == null ? 0 : node.getBoundsInParent().getMinX() + xSceneShift(node.getParent());
    }

    private double ySceneShift(Node node) {
        return node.getParent() == null ? 0 : node.getBoundsInParent().getMinY() + ySceneShift(node.getParent());
    }
}
