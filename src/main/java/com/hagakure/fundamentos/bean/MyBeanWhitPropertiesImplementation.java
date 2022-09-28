package com.hagakure.fundamentos.bean;

public class MyBeanWhitPropertiesImplementation implements  MyBeanWhitProperties{
 private String nombre, apellido;

    public MyBeanWhitPropertiesImplementation(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return "My name is "+nombre +" "+ apellido;
    }
}
