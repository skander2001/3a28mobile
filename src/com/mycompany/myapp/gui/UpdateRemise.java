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
import com.mycompany.entities.Remise;
import com.mycompany.myapp.services.ServiceRemise;


/**
 *
 * @author darka
 */
public class UpdateRemise extends Form  {
      private Remise remise;
    
    public UpdateRemise(Form previous, Remise remise) {
    setTitle("Update remise");
    setLayout(BoxLayout.y());
    
    TextField tfnom = new TextField(remise.getNom());
  TextField tfnb = new TextField(String.valueOf(remise.getNb()));
    
    
    Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener(e -> {
        remise.setNom(tfnom.getText());
        remise.setNb(Integer.parseInt(tfnb.getText()));
 
        
        if(ServiceRemise.getInstance().modifierRemise(remise)) {
            Dialog.show("Success", "Statut updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update statut", new Command("OK"));
        }
    });
    
    addAll(tfnom, tfnb, btnModifier);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
    
}
