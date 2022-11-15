package com.carservice.CarService.data;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name="orders")
public class Order {

    public enum Status{
       CREATED, IN_PROGRESS,READY
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="order_id")
    private Long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    @Column
    private Status status;


}
