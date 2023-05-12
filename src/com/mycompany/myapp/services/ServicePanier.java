/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Cartitem;
import com.mycompany.entities.Produit;

import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author darka
 */
public class ServicePanier {
    
        public ArrayList<Cartitem> cartitem;

    public static ServicePanier instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private ServicePanier() {
        cr = new ConnectionRequest();
    }

    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }
    
    
 
    
   public ArrayList<Cartitem> parseCoach(String jsonText){
        try {
             cartitem = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> StatutListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) StatutListJson.get("root");

           for (Map<String, Object> obj : list) {
    Cartitem coach = new Cartitem();

    float id = Float.parseFloat(obj.get("id").toString());
    float quantity = Float.parseFloat(obj.get("quantity").toString());
    float price = Float.parseFloat(obj.get("price").toString());
    float total = Float.parseFloat(obj.get("Total").toString());

    coach.setId((int) id);
    coach.setQuantity((int) quantity);
    coach.setPrice(price);
    coach.setTotal(total);

    Map<String, Object> produitData = (Map<String, Object>) obj.get("produit");
    Produit produit = new Produit();
    produit.setId((int) Float.parseFloat(produitData.get("id").toString()));
    produit.setNom(produitData.get("nom").toString());
    produit.setDescription(produitData.get("description").toString());
    produit.setPrix(Float.parseFloat(produitData.get("prix").toString()));
    produit.setImage(produitData.get("image").toString());
    produit.setLikes((int) Float.parseFloat(produitData.get("likes").toString()));
    produit.setQte_stock((int) Float.parseFloat(produitData.get("qte_stock").toString()));

    coach.setProduct(produit);

   
           
              //coach.setCreated(obj.get("created").toString());
//               coach.setImage(obj.get("image").toString());
//               coach.setType(obj.get("type").toString());
               cartitem.add(coach);   
             

     
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing remises ");
        }
        return cartitem;
    }
   
   
public ArrayList<Cartitem> getAllStatuts() {
    String url = Statics.BASE_URL + "panierJson";
    cr.setUrl(url);
    cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            cartitem = parseCoach(new String(cr.getResponseData()));
            System.out.println(cartitem);
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
    return cartitem;
    }
}
