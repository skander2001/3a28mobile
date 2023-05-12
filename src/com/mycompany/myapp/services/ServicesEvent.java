/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Evenement;
import com.mycompany.myapp.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author MSI
 */
public class ServicesEvent {
    public static ServicesEvent instance = null;
    public int resultCode;
    private ConnectionRequest cr;
     public boolean resultOK;
    public ArrayList<Evenement> Coachs;
     public ArrayList<Evenement> parseCoach(String jsonText)  {
        try {
             Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
               Evenement coach = new Evenement();
               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);              
               coach.setTitre(obj.get("titre").toString());        
               coach.setDescription(obj.get("description").toString());
               coach.setLocalisation(obj.get("localisation").toString());
                //coach.setDateDebut(obj.get("dateDebut").toString());
                //coach.setDateFin(obj.get("dateFin").toString());
               coach.setImage(obj.get("image").toString());
               float nbPlace = Float.parseFloat(obj.get("nbPlace").toString());
               coach.setNbPlace((int) nbPlace);
                
               float ratingNumber = Float.parseFloat(obj.get("ratingNumber").toString());
               coach.setRatingNumber((int) ratingNumber);
               float rating = Float.parseFloat(obj.get("rating").toString());
               coach.setRating((float) rating);
               float prix = Float.parseFloat(obj.get("prix").toString());
               coach.setPrix((float) prix);
               try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateDebut = format.parse(obj.get("dateDebut").toString());
        Date dateFin = format.parse(obj.get("dateFin").toString());
        coach.setDateDebut(dateDebut);
        coach.setDateFin(dateFin);
    } catch (ParseException ex) {
        System.out.println("Error parsing date: " + ex.getMessage());
    }

               
               

        Coachs.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }
        return Coachs;
    }
    public static boolean resultOk = true;
    private ArrayList<Evenement> listEvenement;
    public static ServicesEvent getInstance(){
    if (instance == null) {
            instance = new ServicesEvent();}
     return instance;
    }
    private ServicesEvent(){
         cr = new ConnectionRequest();
    }
     public boolean ajoutEvent(Evenement evenement) {
         String titre = evenement.getTitre();
         String description = evenement.getDescription();
         String localisation = evenement.getLocalisation();
         Date dateDebut = evenement.getDateDebut();
Date dateFin = evenement.getDateFin();


         Float prix=evenement.getPrix();
         String image=evenement.getImage();
         int nbPlace=evenement.getNbPlace();
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDateDebut = dateFormat.format(dateDebut);
    String formattedDateFin = dateFormat.format(dateFin);
         
        
String url = Statics.BASE_URL + "addEvenementJSON/new?titre=" + evenement.getTitre() + "&localisation=" + evenement.getLocalisation() + "&description=" + evenement.getDescription() + "&dateDebut=" + formattedDateDebut + "&dateFin=" + formattedDateFin + "&prix=" + evenement.getPrix() + "&image=" + evenement.getImage() + "&nbPlace=" + evenement.getNbPlace();
        
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
    public ArrayList<Evenement> findAll() {
    String url = Statics.BASE_URL + "AllEvents";
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
    public boolean modifEvent(Evenement evenement) {
         String titre = evenement.getTitre();
         String description = evenement.getDescription();
         String localisation = evenement.getLocalisation();
         Date dateDebut = evenement.getDateDebut();
Date dateFin = evenement.getDateFin();


         Float prix=evenement.getPrix();
         String image=evenement.getImage();
         int nbPlace=evenement.getNbPlace();
          SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String formattedDateDebut = dateFormat.format(dateDebut);
    String formattedDateFin = dateFormat.format(dateFin);
         
        
String url = Statics.BASE_URL + "updateEvenementJSON/"+evenement.getId()+"?titre=" + evenement.getTitre() + "&localisation=" + evenement.getLocalisation() + "&description=" + evenement.getDescription() + "&dateDebut=" + formattedDateDebut + "&dateFin=" + formattedDateFin + "&prix=" + evenement.getPrix() + "&image=" + evenement.getImage() + "&nbPlace=" + evenement.getNbPlace();
        
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
    public boolean deletEvent(int id ) {
        String url = Statics.BASE_URL +"deleteEvenementJSON/"+id;
        
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
    public ArrayList<Evenement> searchEvent(String searchText) {
    ArrayList<Evenement> evenements = findAll();
    ArrayList<Evenement> matchingEvents = new ArrayList<>();
    for (Evenement event : evenements) {
        if (event.getTitre().contains(searchText) || event.getDescription().contains(searchText)) {
            matchingEvents.add(event);
        }
    }
    return matchingEvents;
}
     
}
