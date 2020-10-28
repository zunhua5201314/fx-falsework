package com.epri.fx.server.rest;

import com.epri.fx.server.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {


    @Autowired
    private MenuService menuService;

    @GetMapping
    public String getWelcomeMsg() {
        return "Hello,Spring Security";
    }

    @GetMapping("/helloAdmin")
    @PreAuthorize("hasAnyRole('admin')")
    public String helloAdmin() {
        return menuService.getMenuAll().toString();
    }

    @GetMapping("/helloUser")
    @PreAuthorize("hasAnyRole('user','normal')")
    public String helloUser() {
        return "Hello,user";
    }



}