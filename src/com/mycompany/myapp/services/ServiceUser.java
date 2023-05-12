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
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.ListeUsersForm;
import com.mycompany.myapp.gui.SignInForm;
import com.mycompany.myapp.gui.SignUpForm;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Skander
 */
public class ServiceUser {
            Form current;
                String json;

        public ArrayList<User> users;

    public static ServiceUser instance = null;
    public static boolean resultOk = true;
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if(instance==null){
            instance= new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        Form current;
        req=new ConnectionRequest();
    }
    
        public void signup (TextField username ,TextField password,TextField email ,TextField tel){
                String url = Statics.BASE_URL+"user/signup?username="+username.getText().toString()
                        +"&password="+password.getText().toString()+
                        "&email="+email.getText().toString()+
                        "&tel="+Integer.parseInt(tel.getText().toString());
                
                req.setUrl(url);
                if (username.getText().equals(" ")&&password.getText().equals(" ")&&email.getText().equals(" ")){
                    Dialog.show("ERROR","REMPLISSEZ TOUS LES CHAMPS","OK",null);
                }
                req.addResponseListener((e)->{
                    byte[]data=(byte[]) e.getMetaData();
                    String responseData = new String (data);
                    System.out.println("data ===>"+responseData);
                });
                NetworkManager.getInstance().addToQueueAndWait(req);
        }
    
        
        
        public void signin(TextField username , TextField password){
            String url = Statics.BASE_URL+"user/signin?username="+username.getText().toString()+"&password="+password.getText().toString();
             req = new ConnectionRequest(url, false); 
            req.setUrl(url);
                  req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
                        
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe éronné","OK",null);
                new SignInForm(current).show();
            }
            else {
                new HomeForm().show();
            }
            
           
                  });
                          
             NetworkManager.getInstance().addToQueueAndWait(req);
        }
        
        
     public ArrayList<User> parseCoach(String jsonText){
        try {
             users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> StatutListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) StatutListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
               User coach = new User();
               float id = Float.parseFloat(obj.get("id").toString());
               coach.setId((int) id);   
               coach.setUsername(obj.get("username").toString());    
                              coach.setPassword(obj.get("password").toString());  
               coach.setEmail(obj.get("Email").toString());     
               float tel = Float.parseFloat(obj.get("Tel").toString());
               coach.setTel((int)tel);


            

        users.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing Users ");
        }
        return users;
    }
   
   
public ArrayList<User> getAllUsers() {
    String url = Statics.BASE_URL + "user/listeUsers";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            users = parseCoach(new String(req.getResponseData()));
            System.out.println(users);
            req.removeResponseListener(this);        }
    });
   NetworkManager.getInstance().addToQueueAndWait(req);
    return users;
    }
    
        public boolean deleteUser(int id ) {
        String url = Statics.BASE_URL +"user/delete/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
        
           public boolean updateUser(User user){
              String url = Statics.BASE_URL +"user/update?id="+user.getId()+"&password="+user.getPassword()+"&email="+user.getEmail()+"&tel="+user.getTel(); 

        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
        
           
            public String getPasswordByEmail(String email, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"user/getPasswordByEmail?email="+email;
        req = new ConnectionRequest(url, false); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
             json = new String(req.getResponseData()) + "";
            
            
            try {
            
          
                System.out.println("data =="+json);
                
                Map<String,Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
            
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
    return json;
    }
        
}
