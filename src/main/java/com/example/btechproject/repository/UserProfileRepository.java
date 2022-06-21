package com.example.btechproject.repository;

import com.example.btechproject.model.UserProfile;
import com.stripe.param.AccountCreateParams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile,Long> {
}
