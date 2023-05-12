/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Evenement;
import com.mycompany.myapp.services.ServicesEvent;

/**
 *
 * @author MSI
 */
public class UpdateEventForm extends Form {
    private Evenement evenement;
    public UpdateEventForm(Form previous, Evenement evenement) {
        setTitle("Update Event");
    setLayout(BoxLayout.y());
     TextField tftitre = new TextField(evenement.getTitre());
      TextField eDesc = new TextField(evenement.getDescription());
       
     
     
     Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener(e -> {
        evenement.setTitre(tftitre.getText());
        if(ServicesEvent.getInstance().modifEvent(evenement)) {
            Dialog.show("Success", "Event updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update Event", new Command("OK"));
        }
    });
    
    addAll(tftitre,eDesc,btnModifier);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        
    }
    
}
