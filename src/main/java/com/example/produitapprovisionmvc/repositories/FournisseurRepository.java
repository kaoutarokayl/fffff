package com.example.produitapprovisionmvc.repositories;

import com.example.produitapprovisionmvc.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    // Recherche de produits par fournisseur

}