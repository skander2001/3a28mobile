/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
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
import com.mycompany.entities.Produit;
import com.mycompany.myapp.services.ServiceCategorie;
import com.mycompany.myapp.services.ServiceProduit;
import java.util.ArrayList;

/**
 *
 * author USER
 */
public class AddProduit extends Form {

    public AddProduit(Form previous) {
        setTitle("Ajouter un produit");
        setLayout(BoxLayout.y());

        TextField tfnom = new TextField("", "nom");
        TextField tfdescription = new TextField("", "description");
        TextField tfimage = new TextField("", "image");
        TextField tfprix = new TextField("", "prix");
        TextField tfqte_stock = new TextField("", "qte_stock");
        TextField tfid_ctg = new TextField("", "id_ctg ");
        Button btnValider = new Button("Add Produit");


        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfnom.getText().length() == 0) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs !", new Command("OK"));
                } else {
                    try {
                        Produit p = new Produit(
                                tfnom.getText().toString(),
                                tfdescription.getText().toString(),
                                tfimage.getText().toString(),
                                Float.parseFloat(tfprix.getText().toString()),
                                Integer.parseInt(tfqte_stock.getText().toString()),
                                Integer.parseInt(tfid_ctg.getText().toString()) // Add 1 because the index starts from 0, but category ID starts from 1
                        );

                        if (ServiceProduit.getInstance().addProduit(p)) {
                            Dialog.show("Success", "Produit ajouté avec succès", new Command("OK"));
                        } else {
                            Dialog.show("Erreur", "Erreur lors de l'ajout du produit", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("Erreur", "Le prix et la quantité doivent être des nombres", new Command("OK"));
                    }
                }
            }
        });

        addAll(tfnom, tfdescription, tfimage, tfprix, tfqte_stock, tfid_ctg, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}

