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
import com.mycompany.entities.Remise;
import com.mycompany.myapp.utils.Statics;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;




/**
 *
 * @author darka
 */
public class ServiceRemise {
    
        public ArrayList<Remise> remises;

    public static ServiceRemise instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private ServiceRemise() {
        cr = new ConnectionRequest();
    }

    public static ServiceRemise getInstance() {
        if (instance == null) {
            instance = new ServiceRemise();
        }
        return instance;
    }
    
    
 
    
   public ArrayList<Remise> parseCoach(String jsonText){
        try {
             remises = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> StatutListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) StatutListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
             Remise coach = new Remise();
               float code= Float.parseFloat(obj.get("code").toString());
               float nb = Float.parseFloat(obj.get("nb").toString());
               
              
        
               coach.setCode((int) code);   
               coach.setNom(obj.get("nom").toString());        
               coach.setNb((int) nb);    
              
                 System.out.print(coach.getCode());
           
              //coach.setCreated(obj.get("created").toString());
//               coach.setImage(obj.get("image").toString());
//               coach.setType(obj.get("type").toString());
               remises.add(coach);   
             

     
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing remises ");
        }
        return remises;
    }
   
   
public ArrayList<Remise> getAllStatuts() {
    String url = Statics.BASE_URL + "remiseJSON";
    cr.setUrl(url);
    cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            remises = parseCoach(new String(cr.getResponseData()));
            System.out.println(remises);
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
    return remises;
    }


public boolean deleteJson(int id ) {
        String url = Statics.BASE_URL +"deleteJSON/"+id;
        
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






  public boolean addRemise(Remise s) {

        int Code = s.getCode();
        String nom = s.getNom();
        int nb = s.getNb();
 

        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       // String url = Statics.BASE_URL + "addStatutJSON/new" + username + "/" + titre + "/" +  contenu + "/" + type;
String url = Statics.BASE_URL + "addremiseJSON";
cr.setUrl(url);
cr.setPost(true);
cr.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
String requestBody = "code=" + s.getCode() + "&nom=" + s.getNom() + "&nb=" + s.getNb();
cr.setRequestBody(requestBody);

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




    public boolean modifierRemise(Remise remise) {

 String url = Statics.BASE_URL +"updateRemiseJSON/"+remise.getCode()+"?nom="+remise.getNom()+"&nb="+remise.getNb(); 
System.out.print(url);
        cr.setUrl(url);
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = cr.getResponseCode() == 200 ;  // Code response Http 200 ok
                cr.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(cr);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOK;
        
    }
  public ArrayList<Remise> searchRemise(String searchText) {
    ArrayList<Remise> remises = getAllStatuts();
    ArrayList<Remise> matchingEvents = new ArrayList<>();
    for (Remise r : remises) {
        if (r.getNom().contains(searchText) ) {
            matchingEvents.add(r);
        }
    }
    return matchingEvents;
}
  



}
