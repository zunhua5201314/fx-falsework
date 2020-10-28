


import com.jfoenix.assets.JFoenixResources;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.stage.Stage;

import java.util.List;


/**
 * A sample that displays data in a stacked area chart.
 */
public class StackedAreaChartApp extends Application {

    private MyStackedAreaChart chart;
    private StackPane pane;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private Circle selector;
    private Line selectorLine;
    private List<Path> strokePaths;
    private Tooltip selectorTooltip;


    public MyStackedAreaChart createContent() {
        xAxis = new CategoryAxis ();
        yAxis = new NumberAxis("Y Values", 0.0d, 30.0d, 2.0d);

        ObservableList<XYChart.Series> areaChartData =
                FXCollections.observableArrayList(
                        new XYChart.Series("Series 1",
                                FXCollections.observableArrayList(
                                        new XYChart.Data("a", 4),
                                        new XYChart.Data("b", 15),
                                        new XYChart.Data("c", 14),
                                        new XYChart.Data("d", 12),
                                        new XYChart.Data("e", 6),
                                        new XYChart.Data("f", 18)
                                )),
                        new XYChart.Series("Series 2",
                                FXCollections.observableArrayList(
                                        new XYChart.Data("a", 26),
                                        new XYChart.Data("b", 5),
                                        new XYChart.Data("c", 10),
                                        new XYChart.Data("d", 16),
                                        new XYChart.Data("e", 9),
                                        new XYChart.Data("f", 13)
                                )),
                        new XYChart.Series("Series 3",
                                FXCollections.observableArrayList(
                                        new XYChart.Data("a", 6),
                                        new XYChart.Data("b", 21),
                                        new XYChart.Data("c", 13),
                                        new XYChart.Data("d", 16),
                                        new XYChart.Data("e", 9),
                                        new XYChart.Data("f", 22)
                                ))
                );
        chart = new MyStackedAreaChart(xAxis, yAxis, areaChartData);
        chart.setCreateSymbols(true);
        return chart;
    }

    private StackPane createPane(){
        selectorLine = new Line();
        selectorLine.setFill(Color.WHEAT);
        selectorLine.setStrokeWidth(5f);
        pane = new StackPane();
        pane.getChildren().addAll(createContent(),selectorLine);
        return pane;
    }




    @Override
    public void start(Stage primaryStage) throws Exception {


        Scene scene = new Scene(createContent());
        scene.getStylesheets().addAll(JFoenixResources.load("/test.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}