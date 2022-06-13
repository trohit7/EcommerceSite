package com.example.btechproject.repository;

import com.example.btechproject.model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderitemsRepository extends JpaRepository<Orderitem,Integer> {
}
