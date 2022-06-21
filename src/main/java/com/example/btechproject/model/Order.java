package com.example.btechproject.model;

import com.example.btechproject.dto.order.PlaceOrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "session_id")
    private String sessionId;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Orderitem> orderItems;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Order(PlaceOrderDto orderDto, User user, String sessionId){
        this.user = user;
        this.createdDate = new Date();
        this.totalPrice = orderDto.getTotalPrice();
        this.sessionId = sessionId;
    }


//    public Order(PlaceOrderDto orderDto, User user, String sessionId){
//        this.user = user;
//        this.createdDate = new Date();
//        this.totalPrice = orderDto.getTotalPrice();
//        this.sessionId = sessionId;
//    }



}
