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
import com.mycompany.entities.Statut;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Map;


/**
 *
 * @author user
 */
public class ServiceStatut {
    
    public ArrayList<Statut> statuts;

    public static ServiceStatut instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private ServiceStatut() {
        cr = new ConnectionRequest();
    }

    public static ServiceStatut getInstance() {
        if (instance == null) {
            instance = new ServiceStatut();
        }
        return instance;
    }
    
    
      public boolean addStatut(Statut s) {

        String username = s.getUsername();
        String titre = s.getTitre();
        String contenu = s.getContenu();
        String type = s.getType();

        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       // String url = Statics.BASE_URL + "addStatutJSON/new" + username + "/" + titre + "/" +  contenu + "/" + type;
        String url =Statics.BASE_URL+"addStatutJSON/new?username=" +s.getUsername()+"&titre="+s.getTitre()+"&contenu="+s.getContenu()+"&type="+s.getType(); 

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
    
   public ArrayList<Statut> parseCoach(String jsonText){
        try {
             statuts = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> StatutListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) StatutListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
               Statut coach = new Statut();
               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);   
               coach.setUsername(obj.get("username").toString());        
               coach.setTitre(obj.get("titre").toString());        
               coach.setContenu(obj.get("contenu").toString());

              //coach.setCreated(obj.get("created").toString());
//               coach.setImage(obj.get("image").toString());
           //   coach.setType(obj.get("type").toString());
               
               float nbrLike = Float.parseFloat(obj.get("nbrLike").toString());
               coach.setNbrLike((int) nbrLike);
            

        statuts.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing statuts ");
        }
        return statuts;
    }
   
   
public ArrayList<Statut> getAllStatuts() {
    String url = Statics.BASE_URL + "AllStatuts";
    cr.setUrl(url);
    cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            statuts = parseCoach(new String(cr.getResponseData()));
            System.out.println(statuts);
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
    return statuts;
    }
 


public boolean deletStatut(int id ) {
        String url = Statics.BASE_URL +"deleteStatutJSON/"+id;
        
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


public boolean modifierStatut(Statut statut) {
              String url = Statics.BASE_URL +"updateStatutJSON/"+statut.getId()+"?titre="+statut.getTitre()+"&contenu="+statut.getContenu()+"&type="+statut.getType(); 

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


public ArrayList<Statut> getFilteredStatuts(String type) {
    ArrayList<Statut> statuts = new ArrayList<>();

    try {
        String url = Statics.BASE_URL + "statutJsonFiltre/" + type;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.setContentType("application/json");

        NetworkManager.getInstance().addToQueueAndWait(request);

        JSONParser parser = new JSONParser();
        Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));

        // Convertir la réponse JSON en une liste de statuts
        if (response.containsKey("root")) {
            ArrayList<Map<String, Object>> statutMaps = (ArrayList<Map<String, Object>>) response.get("root");
            for (Map<String, Object> statutMap : statutMaps) {
                Statut statut = new Statut();
                 statut.setUsername(statutMap.get("username").toString());        
               statut.setTitre(statutMap.get("titre").toString());        
               statut.setContenu(statutMap.get("contenu").toString());

           
               
              float nbrLike = Float.parseFloat(statutMap.get("nbrLike").toString());
              statut.setNbrLike((int) nbrLike);
                statuts.add(statut);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return statuts;
}



public ArrayList<Statut> getStatutsFromJson(String searchquery) {
    ArrayList<Statut> statuts = new ArrayList<>();

    try {
        String url = Statics.BASE_URL + "searchJSON?" + searchquery;
        ConnectionRequest request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.setContentType("application/json");

        NetworkManager.getInstance().addToQueueAndWait(request);

        JSONParser parser = new JSONParser();
        Map<String, Object> response = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));

        // Convertir la réponse JSON en une liste de statuts
        if (response.containsKey("root")) {
            ArrayList<Map<String, Object>> statutMaps = (ArrayList<Map<String, Object>>) response.get("root");
            for (Map<String, Object> statutMap : statutMaps) {
                Statut statut = new Statut();
                 statut.setUsername(statutMap.get("username").toString());        
               statut.setTitre(statutMap.get("titre").toString());        
               statut.setContenu(statutMap.get("contenu").toString());

           
               
              float nbrLike = Float.parseFloat(statutMap.get("nbrLike").toString());
              statut.setNbrLike((int) nbrLike);
                statuts.add(statut);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    return statuts;
}







}

