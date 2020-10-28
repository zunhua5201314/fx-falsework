package com.epri.fx.client.gui.uicomponents.container;

import com.jfoenix.controls.JFXTabPane;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import io.datafx.controller.context.ViewContext;
import io.datafx.controller.flow.FlowContainer;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * @description:
 * @className: TabPaneFlowContainer
 * @author: liwen
 * @date: 2020/6/24 18:09
 */
public class TabPaneFlowContainer implements FlowContainer<JFXTabPane> {

    private final JFXTabPane tabPane;
    private final HashMap<String, Tab> tabsMap = new HashMap<>();


    public TabPaneFlowContainer() {
        this(new JFXTabPane());
    }

    public TabPaneFlowContainer(JFXTabPane tabPane) {
        this.tabPane = tabPane;
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);


    }

    @Override
    public <U> void setViewContext(ViewContext<U> context) {

        String title = context.getMetadata().getTitle();
        Class controller = context.getController().getClass();
        Tab tab = tabsMap.get(controller.getName());
        if (tab == null ) {
            tab = new Tab(title);
            tab.setUserData(context.getController());
            tab.setContent(context.getRootNode());
            tabPane.getTabs().add(tab);
            tabsMap.put(controller.getName(), tab);
            tab.setGraphic(new MaterialDesignIconView(MaterialDesignIcon.HOME));
            tab.setOnClosed(event -> {
                tabsMap.remove(controller.getName());
                try {
                    context.destroy();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });


        }


        tabPane.getSelectionModel().select(tab);

    }

    @Override
    public JFXTabPane getView() {
        return tabPane;
    }
}
