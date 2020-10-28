package com.epri.fx.client.gui.uicomponents.admin.user;

import com.epri.fx.client.gui.feature.FeatureResourceConsumer;
import com.epri.fx.client.gui.feature.HideByFeature;
import com.epri.fx.client.gui.uicomponents.admin.user.components.UserAddController;
import com.epri.fx.client.gui.uicomponents.admin.user.components.UserEditController;
import com.epri.fx.client.model.UserDataModel;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.request.feign.admin.UserFeign;
import com.epri.fx.client.store.ApplicatonStore;
import com.epri.fx.client.utils.Pinyin4jUtil;
import com.epri.fx.server.entity.User;
import com.epri.fx.server.util.DateUtils;
import com.epri.fx.server.vo.UserVO;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.svg.SVGGlyph;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.ActionHandler;
import io.datafx.controller.flow.context.FlowActionHandler;
import io.datafx.controller.util.VetoException;
import io.datafx.core.concurrent.ProcessChain;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @className: UserManagementController
 * @author: liwen
 * @date: 2020/3/4 21:05
 */
@ViewController(value = "/fxml/admin/user/user_management.fxml", title = "用户管理")
public class UserManagementController {

    public static final String CONTENT_PANE = "ContentPane";


    @FXML
    private StackPane root;
    @FXML
    private VBox centPane;
    @FXML
    private StackPane spinnerPane;
    @FXML
    private TextField searchField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField accountTextField;
    @FXML
    private TextField pwdTextField;
    @FXML
    private TextArea descTextArea;
    @FXML
    private ComboBox genderCombobox;
    @FXML
    @ActionTrigger("createUser")
    @HideByFeature("userManager:btn_add")
    private Button addBut;
    @FXML
    @ActionTrigger("accept")
    private Button acceptButton;
    @FXML
    @ActionTrigger("cancel")
    private Button cancelButton;
    @FXML
    @ActionTrigger("search")
    private Button searchBut;


    @FXML
    private Pagination pagination;


    @FXML
    private TableView<UserVO> tableView;
    @FXML
    private TableColumn<UserVO, String> serialNumberColumn;
    @FXML
    private TableColumn<UserVO, String> nameColumn;
    @FXML
    private TableColumn<UserVO, String> accountColumn;
    @FXML
    private TableColumn<UserVO, String> remarksColumn;
    @FXML
    private TableColumn<UserVO, String> lastUpdateTimeColumn;
    @FXML
    private TableColumn<UserVO, String> lastUpdatedByColumn;
    @FXML
    private TableColumn<UserVO, String> operateColumn;

    @ActionHandler
    private FlowActionHandler actionHandler;

    @Inject
    private UserDataModel dataModel;

