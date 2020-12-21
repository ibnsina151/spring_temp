package com.example.demo.controller;

import com.example.demo.utils.JWTUtils;
import com.example.demo.utils.MyUserDetailsService;
import com.example.demo.viewmodel.AuthenticationRequest;
import com.example.demo.viewmodel.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserLogin {

    @Autowired
    private AuthenticationManager mAuthenticationManager;

    @Autowired
    private MyUserDetailsService mMyUserDetailsService;

    @Autowired
    private JWTUtils mJWTUtils;


    @RequestMapping("/login")
    public String login(){
        return "Hello World";
    }

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
            mAuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));

        }catch (BadCredentialsException e){
            throw new Exception("Incorrect username or password",e);
        }

        final UserDetails userDetails = mMyUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = mJWTUtils.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
