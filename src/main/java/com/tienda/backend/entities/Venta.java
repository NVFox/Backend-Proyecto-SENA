package com.tienda.backend.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ventas")
public class Venta {
    @Id
    @GeneratedValue
    public int id_ven;

    @ManyToOne
    @JoinColumn(name = "ord_ven", nullable = false)
    public Orden ord_id;

    public LocalDate fec_ven = LocalDate.now();
}