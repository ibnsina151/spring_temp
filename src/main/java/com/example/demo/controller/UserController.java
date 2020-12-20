package com.example.demo.controller;

import com.example.demo.viewmodel.UserDetailsReqestModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController  {

    @PostMapping
    public String createUser(@RequestBody UserDetailsReqestModel userDetails){
        return userDetails.toString();
    }

}
