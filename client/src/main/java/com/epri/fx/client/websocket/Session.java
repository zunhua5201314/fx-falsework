package com.epri.fx.client.websocket;

import io.datafx.controller.injection.scopes.ApplicationScoped;
import io.datafx.controller.injection.scopes.FlowScoped;

/**
 * @description:
 * @className: Session
 * @author: liwen
 * @date: 2020/6/24 16:17
 */
@ApplicationScoped
public class Session {

    private int counter = 0;

    public Session() {

    }

    public void print(String from) {
        System.out.println(from + ": counter = " + counter++);
    }
}
