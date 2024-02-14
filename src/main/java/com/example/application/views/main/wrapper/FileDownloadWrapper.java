package com.example.application.views.main.wrapper;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.server.StreamResource;

@Tag("file-download-wrapper")
@JsModule("./file-download-wrapper.js")
public class FileDownloadWrapper extends LitTemplate implements HasSize {

    @Id("download-link")
    protected Anchor anchor;

    public FileDownloadWrapper() {
        anchor.getElement().setAttribute("download", true);
    }

    public FileDownloadWrapper(StreamResource streamResource) {
        this();
        setResource(streamResource);
    }

    public void setResource(StreamResource streamResource) {
        anchor.setHref(streamResource);
    }

    public void wrapComponent(Component component) {
        anchor.removeAll();
        if (component != null) {
            anchor.add(component);
        }
    }

}
