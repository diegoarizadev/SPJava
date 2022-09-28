package com.hagakure.fundamentos.configuration;

import com.hagakure.fundamentos.bean.MyBeanWhitProperties;
import com.hagakure.fundamentos.bean.MyBeanWhitPropertiesImplementation;
import com.hagakure.fundamentos.pojo.UserPojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(UserPojo.class) //Se va a representar la clase UserPojo como una propiedad(es)
public class GeneralConfiguration  {

    //Mapeo de variables.
    @Value("${value.name}") //Llama a las propiedades que se inicializaron en el archivo application.properties
    private String name; //Se almacena el valor en la variable

    @Value("${value.random}") //Llama a las propiedades que se inicializaron en el archivo application.properties
    private String random;

    @Value("${value.apellido}") //Llama a las propiedades que se inicializaron en el archivo application.properties
    private String apellido;

    @Bean
    public MyBeanWhitProperties function(){
        return new MyBeanWhitPropertiesImplementation(name, apellido);

    }

    @Bean
    public DataSource dataSource(){
        DataSourceBuilder dsb = DataSourceBuilder.create();
        dsb.driverClassName("org.h2.Driver");
        dsb.url("jdbc:h2:mem:dbJPA");
        dsb.username("javajpa");
        dsb.username("");

        return dsb.build();

    }

}
