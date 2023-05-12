/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
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
import com.mycompany.entities.Statut;
import com.mycompany.myapp.services.ServiceStatut;


/**
 *
 * @author user
 */
public class UpdateStatutForm extends Form {
    
    private Statut statut;
    
    public UpdateStatutForm(Form previous, Statut statut) {
    setTitle("Update Statut");
    setLayout(BoxLayout.y());
    
    TextField tftitre = new TextField(statut.getTitre());
    TextField tfcontenu = new TextField(statut.getContenu());
    ComboBox<String> cbStatus = new ComboBox<>("StreetArt", "Poterie", "Musique", "Danse" , "Peinture" , "Sculpture");
    cbStatus.setSelectedItem(statut.getType());
    cbStatus.addActionListener(evt -> {
        String selectedType = cbStatus.getSelectedItem();
    });
    
    Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener(e -> {
        statut.setTitre(tftitre.getText());
        statut.setContenu(tfcontenu.getText());
        statut.setType(cbStatus.getSelectedItem());
        
        if(ServiceStatut.getInstance().modifierStatut(statut)) {
            Dialog.show("Success", "Statut updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update statut", new Command("OK"));
        }
    });
    
    addAll(tftitre, tfcontenu, cbStatus, btnModifier);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
}
