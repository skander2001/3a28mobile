/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

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
import com.mycompany.entities.Evenement;
import com.mycompany.myapp.services.ServicesEvent;
import java.text.ParseException;

import java.util.Date;
import com.codename1.l10n.SimpleDateFormat;


///**
// *
// * @author MSI
// */
public class AddEventForm extends Form {

    public AddEventForm(Form previous) {
    setTitle("Add a new Event");
    setLayout(BoxLayout.y());
    TextField eTitre = new TextField("", "Titre");
    TextField eLocalisation = new TextField("", "loc");
    TextField eDesc = new TextField("", "desc");
   Picker eDd = new Picker();
    eDd.setType(Display.PICKER_TYPE_DATE_AND_TIME);
SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
eDd.setFormatter(dateFormat);
       
    Picker eDf = new Picker();
    eDf.setType(Display.PICKER_TYPE_DATE_AND_TIME);
    
eDf.setFormatter(dateFormat);
    
    
    TextField ePrix = new TextField("", "prix");
    TextField eImage = new TextField("", "image");
    TextField eNb = new TextField("", "nbPlace");
    Button btnValider = new Button("Add Event");

    btnValider.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (eTitre.getText().length() == 0) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                
                    float prix = Float.parseFloat(ePrix.getText().toString());
                    int nbPlace = Integer.parseInt(eNb.getText().toString());
                       
                    

                    Evenement e = new Evenement(eTitre.getText().toString(), eLocalisation.getText().toString(), eDesc.getText().toString(),eDd.getDate(),eDf.getDate(), prix, eImage.getText().toString(), nbPlace);
                    if (ServicesEvent.getInstance().ajoutEvent(e)) {
                        Dialog.show("Success", "Connection accepted", new Command("OK"));
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
               
            }
        }
    });

    addAll(eTitre, eLocalisation, eDesc, eDd, eDf, ePrix, eImage, eNb, btnValider);
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
}
}
