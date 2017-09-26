package io.gh;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableWebMvc
public class VueApp extends SpringBootServletInitializer {
    @Getter
    private static ApplicationContext context;

    public static void main(String[] args) {
        VueApp.context = SpringApplication.run(VueApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(VueApp.class);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);

            StringBuilder builder = new StringBuilder();
            for (String beanName : beanNames) {
                builder.append(beanName).append("\t");
            }
            log.info("Let's inspect the beans provided by Spring Boot: {}", builder.toString());
        };
    }

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
//        return new JettyEmbeddedServletContainerFactory("/vue", 8080);
        return new JettyEmbeddedServletContainerFactory();
//        return new TomcatEmbeddedServletContainerFactory();
    }

}
