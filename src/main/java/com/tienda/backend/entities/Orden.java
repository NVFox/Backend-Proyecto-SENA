package com.tienda.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue
    public int id_ord;

    @ManyToOne
    @JoinColumn(name = "pro_ord")
    public Producto id_pro;

    @ManyToOne
    @JoinColumn(name = "car_ord")
    public Carrito id_car;

    public int can_ord;
    public int total_ord;
}