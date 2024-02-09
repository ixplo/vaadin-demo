package com.example.application.views.main;

import cn.hutool.core.bean.BeanUtil;
import com.example.application.views.main.model.Person;
import com.example.application.views.main.wrapper.FileDownloadWrapper;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;


@PageTitle("Main")
@Route(value = "")
public class MainView extends HorizontalLayout {
    public static final String CLICKED = "Clicked";

    TextField name;
    Button sayHello;
    FileDownloadWrapper buttonWrapper;
    Text text;
    Grid<Person> grid;
    Grid.Column<Person> viewColumn;
    ConfirmDialog dialog = new ConfirmDialog();
    private Integer counter = 0;

    public MainView() {
        name = new TextField("Your name");
        text = new Text("Before");
        configureHelloButton();
        configureGrid();
        configureDownloadButton();
        setVerticalComponentAlignment(Alignment.BASELINE, name, sayHello);
        setHeight("500px");
        add(name, sayHello, text, grid, buttonWrapper);
    }

    private void configureDownloadButton() {
        Button downloadButton = new Button("Click to download");
        buttonWrapper = new FileDownloadWrapper(
                new StreamResource("foo.txt", () -> new ByteArrayInputStream("foo".getBytes())));
        buttonWrapper.wrapComponent(downloadButton);
    }

    private void configureHelloButton() {
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);
    }

    private void configureGrid() {
        List<Person> persons = Arrays.asList(new Person("Poul", "23"), new Person("Jack", "24"));
        grid = new Grid<>(Person.class);
        grid.addClassName("main-view-grid-1");
        grid.setItems(persons);
        viewColumn = grid.addComponentColumn((person) -> {
            Button viewButton = new Button("View");
            viewButton.addClassName("main-view-button-1");
            viewButton.setId("view-button-1");
            viewButton.addClickListener(buttonClickEvent -> {
                text.setText(CLICKED + " " + ++counter);
                dialog.removeAll();
                VerticalLayout dialogLayout = new VerticalLayout();
                BeanUtil.beanToMap(person)
                        .forEach((key, value) -> {
                            TextField field = new TextField(key);
                            field.setValue(String.valueOf(value));
                            field.setVisible(true);
                            dialogLayout.add(field);
                        });
                dialog.add(dialogLayout);
                dialog.setHeader("Details");
                dialog.setConfirmText("OK");
                dialog.setHeight("550px");
                dialog.setWidth("300px");
                dialog.open();
            });
            return viewButton;
        });

        grid.setId("grid-1");
        grid.setVisible(true);
    }

}
