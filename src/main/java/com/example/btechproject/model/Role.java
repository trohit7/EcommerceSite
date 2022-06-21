package com.example.btechproject.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;




@Data
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private  String name;
}
