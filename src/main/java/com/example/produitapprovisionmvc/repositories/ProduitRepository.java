package com.example.produitapprovisionmvc.repositories;


import com.example.produitapprovisionmvc.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    // Recherche par fournisseurs ou autre logique si nécessaire
}