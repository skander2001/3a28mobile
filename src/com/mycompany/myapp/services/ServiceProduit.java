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
import com.mycompany.entities.Produit;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 *
 * @author USER
 */
public class ServiceProduit {
    
public ArrayList<Produit> produits;

    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private ServiceProduit() {
        cr = new ConnectionRequest();
    }

     public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }
    
    
      public boolean addProduit (Produit p) {

        String nom = p.getNom();
        String description = p.getDescription();
        String image = p.getImage();
        float prix = p.getPrix();
        int qte_stock = p.getQte_stock();
        int id_ctg = p.getId_ctg();

     
        String url = Statics.BASE_URL+"addProduitJSON?nom="+p.getNom()+ "&description=" + p.getDescription()+"&prix="+p.getPrix()+"&image="+p.getImage()+"&stock="+p.getQte_stock()+"&id_ctg="+p.getId_ctg();
        cr.setUrl(url);
        cr.setPost(false);
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200; //Code HTTP 200 OK
                cr.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return resultOK;
    }
    
     public ArrayList<Produit> parseCoach(String jsonText){
        try {
             produits = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) ProduitListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
                Produit coach = new Produit();
               float id = Float.parseFloat(obj.get("id").toString());
               
               coach.setId((int) id);   
               coach.setNom(obj.get("nom").toString());        
               coach.setDescription(obj.get("description").toString());        
               //coach.setImage(obj.get("image").toString());
               float prix = Float.parseFloat(obj.get("prix").toString());
               coach.setPrix(prix);
               float qte_stock = Float.parseFloat(obj.get("qte_stock").toString());
               coach.setQte_stock((int) qte_stock); 
              // float id_ctg  = Float.parseFloat(obj.get("id_ctg ").toString());
              //coach.setId_ctg((int) id_ctg ); 
                 

           produits.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing statuts ");
        }
        return produits;
    }
   
   
public ArrayList<Produit> getAllProduits() {
    String url = Statics.BASE_URL + "AllProduit";
    cr.setUrl(url);
    cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            produits = parseCoach(new String(cr.getResponseData()));
            System.out.println(produits);
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
    return produits;
    }
 


public boolean deletProduit(int id ) {
        String url = Statics.BASE_URL +"deleteProduitJSON/"+id;
        
        cr.setUrl(url);
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    cr.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(cr);
        return  resultOK;
    }


public boolean modifierProduit(Produit produit) {
    String url = Statics.BASE_URL + "updateProduitJSON/" + produit.getId()
            + "?nom=" + produit.getNom()
            + "&description=" + produit.getDescription()
            + "&prix=" + produit.getPrix()
            + "&qte_stock=" + produit.getQte_stock()
            + "&id_ctg=" + produit.getId_ctg();

    cr.setUrl(url);

    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = cr.getResponseCode() == 200;  // HTTP response code 200 indicates success
            cr.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(cr);
    return resultOK;
}


public ArrayList<Produit> searchProduit(String searchText) {
    ArrayList<Produit> produits = getAllProduits();
    ArrayList<Produit> matchingEvents = new ArrayList<>();
    for (Produit p : produits) {
        if (p.getNom().contains(searchText) || p.getDescription().contains(searchText)) {
            matchingEvents.add(p);
        }
    }
    return matchingEvents;
}





}



