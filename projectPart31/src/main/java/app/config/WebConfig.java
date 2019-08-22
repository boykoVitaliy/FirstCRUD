package app.config;

//Настройки конфігурації

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration//<--каже Spring що данийклас являється конфигураціоним і має залежності bean-компонентів

@EnableWebMvc//<--позволяє імпортувати конфігурацію Spring MWC з WebMvcConfigurer
@ComponentScan(basePackages = "app")//<--вказує Spring де шукати компоненти якими він керує (@Controller,@Service,@Repository);
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/res/**").addResourceLocations("/res/");
    }

    @Bean//<-- обєкти які керуються Spring
    ViewResolver viewResolver() {//<-- Вказуєм де імено шукати представлення в webapp
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

}