    @Inject
    private FeatureResourceConsumer featureResourceConsumer;
    @PostConstruct
    public void init() {
        featureResourceConsumer.consumeResource(this);
        spinnerPane.setVisible(false);
        serialNumberColumn.setCellFactory((col) -> {
            TableCell<UserVO, String> cell = new TableCell<UserVO, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    this.setText(null);
                    this.setGraphic(null);

                    if (!empty) {
                        int rowIndex = this.getIndex() + 1;
                        this.setText(String.valueOf(rowIndex));
                    }
                }
            };
            return cell;
        });

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
        remarksColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        lastUpdateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("updTime"));
        lastUpdatedByColumn.setCellValueFactory(new PropertyValueFactory<>("updUser"));
        Callback<TableColumn<UserVO, String>, TableCell<UserVO, String>> cellFactory = new Callback<TableColumn<UserVO, String>, TableCell<UserVO, String>>() {
            @Override
            public TableCell<UserVO, String> call(TableColumn param) {

                final TableCell<UserVO, String> cell = new TableCell<UserVO, String>() {

                    private final ToggleButton resetBut = new ToggleButton();
                    private final ToggleButton editBut = new ToggleButton();
                    private final ToggleButton delBut = new ToggleButton();

                    {


                        resetBut.setTooltip(new Tooltip("重置密码"));
                        resetBut.getStyleClass().add("left-pill");
                        editBut.getStyleClass().add("center-pill");
                        delBut.getStyleClass().add("right-pill");

                        try {
                            editBut.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY + ".create-outline"));
                            resetBut.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY + ".history"));
                            delBut.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY + ".trash-outline"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        editBut.setOnMouseClicked(event -> {

                            try {
                                tableView.getSelectionModel().select(getIndex());
                                actionHandler.navigate(UserEditController.class);
                            } catch (VetoException e) {
                                e.printStackTrace();
                            } catch (FlowException e) {
                                e.printStackTrace();
                            }
                        });
                        delBut.setOnMouseClicked(event -> {
                            tableView.getSelectionModel().select(getIndex());
                            delete();
                        });
                        resetBut.setOnMouseClicked(event -> {
                            tableView.getSelectionModel().select(getIndex());
                            restPassword();
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(resetBut, editBut, delBut);
                            hBox.setSpacing(0);
                            hBox.setAlignment(Pos.CENTER);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        operateColumn.setCellFactory(cellFactory);

        FilteredList<UserVO> filteredData = new FilteredList<>(dataModel.getUsers(), p -> true);
        tableView.setItems(filteredData);
        searchField.textProperty().addListener((o, oldVal, newVal) -> {
            filteredData.setPredicate(elementProp -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String val = Pinyin4jUtil.toPinYinLowercase(newVal);
                return Pinyin4jUtil.toPinYinLowercase(elementProp.getName()).contains(val)
                        || elementProp.getName().toLowerCase().contains(val)
                        || elementProp.getUserName().toLowerCase().contains(val);
            });
        });

        dataModel.selectedPersonIndexProperty().bind(tableView.getSelectionModel().selectedIndexProperty());
        pagination.pageCountProperty().bind(dataModel.pageCountProperty());
        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {
                showPage(param + 1);
                return tableView;
            }
        });


    }


    @ActionMethod("createUser")
    private void createUser() {

        try {
            actionHandler.navigate(UserAddController.class);
        } catch (VetoException e) {
            e.printStackTrace();
        } catch (FlowException e) {
            e.printStackTrace();
        }
    }

    @ActionMethod("cancel")
    private void cancel() {

    }

    @ActionMethod("accept")
    private void accept() {

        JFXAlert alert = new JFXAlert((Stage) root.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();

        layout.setBody(new Label(userNameTextField.getText() + "\t" + accountTextField.getText() + "\t" + genderCombobox.getSelectionModel().getSelectedItem() + "\t" + descTextArea.getText()));

        alert.setContent(layout);
        alert.show();
    }

    private void restPassword() {
        UserVO userVO = dataModel.getUsers().get(dataModel.getSelectedPersonIndex());

        JFXAlert alert = new JFXAlert((Stage) root.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("消息提示"));
        layout.setBody(new Label("确定要重置【" + userVO.getUserName() + "】登录密码吗？"));
        JFXButton closeButton = new JFXButton("取消");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        JFXButton determineButton = new JFXButton("确定");
        determineButton.setOnAction(event -> {
            alert.hideWithAnimation();
            ProcessChain.create()
                    .addSupplierInExecutor(() -> Request.connector(UserFeign.class).restPassword(userVO.getId()))
                    .addConsumerInPlatformThread(result -> {

                    }).onException(e -> e.printStackTrace()).run();
        });
        layout.setActions(closeButton, determineButton);
        alert.setContent(layout);
        alert.show();
    }

    private void delete() {
        UserVO userVO = dataModel.getUsers().get(dataModel.getSelectedPersonIndex());

        JFXAlert alert = new JFXAlert((Stage) root.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("消息提示"));
        layout.setBody(new Label("确实删除【" + userVO.getUserName() + "】吗？"));
        JFXButton closeButton = new JFXButton("取消");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        JFXButton determineButton = new JFXButton("确定");
        determineButton.setOnAction(event -> {
            alert.hideWithAnimation();
            ProcessChain.create()
                    .addSupplierInExecutor(() -> Request.connector(UserFeign.class).delete(userVO.getId()))
                    .addConsumerInPlatformThread(result -> {
                        if (result.isRel()) {
                            dataModel.getUsers().remove(dataModel.getSelectedPersonIndex());
                        }
                    }).onException(e -> e.printStackTrace()).run();
        });
        layout.setActions(closeButton, determineButton);
        alert.setContent(layout);
        alert.show();

    }


    private void showPage(Integer page) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("keyId", searchField.getText());
        queryMap.put("page", page);
        query(queryMap);

    }

    @ActionMethod("search")
    private void search() {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("keyId", searchField.getText());
        query(queryMap);

    }

    private void query(Map<String, Object> queryMap) {

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                    spinnerPane.setVisible(true);
                    centPane.setDisable(true);
                })
                .addSupplierInExecutor(() -> Request.connector(UserFeign.class).getPageList(queryMap)
                )
                .addConsumerInPlatformThread(result -> {
                    dataModel.getUsers().clear();
                    List<User> userList = result.getDatas();
                    dataModel.setPageCount((int) result.getTotal());

                    for (User user : userList) {
                        dataModel.getUsers().add(new UserVO(user.getId(), user.getName(), user.getUsername(), DateUtils.format(user.getUpdTime(), DateUtils.DATE_TIME_PATTERN), user.getUpdUser(), user.getSex(), user.getDescription()));
                    }
                })
                .withFinal(() -> {
                    spinnerPane.setVisible(false);
                    centPane.setDisable(false);
                })
                .onException(e -> e.printStackTrace())
                .run();
    }


    @PreDestroy
    private void destroy() {
        System.err.println("destroy " + this);
    }


    @FXML
    private void test() {
        System.err.println();
    }
}
