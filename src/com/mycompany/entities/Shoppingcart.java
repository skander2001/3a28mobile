/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author darka
 */
public class Shoppingcart {
    int id; 
    String nom, prenom ,ville ,adresse;
   int code_postale;
    Float total_price ;
    String sta;

    public Shoppingcart(String nom, String prenom, String ville, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.adresse = adresse;
    }

    public Shoppingcart(int id, String nom, String prenom, String ville, String adresse, int code_postale, Float total_price, String sta) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.adresse = adresse;
        this.code_postale = code_postale;
        this.total_price = total_price;
        this.sta = sta;
    }

    public Shoppingcart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCode_postale() {
        return code_postale;
    }

    public void setCode_postale(int code_postale) {
        this.code_postale = code_postale;
    }

    public Float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Float total_price) {
        this.total_price = total_price;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }
}
