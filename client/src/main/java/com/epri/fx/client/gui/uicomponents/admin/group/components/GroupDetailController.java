package com.epri.fx.client.gui.uicomponents.admin.group.components;

import com.epri.fx.client.gui.uicomponents.control.FilterableCheckBoxTreeItem;
import com.epri.fx.client.gui.uicomponents.control.FilterableTreeItem;
import com.epri.fx.client.gui.uicomponents.control.TreeItemPredicate;
import com.epri.fx.client.model.GroupDataModel;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.request.feign.admin.GroupFeign;
import com.epri.fx.client.request.feign.admin.MenuFeign;
import com.epri.fx.client.store.ApplicatonStore;
import com.epri.fx.client.utils.Pinyin4jUtil;
import com.epri.fx.server.entity.User;
import com.epri.fx.server.vo.ElementVO;
import com.epri.fx.server.vo.GroupVO;
import com.epri.fx.server.vo.MenuVO;
import com.jfoenix.controls.*;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.core.concurrent.ProcessChain;
import io.datafx.eventsystem.Event;
import io.datafx.eventsystem.OnEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @className: GroupDetail
 * @author: liwen
 * @date: 2020/7/22 16:17
 */
@ViewController(value = "/fxml/admin/group/components/group_detail.fxml", title = "test")
public class GroupDetailController {


    @FXML
    private VBox contentPane;
    @FXML
    private JFXChipView<User> leaderChipView;
    @FXML
    private JFXChipView<User> memberChipView;
    @FXML
    private JFXDialog groupAuthorityDialog;
    @FXML
    private JFXDialog groupUserDialog;
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    @ActionTrigger("saveAuthority")
    private JFXButton saveAuthorityButton;
    @FXML
    @ActionTrigger("selectAllElement")
    private JFXCheckBox selectAllCheckBox;
    @FXML
    @ActionTrigger("handlerAuthority")
    private ToggleButton handlerAuthorityButton;
    @FXML
    @ActionTrigger("handlerUser")
    private ToggleButton handlerUserButton;
    @FXML
    @ActionTrigger("addGroup")
    private ToggleButton addButton;
    @FXML
    @ActionTrigger("editGroup")
    private ToggleButton editButton;
    @FXML
    @ActionTrigger("deleteGroup")
    private ToggleButton deleteButton;
    @FXML
    @ActionTrigger("cancelGroup")
    private JFXButton cancelButton;
    @FXML
    @ActionTrigger("createGroup")
    private JFXButton defineButton;
    @FXML
    @ActionTrigger("updateGroup")
    private JFXButton updateButton;
    @FXML
    @ActionTrigger("cancelAuthority")
    private JFXButton cancelAuthorityButton;
    @FXML
    @ActionTrigger("cancelUser")
    private JFXButton cancelUserButton;
    @FXML
    @ActionTrigger("modifiyUsers")
    private JFXButton saveUserButton;
    @FXML
    private JFXTextField treeSearchTextField;
    @FXML
    private JFXTextField nameTextField;
    @FXML
    private JFXTextField codeTextField;
    @FXML
    private JFXTextArea descTextArea;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private JFXTreeView treeView;
    @Inject
    private GroupDataModel groupDataModel;

    @FXMLViewFlowContext
    private ViewFlowContext viewFlowContext;


    @FXML
    private JFXTextField menuTreeSearchTextField;
    @FXML
    private JFXTreeView menuTreeView;
    @FXML
    private TableView elementTableView;
    @FXML
    private TableColumn<ElementVO, Boolean> selColumn;
    @FXML
    private TableColumn<ElementVO, String> serialNumberColumn;
    @FXML
    private TableColumn<ElementVO, String> codeColumn;
    @FXML
    private TableColumn<ElementVO, String> typeColumn;
    @FXML
    private TableColumn<ElementVO, String> nameColumn;
    @FXML
    private TableColumn<ElementVO, String> addressColumn;
    @FXML
    private TableColumn<ElementVO, String> requestTypeColumn;

    private Integer groupType;
    private Integer groupId = -1;

