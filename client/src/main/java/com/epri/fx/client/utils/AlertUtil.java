package com.epri.fx.client.utils;

import com.epri.fx.server.msg.BaseResponse;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import feign.Response;
import io.datafx.controller.context.ApplicationContext;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * @description:
 * @className: AlerUtil
 * @author: liwen
 * @date: 2020/8/2 13:39
 */
public class AlertUtil {

    public static void show(BaseResponse response) {
        JFXAlert alert = new JFXAlert(ApplicationContext.getInstance().getRegisteredObject(Stage.class));
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("提示"));
        layout.setBody(new Label(response.getStatus() + "\t" + response.getMessage()));
        JFXButton closeButton = new JFXButton("确定");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }

    public static void show(Response response) {
        JFXAlert alert = new JFXAlert(ApplicationContext.getInstance().getRegisteredObject(Stage.class));
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("提示"));
        layout.setBody(new Label(response.status() + "\t" + response.reason()));
        JFXButton closeButton = new JFXButton("确定");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
    }
}
