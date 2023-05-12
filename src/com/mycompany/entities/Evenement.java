/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;
import java.util.Date;
/**
 *
 * @author MSI
 */
public class Evenement {
    private int id;
    private String titre,description,localisation;
    private Date dateDebut,dateFin;
    private String categorie;

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    private Float prix;
    //evaluation
    private float rating;
    private int ratingNumber;
   private  int points;
//
    private String image;
    private int nbPlace;
     public Evenement(int id, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, String image, int nbPlace) {
        this.id = id;

        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.image = image;
        this.nbPlace = nbPlace;
    }

    public Evenement() {
    }

    public Evenement( String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, int nbPlace) {

        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.nbPlace = nbPlace;
    }

    
    public Evenement(String titre, String localisation, String description, Date dateDebut, Date dateFin, Float prix, String image, int nbPlace) {
        this.titre = titre;
        this.localisation = localisation;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.image = image;
        this.nbPlace = nbPlace;
    }

    public Evenement(int id, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, float rating, int ratingNumber, int points, int nbPlace) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.rating = rating;
        this.ratingNumber = ratingNumber;
        this.points = points;
        this.nbPlace = nbPlace;
    }

    public Evenement(int id, String titre, String description, String localisation, Date dateDebut, Date dateFin, Float prix, float rating, int ratingNumber, int points, String image, int nbPlace) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
        this.rating = rating;
        this.ratingNumber = ratingNumber;
        this.points = points;
        this.image = image;
        this.nbPlace = nbPlace;
    }

    public Evenement(String titre, String description, String localisation, Float prix,String image, int nbPlace) {

        this.titre = titre;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.image = image;
        this.nbPlace = nbPlace;
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

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRatingNumber() {
        return ratingNumber;
    }

    public void setRatingNumber(int ratingNumber) {
        this.ratingNumber = ratingNumber;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }



    @Override
    public String toString() {
        return "Evenement{" +
                "id=" + id +

                ", titre='" + titre + '\'' +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prix=" + prix +
                ", image=" + image +
                ", nbPlace=" + nbPlace +
                '}';
    }
}
