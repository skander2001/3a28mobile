/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author user
 */
public class Commentaire {
        private int id;

    private int id_s;
    private String username;
        private String description;

    private Date date_ajout;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_s() {
        return id_s;
    }

    public void setId_s(int id_s) {
        this.id_s = id_s;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(Date date_ajout) {
        this.date_ajout = date_ajout;
    }

    public Commentaire() {
    }

    public Commentaire(String username, String description) {
        this.username = username;
        this.description = description;
    }
    

    public Commentaire(int id, int id_s, String description, Date date_ajout) {
        this.id = id;
        this.id_s = id_s;
        this.description = description;
        this.date_ajout = date_ajout;
    }

   

    public Commentaire(int id, int id_s, String description, String username, Date date_ajout) {
        this.id = id;
        this.id_s = id_s;
        this.description = description;
        this.username = username;
        this.date_ajout = date_ajout;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_s=" + id_s + ", description=" + description + ", username=" + username + ", date_ajout=" + date_ajout + '}';
    }

  
    
    
    
    
    
    
}
