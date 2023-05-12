/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Feedback;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ServicesFeed {
    public ArrayList<Feedback> Feedbacks;
    public static ServicesFeed instance=null;
    public boolean resultOK;
    private ConnectionRequest cr;
    private ServicesFeed(){
          cr = new ConnectionRequest();
    }
    public static ServicesFeed getInstance(){
         if (instance == null) {
            instance = new ServicesFeed();
        }
        return instance;
    }
    public boolean deleteFeed(int id ) {
        String url = Statics.BASE_URL +"deletefJSON/"+id;
        
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
    public boolean modifierFeed(Feedback feed) {
              String url = Statics.BASE_URL +"updatefJSON/"+feed.getId()+"?text="+feed.getText(); 

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


    
}
