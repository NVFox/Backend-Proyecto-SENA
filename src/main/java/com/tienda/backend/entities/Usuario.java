package com.tienda.backend.entities;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id_usu;

    @Column(nullable = false, unique = true)
    public String nom_usu;

    @Column(nullable = false, unique = true)
    public String cor_usu;

    @Column(nullable = false)
    public String con_usu;

    public String ima_usu;

    public int sal_usu = 0;

    @Enumerated(EnumType.STRING)
    public Roles rol_usu;
}