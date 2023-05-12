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
import com.mycompany.entities.Shoppingcart;
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
public class ShoppingcartService {
       public ArrayList<Shoppingcart> shoppincart;

    public static ShoppingcartService instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private ShoppingcartService() {
        cr = new ConnectionRequest();
    }

    public static ShoppingcartService getInstance() {
        if (instance == null) {
            instance = new ShoppingcartService();
        }
        return instance;
    }
    
    
 
   
   







  public boolean addCard(Shoppingcart s) {

   

        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       // String url = Statics.BASE_URL + "addStatutJSON/new" + username + "/" + titre + "/" +  contenu + "/" + type;
String url = Statics.BASE_URL + "orderJson";
cr.setUrl(url);
cr.setPost(true);
cr.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
String requestBody =  "&nom=" + s.getNom() + "&prenom=" + s.getPrenom()+"&ville=" + s.getVille()+"&adresse=" + s.getAdresse();
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




  
    
}
