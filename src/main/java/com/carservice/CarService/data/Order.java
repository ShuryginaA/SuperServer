package com.carservice.CarService.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity(name="orders")
public class Order {

    public Order(String name, User user, Status status) {
        this.name = name;
        this.user = user;
        this.status = status;
    }

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
    private String date;

    @Column
    private Status status;




}
