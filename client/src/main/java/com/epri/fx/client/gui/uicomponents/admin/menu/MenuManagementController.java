package com.epri.fx.client.gui.uicomponents.admin.menu;

import com.epri.fx.client.gui.uicomponents.control.FilterableTreeItem;
import com.epri.fx.client.gui.uicomponents.control.TreeItemPredicate;
import com.epri.fx.client.model.MenuDataModel;
import com.epri.fx.client.request.Request;
import com.epri.fx.client.request.feign.admin.ElementFeign;
import com.epri.fx.client.request.feign.admin.MenuFeign;
import com.epri.fx.client.request.feign.admin.UserFeign;
import com.epri.fx.client.store.ApplicatonStore;
import com.epri.fx.client.utils.Pinyin4jUtil;
import com.epri.fx.server.entity.Element;
import com.epri.fx.server.msg.TableResultResponse;
import com.epri.fx.server.vo.ElementVO;
import com.epri.fx.server.vo.MenuVO;
import com.jfoenix.controls.*;
import com.jfoenix.svg.SVGGlyphLoader;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.action.ActionMethod;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.core.concurrent.ProcessChain;
import io.datafx.eventsystem.Event;
import io.datafx.eventsystem.OnEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @className: UserManagement
 * @author: liwen
 * @date: 2020/6/30 22:37
 */
@ViewController(value = "/fxml/admin/menu/menu_management.fxml", title = "菜单管理")
public class MenuManagementController {

    private static final String[] METHOD_OPTIONS = {"GET", "POST", "PUT", "DELETE"};
    private static final String[] TYPE_OPTIONS = {"uri", "button"};

    @FXML
    private StackPane rootPane;
    @FXML
    private VBox infoPane;
    @FXML
    private TableView<ElementVO> tableView;
    @FXML
    private TreeView treeView;
    @FXML
    private VBox treePane;
    @FXML
    private JFXSpinner treeViewSpinner;
    @FXML
    private JFXTextField treeSearchTextField;
    @FXML
    private JFXProgressBar elementProgressBar;
    //menu
    @FXML
    private JFXTextField titleTextField;
    @FXML
    private JFXTextField codeTextField;
    @FXML
    private JFXTextField orderNumTextField;
    @FXML
    private JFXTextField parentIdTextField;
    @FXML
    private JFXTextField iconTextField;
    @FXML
    private JFXTextArea descriptionTextAre;
    @FXML
    private JFXTextField hrefTextFeild;

    //element
    @FXML
    private VBox elementPane;
    @FXML
    private JFXTextField elementCodeTextField;
    @FXML
    private JFXComboBox<String> elementTypeComboBox;
    @FXML
    private JFXTextField elementNameTextField;
    @FXML
    private JFXTextField elementUriTextField;
    @FXML
    private JFXComboBox<String> elementMethodComBox;
    @FXML
    private JFXTextArea elementDesTextAre;


    @FXML
    @ActionTrigger("updateMenu")
    private JFXButton updateButton;
    @FXML
    @ActionTrigger("saveMenu")
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    @ActionTrigger("addElement")
    private JFXButton addElement;
    @FXML
    @ActionTrigger("addMenu")
    private ToggleButton addButton;
    @FXML
    @ActionTrigger("editMenu")
    private ToggleButton editButton;
    @FXML
    @ActionTrigger("deleteMenu")
    private ToggleButton deleteButton;
    @FXML
    private ButtonBar buttonBar;
    @FXML
    private GridPane gridPane;

    @FXML
    @ActionTrigger("cancelElementDialog")
    private JFXButton cancelElementButton;
    @FXML
    @ActionTrigger("upeateElement")
    private JFXButton updateElementButton;
    @FXML
    @ActionTrigger("createElement")
    private JFXButton saveElementButton;
    @FXML
    private JFXTextField elementfilterField;

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
    @FXML
    private TableColumn<ElementVO, String> descriptionColumn;
    @FXML
    private TableColumn<ElementVO, String> operateColumn;
    @FXML
    private JFXDialog dialog;


