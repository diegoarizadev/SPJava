package com.hagakure.fundamentos.configuration;

import com.hagakure.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//Se marca con la notación @Configuration ya que se van agregar configuraciones.
public class MyConfigurationBean {
    @Bean
    public MyBean beanOperation(){
        return new MyBeanTwoImplement(); //No se  invoca al Qualifier
    }

    @Bean
    public MyOperation beanOperationSum(){
        return new MyOperationImplement(); //No se  invoca al Qualifier
    }

    @Bean
    public MyBeanWithDependency beanOperationSumWhitDependency(MyOperation operation){
        return new MyBeanWithDependencyImplement(operation);
    }
}
