/**
 * @description:
 * @className: ZTest
 * @author: liwen
 * @date: 2020/9/25 09:29
 */

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ZTest extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new JFXButton("aaa"), 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Welcome");
        primaryStage.show();
    }
}
