package com.poly.j6demo3;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class I18NConfig implements WebMvcConfigurer {

    @Bean("messageSource")
    public MessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setDefaultEncoding("utf-8");
        ms.setBasenames("classpath:i18n/messages","classpath:i18n/global");
        return ms;
    }

    @Bean("localeResolver")
    public LocaleResolver getLocaleResolver() {
        CookieLocaleResolver cookie = new CookieLocaleResolver();
//        SessionLocaleResolver session = new SessionLocaleResolver();
        cookie.setDefaultLocale(new Locale("vi")); // mặc định
        cookie.setCookiePath("/"); // đường dẫn
        cookie.setCookieMaxAge(10*24*60*60);
        return cookie;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        registry.addInterceptor(lci)
//                Gắn đổi ngôn ngữ vào tất cả các url trừ images
                .addPathPatterns("/**")
                .excludePathPatterns("/images/**");
    }

}
