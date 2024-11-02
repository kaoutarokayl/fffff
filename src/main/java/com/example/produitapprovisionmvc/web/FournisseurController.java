package com.example.produitapprovisionmvc.web;
import com.example.produitapprovisionmvc.entities.Fournisseur;
import com.example.produitapprovisionmvc.entities.Produit;
import com.example.produitapprovisionmvc.repositories.FournisseurRepository;
import com.example.produitapprovisionmvc.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class FournisseurController {
    @Autowired
    private FournisseurRepository fournisseurRepository;
    @Autowired
    private ProduitRepository produitRepository;



    // Récupérer tous les fournisseurs
    @GetMapping("/fournisseurs")
    public String findAllFournisseurs(Model model) {
        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
        return "fournisseurs"; // Assurez-vous que vous avez créé la vue 'fournisseurs'
    }

    // Afficher le formulaire de création d'un nouveau fournisseur
    @GetMapping("/fournisseur_form")
    public String afficherFormFournisseur(Model model) {
        model.addAttribute("fournisseur", new Fournisseur());
        List<Produit> produitsList = produitRepository.findAll(); // Charger tous les produits
        model.addAttribute("produitList", produitsList);
        return "fournisseur_form";
    }

    // Ajouter un nouveau fournisseur
    @PostMapping("/fournisseur/add")
    public String saveFournisseur(@ModelAttribute Fournisseur fournisseur) {
        fournisseurRepository.save(fournisseur);


        // Rediriger vers la liste des produits (ou la page où vous voulez aller)
        return "redirect:/produits"; // Rediriger vers /produits/new
    }

    // Trouver un fournisseur par ID
    @GetMapping("/fournisseur/{id}")
    public String findFournisseurById(@PathVariable Long id, Model model) {
        Optional<Fournisseur> fournisseur = fournisseurRepository.findById(id);
        if (fournisseur.isPresent()) {
            model.addAttribute("fournisseur", fournisseur.get());
            return "fournisseurDetail"; // Assurez-vous que vous avez créé la vue 'fournisseurDetail'
        } else {
            // Gérer le cas où le fournisseur n'est pas trouvé
            return "redirect:/produits";
        }
    }
    @GetMapping("/fournisseur/update/{id}")
    public String updateFournisseur(@PathVariable Long id, Model model) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé"));
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur_form";
    }

    // Mettre à jour un fournisseur
    @PostMapping("/fournisseur/update")
    public String saveFournisseur(@Validated @ModelAttribute("fournisseur") Fournisseur fournisseur,
                              BindingResult result, Model model, RedirectAttributes redirectAttributes) {


        fournisseurRepository.save(fournisseur);
        return "redirect:/produits";
    }

    // Supprimer un fournisseur par ID
    @GetMapping("/fournisseur/delete/{id}")
    public String deleteFournisseur(@PathVariable Long id) {
        fournisseurRepository.deleteById(id);
        return "redirect:/produits";
    }
}
