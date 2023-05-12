/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
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
import com.mycompany.entities.Categorie;
import com.mycompany.myapp.services.ServiceCategorie;

/**
 *
 * @author USER
 */
public class AddCategorie extends Form {
    
        public AddCategorie (Form previous) {
        setTitle("Ajouter une categorie");
        setLayout(BoxLayout.y());
        
        TextField tfnom_ctg = new TextField("","nom_ctg");

        Container emptySpace1 = new Container(new BorderLayout());
        emptySpace1.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace2 = new Container(new BorderLayout());
        emptySpace2.add(BorderLayout.CENTER, new Label(" "))


;
    Button btnValider = new Button("Add categorie");
    
     btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfnom_ctg.getText().length()==0))
                    Dialog.show("Alert", "Veuillez replir tous les champs !", new Command("OK"));
                else
                {
                    try {                                            

                        Categorie c = new Categorie(
                                               tfnom_ctg.getText().toString()
                                       );                     
                    if( ServiceCategorie.getInstance().addCategorie(c))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(emptySpace2,tfnom_ctg,emptySpace1,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().showBack());

                
    }

}
