package com.epri.fx.client.gui.uicomponents.main.components;

import com.epri.fx.client.store.ApplicatonStore;
import com.jfoenix.controls.JFXListView;
import io.datafx.controller.ViewController;
import io.datafx.eventsystem.EventProducer;
import io.datafx.eventsystem.EventTrigger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

/**
 * @description:
 * @className: UserInfoController
 * @author: liwen
 * @date: 2020/8/16 10:52
 */
@ViewController("/fxml/main/components/user_info.fxml")
public class UserInfoController {

    @FXML
    private JFXListView listView;

    @FXML
    private Label userLabel;
    @FXML
    private Label personalCenterLabel;
    @FXML
    @EventTrigger("dropOut")
    private Label dropOutLabel;

    @FXML
    private Label roleLabel;

    @PostConstruct
    private void init() {
        userLabel.textProperty().bind(ApplicatonStore.nameProperty());
        roleLabel.textProperty().bind(ApplicatonStore.getRoles().asString());
        listView.expandedProperty().bind(listView.visibleProperty());

    }

    @EventProducer("dropOut")
    private String listViewListener() {


        return "退出";

    }
}
