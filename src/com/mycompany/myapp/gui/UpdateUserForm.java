/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;
import com.mycompany.myapp.services.ServiceUser;

/**
 *
 * @author Skander
 */
public class UpdateUserForm extends Form {
    private User user;
   public UpdateUserForm(Form previous,User u){
       
          setTitle("Sign Up");
        setLayout(BoxLayout.y());
        
         TextField tfPassword = new TextField("","Password",20,TextField.PASSWORD);
        TextField tfEmail = new TextField("","Email");
                TextField tfTel = new TextField("","Tel");

                
                Button btnUser = new Button("Modifier");
                user = u;
                
                
                btnUser.addActionListener(e->{
                user.setPassword(tfPassword.getText());
                user.setEmail(tfEmail.getText());
                float tel = Float.parseFloat(tfTel.getText());  
                user.setTel((int)tel);
                
                 
        if(ServiceUser.getInstance().updateUser(user)) {
            Dialog.show("Success", "User updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update User", new Command("OK"));
        }


                });
                        
                    addAll(tfEmail,tfPassword,tfTel,btnUser);
                        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

                        
       
       
    }
}
