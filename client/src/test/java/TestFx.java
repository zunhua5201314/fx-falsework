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
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Point3D;
import javafx.scene.DepthTest;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
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
        button.setDepthTest(DepthTest.DISABLE);
        SVGGlyph materialIconView=  SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".home-outline");
        materialIconView.setSize(80);
        button.setGraphic(materialIconView);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(button);
        button.setRotationAxis(new Point3D(1, 0, 0));

        RotateTransition transition1=new RotateTransition(Duration.millis(400),button);
        transition1.setByAngle(180);
        transition1.setInterpolator(Interpolator.EASE_OUT);
        transition1.setOnFinished(event -> {
        });
        RotateTransition transition2=new RotateTransition(Duration.millis(300),button);
        transition2.setByAngle(90);
        transition2.setInterpolator(Interpolator.EASE_BOTH);
        transition2.setOnFinished(event -> {
        });

        SequentialTransition sequentialTransition=new SequentialTransition();
        sequentialTransition.getChildren().addAll(transition1);

        button.setOnMouseEntered(event -> {
            sequentialTransition.play();
        });

        Scene scene = new Scene(stackPane, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Welcome");
        primaryStage.show();
    }
}
