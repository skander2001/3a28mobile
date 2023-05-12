/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author Skander
 */
public class Reclamation {
    private int id,userId;
    private String sujet,plainte;

    public Reclamation() {
    }

    public Reclamation(String sujet, String plainte) {
        this.sujet = sujet;
        this.plainte = plainte;
    }

    public Reclamation(int id, int userId, String sujet, String plainte) {
        this.id = id;
        this.userId = userId;
        this.sujet = sujet;
        this.plainte = plainte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getPlainte() {
        return plainte;
    }

    public void setPlainte(String plainte) {
        this.plainte = plainte;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", userId=" + userId + ", sujet=" + sujet + ", plainte=" + plainte + '}';
    }
    
}
