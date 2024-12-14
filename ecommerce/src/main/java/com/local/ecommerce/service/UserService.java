package com.local.ecommerce.service;

import com.local.ecommerce.dto.ResponseDto;
import com.local.ecommerce.dto.user.SignInDto;
import com.local.ecommerce.dto.user.SignInReponseDto;
import com.local.ecommerce.dto.user.SignupDto;
import com.local.ecommerce.exceptions.AuthenticationFailException;
import com.local.ecommerce.exceptions.CustomException;
import com.local.ecommerce.model.AuthenticationToken;
import com.local.ecommerce.model.User;
import com.local.ecommerce.repository.UserRepository;
import com.local.ecommerce.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {
        // Lógica para registrar el usuario sin necesidad del token
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            throw new CustomException("User already present");
        }
    
        String encryptedPassword = signupDto.getPassword();
        try {
            encryptedPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    
        Role role = Role.USER;  // Asumiendo que el rol por defecto es 'USER'
        if (signupDto.getRole() != null && (signupDto.getRole().equalsIgnoreCase("user") || signupDto.getRole().equalsIgnoreCase("admin"))) {
            role = Role.valueOf(signupDto.getRole().toUpperCase());
        }
    
        // Crear el usuario
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), role, encryptedPassword);
        userRepository.save(user);
    
        // Generar token de autenticación
        AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);
    
        return new ResponseDto("success", "User created successfully");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SignInReponseDto signIn(SignInDto signInDto) {
        // find user by email
        User user = userRepository.findByEmail(signInDto.getEmail());
    
        if (Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }
    
        try {
            if (!user.getPasswoprd().equals(hashPassword(signInDto.getPassword()))) {
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    
        AuthenticationToken token = authenticationService.getToken(user);
    
        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }
    
        // Add role to response
        return new SignInReponseDto("success", token.getToken(), user.getRole().toString());
    }
    

}