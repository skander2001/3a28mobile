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
public class Paiement {
    
    
 

    public static Paiement instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private Paiement() {
        cr = new ConnectionRequest();
    }

    public static Paiement getInstance() {
        if (instance == null) {
            instance = new Paiement();
        }
        return instance;
    }
    
    
   
public void  paiement() {
    String url = Statics.BASE_URL + "checkoutJson";
    cr.setUrl(url);
    cr.setPost(false);
    cr.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
         
           
            cr.removeResponseListener(this);
        }
    });
   NetworkManager.getInstance().addToQueueAndWait(cr);
   
    }
    
}
