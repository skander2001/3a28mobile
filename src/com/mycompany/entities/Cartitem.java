/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author darka
 */
public class Cartitem {
    int id;
    Produit product ;
    int quantity;
    Float total,price;

    public Cartitem(int id, Produit product, int quantity, Float total, Float price) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.total = total;
        this.price = price;
    }

    public Cartitem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduct() {
        return product;
    }

    public void setProduct(Produit product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

   
    
}
