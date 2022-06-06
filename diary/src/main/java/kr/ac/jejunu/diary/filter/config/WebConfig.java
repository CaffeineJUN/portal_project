package kr.ac.jejunu.diary.filter.config;

import kr.ac.jejunu.diary.filter.ApiServiceFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.Basic;
import javax.servlet.Filter;
import java.net.PortUnreachableException;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean apiServiceFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean=new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new ApiServiceFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        return filterFilterRegistrationBean;
    }
}