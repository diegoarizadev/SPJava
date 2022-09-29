package com.hagakure.fundamentos.service;

import com.hagakure.fundamentos.entity.User;
import com.hagakure.fundamentos.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private final Log LOG = LogFactory.getLog(UserService.class);
    //Injección de dependencia.
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Metodo para almacenar información
    @Transactional //La anotación permite hacer un rollback cuando se haya encontrado un error..
    public void saveTransactional(List<User> users){
        users.stream().peek(user -> LOG.info("--> UserService - saveTransactional : Usuario registrado : " + user)).forEach(user -> userRepository.save(user)); //Peek = registra o mostrar por pantalla lo que se viene registrando.
    }

    //Retornara todos los usuarios en una lista.
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
