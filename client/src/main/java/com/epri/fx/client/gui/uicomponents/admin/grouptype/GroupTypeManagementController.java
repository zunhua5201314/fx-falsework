package com.epri.fx.client.gui.uicomponents.admin.grouptype;

import com.epri.fx.client.model.GroupTypeDataModel;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.request.feign.admin.GroupTypeFeign;
import com.epri.fx.client.request.feign.admin.UserFeign;
import com.epri.fx.client.store.ApplicatonStore;
import com.epri.fx.client.utils.Pinyin4jUtil;
import com.epri.fx.server.vo.GroupTypeVO;
import com.jfoenix.controls.*;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.core.concurrent.ProcessChain;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Date;


/**
 * @description:
 * @className: RoleRightsManagement
 * @author: liwen
 * @date: 2020/7/12 01:23
 */
@ViewController(value = "/fxml/admin/groupType/group_type_management.fxml", title = "角色类型管理")
public class GroupTypeManagementController {


    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXDialog dialog;
    @FXML
    private Label title;
    @FXML
    private JFXButton searchButton;
    @FXML
    @ActionTrigger("createDialog")
    private JFXButton addButton;
    @FXML
    @ActionTrigger("closeDialog")
    private JFXButton cancelButton;
    @FXML
    @ActionTrigger("createGroupType")
    private JFXButton saveButton;
    @FXML
    @ActionTrigger("updateGroupType")
    private JFXButton updateButton;
    @FXML
    private JFXTextField searchField;
    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField codeTextField;
    @FXML
    private JFXTextArea descriptionTextArea;
    @FXML
    private TableView<GroupTypeVO> tableView;
    @FXML
    private TableColumn<GroupTypeVO, String> serialNumberColumn;
    @FXML
    private TableColumn<GroupTypeVO, String> codeColumn;
    @FXML
    private TableColumn<GroupTypeVO, String> nameColumn;
    @FXML
    private TableColumn<GroupTypeVO, String> descriptionColumn;
    @FXML
    private TableColumn<GroupTypeVO, Date> updTimeColumn;
    @FXML
    private TableColumn<GroupTypeVO, String> updHostColumn;
    @FXML
    private TableColumn<GroupTypeVO, String> operatingColumn;
    @Inject
    private GroupTypeDataModel groupTypeDataModel;

    @PostConstruct
    private void init() {

        progressBar.visibleProperty().bind(dialog.disableProperty());
        progressBar.managedProperty().bind(progressBar.visibleProperty());
        updateButton.visibleProperty().bind(saveButton.visibleProperty().not());
        updateButton.managedProperty().bind(updateButton.visibleProperty());
        cancelButton.disableProperty().bind(saveButton.disableProperty().or(updateButton.disableProperty()));

        serialNumberColumn.setCellFactory((col) -> {
            TableCell<GroupTypeVO, String> cell = new TableCell<GroupTypeVO, String>() {
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
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        updTimeColumn.setCellValueFactory(new PropertyValueFactory<>("updTime"));
        updHostColumn.setCellValueFactory(new PropertyValueFactory<>("updHost"));

        Callback<TableColumn<GroupTypeVO, String>, TableCell<GroupTypeVO, String>> cellFactory = new Callback<TableColumn<GroupTypeVO, String>, TableCell<GroupTypeVO, String>>() {
            @Override
            public TableCell<GroupTypeVO, String> call(TableColumn param) {

                final TableCell<GroupTypeVO, String> cell = new TableCell<GroupTypeVO, String>() {

                    private final ToggleButton editBut = new ToggleButton();
                    private final ToggleButton delBut = new ToggleButton();

                    {


                        editBut.getStyleClass().add("left-pill");
                        delBut.getStyleClass().add("right-pill");

                        try {
                            editBut.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY + ".create-outline"));
                            delBut.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY + ".trash-outline"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        editBut.setOnMouseClicked(event -> {

                            tableView.getSelectionModel().select(getIndex());
                            updateDialog();
                        });
                        delBut.setOnMouseClicked(event -> {
                            tableView.getSelectionModel().select(getIndex());
                            delete();
                        });
                    }

                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            HBox hBox = new HBox(editBut, delBut);
                            hBox.setAlignment(Pos.CENTER);
                            hBox.setSpacing(0);
                            setGraphic(hBox);
                        }
                    }
                };
                return cell;
            }
        };

        operatingColumn.setCellFactory(cellFactory);

        FilteredList<GroupTypeVO> filteredData = new FilteredList<>(groupTypeDataModel.getGroupTypes(), p -> true);
        tableView.setItems(filteredData);
        searchField.textProperty().addListener((o, oldVal, newVal) -> {
            filteredData.setPredicate(elementProp -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String val = Pinyin4jUtil.toPinYinLowercase(newVal);
                return Pinyin4jUtil.toPinYinLowercase(elementProp.getName()).contains(val)
                        || Pinyin4jUtil.toPinYinLowercase(elementProp.getCode()).contains(val);
            });
        });

