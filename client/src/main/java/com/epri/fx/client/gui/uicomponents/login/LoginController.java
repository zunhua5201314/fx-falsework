package com.epri.fx.client.gui.uicomponents.login;

import com.epri.fx.client.bean.MenuVoCell;
import com.epri.fx.client.gui.uicomponents.main.MainController;
import com.epri.fx.client.request.feign.admin.MenuFeign;
import com.epri.fx.client.store.ApplicatonStore;
import com.epri.fx.client.request.feign.login.LoginFeign;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.utils.AlertUtil;
import com.epri.fx.client.websocket.Session;
import com.epri.fx.server.util.DynamicEnumUtils;
import com.epri.fx.server.util.EncryptUtil;
import com.epri.fx.server.util.user.JwtAuthenticationRequest;
import com.epri.fx.server.vo.FrontUser;
import com.epri.fx.server.vo.MenuVO;
import com.epri.fx.server.vo.PermissionInfo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import io.datafx.core.concurrent.ProcessChain;
import javafx.animation.*;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @className: ConversationItemPresenter
 * @author: liwen
 * @date: 2019-09-25 16:51
 */
@ViewController("/fxml/login/login.fxml")
public class LoginController {


    @FXML

    private Pane imagePane;
    @FXML
    private GridPane enterPane;
    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTextField userNameTextField;
    @FXML
    private JFXPasswordField passWordTextField;
    @FXML
    private Label errorLabel;
    @FXML
    @ActionTrigger("login")
    private JFXButton loginBut;
    @FXML
    private VBox signCredsPane;

    @FXML
    private JFXProgressBar lodingBar;

    @ActionHandler
    private FlowActionHandler actionHandler;

    private SequentialTransition sequentialTransition = new SequentialTransition();

    private DoubleProperty imageWidth = new SimpleDoubleProperty();
    private DoubleProperty imageHeiht = new SimpleDoubleProperty();
    private Stage stage;


