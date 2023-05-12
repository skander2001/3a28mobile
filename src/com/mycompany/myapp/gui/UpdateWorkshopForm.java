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
import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.services.ServicesWorkshop;

/**
 *
 * @author Lenovo
 */
public class UpdateWorkshopForm extends Form {
    private Workshop workshop;
    public UpdateWorkshopForm(Form previous, Workshop workshop) {
        setTitle("Update Event");
    setLayout(BoxLayout.y());
     TextField tftitre = new TextField(workshop.getTitre());
      TextField eDesc = new TextField(workshop.getDescription());
       
     
     
     Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener(e -> {
        workshop.setTitre(tftitre.getText());
        if(ServicesWorkshop.getInstance().modifWorkshop(workshop)) {
            Dialog.show("Success", "Event updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update Event", new Command("OK"));
        }
    });
    
    addAll(tftitre,eDesc,btnModifier);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
}
