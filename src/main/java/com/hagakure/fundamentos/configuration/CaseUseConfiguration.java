package com.hagakure.fundamentos.configuration;


import com.hagakure.fundamentos.caseuse.GetUser;
import com.hagakure.fundamentos.caseuse.GetUserImplement;
import com.hagakure.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService );
    }
}
