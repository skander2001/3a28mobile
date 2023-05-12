/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author USER
 */
public class Produit {
    
    private int id, qte_stock, id_ctg, likes;
    private String nom,description,image;
    private Float prix;

    public Produit(int id, int qte_stock, int id_ctg, int likes, String nom, String description, String image, Float prix) {
        this.id = id;
        this.qte_stock = qte_stock;
        this.id_ctg = id_ctg;
        this.likes = likes;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public Produit() {
    }

    public Produit(String nom, String description, String image, Float prix, int qte_stock, int id_ctg ) {
        this.nom = nom;    
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.qte_stock = qte_stock;
        this.id_ctg = id_ctg;

    }
    
    

    public Produit(int qte_stock, int id_ctg, int likes, String nom, String description, String image, Float prix) {
        this.qte_stock = qte_stock;
        this.id_ctg = id_ctg;
        this.likes = likes;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQte_stock() {
        return qte_stock;
    }

    public void setQte_stock(int qte_stock) {
        this.qte_stock = qte_stock;
    }

    public int getId_ctg() {
        return id_ctg;
    }

    public void setId_ctg(int id_ctg) {
        this.id_ctg = id_ctg;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", qte_stock=" + qte_stock + ", id_ctg=" + id_ctg + ", likes=" + likes + ", nom=" + nom + ", description=" + description + ", image=" + image + ", prix=" + prix + '}';
    }


    
    
}
