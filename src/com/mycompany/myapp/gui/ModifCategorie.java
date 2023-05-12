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
import com.mycompany.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;

/**
 *
 * @author USER
 */
class ModifCategorie extends Form {
    
    private Categorie categorie ;
    
    public ModifCategorie (Form previous, Categorie categorie) {
    setTitle("mettre a jour Categorie");
    setLayout(BoxLayout.y());
    
    TextField tfnom_ctg = new TextField(categorie.getNom_ctg());


    
    Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener(e -> {
        categorie.setNom_ctg(tfnom_ctg.getText());

        
        
        if(ServiceCategorie.getInstance().modifCategorie(categorie)) {
            Dialog.show("Success", "categorie updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update categorie", new Command("OK"));
        }
    });
    
    addAll(tfnom_ctg, btnModifier);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
    
}
