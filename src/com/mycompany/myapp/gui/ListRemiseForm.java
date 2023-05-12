/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Remise;
import com.mycompany.myapp.services.ServiceRemise;


import java.util.ArrayList;

/**
 *
 * @author darka
 */
public class ListRemiseForm extends Form {
     public ListRemiseForm(Form previous) {
        setTitle("Forum");
        setLayout(BoxLayout.y());

        /*SpanLabel sp = new SpanLabel();
        sp.setText(ServiceTask.getInstance().getAllTasks().toString());
        add(sp);
         */
    TextField searchField = new TextField("", "Search");
    Button searchButton = new Button("Search");
    searchButton.addActionListener(e -> searchRemise(searchField.getText()));
    
    // Add search field and button to form
    add(searchField);
    add(searchButton);
        ArrayList<Remise> remises = ServiceRemise.getInstance().getAllStatuts();
        for (Remise t : remises) {
            addElement(t);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   public void addElement(Remise stat) {

    Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");

    Label code = new Label("code: " + stat.getCode());
    Label nom= new Label("nom: " + stat.getNom());
    Label nb = new Label("nb: " + stat.getNb());

     Button editButton = new Button("Modifier");
    editButton.addActionListener(e -> {
    UpdateRemise updateForm = new UpdateRemise(this, stat);
    updateForm.show();
    

     });
     
     
      Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(e -> {
    try {

       if( ServiceRemise.getInstance().deleteJson(stat.getCode())){
            // Suppression réussie, enlever le container correspondant
            removeComponent(container);
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);

        } else 
            // Afficher un message d'erreur si la suppression échoue
            Dialog.show("Succès", "La suppression a été effectuée", "OK", null);
            refreshList();

        }
     catch (Exception ex) {
        // Afficher une exception si elle se produit
        ex.printStackTrace();
        Dialog.show("Erreur", "Une erreur s'est produite lors de la suppression", "OK", null);
    }
    });
     
     
    
    
    
    
     
     
     
     
     
     container.add(BorderLayout.WEST, BoxLayout.encloseY(
          code, nom, nb
    ));
    container.add(BorderLayout.EAST, BoxLayout.encloseY(
           deleteButton,editButton
    ));
    
     
     
     
    // Ajouter un séparateur rouge entre chaque élément
    Container separator = new Container();
    separator.setUIID("Separator");
    add(separator);

    add(container);
}

public void refreshList() {
    removeAll();
    ArrayList<Remise> remises = ServiceRemise.getInstance().getAllStatuts();
    for (Remise s : remises) {
        addElement(s);
    }
    revalidate();
}


public void searchRemise(String searchText) {
    removeAll();
    ArrayList<Remise> remises = ServiceRemise.getInstance().searchRemise(searchText);
    for (Remise r : remises) {
        addElement(r);
    }
    revalidate();
}
}
