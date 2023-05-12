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
import com.mycompany.entities.Produit;
import com.mycompany.myapp.services.ServiceProduit;

/**
 *
 * @author USER
 */
public class ModifProduit extends Form {
    
     private Produit produit;
    
    public ModifProduit (Form previous, Produit produit) {
    setTitle("Update Statut");
    setLayout(BoxLayout.y());
    
    TextField tfnom = new TextField(produit.getNom());
    TextField tfdescription = new TextField(produit.getDescription());
    TextField tfprix = new TextField(String.valueOf(produit.getPrix()));
    TextField tfqte_stock = new TextField(String.valueOf(produit.getQte_stock()));
    TextField tfid_ctg = new TextField(String.valueOf(produit.getId_ctg()));

    
    Button btnModifier = new Button("Modifier");
    btnModifier.addActionListener(e -> {
        produit.setNom(tfnom.getText());
        produit.setDescription(tfdescription.getText());
        produit.setPrix(Float.parseFloat(tfprix.getText()));
        produit.setQte_stock(Integer.parseInt(tfqte_stock.getText()));
        produit.setId_ctg(Integer.parseInt(tfid_ctg.getText()));
        
        
        if(ServiceProduit.getInstance().modifierProduit(produit)) {
            Dialog.show("Success", "prod updated successfully", new Command("OK"));
        } else {
            Dialog.show("Error", "Unable to update prod", new Command("OK"));
        }
    });
    
    addAll(tfnom, tfdescription,tfprix, tfqte_stock, tfid_ctg, btnModifier);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
    
}
