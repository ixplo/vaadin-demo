package com.example.application.views.main;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.*;
import com.vaadin.testbench.unit.mocks.MockedUI;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.Principal;
import java.util.*;
import java.util.stream.Stream;

import static com.vaadin.testbench.unit.internal.ElementUtilsKt.getElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VaadinTest {

    public static final String CLICKED = "Clicked";

    private UI ui;
    @BeforeEach
    void setUp() {
    }

    @Test
    @Disabled
    void getById() {
        MainView mainView = new MainView();

        mainView.getElement().executeJs("this.document.getElementById(\"view-button-1\").click()");


        mainView.setEnabled(true);
        mainView.setVisible(true);
        assertEquals(CLICKED, mainView.text.getText());
    }


}
