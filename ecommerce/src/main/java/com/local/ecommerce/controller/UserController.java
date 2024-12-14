package com.local.ecommerce.controller;

import com.local.ecommerce.dto.ResponseDto;
import com.local.ecommerce.dto.user.SignInDto;
import com.local.ecommerce.dto.user.SignInReponseDto;
import com.local.ecommerce.dto.user.SignupDto;
import com.local.ecommerce.exceptions.AuthenticationFailException;
import com.local.ecommerce.repository.UserRepository;
import com.local.ecommerce.service.AuthenticationService;
import com.local.ecommerce.service.UserService;
import com.local.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }

    @PostMapping("/signIn")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }

    @PreAuthorize("hasRole('ADMIN')") 
    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }


}
