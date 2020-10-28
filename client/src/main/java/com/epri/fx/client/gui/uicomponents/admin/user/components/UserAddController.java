package com.epri.fx.client.gui.uicomponents.admin.user.components;

import com.epri.fx.client.gui.uicomponents.admin.user.UserManagementController;
import com.epri.fx.client.model.UserDataModel;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.request.feign.admin.UserFeign;
import com.epri.fx.server.entity.User;
import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.action.BackAction;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import io.datafx.core.concurrent.ProcessChain;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @description:
 * @className: UserAddController
 * @author: liwen
 * @date: 2020/7/3 17:04
 */

@ViewController(value = "/fxml/admin/user/user_detailed.fxml", title = "添加用户")
public class UserAddController {
    @ActionHandler
    private FlowActionHandler actionHandler;

    @FXML
    @BackAction
    private JFXButton cancelBut;
    @FXML
    private Label title;
    @FXML
    @ActionTrigger("save")
    private JFXButton saveBut;
    @FXML
    @ActionTrigger("update")
    private JFXButton updateBut;
    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField userNameTextField;
    @FXML
    private JFXPasswordField pwdTextField;
    @FXML
    private JFXTextArea descTextArea;
    @FXML
    private JFXComboBox genderCombobox;

    @Inject
    private UserDataModel dataModel;

    @PostConstruct
    private void init() {


        title.setText("添加用户");
        updateBut.setVisible(false);
        updateBut.managedProperty().bind(updateBut.visibleProperty());
    }


    @ActionMethod("save")
    private void save() {

        User user = new User();
        user.setName(nameTextField.getText());
        user.setDescription(descTextArea.getText());
        user.setUsername(userNameTextField.getText());
        user.setPassword(pwdTextField.getText());
        user.setSex(genderCombobox.getSelectionModel().getSelectedIndex() == 0 ? "男" : "女");

        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(UserFeign.class).add(user))
                .addConsumerInPlatformThread(rel -> {
                    if (rel.isRel()) {
                        try {
                            actionHandler.navigate(UserManagementController.class);
                        } catch (VetoException e) {
                            e.printStackTrace();
                        } catch (FlowException e) {
                            e.printStackTrace();
                        }
                    }
                }).run();

    }
}
