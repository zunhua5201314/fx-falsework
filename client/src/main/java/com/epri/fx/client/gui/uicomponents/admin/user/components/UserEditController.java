package com.epri.fx.client.gui.uicomponents.admin.user.components;

import com.epri.fx.client.gui.uicomponents.admin.user.UserManagementController;
import com.epri.fx.client.model.UserDataModel;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.request.feign.admin.UserFeign;
import com.epri.fx.server.entity.User;
import com.epri.fx.server.vo.UserVO;
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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

@ViewController(value = "/fxml/admin/user/user_detailed.fxml", title = "编辑用户")
public class UserEditController {

    @ActionHandler
    private FlowActionHandler actionHandler;

    @FXML
    @BackAction
    private JFXButton cancelBut;

    @FXML
    private Label title;
    @FXML
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
        title.setText("编辑用户");
        saveBut.managedProperty().bind(saveBut.visibleProperty());
        pwdTextField.setDisable(true);
        saveBut.setVisible(false);
        genderCombobox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                UserVO userVO = dataModel.getUsers().get(dataModel.getSelectedPersonIndex());
                userVO.setSex(newValue.intValue() == 0 ? "男" : "女");
            }
        });
        UserVO userVO = dataModel.getUsers().get(dataModel.getSelectedPersonIndex());

        nameTextField.textProperty().bindBidirectional(userVO.nameProperty());
        userNameTextField.textProperty().bindBidirectional(userVO.userNameProperty());
        pwdTextField.textProperty().bindBidirectional(userVO.userNameProperty());
        descTextArea.textProperty().bindBidirectional(userVO.descriptionProperty());
        String sex = userVO.sexProperty().getValue();
        genderCombobox.getSelectionModel().select("男".equals(sex) ? 0 : 1);
    }

    @ActionMethod("update")
    private void update() {

        UserVO userVO = dataModel.getUsers().get(dataModel.getSelectedPersonIndex());

        User user = new User();
        user.setId(userVO.getId());
        user.setName(userVO.getName());
        user.setDescription(userVO.getDescription());
        user.setUsername(userVO.getUserName());
        user.setSex(userVO.getSex());
        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(UserFeign.class).update(user.getId(), user))
                .addConsumerInPlatformThread((rel) -> {
                    if (rel.isRel()) {
                        try {
                            actionHandler.navigate(UserManagementController.class);
                        } catch (VetoException e) {
                            e.printStackTrace();
                        } catch (FlowException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .run();
    }
}
