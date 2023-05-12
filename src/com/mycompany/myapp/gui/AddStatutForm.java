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
public class AddStatutForm extends Form{
    
     public AddStatutForm(Form previous) {
        setTitle("Add a new Statut");
        setLayout(BoxLayout.y());
        
        TextField tfusername = new TextField("","username");
        TextField tftitre = new TextField("","titre");
        TextField tfcontenu = new TextField("","contenu");

        Container emptySpace1 = new Container(new BorderLayout());
        emptySpace1.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace2 = new Container(new BorderLayout());
        emptySpace2.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace3 = new Container(new BorderLayout());
        emptySpace3.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace4 = new Container(new BorderLayout());
        emptySpace4.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace5 = new Container(new BorderLayout());
        emptySpace5.add(BorderLayout.CENTER, new Label(" "));


ComboBox<String> cbStatus = new ComboBox<>("StreetArt", "Poterie", "Musique", "Danse" , "Peinture" , "Sculpture");
TextField tftype = new TextField("", "Type");
        cbStatus.addActionListener(evt -> {
            String selectedType = cbStatus.getSelectedItem();
            tftype.setText(selectedType);
        });
Button btnValider = new Button("Add Statut");

        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfusername.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs !", new Command("OK"));
                else
                {
                    try {                                            

                        Statut t = new Statut( tfusername.getText().toString(), tftitre.getText().toString(), tfcontenu.getText().toString(),tftype.getText().toString());
                        if( ServiceStatut.getInstance().addStatut(t))
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
        
        addAll(emptySpace5,tfusername,emptySpace1,tftitre,emptySpace2,tfcontenu,emptySpace3, cbStatus,emptySpace4,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().showBack());

                
    }
    
    

    
}
