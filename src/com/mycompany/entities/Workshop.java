/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Workshop {
    private int id;
    private String titre,description,nom_artiste,categorie;
    private int duree;
    private Float prix;

    public Workshop(int id, String titre, String description, String nom_artiste, String categorie, int duree, float prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.nom_artiste = nom_artiste;
        this.categorie = categorie;
        this.duree = duree;
        this.prix = prix;
    }

    public Workshop(String titre, String description, String nom_artiste, String categorie, int duree, float prix) {
        this.titre = titre;
        this.description = description;
        this.nom_artiste = nom_artiste;
        this.categorie = categorie;
        this.duree = duree;
        this.prix = prix;
    }

    public Workshop() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom_artiste() {
        return nom_artiste;
    }

    public void setNom_artiste(String nom_artiste) {
        this.nom_artiste = nom_artiste;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    
    
    
    @Override
    public String toString() {
        return "Workshop{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", nom_artiste=" + nom_artiste + ", categorie=" + categorie + ", duree=" + duree + ", prix=" + prix + '}';
    }
    

    
    
}
