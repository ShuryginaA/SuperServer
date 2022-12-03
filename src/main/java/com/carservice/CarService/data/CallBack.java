package com.carservice.CarService.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name="callback")
public class CallBack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="callback_id")
    private Long id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String comment;

}
