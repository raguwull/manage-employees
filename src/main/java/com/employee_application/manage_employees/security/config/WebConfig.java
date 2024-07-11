package com.employee_application.manage_employees.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.employee_application.manage_employees.formatter.date.DateFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final DateFormatter dateFormatter;

    public WebConfig(DateFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(dateFormatter);
    }
}
