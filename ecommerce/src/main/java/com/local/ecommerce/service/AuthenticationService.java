package com.local.ecommerce.service;

import com.local.ecommerce.exceptions.AuthenticationFailException;
import com.local.ecommerce.model.AuthenticationToken;
import com.local.ecommerce.model.User;
import com.local.ecommerce.repository.TokenRepository;
import com.local.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token) {
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)) {
            return null;
        }
        // authenticationToken is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        // null check
        if (Objects.isNull(token)) {
            // throw an exception
            throw new AuthenticationFailException("Token not present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("Token not valid");
        }
    }

    // Método para verificar si el token corresponde a un usuario autenticado
    public boolean isAuthenticated(String token) {
        return getUser(token) != null; // Si getUser() no devuelve null, el token es válido
    }

    // Método para verificar si el usuario tiene el rol especificado
    public boolean hasRole(String token, String role) {
        User user = getUser(token); // Obtener el usuario asociado al token
        return user != null && user.getRole().name().equalsIgnoreCase(role); // Verificar si el rol coincide
    }

    public String getTokenByEmail(String email) {
        // Buscar el usuario por su correo electrónico
        User user = userRepository.findByEmail(email);
        
        if (user != null) {
            // Si el usuario existe, obtener el token asociado
            AuthenticationToken authToken = tokenRepository.findByUser(user);
            return (authToken != null) ? authToken.getToken() : null;
        }
        
        return null;  // Si el usuario no existe
    }
    
}