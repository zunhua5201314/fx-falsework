/**
 * @description:
 * @className: TestFx
 * @author: liwen
 * @date: 2020/9/11 10:53
 */

import com.epri.fx.client.AppStartup;
import com.epri.fx.client.store.ApplicatonStore;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class TestFx extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        new Thread(() -> {
            try {
                SVGGlyphLoader.loadGlyphsFont(AppStartup.class.getResourceAsStream("/fonts/icon_font/iconfont.svg"),
                        ApplicatonStore.ICON_FONT_KEY);
//                SVGGlyphLoader.loadGlyphsFont(AppStartup.class.getResourceAsStream("/fonts/icon_font/icon-font-solid.svg"),
//                        "IconFontSolid.svg");
            } catch (IOException ioExc) {
                ioExc.printStackTrace();
            }
        }).start();

        JFXButton button = new JFXButton("");
        button.setPrefHeight(100);
        button.setPrefWidth(200);
        button.setRipplerFill(Color.RED);
        SVGGlyph materialIconView = SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY + ".home-outline");
//        materialIconView.setDepthTest(DepthTest.DISABLE);
        materialIconView.setRotationAxis(new Point3D(0, 1, 0));
        materialIconView.setSize(80);
        button.setGraphic(materialIconView);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);

        RotateTransition transition1 = new RotateTransition(Duration.millis(500), materialIconView);
        transition1.setByAngle(180);
        transition1.setInterpolator(Interpolator.EASE_BOTH);
        transition1.setOnFinished(event -> {
        });


        button.setOnMouseEntered(event -> {
            transition1.play();
        });
        button.setOnMouseExited(event -> {
            transition1.play();
        });

        Scene scene = new Scene(stackPane, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Welcome");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
