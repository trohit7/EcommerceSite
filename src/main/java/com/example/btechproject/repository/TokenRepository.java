package com.example.btechproject.repository;

import com.example.btechproject.model.AuthenticationToken;
import com.example.btechproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);


}
