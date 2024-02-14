package com.example.application.views.main;

import com.example.application.views.main.wrapper.FileDownloadWrapper;
import com.vaadin.flow.dom.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VaadinTest {

    public static final String CLICKED = "Clicked";

    @Mock
    private FileDownloadWrapper fileDownloadWrapper;


    @BeforeEach
    void setUp() {
        when(fileDownloadWrapper.getElement()).thenReturn(new Element("div"));
    }

    @Test
    void getById() {
        MainView mainView = new MainView(fileDownloadWrapper);

        mainView.getElement().executeJs("this.document.getElementById(\"view-button-1\").click()");

//        assertEquals(CLICKED, mainView.text.getText());
    }


}
