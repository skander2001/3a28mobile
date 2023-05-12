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
public class AddRemieForm extends Form {
    
    
     public AddRemieForm(Form previous) {
        setTitle("Add a new code promo");
        setLayout(BoxLayout.y());
        
        TextField Code = new TextField("","code");
        TextField nom = new TextField("","nom");
        TextField nb = new TextField("","nombre");

        Container emptySpace1 = new Container(new BorderLayout());
        emptySpace1.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace2 = new Container(new BorderLayout());
        emptySpace2.add(BorderLayout.CENTER, new Label(" "));
        Container emptySpace3 = new Container(new BorderLayout());
        emptySpace3.add(BorderLayout.CENTER, new Label(" "));
      



Button btnValider = new Button("Add Promo");

        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((Code.getText().length()==0))
                    Dialog.show("Alert", "Veuillez replir tous les champs !", new Command("OK"));
                else
                {
                    try {                                            
                     Remise t = new Remise(Integer.parseInt(Code.getText().toString()), nom.getText().toString(), Integer.parseInt(nb.getText().toString()));

                        if( ServiceRemise.getInstance().addRemise(t))
                        {
                           Dialog.show("Success","le code promo a été ajouté avec succés ",new Command("OK"));
                           
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });
        
        addAll(Code,emptySpace1,nom,emptySpace2,nb,emptySpace3,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().showBack());

                
    }
    

   
    
}
