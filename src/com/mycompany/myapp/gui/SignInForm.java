/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author Skander
 */
public class SignInForm extends Form {
    
    public SignInForm(Form previous){
         setTitle("Sign In");
        setLayout(BoxLayout.y());
        
        TextField tfusername = new TextField("","username");
        
        TextField tfPassword = new TextField("","Password",20,TextField.PASSWORD);


        

        Container emptySpace1 = new Container(new BorderLayout());
        emptySpace1.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace2 = new Container(new BorderLayout());
        emptySpace2.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace3 = new Container(new BorderLayout());
        emptySpace3.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace4 = new Container(new BorderLayout());
        emptySpace4.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace5 = new Container(new BorderLayout());
        emptySpace5.add(BorderLayout.CENTER, new Label(" "));



Button btnValider = new Button("Connexion");
Button btnInsc = new Button("Sign Up");

        
     btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                    ServiceUser.getInstance().signin(tfusername, tfPassword);
            }

        });
        
                btnInsc.addActionListener((evt) -> {new  SignUpForm(this).show();});
        
        
        addAll(emptySpace5,tfusername,emptySpace1,tfPassword,emptySpace2,btnValider,btnInsc);
        
        
    
    
}
}