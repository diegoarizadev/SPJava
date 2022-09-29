package com.hagakure.fundamentos.controller;

import com.hagakure.fundamentos.caseuse.GetUser;
import com.hagakure.fundamentos.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Todos los metodos quedaran formateados con JSON
@RequestMapping("/api/users")
public class UserRestController {


    private GetUser getUser; //Injección de dependencia del caso de uso

    public UserRestController(GetUser getUser) {
        this.getUser = getUser;
    }

    //Creación con la capa de servicios.
    //Crear
    //Obtener
    @GetMapping//("/") //entre parentesis se puede agregar la ruta de consumo
    @ResponseBody
    List<User> get(){
        return getUser.getAll();
    }
    //Eliminar
    //Actualizar

}