    @Inject
    private MenuDataModel menuDataModel;


    @PostConstruct
    private void init() {
        final ToggleGroup group = new ToggleGroup();
        addButton.setToggleGroup(group);
        editButton.setToggleGroup(group);
        deleteButton.setToggleGroup(group);

        try {
            addButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".add-circle-outline"));
            editButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".create-outline"));
            deleteButton.setGraphic(SVGGlyphLoader.getIcoMoonGlyph(ApplicatonStore.ICON_FONT_KEY+".trash-outline"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        elementTypeComboBox.getItems().addAll(TYPE_OPTIONS);
        elementMethodComBox.getItems().addAll(METHOD_OPTIONS);

        elementProgressBar.visibleProperty().bind(updateElementButton.disableProperty().or(saveElementButton.disableProperty()));
        elementProgressBar.managedProperty().bind(elementProgressBar.visibleProperty());

        updateElementButton.visibleProperty().bind(saveElementButton.visibleProperty().not());
        updateElementButton.managedProperty().bind(updateElementButton.visibleProperty());
        cancelElementButton.disableProperty().bind(saveElementButton.disableProperty().or(updateElementButton.disableProperty()));

        buttonBar.visibleProperty().bind(infoPane.disableProperty().not());
        buttonBar.managedProperty().bind(buttonBar.visibleProperty());
        saveButton.managedProperty().bind(saveButton.visibleProperty());
        updateButton.visibleProperty().bind(saveButton.visibleProperty().not());
        updateButton.managedProperty().bind(updateButton.visibleProperty());

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
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("uri"));
        requestTypeColumn.setCellValueFactory(new PropertyValueFactory<>("method"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        addOperateButtonToTable();
        menuDataModel.selectedElementIndexProperty().bind(tableView.getSelectionModel().selectedIndexProperty());
        FilteredList<ElementVO> filteredData = new FilteredList<>(menuDataModel.getElementVOS(), p -> true);
        tableView.setItems(filteredData);
        elementfilterField.textProperty().addListener((o, oldVal, newVal) -> {
            filteredData.setPredicate(elementProp -> {
                if (newVal == null || newVal.isEmpty()) {
                    return true;
                }
                String val = Pinyin4jUtil.toPinYinLowercase(newVal);
                return Pinyin4jUtil.toPinYinLowercase(elementProp.getName()).contains(val)
                        || elementProp.getMethod().toLowerCase().contains(val)
                        || elementProp.getType().toLowerCase().contains(val);
            });
        });


        treeViewSpinner.visibleProperty().bind(treePane.disableProperty());
        gridPane.disableProperty().bind(buttonBar.visibleProperty().not());
        treeView.setCellFactory(new Callback<TreeView, TreeCell>() {
            @Override
            public TreeCell<MenuVO> call(TreeView param) {
                return new TreeCell<MenuVO>() {
                    @Override
                    protected void updateItem(MenuVO menu, boolean empty) {
                        super.updateItem(menu, empty);
                        if (!empty && menu != null) {
                            setText(menu.getTitle());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });

        parentIdTextField.setDisable(true);

        cancelButton.setOnAction(event -> {
           infoPane.setDisable(true);
        });


        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<MenuVO>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<MenuVO>> observable, TreeItem<MenuVO> oldValue, TreeItem<MenuVO> newValue) {

                updateMenuInfo(newValue);
            }
        });


        initTreeData();


    }

    private void updateMenuInfo(TreeItem<MenuVO> newValue) {
        infoPane.setDisable(true);

        if (newValue == null) {
            return;
        }
        MenuVO menuVO = newValue.getValue();
        menuDataModel.setSelectMenuVO(menuVO);

        if (newValue.isLeaf()) {
            elementPane.setVisible(true);
            elementPane.setManaged(true);
            updateMenuElement();
        } else {
            elementPane.setVisible(false);
            elementPane.setManaged(false);
            menuDataModel.getElementVOS().clear();
        }

        codeTextField.setText(menuVO.getCode());
        orderNumTextField.setText(menuVO.getOrderNum() + "");
        titleTextField.setText(menuVO.getTitle());
        parentIdTextField.setText(menuVO.getParentId() + "");
        iconTextField.setText(menuVO.getIcon());
        hrefTextFeild.setText(menuVO.getHref());
        descriptionTextAre.setText(menuVO.getDescription());
    }

    private void updateMenuElement() {
        MenuVO menuVO = menuDataModel.getSelectMenuVO();
        ProcessChain.create()
                .addSupplierInExecutor(() -> {
                    TableResultResponse<Element> tableResultResponse = Request.connector(ElementFeign.class).getMenuElementList(menuVO.getId());
                    List<Element> elementList = tableResultResponse.getDatas();
                    List<ElementVO> elementVOList = new ArrayList<>();

                    elementList.forEach(element -> {
                        elementVOList.add(new ElementVO(element.getId(), element.getCode(), element.getType(), element.getName(), element.getUri(), element.getMenuId(), element.getParentId(), element.getPath(), element.getMethod(), element.getDescription()));
                    });
                    return elementVOList;
                })
                .addConsumerInPlatformThread(rel -> {
                    menuVO.getElementVOS().clear();
                    menuVO.getElementVOS().addAll(rel);
                    menuDataModel.getElementVOS().clear();
                    menuDataModel.getElementVOS().addAll(rel);
                }).onException(e -> e.printStackTrace())
                .run();
    }


    private <T> void setupCellValueFactory(JFXTreeTableColumn<ElementVO, T> column, Function<ElementVO, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<ElementVO, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

    private void initTreeData() {
        ProcessChain.create().addRunnableInPlatformThread(() -> treePane.setDisable(true))
                .addSupplierInExecutor(() -> Request.connector(MenuFeign.class).getMenuAll())
                .addConsumerInPlatformThread(rel -> {
                    buildMenuTree(rel);
                }).onException(e -> e.printStackTrace()).withFinal(() -> treePane.setDisable(false)).run();
    }


    private void buildMenuTree(List<MenuVO> menuList) {

        MenuVO rootMenu = menuList.stream().min(Comparator.comparing(MenuVO::getParentId)).get();
        Map<Integer, List<MenuVO>> map = menuList.stream().collect(Collectors.groupingBy(MenuVO::getParentId));
        map.remove(rootMenu.getParentId());

        FilterableTreeItem<MenuVO> rootNode = new FilterableTreeItem<MenuVO>(rootMenu);
        treeView.setRoot(rootNode);
        treeView.setShowRoot(false);
        addChildrenNode(rootNode, map);
        rootNode.predicateProperty().bind(Bindings.createObjectBinding(() -> {
            if (treeSearchTextField.getText() == null || treeSearchTextField.getText().isEmpty()) {
                return null;
            }
            return TreeItemPredicate.create(actor -> {
                String searchText = Pinyin4jUtil.toPinYinLowercase(treeSearchTextField.getText());
                String itemText = Pinyin4jUtil.toPinYinLowercase(actor.toString());
                return itemText.contains(searchText);
            });
        }, treeSearchTextField.textProperty()));
        rootNode.setExpanded(true);
        treeView.getSelectionModel().select(rootNode);


    }

    private void addChildrenNode(FilterableTreeItem<MenuVO> parentNode, Map<Integer, List<MenuVO>> map) {

        List<MenuVO> childrenList = map.get(parentNode.getValue().getId());

        if (childrenList != null) {

            for (MenuVO menu : childrenList) {

                FilterableTreeItem<MenuVO> clildrenNode = new FilterableTreeItem<>(menu);
                parentNode.getInternalChildren().add(clildrenNode);

                addChildrenNode(clildrenNode, map);
            }
        }

    }


    private void addOperateButtonToTable() {

        Callback<TableColumn<ElementVO, String>, TableCell<ElementVO, String>> cellFactory = new Callback<TableColumn<ElementVO, String>, TableCell<ElementVO, String>>() {
            @Override
            public TableCell<ElementVO, String> call(TableColumn param) {

                final TableCell<ElementVO, String> cell = new TableCell<ElementVO, String>() {

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
                            editElement();
                        });
                        delBut.setOnMouseClicked(event -> {
                            tableView.getSelectionModel().select(getIndex());
                            deleteElement();
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

        operateColumn.setCellFactory(cellFactory);


    }

    @ActionMethod("addMenu")
    private void addMenu() {

        infoPane.setDisable(false);
        saveButton.setVisible(true);

        codeTextField.setText("");
        orderNumTextField.setText("");
        titleTextField.setText("");
        parentIdTextField.setText(menuDataModel.getSelectMenuVO().getId() + "");
        iconTextField.setText("");
        hrefTextFeild.setText("");
        descriptionTextAre.setText("");
    }

    @ActionMethod("saveMenu")
    private void saveMenu() {

        MenuVO menuVO = new MenuVO();
        menuVO.setTitle(titleTextField.getText());
        menuVO.setCode(codeTextField.getText());
        menuVO.setIcon(iconTextField.getText());
        menuVO.setHref(hrefTextFeild.getText());
        menuVO.setOrderNum(Integer.parseInt(orderNumTextField.getText()));
        menuVO.setParentId(Integer.parseInt(parentIdTextField.getText()));
        menuVO.setDescription(descriptionTextAre.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> infoPane.setDisable(true))
                .addSupplierInExecutor(() -> Request.connector(MenuFeign.class).addMenu(menuVO))
                .addConsumerInPlatformThread(rel -> {
                    if (rel == 1) {
                        initTreeData();
                    }
                })
                .withFinal(() -> {
                    infoPane.setDisable(false);
                    treeView.getRoot().setExpanded(true);

                }).onException(e -> e.printStackTrace())
                .run();
    }

    @ActionMethod("editMenu")
    private void editMenu() {

        infoPane.setDisable(false);
        saveButton.setVisible(false);

    }

    @ActionMethod("updateMenu")
    private void updateMenu() {

        MenuVO menuVO = menuDataModel.getSelectMenuVO();
        menuVO.setTitle(titleTextField.getText());
        menuVO.setCode(codeTextField.getText());
        menuVO.setIcon(iconTextField.getText());
        menuVO.setHref(hrefTextFeild.getText());
        menuVO.setOrderNum(Integer.parseInt(orderNumTextField.getText()));
        menuVO.setParentId(Integer.parseInt(parentIdTextField.getText()));
        menuVO.setDescription(descriptionTextAre.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> infoPane.setDisable(true))
                .addSupplierInExecutor(() -> Request.connector(MenuFeign.class).updateMenu(menuVO))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        initTreeData();
                    }
                }).withFinal(() -> {
            infoPane.setDisable(false);
            treeView.getRoot().setExpanded(true);

        }).onException(e -> e.printStackTrace())
                .run();
    }

    @ActionMethod("deleteMenu")
    private void deleteMenu() {

        JFXAlert alert = new JFXAlert((Stage) rootPane.getScene().getWindow());
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("消息提示"));
        layout.setBody(new Label("确实删除【" + menuDataModel.getSelectMenuVO().getTitle() + "】吗？"));
        JFXButton closeButton = new JFXButton("取消");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        JFXButton determineButton = new JFXButton("确定");
        determineButton.setOnAction(event -> {
            alert.hideWithAnimation();
            ProcessChain.create()
                    .addSupplierInExecutor(() -> Request.connector(MenuFeign.class).deleteMenu(menuDataModel.getSelectMenuVO()))
                    .addConsumerInPlatformThread(rel -> {
                        if (rel >= 0) {
                            initTreeData();
                        }
                    }).withFinal(() -> {
                treeView.getRoot().setExpanded(true);

            }).onException(e -> e.printStackTrace())
                    .run();
        });
        layout.setActions(closeButton, determineButton);
        alert.setContent(layout);
        alert.show();

    }


    @ActionMethod("addElement")
    private void addElement() {

        elementCodeTextField.setText("");
        elementTypeComboBox.getSelectionModel().select(null);
        elementMethodComBox.getSelectionModel().select(null);
        elementNameTextField.setText("");
        elementUriTextField.setText("");
        elementCodeTextField.setText("");
        elementDesTextAre.setText("");


        saveElementButton.setManaged(true);
        saveElementButton.setVisible(true);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.show(rootPane);
    }

    @ActionMethod("createElement")
    private void createElement() {

        MenuVO menuVO = menuDataModel.getSelectMenuVO();
        Element newElect = new Element();

        newElect.setCode(elementCodeTextField.getText());
        newElect.setMenuId(menuVO.getId() + "");
        newElect.setName(elementNameTextField.getText());
        newElect.setUri(elementUriTextField.getText());
        newElect.setType(elementTypeComboBox.getSelectionModel().getSelectedItem());
        newElect.setMethod(elementMethodComBox.getSelectionModel().getSelectedItem());
        newElect.setDescription(elementDesTextAre.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                    saveElementButton.setDisable(true);
                })
                .addSupplierInExecutor(() -> Request.connector(ElementFeign.class).addElement(newElect))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        updateMenuElement();
                    }
                })
                .withFinal(() -> {
                    saveElementButton.setDisable(false);
                    dialog.close();
                }).onException(e -> e.printStackTrace())
                .run();

    }

    @ActionMethod("upeateElement")
    private void updateElement() {

        MenuVO menuVO = menuDataModel.getSelectMenuVO();
        Element newElect = new Element();
        newElect.setId(menuDataModel.getElementVOS().get(menuDataModel.getSelectedElementIndex()).getId());
        newElect.setCode(elementCodeTextField.getText());
        newElect.setMenuId(menuVO.getId() + "");
        newElect.setName(elementNameTextField.getText());
        newElect.setUri(elementUriTextField.getText());
        newElect.setType(elementTypeComboBox.getSelectionModel().getSelectedItem());
        newElect.setMethod(elementMethodComBox.getSelectionModel().getSelectedItem());
        newElect.setDescription(elementDesTextAre.getText());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                    updateElementButton.setDisable(true);
                })
                .addSupplierInExecutor(() -> Request.connector(ElementFeign.class).updateElement(newElect))
                .addConsumerInPlatformThread(rel -> {
                    if (rel >= 0) {
                        updateMenuElement();

                    }
                })
                .withFinal(() -> {
                    updateElementButton.setDisable(false);
                    dialog.close();
                }).onException(e -> e.printStackTrace())
                .run();

    }


    private void editElement() {

        ElementVO elementVO = menuDataModel.getElementVOS().get(menuDataModel.getSelectedElementIndex());
        elementCodeTextField.setText(elementVO.getCode());
        elementTypeComboBox.getSelectionModel().select(elementVO.getType());
        elementMethodComBox.getSelectionModel().select(elementVO.getMethod());
        elementNameTextField.setText(elementVO.getName());
        elementUriTextField.setText(elementVO.getUri());
        elementDesTextAre.setText(elementVO.getDescription());

        saveElementButton.setManaged(false);
        saveElementButton.setVisible(false);
        dialog.setTransitionType(JFXDialog.DialogTransition.TOP);
        dialog.show(rootPane);
    }

    private void deleteElement() {

        ElementVO elementVO = menuDataModel.getElementVOS().get(menuDataModel.getSelectedElementIndex());

        ProcessChain.create()
                .addRunnableInPlatformThread(() -> {
                })
                .addSupplierInExecutor(() -> Request.connector(ElementFeign.class).deleteElement(elementVO.getId()))
                .addConsumerInPlatformThread(rel -> {

                    if (rel >= 0) {
                        updateMenuElement();
                    }
                })
                .withFinal(() -> {

                }).onException(e -> e.printStackTrace())
                .run();

    }


    @ActionMethod("cancelElementDialog")
    private void cancelElementDialog() {
        dialog.close();
    }

    @OnEvent("test-message")
    private void onNewChatMessage(Event<String> e) {
        System.err.println(this.getClass() + "\t" + e.getContent());
    }
}