    @Inject
    private Session session;


    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    @PostConstruct
    public void init() {
        errorLabel.visibleProperty().bind(errorLabel.textProperty().isNotEmpty());
        errorLabel.managedProperty().bind(errorLabel.visibleProperty());

        lodingBar.visibleProperty().bind(enterPane.disableProperty());
        lodingBar.managedProperty().bind(lodingBar.visibleProperty());

        userNameTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                userNameTextField.validate();
            }
        });
        passWordTextField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                passWordTextField.validate();
            }
        });

        loginBut.disableProperty().bind(Bindings.or(
                userNameTextField.textProperty().isEqualTo(""),
                passWordTextField.textProperty().isEqualTo("")));

        rootPane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (loginBut.isDisable() == false) {
                    login();
                }
            }

        });
        imagePane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageWidth.setValue(newValue);
            }
        });
        imagePane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                imageHeiht.setValue(newValue);
            }
        });



        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), signCredsPane);
        fadeTransition.setFromValue(0f);
        fadeTransition.setToValue(1f);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), signCredsPane);
        translateTransition.setInterpolator(Interpolator.EASE_BOTH);
        translateTransition.setFromY(400);
        translateTransition.setToY(signCredsPane.getLayoutY());

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.setDelay(Duration.millis(1500));
        parallelTransition.getChildren().addAll(
                fadeTransition,
                translateTransition
        );
        parallelTransition.setCycleCount(1);
        parallelTransition.play();

        ImageView logBack = new ImageView("/images/loginBack.jpg");
        logBack.fitHeightProperty().bind(imageHeiht);
        logBack.fitWidthProperty().bind(imageWidth);
        imagePane.getChildren().add(new Label("", logBack));

        List<File> files = Arrays.asList(new File(this.getClass().getResource("/images/login/").getPath()).listFiles());

        sequentialTransition.setAutoReverse(true);
        sequentialTransition.setCycleCount(Timeline.INDEFINITE);

        ProcessChain.create().addPublishingTask(() -> imagePane.getChildren(), p -> {
            for (int i = 0; i < files.size(); i++) {

                File file = files.get(i);
                if (!file.isDirectory()) {
                    String url = "/images/login/" + file.getName();
                    ImageView imageView = new ImageView(url);
                    imageView.fitHeightProperty().bind(imageHeiht);
                    imageView.fitWidthProperty().bind(imageWidth);
                    Label label = new Label("", imageView);

                    label.setOpacity(0d);
                    FadeTransition fadeT = new FadeTransition(Duration.millis(500), label);
                    fadeT.setDelay(Duration.millis(1500));
                    fadeT.setFromValue(0f);
                    fadeT.setToValue(1f);
                    fadeT.setCycleCount(1);
                    sequentialTransition.getChildren().add(fadeT);

                    p.publish(label);

                }

            }
        }).withFinal(() -> sequentialTransition.play()).run();


    }


    @ActionMethod("login")
    private void login() {

        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest();
        jwtAuthenticationRequest.setUsername(userNameTextField.getText());
        jwtAuthenticationRequest.setPassword(EncryptUtil.getInstance().Base64Encode(passWordTextField.getText()));
        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                    enterPane.setDisable(true);
                    loginBut.setText("正在登录...");
                })
                .addSupplierInExecutor(() -> Request.connector(LoginFeign.class).login(jwtAuthenticationRequest))
                .addConsumerInPlatformThread(rel -> {

                    if (rel.getStatus() == 200) {
                        errorLabel.setText("");
                        ApplicatonStore.setToken(rel.getData());
                        loadApplicatonStore();
                    } else {
                        lodingBar.requestFocus();
                        errorLabel.setText(rel.getMessage());
                    }

                })
                .onException(e -> {
                    e.printStackTrace();
                    errorLabel.setText("无法连接服务器，请检查服务器是否启动。");
                    lodingBar.requestFocus();
                })
                .withFinal(() -> {
                    enterPane.setDisable(false);
                    loginBut.setText("登录");
                }).run();


    }

    public void loadApplicatonStore() {
        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                    try {
                        actionHandler.navigate(LoadingController.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    ApplicatonStore.setName("");
                    ApplicatonStore.getAllMenu().clear();
                    ApplicatonStore.getMenus().clear();
                    ApplicatonStore.getElements().clear();
                    ApplicatonStore.getPermissionMenus().clear();
                    ApplicatonStore.getRoles().clear();
                })
                .addSupplierInExecutor(() -> Request.connector(MenuFeign.class).getMenuAll())
                .addConsumerInPlatformThread(rel -> ApplicatonStore.getAllMenu().addAll(rel))
                .addSupplierInExecutor(() ->
                        Request.connector(LoginFeign.class).getInfo(ApplicatonStore.getToken())
                )
                .addConsumerInPlatformThread(rel -> {
                    if (rel.getStatus() == 200) {
                        FrontUser frontUser = rel.getData();
                        ApplicatonStore.setName(frontUser.name);
                        ApplicatonStore.getMenus().addAll(frontUser.getMenus());
                        ApplicatonStore.getRoles().addAll(frontUser.getRoles());
                        ApplicatonStore.getElements().addAll(frontUser.getElements());
                        ApplicatonStore.setIntroduction(frontUser.getDescription());

                        for (PermissionInfo permissionInfo : frontUser.getElements()) {
                            ApplicatonStore.getFeatureMap().put(permissionInfo.getCode(), permissionInfo.getName());
                        }


                    } else {
                        AlertUtil.show(rel);
                    }
                })
                .addSupplierInExecutor(() ->
                        Request.connector(LoginFeign.class).getMenus(ApplicatonStore.getToken())
                )
                .addConsumerInPlatformThread(rel -> {
                    ApplicatonStore.getPermissionMenus().addAll(rel);

                })
                .addSupplierInExecutor(() -> {

                    List<MenuVO> allMenuList = ApplicatonStore.getAllMenu();
                    Map<Integer, List<MenuVO>> allMap = allMenuList.stream().collect(Collectors.groupingBy(MenuVO::getParentId));
                    MenuVO rootMenu = allMenuList.stream().min(Comparator.comparing(MenuVO::getParentId)).get();


                    List<MenuVO> permissionInfoList = ApplicatonStore.getPermissionMenus();
                    Map<Integer, List<MenuVO>> permissionInfoMap = permissionInfoList.stream().collect(Collectors.groupingBy(MenuVO::getParentId));
                    Map<String, List<MenuVO>> permissonTitleMap = permissionInfoList.stream().collect(Collectors.groupingBy(MenuVO::getTitle));

                    for (MenuVO menu : allMap.get(rootMenu.getId())) {

                        List<MenuVO> childrenMenus = permissionInfoMap.get(menu.getId());

                        List<MenuVO> partMenus = permissonTitleMap.get(menu.getTitle());
                        if (childrenMenus == null && partMenus == null) {

                            continue;
                        }

                        MenuVoCell menuVoCell=new MenuVoCell(menu,childrenMenus);

                        ApplicatonStore.getMenuVoCells().add(menuVoCell);

                    }
                    return 0;

                })
                .addConsumerInPlatformThread(rel -> {

                    try {
                        actionHandler.navigate(MainController.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .onException(e -> {
                    e.printStackTrace();
                    try {
                        actionHandler.navigate(LoginController.class);
                    } catch (VetoException vetoException) {
                        vetoException.printStackTrace();
                    } catch (FlowException flowException) {
                        flowException.printStackTrace();
                    }
                })
                .run();
    }

    @PreDestroy
    private void destroy() {
        sequentialTransition.stop();
    }


}