        groupTypeDataModel.selectedIndexProperty().bind(tableView.getSelectionModel().selectedIndexProperty());

        initData();
    }

    private void initData() {
        loadingTableData();
    }

    private void loadingTableData() {
        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(GroupTypeFeign.class).getAllGroupTypes())
                .addConsumerInPlatformThread(rel -> {
                    groupTypeDataModel.getGroupTypes().clear();
                    groupTypeDataModel.getGroupTypes().addAll(rel);})
                .run();
    }

    @ActionMethod("closeDialog")
    private void closeDialog() {
        dialog.close();
    }

    @ActionMethod("createDialog")
    private void createDialog() {
        title.setText("新建");
        codeTextField.setText("");
        nameTextField.setText("");
        descriptionTextArea.setText("");
        saveButton.setVisible(true);
        dialog.show(rootPane);
    }

    @ActionMethod("createGroupType")
    private void createGroupType() {

        GroupTypeVO groupTypeVO = new GroupTypeVO();
        groupTypeVO.setCode(codeTextField.getText());
        groupTypeVO.setName(nameTextField.getText());
        groupTypeVO.setDescription(descriptionTextArea.getText());

        ProcessChain.create().addRunnableInPlatformThread(() -> dialog.setDisable(true))
                .addSupplierInExecutor(() -> Request.connector(GroupTypeFeign.class).addGroupType(groupTypeVO))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >=0) {
                        loadingTableData();
                    }
                }).withFinal(() -> {
            dialog.setDisable(false);
            dialog.close();
        })
                .run();

    }

    @ActionMethod("updateGroupType")
    private void updateGroupType() {
        GroupTypeVO groupTypeVO = new GroupTypeVO();
        groupTypeVO.setId((groupTypeDataModel.getGroupTypes().get(groupTypeDataModel.getSelectedIndex())).getId());
        groupTypeVO.setCode(codeTextField.getText());
        groupTypeVO.setName(nameTextField.getText());
        groupTypeVO.setDescription(descriptionTextArea.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> dialog.setDisable(true))
                .addSupplierInExecutor(() -> Request.connector(GroupTypeFeign.class).updateGroupType(groupTypeVO))
                .addConsumerInPlatformThread(rel -> {
                    if (rel > -0) {
                        loadingTableData();
                    }
                }).withFinal(() -> {
            dialog.setDisable(false);
            dialog.close();
        })
                .run();
    }

    private void delete() {
        GroupTypeVO groupTypeVO = groupTypeDataModel.getGroupTypes().get(groupTypeDataModel.getSelectedIndex());

        JFXAlert alert = new JFXAlert((Stage) rootPane.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("消息提示"));
        layout.setBody(new Label("确实删除【" + groupTypeVO.getName() + "】吗？"));
        JFXButton closeButton = new JFXButton("取消");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        JFXButton determineButton = new JFXButton("确定");
        determineButton.setOnAction(event -> {
            alert.hideWithAnimation();
            ProcessChain.create()
                    .addSupplierInExecutor(() -> Request.connector(GroupTypeFeign.class).deleteGroupTypes(groupTypeVO.getId()))
                    .addConsumerInPlatformThread(result -> {
                        if (result >= 0) {
                            groupTypeDataModel.getGroupTypes().remove(groupTypeDataModel.getSelectedIndex());
                        }
                    }).onException(e -> e.printStackTrace()).run();
        });
        layout.setActions(closeButton, determineButton);
        alert.setContent(layout);
        alert.show();

    }

    private void updateDialog() {
        title.setText("编辑");
        saveButton.setVisible(false);
        GroupTypeVO groupTypeVO = groupTypeDataModel.getGroupTypes().get(groupTypeDataModel.getSelectedIndex());
        codeTextField.setText(groupTypeVO.getCode());
        nameTextField.setText(groupTypeVO.getName());
        descriptionTextArea.setText(groupTypeVO.getDescription());
        dialog.show(rootPane);
    }


}
