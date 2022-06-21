package com.example.btechproject.service;

import com.example.btechproject.model.Orderitem;
import com.example.btechproject.repository.OrderitemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OrderItemsService {

    @Autowired
    private OrderitemsRepository orderitemsRepository;

    public void addOrderedProducts(Orderitem orderitem){
        orderitemsRepository.save(orderitem);
    }
}
