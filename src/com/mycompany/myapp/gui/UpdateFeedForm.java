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
import com.mycompany.entities.Feedback;
import com.mycompany.myapp.services.ServicesFeed;

/**
 *
 * @author MSI
 */
public class UpdateFeedForm extends Form {
    private Feedback feed;
    public UpdateFeedForm(Form previous,Feedback feed){
        setTitle("Update Feedback");
    setLayout(BoxLayout.y());
    
    TextField tText = new TextField(feed.getText());
        
    
    Button btnModifiercom = new Button("Modifier");
    btnModifiercom.addActionListener(e -> {
        feed.setText(tText.getText());
       
        
        if(ServicesFeed.getInstance().modifierFeed(feed)) {
            Dialog.show("Success", "Feedback updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update Commentaire", new Command("OK"));
        }
    });
    
    addAll(tText,  btnModifiercom);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
