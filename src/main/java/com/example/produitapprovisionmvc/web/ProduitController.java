package com.example.produitapprovisionmvc.web;


import com.example.produitapprovisionmvc.entities.Fournisseur;
import com.example.produitapprovisionmvc.entities.Produit;

import com.example.produitapprovisionmvc.repositories.FournisseurRepository;
import com.example.produitapprovisionmvc.repositories.ProduitRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;


@Controller
public class ProduitController {
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private FournisseurRepository fournisseurRepository;

    // Afficher la liste des produits
    @GetMapping("/produits")
    public String listeProduits(Model model) {
        model.addAttribute("produits", produitRepository.findAll());
        return "produits";
    }


    @GetMapping("/produits/new")
    public String creerProduit(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        // Rediriger vers le formulaire du fournisseur
        return "produit_form";
    }

    // Enregistrer un produit (ajouter ou modifier)
    @PostMapping("/produits/save")
    public String saveProduit(@Validated @ModelAttribute("produit") Produit produit,
                              BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("fournisseurs", fournisseurRepository.findAll());
            return "produit_form";
        }

        // Récupérer le fournisseur associé
        Fournisseur fournisseur = fournisseurRepository.findById(produit.getFournisseur().getId())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        produit.setFournisseur(fournisseur);

        produitRepository.save(produit);
        return "redirect:/produits";
    }

    // Afficher le formulaire de modification d'un produit
    @GetMapping("/produits/edit/{id}")
    public String editerProduit(@PathVariable Long id, Model model) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));
        model.addAttribute("produit", produit);
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        return "produit_form";
    }

    // Supprimer un produit
    @GetMapping("/produits/delete/{id}")
    public String supprimerProduit(@PathVariable Long id) {
        produitRepository.deleteById(id);
        return "redirect:/produits";
    }
}