    @PostConstruct
    private void init() {
        final ToggleGroup group = new ToggleGroup();

        addButton.setToggleGroup(group);
        editButton.setToggleGroup(group);
        deleteButton.setToggleGroup(group);
        handlerAuthorityButton.setToggleGroup(group);
        handlerUserButton.setToggleGroup(group);
        try {
            addButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".add-circle-outline"));
            editButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".create-outline"));
            deleteButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".trash-outline"));
            handlerAuthorityButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".quanxianfenpei"));
            handlerUserButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".yonghugl"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        buttonBar.managedProperty().bind(buttonBar.visibleProperty());
        contentPane.disableProperty().bind(buttonBar.visibleProperty().not());
        defineButton.managedProperty().bind(defineButton.visibleProperty());
        updateButton.visibleProperty().bind(defineButton.visibleProperty().not());
        updateButton.managedProperty().bind(updateButton.visibleProperty());
        progressBar.visibleProperty().bind(buttonBar.disableProperty().and(buttonBar.visibleProperty()));
        progressBar.managedProperty().bind(progressBar.visibleProperty());
        groupType = (Integer) viewFlowContext.getRegisteredObject("groupType");

        serialNumberColumn.setCellFactory((col) -> {
            TableCell<ElementVO, String> cell = new TableCell<ElementVO, String>() {
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
        selColumn.setCellValueFactory(new PropertyValueFactory<>("sel"));
        selColumn.setCellFactory(new Callback<TableColumn<ElementVO, Boolean>, //
                TableCell<ElementVO, Boolean>>() {
            @Override
            public TableCell<ElementVO, Boolean> call(TableColumn<ElementVO, Boolean> p) {
                CheckBoxTableCell<ElementVO, Boolean> cell = new CheckBoxTableCell<ElementVO, Boolean>() {
                    @Override
                    public void updateItem(Boolean item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            CheckBox cb = (CheckBox) getGraphic();
                            JFXCheckBox checkBox = new JFXCheckBox();
                            checkBox.indeterminateProperty().bindBidirectional(cb.indeterminateProperty());
                            checkBox.selectedProperty().bindBidirectional(cb.selectedProperty());
                            setGraphic(checkBox);
                        }
                    }
                };
                cell.setAlignment(Pos.CENTER);
                return cell;
            }

        });
        codeColumn.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("uri"));
        requestTypeColumn.setCellValueFactory(new PropertyValueFactory<>("method"));

        treeView.setCellFactory(new Callback<TreeView, TreeCell>() {
            @Override
            public TreeCell<GroupVO> call(TreeView param) {
                return new TreeCell<GroupVO>() {
                    @Override
                    protected void updateItem(GroupVO menu, boolean empty) {
                        super.updateItem(menu, empty);
                        if (!empty && menu != null) {
                            setText(menu.getName());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
        menuTreeView.setCellFactory(new Callback<TreeView, CheckBoxTreeCell>() {
            @Override
            public CheckBoxTreeCell<MenuVO> call(TreeView param) {
                return new CheckBoxTreeCell<MenuVO>() {
                    @Override
                    public void updateItem(MenuVO item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!empty && item != null) {

                            setText(item.getTitle());
                            CheckBox cb = (CheckBox) getGraphic();
                            JFXCheckBox checkBox = new JFXCheckBox();
                            checkBox.indeterminateProperty().bindBidirectional(cb.indeterminateProperty());
                            checkBox.selectedProperty().bindBidirectional(cb.selectedProperty());
                            setGraphic(checkBox);
                        } else {
                            setText(null);
                        }
                    }

                };
            }
        });
        menuTreeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<MenuVO>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<MenuVO>> observable, TreeItem<MenuVO> oldValue, TreeItem<MenuVO> newValue) {

                if (newValue.isLeaf()) {
                    MenuVO menuVO = newValue.getValue();
                    groupDataModel.setSelectedMenuVO(menuVO);
                    elementTableView.setItems(groupDataModel.getElementVOS());
                } else {
                    groupDataModel.getElementVOS().clear();
                }

                int sel = 0;
                if (groupDataModel.getElementVOS().isEmpty()) {
                    sel=-1;
                }
                for (ElementVO elementVO : groupDataModel.getElementVOS()) {

                    if (elementVO.isSel() == false) {
                        sel = -1;
                        break;
                    } else {
                        sel += 1;
                    }
                }


                selectAllCheckBox.setSelected(sel==groupDataModel.getElementVOS().size());

            }
        });
        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<GroupVO>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<GroupVO>> observable, TreeItem<GroupVO> oldValue, TreeItem<GroupVO> newValue) {

                updateMenuInfo(newValue);
            }
        });


        leaderChipView.setChipFactory((emailJFXChipView, email) -> new JFXDefaultChip<User>(emailJFXChipView, email) {
            {
                if (getItem() != null) {
                    try {
                        Node image = SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".user-tie");
                        image.getStyleClass().add("chip-icon");
                        root.getChildren().add(0, image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        leaderChipView.setPredicate((user, val) -> {

            val = Pinyin4jUtil.toPinYinLowercase(val);
            return Pinyin4jUtil.toPinYinLowercase(user.getName()).contains(val);
        });

        memberChipView.setPredicate((user, val) -> {

            val = Pinyin4jUtil.toPinYinLowercase(val);
            return Pinyin4jUtil.toPinYinLowercase(user.getName()).contains(val);
        });


        memberChipView.setChipFactory((emailJFXChipView, email) -> new JFXDefaultChip<User>(emailJFXChipView, email) {
            {
                if (getItem() != null) {
                    try {
                        Node image = SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".user");
                        image.getStyleClass().add("chip-icon");
                        root.getChildren().add(0, image);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        leaderChipView.setSuggestionsCellFactory(param -> new JFXListCell<>());
        memberChipView.setSuggestionsCellFactory(param -> new JFXListCell<>());

        initLoadData();

    }

    private void initLoadData() {

        loadingTreeData();

    }

    private void loadingTreeData() {
        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(GroupFeign.class).getGroupList(groupType))
                .addConsumerInPlatformThread(rel -> buildGroupTree(rel)).onException(e -> e.printStackTrace()).run();
    }

    private void updateMenuInfo(TreeItem<GroupVO> newValue) {
        buttonBar.setVisible(false);
        if (newValue == null) {
            groupId = -1;
            groupDataModel.setSelectedGroup(null);
            return;
        }

        GroupVO groupVO = newValue.getValue();
        groupDataModel.setSelectedGroup(groupVO);
        groupId = groupVO.getId();
        codeTextField.textProperty().unbind();
        codeTextField.textProperty().bind(groupVO.codeProperty());
        nameTextField.textProperty().unbind();
        nameTextField.textProperty().bind(groupVO.nameProperty());
        descTextArea.textProperty().unbind();
        descTextArea.textProperty().bind(groupVO.descriptionProperty());

    }

    private void buildMenuTree(List<MenuVO> menuVOList) {


        if (menuVOList.isEmpty()) {
            menuTreeView.setRoot(null);
            return;
        }
        groupDataModel.getMenuVOS().clear();
        for (MenuVO menuVO : menuVOList) {
            groupDataModel.getMenuVOS().add(menuVO);
        }

        MenuVO root = menuVOList.stream().min(Comparator.comparing(MenuVO::getParentId)).get();
        Map<Integer, List<MenuVO>> map = menuVOList.stream().collect(Collectors.groupingBy(MenuVO::getParentId));
        map.remove(root.getParentId());

        FilterableCheckBoxTreeItem<MenuVO> rootNode = new FilterableCheckBoxTreeItem<MenuVO>(root);
        groupDataModel.setSelectedMenuVO(root);
        menuTreeView.setRoot(rootNode);
        menuTreeView.setShowRoot(false);
        addMenuChildrenNode(rootNode, map);
        rootNode.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            if (menuTreeSearchTextField.getText() == null || menuTreeSearchTextField.getText().isEmpty()) {
                return null;
            }
            return TreeItemPredicate.create(actor -> {
                String searchText = Pinyin4jUtil.toPinYinLowercase(menuTreeSearchTextField.getText());
                String itemText = Pinyin4jUtil.toPinYinLowercase(actor.getTitle());
                return itemText.contains(searchText);
            });
        }, menuTreeSearchTextField.textProperty()));
        menuTreeView.getSelectionModel().select(1);

        rootNode.setExpanded(true);
    }

    private void addMenuChildrenNode(FilterableCheckBoxTreeItem<MenuVO> parentNode, Map<Integer, List<MenuVO>> map) {

        List<MenuVO> childrenList = map.get(parentNode.getValue().getId());

        if (childrenList != null) {

            for (MenuVO menu : childrenList) {

                FilterableCheckBoxTreeItem<MenuVO> clildrenNode = new FilterableCheckBoxTreeItem<>(menu);
                clildrenNode.selectedProperty().bindBidirectional(menu.selProperty());
                parentNode.getInternalChildren().add(clildrenNode);
                addMenuChildrenNode(clildrenNode, map);
            }
            parentNode.setExpanded(true);

        }

    }

    private void buildGroupTree(List<GroupVO> groupVOList) {

        if (groupVOList.isEmpty()) {
            treeView.setRoot(null);
            return;
        }
        Map<Integer, List<GroupVO>> map = groupVOList.stream().collect(Collectors.groupingBy(GroupVO::getParentId));
        GroupVO root = new GroupVO();
        root.setId(-1);

        FilterableTreeItem<GroupVO> rootNode = new FilterableTreeItem<GroupVO>(root);
        treeView.setRoot(rootNode);
        treeView.setShowRoot(false);
        addChildrenNode(rootNode, map);
        rootNode.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            if (treeSearchTextField.getText() == null || treeSearchTextField.getText().isEmpty()) {
                return null;
            }
            return TreeItemPredicate.create(actor -> {
                String searchText = Pinyin4jUtil.toPinYinLowercase(treeSearchTextField.getText());
                String itemText = Pinyin4jUtil.toPinYinLowercase(actor.getName());
                return itemText.contains(searchText);
            });
        }, treeSearchTextField.textProperty()));
        rootNode.setExpanded(true);
        treeView.getSelectionModel().select(rootNode);
    }

    private void addChildrenNode(FilterableTreeItem<GroupVO> parentNode, Map<Integer, List<GroupVO>> map) {

        List<GroupVO> childrenList = map.get(parentNode.getValue().getId());

        if (childrenList != null) {

            for (GroupVO menu : childrenList) {

                FilterableTreeItem<GroupVO> clildrenNode = new FilterableTreeItem<>(menu);
                parentNode.getInternalChildren().add(clildrenNode);
                parentNode.setExpanded(true);
                addChildrenNode(clildrenNode, map);
            }
        }

    }

    @ActionMethod("addGroup")
    private void addGroup() {
        codeTextField.textProperty().unbind();
        codeTextField.setText("");
        nameTextField.textProperty().unbind();
        nameTextField.setText("");
        descTextArea.textProperty().unbind();
        descTextArea.setText("");
        buttonBar.setVisible(true);
        defineButton.setVisible(true);

    }

    @ActionMethod("editGroup")
    private void editGroup() {
        codeTextField.textProperty().unbind();
        nameTextField.textProperty().unbind();
        descTextArea.textProperty().unbind();
        buttonBar.setVisible(true);
        defineButton.setVisible(false);
    }

    @ActionMethod("cancelGroup")
    private void cancelGroup() {
        buttonBar.setVisible(false);
    }

    @ActionMethod("createGroup")
    private void createGroup() {
        GroupVO groupVO = new GroupVO();
        groupVO.setParentId(groupId);
        groupVO.setGroupType(groupType);
        groupVO.setCode(codeTextField.getText());
        groupVO.setName(nameTextField.getText());
        groupVO.setDescription(descTextArea.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> buttonBar.setDisable(true)).addSupplierInExecutor(() -> Request.connector(GroupFeign.class).addGroup(groupVO))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        loadingTreeData();
                    }
                })
                .withFinal(() -> {
                    buttonBar.setDisable(false);
                    buttonBar.setVisible(false);
                })
                .run();


    }

    @ActionMethod("updateGroup")
    private void updateGroup() {
        GroupVO groupVO = new GroupVO();
        groupVO.setId(groupDataModel.getSelectedGroup().getId());
        groupVO.setParentId(groupDataModel.getSelectedGroup().getParentId());
        groupVO.setGroupType(groupType);
        groupVO.setCode(codeTextField.getText());
        groupVO.setName(nameTextField.getText());
        groupVO.setDescription(descTextArea.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> buttonBar.setDisable(true)).addSupplierInExecutor(() -> Request.connector(GroupFeign.class).updateGroup(groupVO))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        loadingTreeData();
                    }
                })
                .withFinal(() -> {
                    buttonBar.setDisable(false);
                    buttonBar.setVisible(false);
                })
                .run();


    }

    @ActionMethod("deleteGroup")
    private void deleteGroup() {
        GroupVO groupVO = groupDataModel.getSelectedGroup();

        JFXAlert alert = new JFXAlert((Stage) contentPane.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("消息提示"));
        layout.setBody(new Label("确实删除【" + groupVO.getName() + "】吗？"));
        JFXButton closeButton = new JFXButton("取消");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        JFXButton determineButton = new JFXButton("确定");
        determineButton.setOnAction(event -> {
            alert.hideWithAnimation();
            ProcessChain.create()
                    .addSupplierInExecutor(() -> Request.connector(GroupFeign.class).deleteGroup(groupVO))
                    .addConsumerInPlatformThread(rel -> {
                        if (rel >= 0) {
                            loadingTreeData();
                        }
                    }).onException(e -> e.printStackTrace())

                    .run();
        });
        layout.setActions(closeButton, determineButton);
        alert.setContent(layout);
        alert.show();




    }

    @ActionMethod("saveAuthority")
    private void saveAuthority() {

        List<MenuVO> saveMenuList = new ArrayList<>();

        for (MenuVO menuVO : groupDataModel.getMenuVOS()) {
            if (menuVO.isSel()) {
                saveMenuList.add(menuVO);
                List<ElementVO> elementList = menuVO.getElementVOS().stream().filter(elementVO -> elementVO.isSel()).collect(Collectors.toList());
                menuVO.getElementVOS().clear();
                menuVO.getElementVOS().addAll(elementList);
            }
        }

        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(GroupFeign.class).modifyMenuAuthority(groupId, saveMenuList))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        groupDataModel.setSelectedMenuVO(new MenuVO());
                    }
                }).onException(e -> e.printStackTrace())
                .withFinal(() -> groupAuthorityDialog.close())

                .run();
    }

    @ActionMethod("cancelAuthority")
    private void cancelAuthorityButton() {
        groupAuthorityDialog.close();
    }

    @ActionMethod("selectAllElement")
    private void selectAllElement() {

        List<ElementVO> elementVOS = groupDataModel.getElementVOS();
        for (ElementVO elementVO : elementVOS) {
            elementVO.setSel(selectAllCheckBox.isSelected());
        }
    }

    @ActionMethod("handlerAuthority")
    private void handlerAuthority() throws FlowException {
        groupAuthorityDialog.show((StackPane) viewFlowContext.getRegisteredObject("rootPane"));
        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(GroupFeign.class).getAuthorityMenuElementAll(groupId))
                .addConsumerInPlatformThread(rel -> buildMenuTree(rel)).onException(e -> e.printStackTrace())
                .run();
    }

    @ActionMethod("cancelUser")
    private void cancelUser() throws FlowException {
        groupUserDialog.close();
    }

    @ActionMethod("modifiyUsers")
    private void modifiyUsers() throws FlowException {

        Set<Integer> ls = new HashSet<>();
        Set<Integer> ms = new HashSet<>();

        for (User user : memberChipView.getChips()) {
            ms.add(user.getId());
        }
        for (User user : leaderChipView.getChips()) {
            ls.add(user.getId());
        }

        Map<String, String> parmMap = new HashMap<>();
        parmMap.put("members", ms.toString());
        parmMap.put("leaders", ls.toString());

        ProcessChain.create()
                .addSupplierInExecutor(() -> Request.connector(GroupFeign.class).modifiyUsers(groupId, parmMap))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        groupUserDialog.close();
                    }
                }).onException(e -> e.printStackTrace())
                .run();
    }

    @ActionMethod("handlerUser")
    private void handlerUser() {
        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                    leaderChipView.getChips().clear();
                    leaderChipView.getSuggestions().clear();
                    memberChipView.getChips().clear();
                    memberChipView.getSuggestions().clear();
                })
                .addSupplierInExecutor(() -> Request.connector(GroupFeign.class).getUsers(groupId))
                .addConsumerInPlatformThread(rel -> {
                    leaderChipView.getChips().addAll(rel.getLeaders());
                    leaderChipView.getSuggestions().addAll(rel.getUsers());
                    memberChipView.getChips().addAll(rel.getMembers());
                    memberChipView.getSuggestions().addAll(rel.getUsers());
                }).withFinal(() -> groupUserDialog.show((StackPane) viewFlowContext.getRegisteredObject("rootPane")))
                .onException(e -> e.printStackTrace()).run();

    }
    @OnEvent("test-message")
    private void onNewChatMessage(Event<String> e) {
        System.err.println(this.getClass() + "\t" + e.getContent());
    }

}
