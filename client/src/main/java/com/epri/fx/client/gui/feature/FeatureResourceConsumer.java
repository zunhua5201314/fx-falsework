package com.epri.fx.client.gui.feature;

import io.datafx.controller.ViewNode;
import io.datafx.controller.injection.scopes.ApplicationScoped;
import io.datafx.core.DataFXUtils;
import javafx.fxml.FXML;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class FeatureResourceConsumer {

    public void consumeResource(Object object) {

        Class<? extends Object> cls = object.getClass();
        for (final Field field : DataFXUtils.getInheritedDeclaredFields(cls)) {

            if (field.getAnnotation(FXML.class) != null || field.getAnnotation(ViewNode.class) != null) {

                List<Annotation> fieldAnnotations = Arrays.asList(field.getAnnotations());

                for (Annotation annotation : fieldAnnotations) {
                    if (annotation instanceof DisabledByFeature) {
                        FeatureHandler.getInstance().disableByFeature(DataFXUtils.getPrivileged(field, object), ((DisabledByFeature) annotation).value());
                    } else if (annotation instanceof HideByFeature) {
                        FeatureHandler.getInstance().hideByFeature(DataFXUtils.getPrivileged(field, object), ((HideByFeature) annotation).value());
                    }
                }
            }
        }
    }


}
