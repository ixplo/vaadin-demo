package com.example.application.views.main;

import com.vaadin.flow.component.UI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

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
