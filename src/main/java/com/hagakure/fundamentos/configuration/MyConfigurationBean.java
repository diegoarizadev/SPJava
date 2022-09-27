package com.hagakure.fundamentos.configuration;

import com.hagakure.fundamentos.bean.MyBean;
import com.hagakure.fundamentos.bean.MyBeanImplement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//Se marca con la notación @Configuration ya que se van agregar configuraciones.
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBeanImplement();
    }
}
