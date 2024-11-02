package com.example.produitapprovisionmvc.entities;

import jakarta.persistence.*;
import  com.example.produitapprovisionmvc.entities.Fournisseur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int quantiteEnStock;

    @ManyToOne
    private Fournisseur fournisseur;
}