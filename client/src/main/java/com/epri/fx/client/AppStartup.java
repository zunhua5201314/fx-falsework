package com.epri.fx.client;

import com.epri.fx.client.gui.uicomponents.control.LFXDecorator;
import com.epri.fx.client.gui.uicomponents.login.LoginController;
import com.epri.fx.client.store.ApplicatonStore;
import com.jfoenix.assets.JFoenixResources;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import com.netflix.config.ConfigurationManager;
import io.datafx.controller.context.ApplicationContext;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.io.InputStream;


/**
 * @description:
 * @className: AppStartup
 * @author: liwen
 * @date: 2019-08-26 16:24
 */
public class AppStartup extends Application {



    @Override
    public void init() throws Exception {

        InputStream in = AppStartup.class.getClassLoader().getResourceAsStream("fonts/msyh.ttf");
        Font font1 = Font.loadFont(in, 12);

        System.err.println(font1.getName()+"\t"+font1.getFamily());
        ConfigurationManager.loadPropertiesFromResources("sample-client.properties");
//


    }

    @Override
    public void start(Stage stage) throws Exception {

        String keyPrefix = "";
//        //全局样式
//        setUserAgentStylesheet(null);
//        StyleManager.getInstance().addUserAgentStylesheet("css/app.css");

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


        ApplicationContext.getInstance().register(stage, Stage.class);

        Flow flow = new Flow(LoginController.class);
        FlowHandler flowHandler = flow.createHandler();
        StackPane rootPane = flowHandler.start(new AnimatedFlowContainer(Duration.millis(320), ContainerAnimations.SWIPE_LEFT));

        ApplicationContext.getInstance().register("mainFlowHandler", flowHandler);
        ApplicationContext.getInstance().register(rootPane, StackPane.class);

        LFXDecorator wfxDecorator = new LFXDecorator(stage, rootPane, false, true, true);
        wfxDecorator.setCustomMaximize(true);
        wfxDecorator.setGraphic(new SVGGlyph(""));

        Scene scene = new Scene(wfxDecorator, 1000, 700);
        stage.setTitle("JavaFX Welcome");
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();


        scene.getStylesheets().addAll(JFoenixResources.load("/css/app-fonts.css").toExternalForm(),AppStartup.class.getResource("/css/app.css").toExternalForm());

    }

    @Override
    public void stop() throws Exception {

        System.err.println(Font.getFontNames());
    }
}
