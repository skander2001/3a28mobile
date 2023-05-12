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
import com.mycompany.entities.Commentaire;
import com.mycompany.myapp.services.ServiceCommentaire;


/**
 *
 * @author user
 */
public class UpdateComForm extends Form {
  private Commentaire com;
    
    public UpdateComForm(Form previous, Commentaire com) {
    setTitle("Update Commentaire");
    setLayout(BoxLayout.y());
    
    TextField tfdescription = new TextField(com.getDescription());
        
    
    Button btnModifiercom = new Button("Modifier");
    btnModifiercom.addActionListener(e -> {
        com.setDescription(tfdescription.getText());
       
        
        if(ServiceCommentaire.getInstance().modifierCommentaire(com)) {
            Dialog.show("Success", "Commentaire updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update Commentaire", new Command("OK"));
        }
    });
    
    addAll(tfdescription,  btnModifiercom);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}  
}
