/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author USER
 */
public class Categorie {
     private int id;
      private String nom_ctg;

    public Categorie() {
    }

    public Categorie(int id, String nom_ctg) {
        this.id = id;
        this.nom_ctg = nom_ctg;
    }

    public Categorie(String nom_ctg) {
        this.nom_ctg = nom_ctg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_ctg() {
        return nom_ctg;
    }

    public void setNom_ctg(String nom_ctg) {
        this.nom_ctg = nom_ctg;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom_ctg=" + nom_ctg + '}';
    }
      
      
}
