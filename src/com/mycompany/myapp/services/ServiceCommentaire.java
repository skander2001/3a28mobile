/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Commentaire;
import com.mycompany.myapp.utils.Statics;

import java.util.ArrayList;



/**
 *
 * @author user
 */
public class ServiceCommentaire {
    public ArrayList<Commentaire> Commentaires;

    public static ServiceCommentaire instance = null;
    public boolean resultOK;
    private ConnectionRequest cr;


    private ServiceCommentaire() {
        cr = new ConnectionRequest();
    }

    public static ServiceCommentaire getInstance() {
        if (instance == null) {
            instance = new ServiceCommentaire();
        }
        return instance;
    }
    
    
    

      public boolean addCmt(Commentaire c) {
    String url = Statics.BASE_URL + "addCmtJSON/new/"+ c.getId_s() + "?username=" + c.getUsername() + "&description=" + c.getDescription();
    ConnectionRequest request = new ConnectionRequest(url);
    NetworkManager.getInstance().addToQueueAndWait(request);
    return request.getResponseCode() == 200; // ou tout autre code de réponse que vous utilisez
}


 public boolean deleteCommentaire(int id ) {
        String url = Statics.BASE_URL +"deleteCommentaireJSON/"+id;
        
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


public boolean modifierCommentaire(Commentaire com) {
              String url = Statics.BASE_URL +"updateCmtJSON/"+com.getId()+"?description="+com.getDescription(); 

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