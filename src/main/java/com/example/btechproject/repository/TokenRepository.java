package com.example.btechproject.repository;

import com.example.btechproject.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
}
