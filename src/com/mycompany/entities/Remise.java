/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author darka
 */
public class Remise {
    
    private int code ;
    private String nom;
    private int nb;

    public Remise(int code, String nom, int nb) {
        this.code = code;
        this.nom = nom;
        this.nb = nb;
    }

    public Remise() {
    }

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public int getNb() {
        return nb;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    @Override
    public String toString() {
        return "Remise{" + "code=" + code + ", nom=" + nom + ", nb=" + nb + '}';
    }
    
    
   
}
