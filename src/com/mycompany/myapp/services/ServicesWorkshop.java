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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.entities.Workshop;


/**
 *
 * @author Lenovo
 */
public class ServicesWorkshop {
     public static ServicesWorkshop instance = null;
    public int resultCode;
    private ConnectionRequest cr;
     public boolean resultOK;
    public ArrayList<Workshop> Coachs;
     public ArrayList<Workshop> parseCoach(String jsonText)  {
        try {
             Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> WorkshopListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) WorkshopListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
               Workshop coach = new Workshop();
               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);              
               
               coach.setTitre(obj.get("titre").toString());      
               
               coach.setDescription(obj.get("description").toString());
               
               coach.setNom_artiste(obj.get("nom_artiste").toString());
               
               coach.setCategorie(obj.get("categorie").toString());
               
               float duree = Float.parseFloat(obj.get("duree").toString());
               coach.setDuree((int) duree);

               
             float prix = Float.parseFloat(obj.get("prix").toString());
             coach.setPrix((float) prix);
               
               
             
              
             

               
               

        Coachs.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }
        return Coachs;
    }
    public static boolean resultOk = true;
    private ArrayList<Workshop> listWorkshop;
    public static ServicesWorkshop getInstance(){
    if (instance == null) {
            instance = new ServicesWorkshop();}
     return instance;
    }
    private ServicesWorkshop(){
         cr = new ConnectionRequest();
    }
     public boolean ajoutWorkshop(Workshop workshop) {
         String titre = workshop.getTitre();
         String description = workshop.getDescription();
         String nom_artiste = workshop.getNom_artiste();
         String categorie = workshop.getCategorie();
         int duree=workshop.getDuree();
         float prix=workshop.getPrix();

        
        
String url = Statics.BASE_URL + "workshop/addWorkshopJSON/new?titre=" + workshop.getTitre() + "&description=" + workshop.getDescription() + "&nom_artiste=" + workshop.getNom_artiste() + "&categorie"  + workshop.getCategorie() + "&duree=" + workshop.getDuree() + "&prix=" + workshop.getPrix();
        
        cr.setUrl(url);
        cr.setPost(true);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200; //Code HTTP 200 OK
                cr.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(cr);//execution ta3 request sinon yet3ada chy dima nal9awha
         return resultOK;
    } 
    public ArrayList<Workshop> findAll() {
    String url = Statics.BASE_URL + "workshop/AllWorkshops/list";
    cr.setUrl(url);
   cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            Coachs = parseCoach(new String(cr.getResponseData()));
            System.out.println(Coachs);
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
    return Coachs;
    }
    public boolean modifWorkshop(Workshop workshop) {
         String titre = workshop.getTitre();
         String description = workshop.getDescription();
         String nom_artiste = workshop.getNom_artiste();
         String categorie = workshop.getCategorie();
         int duree=workshop.getDuree();
         float prix=workshop.getPrix();
          
         
        
String url = Statics.BASE_URL + "updateEvenementJSON/"+workshop.getId()+"?titre=" + workshop.getTitre() + "&description=" + workshop.getDescription() + "&nom_artiste=" + workshop.getNom_artiste() + "&categorie"  + workshop.getCategorie() + "&duree=" + workshop.getDuree() + "&prix=" + workshop.getPrix();

        cr.setUrl(url);
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200; //Code HTTP 200 OK
                cr.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(cr);//execution ta3 request sinon yet3ada chy dima nal9awha
         return resultOK;
    } 
    public boolean deletWorkshop(int id ) {
        String url = Statics.BASE_URL +"deleteWorkshopJSON/"+id;
        
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
    public ArrayList<Workshop> searchEvent(String searchText) {
    ArrayList<Workshop> workshops = findAll();
    ArrayList<Workshop> matchingWorkshops= new ArrayList<>();
    for (Workshop workshop : workshops) {
        if (workshop.getTitre().contains(searchText) || workshop.getDescription().contains(searchText)) {
            matchingWorkshops.add(workshop);
        }
    }
    return matchingWorkshops;
}
     
}
