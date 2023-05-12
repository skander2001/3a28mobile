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
import com.mycompany.entities.Shoppingcart;
import com.mycompany.myapp.services.Paiement;
import com.mycompany.myapp.services.ServiceRemise;
import com.mycompany.myapp.services.ShoppingcartService;

/**
 *
 * @author darka
 */
public class Card extends Form{
    
      public Card(Form previous) {
        setTitle("add your information");
        setLayout(BoxLayout.y());
        
        TextField Nom = new TextField("","nom");
        TextField ville = new TextField("","ville");
           TextField prenom = new TextField("","prenom");
        TextField adresse = new TextField("","adresse");

        Container emptySpace1 = new Container(new BorderLayout());
        emptySpace1.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace2 = new Container(new BorderLayout());
        emptySpace2.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace3 = new Container(new BorderLayout());
        emptySpace3.add(BorderLayout.CENTER, new Label(" "));
          Container emptySpace4 = new Container(new BorderLayout());
        emptySpace4.add(BorderLayout.CENTER, new Label(" "));
        
        
      



Button btnValider = new Button("buy");

        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Nom.getText().length()==0))
                    Dialog.show("Alert", "Veuillez replir tous les champs !", new Command("OK"));
                else
                {
                    try {                                            
                     Shoppingcart t = new Shoppingcart(Nom.getText().toString(), ville.getText().toString(), prenom.getText().toString(), adresse.getText().toString());

                        if( ShoppingcartService.getInstance().addCard(t))
                        {
                           Dialog.show("Success"," succés ",new Command("OK"));
                           
                           Paiement.getInstance().paiement();
                           
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(Nom,emptySpace1,prenom,emptySpace2,ville,emptySpace3,adresse,emptySpace4,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().showBack());

                
    }
    
}
