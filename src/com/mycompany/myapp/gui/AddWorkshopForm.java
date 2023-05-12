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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.services.ServicesWorkshop;
import java.text.ParseException;

/**
 *
 * @author Lenovo
 */
public class AddWorkshopForm extends Form {

    public AddWorkshopForm(Form previous) {
    setTitle("Add a new Workshop");
    setLayout(BoxLayout.y());
    TextField tfTitre = new TextField("", "titre");
    TextField tfDescription = new TextField("", "description");
    TextField tfNom = new TextField("", "nom_artiste");
    TextField tfCategorie = new TextField("", "categorie");
    
    TextField tfDuree = new TextField("", "duree");
    TextField tfPrix = new TextField("", "prix");
    
   
    Button btnValider = new Button("Add Workshop");

    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (  ((tfTitre.getText().length()==0)) 
                        || ((tfDescription.getText().length()==0)) || ((tfCategorie.getText().length()==0))
                        
                        || ((tfPrix.getText().length()==0))|| ((tfNom.getText().length()==0))
                        || ((tfDuree.getText().length()==0))) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                
                    float prix = Float.parseFloat(tfPrix.getText().toString());
                    int duree = Integer.parseInt(tfDuree.getText().toString());
                       
                    

                    Workshop w = new Workshop(tfTitre.getText().toString(), tfDescription.getText().toString(), tfNom.getText().toString(),tfCategorie.getText().toString() ,duree,prix );
                    if (ServicesWorkshop.getInstance().ajoutWorkshop(w)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
               
            }
        }
    });

    addAll(tfTitre, tfDescription, tfNom, tfCategorie, tfDuree, tfPrix, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
} 
    
}
