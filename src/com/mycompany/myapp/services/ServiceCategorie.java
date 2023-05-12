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
import com.mycompany.entities.Categorie;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author USER
 */
public class ServiceCategorie {
    
    public ArrayList<Categorie> categories;

    public static ServiceCategorie instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;
    
     private ServiceCategorie() {
        cr = new ConnectionRequest();
    }

     public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }
    
     public boolean addCategorie (Categorie c) {

        String nom_ctg = c.getNom_ctg();
        String url = Statics.BASE_URL+"addCategorieJSON?nom_ctg="+c.getNom_ctg();
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
     public ArrayList<Categorie> parseCoach(String jsonText){
        try {
             categories = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) CategorieListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
                Categorie coach = new Categorie();
               float id = Float.parseFloat(obj.get("id").toString());
               
               coach.setId((int) id);   
               coach.setNom_ctg(obj.get("nom_ctg").toString());        

                 

           categories.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing statuts ");
        }
        return categories;
    }

public ArrayList<Categorie> getAllCategorie() {
    String url = Statics.BASE_URL + "AllCategorie";
    cr.setUrl(url);
    cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            categories = parseCoach(new String(cr.getResponseData()));
            System.out.println(categories);
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
    return categories;
    }
     
     public boolean deletCategorie(int id ) {
        String url = Statics.BASE_URL +"deleteCategorieJSON/"+id;
        
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
     
     public boolean modifCategorie(Categorie categorie) {
    String url = Statics.BASE_URL + "updateCategorieJSON/" + categorie.getId()
            + "?nom_ctg=" + categorie.getNom_ctg();

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
   
     public ArrayList<Categorie> searchCategorie(String searchText) {
    ArrayList<Categorie> categories = getAllCategorie();
    ArrayList<Categorie> matchingEvents = new ArrayList<>();
    for (Categorie p : categories) {
        if (p.getNom_ctg().contains(searchText) ) {
            matchingEvents.add(p);
        }
    }
    return matchingEvents;
}
    
}
