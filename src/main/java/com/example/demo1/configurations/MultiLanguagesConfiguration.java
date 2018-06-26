package com.example.demo1.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
@Configuration
public class MultiLanguagesConfiguration implements WebMvcConfigurer {

    /** This bean and method is used to set the default language for the web browser*/
    @Bean
    @Description("Set Default Language")
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(new Locale("en"));
        return sessionLocaleResolver;
    }

    /** This bean is used to create and enable us to change language using parameter that we've created [lang]
     *  for example: localhost:1234/index/?lang=km */
    @Bean
    @Description("Enable change language using parameter [lang]")
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /** this method is overridden to help the browser to remember the default language that we have set above
     *  by adding it into the registry and in order to override this method we do need to implements
     *  WebMvcConfigurer interface */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
