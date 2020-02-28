package epam.ua.javacore.config;

import epam.ua.javacore.annotation.TimedPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(value = {"epam.ua.javacore.service","epam.ua.javacore.repository"})
@Import({WebConfig.class})
public class SpringConfig {

@Bean
TimedPostProcessor timedPostProcessor(){
    return new TimedPostProcessor();
};


}
