package com.tienda.backend.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categorias")
@Data
public class Categoria {
    @Id
    public String nom_cate;

    @Column(columnDefinition = "TEXT")
    public String des_cate;

    @OneToMany(mappedBy = "nom_cate", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Producto> productos;
}