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
public class Statut implements Comparable<Statut> {
     private int id;
     private String username;
     private String titre;
     private String contenu;
     private int nbrLike;
     private Date created;
     private Date updated;
     private String image;
     private String type;
    

     public Statut() {
    }

    public Statut(int i, String toString, String toString0, String toString1, int i0, String toString2) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Statut(int id, String username, String titre, String contenu, int nbrLike, Date created, Date updated, String image, String type) {
        this.id = id;
        this.username = username;
        this.titre = titre;
        this.contenu = contenu;
        this.nbrLike = nbrLike;
        this.created = created;
        this.updated = updated;
        this.image = image;
        this.type = type;
    }

    public Statut(String username, String titre, String contenu, String type) {
        this.username = username;
        this.titre = titre;
        this.contenu = contenu;
        this.type = type;
    }

    public Statut(String username, String titre, String contenu, int nbrLike, Date created, Date updated, String image, String type) {
        this.username = username;
        this.titre = titre;
        this.contenu = contenu;
        this.nbrLike = nbrLike;
        this.created = created;
        this.updated = updated;
        this.image = image;
        this.type = type;
    }

    public Statut(int id) {
        this.id = id;
    }
    

    @Override
    public String toString() {
        return "Statut{" + "id=" + id + ", username=" + username + ", titre=" + titre + ", contenu=" + contenu + ", nbrLike=" + nbrLike + ", created=" + created + ", updated=" + updated + ", image=" + image + ", type=" + type + '}';
    }

    @Override
    public int compareTo(Statut o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     
     
}
