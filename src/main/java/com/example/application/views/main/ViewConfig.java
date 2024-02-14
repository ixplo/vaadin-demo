package com.example.application.views.main;

import com.example.application.views.main.wrapper.FileDownloadWrapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public FileDownloadWrapper fileDownloadWrapper() {
        return null;
    }
}
