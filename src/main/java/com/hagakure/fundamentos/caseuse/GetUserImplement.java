package com.hagakure.fundamentos.caseuse;

import com.hagakure.fundamentos.entity.User;
import com.hagakure.fundamentos.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;//Injección de dependencia,.

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
