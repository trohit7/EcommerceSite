package com.example.btechproject.repository;

import com.example.btechproject.model.Order;
import com.example.btechproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    List<Order> findAllByUserOrderByCreatedDateDesc(User user);
}
