package com.local.ecommerce.repository;

import com.local.ecommerce.model.AuthenticationToken;
import com.local.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
    AuthenticationToken findByUserEmail(String email);  
}