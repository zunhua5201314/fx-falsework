package com.epri.fx.client.gui.feature;

import com.epri.fx.client.store.ApplicatonStore;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;

import java.util.Map;

public class FeatureHandler {

    private static FeatureHandler instance;

    private Map<String, String> permissionInfoMap;

    private FeatureHandler() {
    }

    public static synchronized FeatureHandler getInstance() {
        if (instance == null) {
            instance = new FeatureHandler();
        }
        return instance;
    }




    public void hideByFeature(Node node, String featureName) {
        node.visibleProperty().bind(new SimpleBooleanProperty(ApplicatonStore.getFeatureMap().get(featureName)!=null));
        node.managedProperty().bind(node.visibleProperty());
    }

    public void disableByFeature(Node node, String featureName) {
        node.disableProperty().bind(new SimpleBooleanProperty(ApplicatonStore.getFeatureMap().get(featureName)!=null));
    }

}
