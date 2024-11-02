package com.example.produitapprovisionmvc.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import com.example.produitapprovisionmvc.entities.Produit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private long contact;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL)
    private List<Produit> produitsFournis ;

    public Fournisseur(Long id, String nom, long contact) {
        this.id = id;
        this.nom = nom;
        this.contact = contact;
    }
}