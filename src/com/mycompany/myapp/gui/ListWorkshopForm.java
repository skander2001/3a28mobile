/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.utils.Statics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import com.mycompany.myapp.entities.Workshop;
import com.mycompany.myapp.services.ServicesWorkshop;

/**
 *
 * @author Lenovo
 */
public class ListWorkshopForm extends Form {

    public ListWorkshopForm(Form previous) {
        setTitle("List Workshop");
       setLayout(BoxLayout.y());
        TextField searchField = new TextField("", "Search");
    Button searchButton = new Button("Search");
    searchButton.addActionListener(e -> searchEvents(searchField.getText()));
    

    // Add search field and button to form
    add(searchField);
    add(searchButton);
    
       
        ArrayList<Workshop> workshops = ServicesWorkshop.getInstance().findAll();
        for (Workshop t : workshops) {
             addElement(t);
            
            

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
     
    
     public void addElement(Workshop workshop) {
 Container container = new Container(new BorderLayout());
    container.setUIID("ListRenderer");
    Label Titre= new Label("titre: " + workshop.getTitre());
    Label Description= new Label("description: " + workshop.getDescription());
    Label Nom= new Label("nom_artiste: " + workshop.getNom_artiste());
    Label Categorie= new Label("categorie: " + workshop.getCategorie());

    Label Duree= new Label("duree: " + workshop.getDuree());

    Label prix=new Label("prix:"+workshop.getPrix());
          

    Button editButton = new Button("Modifier");
    editButton.addActionListener(e -> {
    UpdateWorkshopForm updateForm = new UpdateWorkshopForm(this, workshop);
    updateForm.show();




    // Afficher la nouvelle page
});
     Button deleteButton = new Button("Supprimer");
    deleteButton.addActionListener(e -> {
    try {

       if( ServicesWorkshop.getInstance().deletWorkshop(workshop.getId())){
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
            Titre, Description,Nom,Categorie,Duree,prix,editButton,deleteButton
    ));
 
     Container separator = new Container();
    separator.setUIID("Separator");
    add(separator);

    add(container);
    
    
    
     }
     public void refreshList() {
    removeAll();
    ArrayList<Workshop> workshops = ServicesWorkshop.getInstance().findAll();
    for (Workshop e : workshops) {
        addElement(e);
    }
    revalidate();
}
     public void searchEvents(String searchText) {
    removeAll();
    ArrayList<Workshop> workshops = ServicesWorkshop.getInstance().searchEvent(searchText);
    for (Workshop e : workshops) {
        addElement(e);
    }
    revalidate();
}
    

    
    
     
}
    
    
