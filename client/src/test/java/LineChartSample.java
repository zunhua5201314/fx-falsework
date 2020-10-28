import javafx.application.Application;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class LineChartSample extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("people");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Country");
        final BarChart<String, Number> chart =
                new BarChart<String, Number>(xAxis, yAxis);

        chart.setTitle("people");

        XYChart.Series series = new XYChart.Series();
        series.setName("flag");
        series.getData().add(new XYChart.Data("China", 14.7));
        series.getData().add(new XYChart.Data("America", 2.5));
        series.getData().add(new XYChart.Data("India", 14));
        XYChart.Data data = new XYChart.Data("Russa", 2);
        data.setNode(new Label("2aaaaaaaaaaaaa"));
        series.getData().add(data);

        Scene scene = new Scene(chart, 800, 600);
        chart.getData().addAll(series);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}