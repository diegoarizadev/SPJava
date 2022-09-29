package com.hagakure.fundamentos.caseuse;

import com.hagakure.fundamentos.entity.User;
import com.hagakure.fundamentos.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser){
        return userService.save(newUser);
    }
}
