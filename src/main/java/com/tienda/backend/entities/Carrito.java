package com.tienda.backend.entities;

import com.tienda.backend.enums.CarritoStatus;

import javax.persistence.*;

@Entity
@Table(name = "carritos")
public class Carrito {
    @Id
    @GeneratedValue
    public int id_car;

    @Column(nullable = false)
    public int total_car = 0;

    @ManyToOne
    @JoinColumn(name = "usu_car")
    public Usuario id_usu;

    @Column(nullable = false)
    public String status = CarritoStatus.ACTIVE.name();
}