package com.example.demo.config;

import com.example.demo.Filter.XssFilter;
import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;
import java.util.Map;

/**
 * @author Nicole
 */
public class WebConfig {
    /**
     * xss过滤拦截器
     */
    @Bean
    public FilterRegistrationBean<Filter> xssFilterRegistrationBean() {

        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        //注册XSSFilter，使其生效
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");

        Map<String, String> initParameters = Maps.newHashMap();

        //excludes用于配置不需要参数过滤的请求url
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");

        //isIncludeRichText默认为true，
        //设置富文本（项目内约束以content为名或以WithHtml结尾）内容是否需要过滤
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);

        return filterRegistrationBean;
    }
}
