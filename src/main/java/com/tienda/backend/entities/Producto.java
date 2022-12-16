package com.tienda.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_pro;

    @Column(nullable = false)
    public String nom_pro;

    public String ima_pro;

    @Column(nullable = false, columnDefinition = "TEXT")
    public String des_pro;

    @ManyToOne
    @JoinColumn(name = "pro_cate", nullable = false)
    public Categoria nom_cate;

    @ManyToOne
    @JoinColumn(name = "pro_usu", nullable = false)
    public Usuario usu_id;

    @Column(nullable = false)
    public int can_pro;

    @Column(nullable = false)
    public int pre_pro;
}
