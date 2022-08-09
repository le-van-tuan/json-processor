package vn.evolus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import vn.evolus.controller.GatewayServlet;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public FilterRegistrationBean<GatewayServlet> filter() {
        FilterRegistrationBean<GatewayServlet> bean = new FilterRegistrationBean<>();

        bean.setFilter(new GatewayServlet());
        bean.addUrlPatterns("/another/*");

        return bean;
    }
}
