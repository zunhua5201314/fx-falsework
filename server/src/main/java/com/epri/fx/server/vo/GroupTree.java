package com.epri.fx.server.vo;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-17 15:21
 */
public class GroupTree {

    int id;
    int parentId;

    String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
