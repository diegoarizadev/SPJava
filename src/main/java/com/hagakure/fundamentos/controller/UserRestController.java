package com.hagakure.fundamentos.controller;

import com.hagakure.fundamentos.caseuse.CreateUser;
import com.hagakure.fundamentos.caseuse.DeleteUser;
import com.hagakure.fundamentos.caseuse.GetUser;
import com.hagakure.fundamentos.caseuse.UpdateUser;
import com.hagakure.fundamentos.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Todos los metodos quedaran formateados con JSON
@RequestMapping("/api/users")
public class UserRestController {


    private GetUser getUser; //Injección de dependencia del caso de uso
    private CreateUser createUser;
    private DeleteUser deleteUser;
    private UpdateUser updateUser;

    public UserRestController(GetUser getUser, CreateUser newUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getUser = getUser;
        this.createUser = newUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    //Creación con la capa de servicios.
    //Obtener GET
    @GetMapping("/") //entre parentesis se puede agregar la ruta de consumo
    @ResponseBody
    List<User> get(){
        return getUser.getAll();
    }
    //Crear POST
    @PostMapping("/")
    ResponseEntity<User> newUser(@RequestBody User newUser){//@ResponseBody Cuerpo de entrada o request
        return new ResponseEntity<>(createUser.save(newUser), HttpStatus.CREATED);
    }
    //Eliminar DELETE
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        deleteUser.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT); //Responde un 204
    }
    //Actualizar PUT
    @PutMapping("/{id}")
    ResponseEntity replaceUser(@RequestBody User newUser, @PathVariable Long id){
        return new ResponseEntity<>(updateUser.update(newUser, id), HttpStatus.OK);
    }
}
