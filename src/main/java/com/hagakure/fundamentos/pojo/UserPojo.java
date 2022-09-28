package com.hagakure.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding //Inyectar el modelo como dependencia o POJO
@ConfigurationProperties(prefix = "user") //prefijo para el archivo de las propiedades.
public class UserPojo {

    private String email, contrasena;
    private int edad;

    public UserPojo(String email, String contrasena, int edad) {
        this.email = email;
        this.contrasena = contrasena;
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
