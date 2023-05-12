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
import com.mycompany.entities.Reclamation;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;



/**
 *
 * @author Skander
 */
public class ServiceReclamation {
    public static ServiceReclamation instance ;
    public boolean resultOk;
    private ConnectionRequest req;
    public ArrayList<Reclamation> Reclamations;
    
    public ServiceReclamation(){
        req =  new ConnectionRequest();
    }
    
        public static ServiceReclamation getInstance() {
        if(instance == null )
            instance = new ServiceReclamation();
        return instance ;
    }
    

    public boolean AddReclamation(Reclamation r) {
        
        String url = Statics.BASE_URL + "reclamation/add_json/"+r.getSujet()+"/"+r.getPlainte();
        req.setUrl(url);
        req.setPost(true);

          req.setHttpMethod("POST"); // Use HTTP instead of HTTPS

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
                            req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    
        public ArrayList<Reclamation>parseRec(String jsonText){
            try{
            Reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ReclamationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List <Map<String,Object>> list = (List <Map<String,Object>>)ReclamationsListJson.get("root");
            for(Map<String,Object>obj:list){
                Reclamation r= new Reclamation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int)id);
                r.setUserId(2);
                r.setSujet(obj.get("sujet").toString());
                r.setPlainte(obj.get("plainte").toString());
                Reclamations.add(r);
            }
            }catch(Exception ex)
            {
                System.out.println(ex);
            }
            return Reclamations;  
        }    
        public ArrayList<Reclamation> listRec(){
            String url = Statics.BASE_URL+"gestion/reclamation_json";
            req.setUrl(url);
            req.setPost(false);
            req.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    Reclamations=parseRec(new String(req.getResponseData()));
                    req.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(req);
       return Reclamations;
        } 

}



